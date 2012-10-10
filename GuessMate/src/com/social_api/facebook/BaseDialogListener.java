package com.social_api.facebook;


import com.social_api.facebook.core.DialogError;
import com.social_api.facebook.core.Facebook;
import com.social_api.facebook.core.FacebookError;

/**
 * Skeleton base class for RequestListeners, providing default error
 * handling. Applications should handle these error conditions.
 */
public abstract class BaseDialogListener implements Facebook.DialogListener {

    public void onFacebookError(FacebookError e) {
        e.printStackTrace();
    }

    public void onError(DialogError e) {
        e.printStackTrace();
    }

    public void onCancel() {
    }

}
