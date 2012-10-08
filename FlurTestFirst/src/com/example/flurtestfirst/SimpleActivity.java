package com.example.flurtestfirst;

import android.os.Bundle;

public abstract class SimpleActivity extends AllActivity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
	}
}