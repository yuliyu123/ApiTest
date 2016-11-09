package com.example.faw.test.easyRecyclerView;

import android.content.Context;
import android.view.ViewGroup;

import com.example.faw.test.datas.MeiNv;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by faw on 16/11/7.
 */

public class ImageAdapter extends RecyclerArrayAdapter<MeiNv> {

	public ImageAdapter(Context context) {
		super(context);
	}

	@Override
	public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
		return new ImageViewHolder(parent);
	}
}
