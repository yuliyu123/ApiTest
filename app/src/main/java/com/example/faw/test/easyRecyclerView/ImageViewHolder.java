package com.example.faw.test.easyRecyclerView;

import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.example.faw.test.datas.MeiNv;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by faw on 16/11/7.
 */

public class ImageViewHolder extends BaseViewHolder<MeiNv>{
	ImageView imgPicture;

	public ImageViewHolder(ViewGroup parent) {
		super(new ImageView(parent.getContext()));
		imgPicture = (ImageView) itemView;
		imgPicture.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		imgPicture.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        super(parent,R.layout.item_person);
//        imgPicture = $(R.id.person_face);

	}

	@Override
	public void setData(MeiNv data) {
		// final ViewGroup.LayoutParams params = imgPicture.getLayoutParams();
		final DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
		Glide.with(getContext())
				.load(data.getPicUrl())
				.asBitmap()
				.into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
					      @Override
					      public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
						      int width = dm.widthPixels / 2 - 10;//宽度为屏幕宽度一半
						      int height = bitmap.getHeight() * width / bitmap.getWidth();//计算View的高度
						      //获取bitmap信息，可赋值给外部变量操作，也可在此时行操作。
						      ViewGroup.LayoutParams params = imgPicture.getLayoutParams();
						      params.height = height;
						      params.width = width;
						      imgPicture.setLayoutParams(params);
						      imgPicture.setScaleType(ImageView.ScaleType.FIT_XY);
						      imgPicture.setImageBitmap(bitmap);

					      }
				      }
				);
	}

}
