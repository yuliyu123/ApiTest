package com.example.faw.test.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by faw on 16/10/27.
 */

public class BaseViewActivity extends AppCompatActivity {
	String TAG = getClass().getSimpleName();

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e(TAG,TAG + "create");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.e(TAG,TAG + "destroy");
	}
}
