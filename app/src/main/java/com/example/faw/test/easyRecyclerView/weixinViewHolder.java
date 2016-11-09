package com.example.faw.test.easyRecyclerView;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.faw.test.R;
import com.example.faw.test.datas.weixin;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by faw on 16/11/8.
 */

public class weixinViewHolder extends BaseViewHolder<weixin> {

	private TextView title;
	private ImageView imageView;
	private TextView webText;

	public weixinViewHolder(ViewGroup parent) {
		super(parent,R.layout.wechat_recycler_item);
		title = $(R.id.person_name);
		imageView = $(R.id.person_face);
		webText = $(R.id.person_sign);
	}

	@Override
	public void setData(final weixin data) {
		title.setText(data.getTitle());
		webText.setText(data.getTime());
		Glide.with(getContext())
		.load(data.getPicurl())
		.placeholder(R.mipmap.ic_launcher)
		.into(imageView);
	}
}
