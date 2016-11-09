package com.example.faw.test.activity;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.example.faw.test.R;
import com.example.faw.test.Util.PictureUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by faw on 16/11/7.
 */

public class PictDescActivity extends BaseViewActivity {

	@BindView(R.id.image)
	ImageView imgPicture;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picturedescribe);
		ButterKnife.bind(this);

		Bundle bundle = this.getIntent().getExtras();

		final ArrayList<String> data = bundle.getStringArrayList("data");

		final DisplayMetrics dm = getResources().getDisplayMetrics();

		int width = dm.widthPixels / 2;
		Glide.with(this)
				.load(data.get(0))
				.asBitmap()
				.into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
					      @Override
					      public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
						      Log.d("bitmap","高"+bitmap.getHeight()+"宽"+bitmap.getWidth());
						      int width = dm.widthPixels;//宽度为屏幕宽度一半
						      int height = bitmap.getHeight() * width / bitmap.getWidth();//计算View的高度
						      Log.d("picture", "高"+height + "宽" +width); //获取bitmap信息，可赋值给外部变量操作，也可在此时行操作。
						      ViewGroup.LayoutParams params = imgPicture.getLayoutParams();
						      params.height = height;
						      params.width = width;
						      imgPicture.setLayoutParams(params);
						      imgPicture.setScaleType(ImageView.ScaleType.FIT_XY);
						      imgPicture.setImageBitmap(bitmap);
					      }
				      }
				);


		imgPicture.setDrawingCacheEnabled(true);
		imgPicture.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				new AlertDialog.Builder(PictDescActivity.this)
						.setMessage("保存图片")
						.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface anInterface, int i) {
								anInterface.dismiss();
							}
						})
						.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface anInterface, int i) {

								anInterface.dismiss();
								PictureUtil.saveImage(imgPicture, PictDescActivity.this);
							}
						}).show();
				return true;
			}
		});


	}
}
