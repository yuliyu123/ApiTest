package com.example.faw.test.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.faw.test.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by faw on 16/11/9.
 */

public class ComicActivity extends BaseViewActivity {

	@BindView(R.id.toolbar)
	Toolbar toolbar;

	@BindView(R.id.collapsing_toolbar)
	CollapsingToolbarLayout collapsingToolbarLayout;
	@BindView(R.id.web_text)
	WebView webText;
	@BindView(R.id.ivImage)
	ImageView ivImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_main);
		ButterKnife.bind(this);

		toolbar.setTitle("news details");

		setSupportActionBar(toolbar);
		// 设置返回箭头
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		toolbar.setNavigationOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});

		Bundle bundle = this.getIntent().getExtras();

		final ArrayList<String> data = bundle.getStringArrayList("data");
		Log.d("url",data.get(0));

		webText.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
		webText.loadUrl(data.get(1));

		Glide.with(this)
				.load(data.get(0)).error(R.mipmap.ic_launcher)
				.fitCenter().into(ivImage);
	}
}
