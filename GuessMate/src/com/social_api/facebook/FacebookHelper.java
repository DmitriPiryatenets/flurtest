package com.social_api.facebook;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.social_api.facebook.beans.FacebookUser;
import com.social_api.facebook.core.AsyncFacebookRunner;
import com.social_api.facebook.core.DialogError;
import com.social_api.facebook.core.Facebook;
import com.social_api.facebook.core.FacebookError;

/**
 * @author: Daniel Goncharov
 * Date: 27.08.12
 * Time: 14:24
 */
public class FacebookHelper {

    private Facebook facebook = null;

    private Activity activity;

    private String[] permissions = {"publish_stream", "user_about_me", "email"};

    private Handler mHandler;

    private SessionListener mSessionListener = new SessionListener();

    private static final String FACEBOOK_APPID = "164026980387786";
    static final String FACEBOOK_PREF_KEY_ON = "FACEBOOK_PREF_KEY_ON";
    static final String FACEBOOK_STORE = "FACEBOOK_STORE";

    private static final String graphURl = "http://graph.facebook.com/";
    private static final String pictureURL = "/picture?type=small";

    private static final String ME = "me";

    private static FacebookHelper helper;

    Object currentFacebookObject;
    private Handler handler;
    FacebookCallBack callback;

    public String getImageUrl(Long id) {
        StringBuilder stringBuilder = new StringBuilder(graphURl);
        stringBuilder.append(String.valueOf(id));
        stringBuilder.append(pictureURL);
        return stringBuilder.toString();
    }

    public interface FacebookCallBack {
        void objectReceived(Object facebookObject);
    }

    public FacebookHelper(Activity activity) {
        handler = getCallBackHandler();
        this.facebook = new Facebook(FACEBOOK_APPID);

        SessionStore.restore(facebook, activity);
        SessionEvents.addAuthListener(mSessionListener);
        SessionEvents.addLogoutListener(mSessionListener);

        this.activity = activity;
        this.mHandler = new Handler();
    }

//    public synchronized static FacebookHelper getFacebookHelper(Activity activity) {
//        if (helper == null) {
//            helper = new FacebookHelper(activity);
//        }
//        return helper;
//    }

    private Handler getCallBackHandler() {
        return new Handler() {
            @Override
            public void handleMessage(Message message) {
                if (callback != null) {
                    callback.objectReceived(currentFacebookObject);
                }
            }
        };
    }


    public boolean isConnected() {
        return facebook.isSessionValid();
    }

    public void login() {
        if (!facebook.isSessionValid()) {
            facebook.authorize(activity, permissions, new LoginDialogListener());
        }
    }

    public void logout() {
        SessionEvents.onLogoutBegin();
        AsyncFacebookRunner asyncRunner = new AsyncFacebookRunner(this.facebook);
        asyncRunner.logout(this.activity, new LogoutRequestListener());
    }

    public void postMessageOnWall(String msg) {
        if (facebook.isSessionValid()) {
            Bundle parameters = new Bundle();
            parameters.putString("message", msg);
            try {
                String response = facebook.request("me/feed", parameters, "POST");
                System.out.println(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            login();
        }
    }

    public boolean isOn() {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(FACEBOOK_STORE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(FACEBOOK_PREF_KEY_ON, false);
    }

    public void setOn(boolean state) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(FACEBOOK_STORE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(FACEBOOK_PREF_KEY_ON, state);
        editor.commit();
    }

    public void getCurrentFacebookUser(FacebookCallBack callback) {
        this.callback = callback;
        Thread background = new Thread(new Runnable() {
            @Override
            public void run() {

                currentFacebookObject = requestForCurrentUser();

                handler.sendMessage(handler.obtainMessage());
            }
        });
        background.start();
    }

    private FacebookUser requestForCurrentUser() {
        String stringToParse = null;
        try {
            stringToParse = facebook.request(ME);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        FacebookUser user = gson.fromJson(stringToParse, FacebookUser.class);

        return user;
    }

    private final class LoginDialogListener implements Facebook.DialogListener {
        public void onComplete(Bundle values) {
            SessionEvents.onLoginSuccess();
        }

        public void onFacebookError(FacebookError error) {
            SessionEvents.onLoginError(error.getMessage());
        }

        public void onError(DialogError error) {
            SessionEvents.onLoginError(error.getMessage());
        }

        public void onCancel() {
            SessionEvents.onLoginError("Action Canceled");
        }
    }

    public class LogoutRequestListener extends BaseRequestListener {
        public void onComplete(String response, final Object state) {
            // callback should be run in the original thread,
            // not the background thread
            mHandler.post(new Runnable() {
                public void run() {
                    SessionEvents.onLogoutFinish();
                }
            });
        }
    }

    private class SessionListener implements SessionEvents.AuthListener, SessionEvents.LogoutListener {

        public void onAuthSucceed() {
            SessionStore.save(facebook, activity);
        }

        public void onAuthFail(String error) {
        }

        public void onLogoutBegin() {
        }

        public void onLogoutFinish() {
            SessionStore.clear(activity);
        }
    }

    public Facebook getFacebook() {
        return this.facebook;
    }
}
