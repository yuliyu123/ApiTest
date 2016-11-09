package com.example.faw.test.easyRecyclerView;

import android.content.Context;
import android.view.ViewGroup;

import com.example.faw.test.datas.weixin;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by faw on 16/11/8.
 */

public class weixinAdapter extends RecyclerArrayAdapter<weixin> {
	public weixinAdapter(Context context) {
		super(context);
	}

	@Override
	public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
		return new weixinViewHolder(parent);
	}
}
