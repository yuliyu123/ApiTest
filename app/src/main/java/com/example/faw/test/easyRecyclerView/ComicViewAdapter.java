package com.example.faw.test.easyRecyclerView;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.faw.test.R;
import com.example.faw.test.datas.Comic;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by faw on 16/11/9.
 */

public class ComicViewAdapter extends BaseViewHolder<Comic> {

	private TextView tv_name;
	private ImageView img_face;
	private TextView tv_sign;

	public ComicViewAdapter(ViewGroup parent) {
		super(parent, R.layout.news_recycler_item);
		tv_name = $(R.id.person_name);
		tv_sign = $(R.id.person_sign);
		img_face = $(R.id.person_face);
	}
	/*
	* Glide 一个专注于平滑滚动的图片加载和缓存库
	* details http://www.jianshu.com/p/4a3177b57949
	* */

	@Override
	public void setData(Comic data) {
		tv_name.setText(data.getTitle());
		tv_sign.setText(data.getCtime());
		Glide.with(getContext())
				.load(data.getPicUrl())
				.placeholder(R.mipmap.ic_launcher)
				.into(img_face);
	}

}
