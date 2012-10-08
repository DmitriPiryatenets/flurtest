package com.example.flurtestfirst;

import android.app.Activity;
import android.view.Window;

import com.example.flurtestfirst.Constants.Consts;
import com.flurry.android.FlurryAgent;

public abstract class AllActivity extends Activity {

        protected String page_title;

        @Override
        public void setContentView(int layoutResID) {
                this.requestWindowFeature(Window.FEATURE_NO_TITLE);
                super.setContentView(layoutResID);
        }

        protected void onStart() {
                super.onStart();
            	FlurryAgent.onStartSession(this, Consts.APP_KEY);
                System.out.println("onStart");
        }

        protected void onRestart() {
                super.onRestart();
                System.out.println("onRestart");
        }

        protected void onResume() {
                super.onResume();
                System.out.println("onResume command");
        }

        protected void onPause() {
                super.onPause();
                System.out.println("onPause");
        }

        protected void onStop() {
                super.onStop();
            	FlurryAgent.onEndSession(this);
                System.out.println("onStop");
        }

        protected void onDestroy() {
                super.onDestroy();
                System.out.println("onDestroy");
        }
}