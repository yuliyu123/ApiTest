package com.example.faw.test.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.faw.test.R;
import com.example.faw.test.Util.PixUtil;
import com.example.faw.test.activity.WechatActivity;
import com.example.faw.test.datas.Api;
import com.example.faw.test.datas.weixin;
import com.example.faw.test.datas.weixinGson;
import com.example.faw.test.easyRecyclerView.weixinAdapter;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by faw on 16/11/8.
 */

public class weixinFragment extends BaseFragment {
	//private NewsAdapter adapter;
	private weixinAdapter adapter;
	private int page = 0;
	@BindView(R.id.recyclerView)
	EasyRecyclerView recyclerView;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.wechat_fragment, container, false);
		ButterKnife.bind(this, view);
		recyclerView.setAdapter(adapter = new weixinAdapter(getActivity()));
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

		//添加边框
		SpaceDecoration itemDecoration = new SpaceDecoration((int) PixUtil.convertDpToPixel(8, getContext()));
		itemDecoration.setPaddingEdgeSide(true);
		itemDecoration.setPaddingStart(true);
		itemDecoration.setPaddingHeaderFooter(false);
		recyclerView.addItemDecoration(itemDecoration);

		//更多加载
		adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
			@Override
			public void onMoreShow() {
				getData();
			}

			@Override
			public void onMoreClick() {

			}
		});
		//写刷新事件
		recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				recyclerView.postDelayed(new Runnable() {
					@Override
					public void run() {
						adapter.clear();
						page = 0;
						getData();
					}
				}, 1000);
			}
		});

		//点击事件
		adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(int position) {
				ArrayList<String> data = new ArrayList<String>();
				data.add(adapter.getAllData().get(position).getPicurl());
				data.add(adapter.getAllData().get(position).getUrl());
				Intent intent = new Intent(getActivity(), WechatActivity.class);
				//用Bundle携带数据
				Bundle bundle = new Bundle();
				bundle.putStringArrayList("data", data);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});

		return view;

	}


	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		getData();
	}


	private void getData() {
		Log.d("page", page + "");
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://api.tianapi.com/")
				//String
				.addConverterFactory(ScalarsConverterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())//添加 json 转换器
				//    compile 'com.squareup.retrofit2:adapter-rxjava:2.1.0'
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())//添加 RxJava 适配器
				.build();
		Api apiManager = retrofit.create(Api.class);//这里采用的是Java的动态代理模式
		apiManager.getWeixinData("0271191a3d0bcd8483debff0c759f20a", "10", page)
				.subscribeOn(Schedulers.io())
				.map(new Func1<weixinGson, List<weixin>>() {
					@Override
					public List<weixin> call(weixinGson weixinGson) {
						List<weixin> weixinList = new ArrayList<weixin>();
						for (weixinGson.NewslistBean newslistBean : weixinGson.getNewslist()) {
							weixin wexin = new weixin();
							wexin.setTitle(newslistBean.getTitle());
							wexin.setTime(newslistBean.getCtime());
							wexin.setDesc(newslistBean.getDescription());
							wexin.setPicurl(newslistBean.getPicUrl());
							wexin.setUrl(newslistBean.getUrl());
							weixinList.add(wexin);
						}
						return weixinList; // 返回类型
					}
				})
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<List<weixin>>() {
					@Override
					public void onNext(List<weixin> weixinList) {
						adapter.addAll(weixinList);
					}

					@Override
					public void onCompleted() {
					}

					@Override
					public void onError(Throwable e) {
						Toast.makeText(getContext(),
								"网络连接失败", Toast.LENGTH_LONG).show();
					}
				});
		page = page + 1;
	}

}

