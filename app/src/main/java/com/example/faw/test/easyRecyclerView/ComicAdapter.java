package com.example.faw.test.easyRecyclerView;

import android.content.Context;
import android.view.ViewGroup;

import com.example.faw.test.datas.Comic;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by faw on 16/11/9.
 */

public class ComicAdapter extends RecyclerArrayAdapter<Comic> {
	public ComicAdapter(Context context) {
		super(context);
	}

	@Override
	public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
		return new ComicViewAdapter(parent);
	}
}
