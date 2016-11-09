package com.example.faw.test.easyRecyclerView;

import android.content.Context;
import android.view.ViewGroup;

import com.example.faw.test.datas.News;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by faw on 16/10/27.
 */

public class  NewsAdapter extends RecyclerArrayAdapter<News>{

	public NewsAdapter(Context context) {
		super(context);
	}

	@Override
	public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {

		return new NewsViewHolder(parent);
	}
}
