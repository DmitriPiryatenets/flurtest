package com.example.guessmate;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Set;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.APIConnection.API;
import com.example.qbcontenttest.sdk.definitions.ActionResultDelegate;
import com.example.qbcontenttest.sdk.definitions.QBQueries.QBQueryType;
import com.example.qbcontenttest.sdk.definitions.ResponseHttpStatus;
import com.example.qbcontenttest.sdk.helpers.Store;
import com.example.qbcontenttest.sdk.helpers.ValidationManager;
import com.example.qbcontenttest.sdk.objects.RestResponse;
import com.social_api.facebook.FacebookHelper;
import com.social_api.facebook.FacebookHelper.FacebookCallBack;
import com.social_api.facebook.beans.FacebookUser;
import com.social_api.facebook.core.DialogError;
import com.social_api.facebook.core.Facebook;
import com.social_api.facebook.core.Facebook.DialogListener;
import com.social_api.facebook.core.FacebookError;

public class StartActivity extends Activity implements ActionResultDelegate {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		final FacebookHelper fh = new FacebookHelper(this);
		Facebook fb = fh.getFacebook();
		fb.authorize(this, new DialogListener() {			
			@Override
			public void onFacebookError(FacebookError e) {
				System.out.println("onFacebookError");
			}
			
			@Override
			public void onError(DialogError e) {
				System.out.println("onError");
			}
			
			@Override
			public void onComplete(Bundle values) {
				Set<String> list = values.keySet();
				Iterator <String> it = list.iterator();
				while (it.hasNext()){
					String key = it.next();
					System.out.println(key + " <-> " + values.get(key) + " " + values.get(key).getClass().getSimpleName());
				}
				System.out.println("onComplete");
				fh.getCurrentFacebookUser(new FacebookCallBack() {			
					@Override
					public void objectReceived(Object facebookObject) {
						System.out.println("objectReceived");
						System.out.println(facebookObject.getClass().getSimpleName());
						FacebookUser fu = (FacebookUser) facebookObject;
						ImageView im = (ImageView) findViewById(R.id.imageView1);
						String imageURL = fh.getImageUrl(fu.getId());
						System.out.println("imageURL: " + imageURL);
						im.setImageBitmap(loadBitmap(imageURL));
					}
				});
			}
			
			@Override
			public void onCancel() {
				System.out.println("onCancel");
			}
		});
	}
	public static Bitmap loadBitmap(String url1) {
	    Bitmap bitmap = null;
//	    InputStream in = null;
	    BufferedOutputStream out = null;

	    try {
			URL url = new URL(url1);
			URLConnection conn = url.openConnection();
			DataInputStream d = new DataInputStream(new BufferedInputStream(
					conn.getInputStream()));
			
	        final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
	        out = new BufferedOutputStream(dataStream, 512);

			byte buffer[] = new byte[512];
			int read;
	        while ((read = d.read(buffer)) != -1) {
				out.write(buffer, 0, read);
				out.flush();
			}
	        out.flush();
	        
	        d.close();
	        out.close();

	        final byte[] data = dataStream.toByteArray();
	        BitmapFactory.Options options = new BitmapFactory.Options();
	        //options.inSampleSize = 1;

	        bitmap = BitmapFactory.decodeByteArray(data, 0, data.length,options);
	    } catch (IOException e) {
	        System.out.println("Could not load Bitmap from: " + url1);
	    }

	    return bitmap;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_start, menu);
		return true;
	}

	private void registerByEmail(String email, String pass, String pass2) {

		switch (ValidationManager.checkInputParameters(email, pass, pass)) {
		case ValidationManager.ALERT_EMAIL:
			break;
		case ValidationManager.ALERT_LOGIN:
			break;
		case ValidationManager.ALERT_PASSWORD:
			break;
		case ValidationManager.ALERT_PASSWORD_REPEAT:
			break;
		case ValidationManager.ALERT_OK:
			API.getInstance().registerByEmail(this, email, pass);
			break;
		}
	}

	private void login(String login, String pass) {

		switch (ValidationManager.checkInputParameters(login, pass)) {
		case ValidationManager.ALERT_EMAIL:
			break;
		case ValidationManager.ALERT_LOGIN:
			break;
		case ValidationManager.ALERT_PASSWORD:
			break;
		case ValidationManager.ALERT_PASSWORD_REPEAT:
			break;
		case ValidationManager.ALERT_OK:
			API.getInstance().login(this, login, pass);
			break;
		}
	}

	@Override
	public void completedWithResult(QBQueryType queryType, RestResponse response) {
		if (response == null) {
			Toast.makeText(this, "Please check your internet connection",
					Toast.LENGTH_LONG).show();
			return;
		}
		switch (queryType) {

		// QuickBlox application authorization result
		case QBQueryTypeGetAuthToken:
			if (response.getResponseStatus() == ResponseHttpStatus.ResponseHttpStatus201) {

				// save token
				Store.getInstance().setAuthToken(
						response.getBody().findChild("token").getText());

				// Errors
			} else if (response.getResponseStatus() == ResponseHttpStatus.ResponseHttpStatus422) {
				String error = response.getBody().getChildren().get(0)
						.getText();
				// Toast.makeText(this, error, Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(this, "Oops! Something went wrong",
						Toast.LENGTH_LONG).show();
			}
			break;
		case QBQueryTypeLoginUser:
			if (response.getResponseStatus() == ResponseHttpStatus.ResponseHttpStatus202) {

				// store current user
				Store.getInstance().setCurrentUser(response.getBody());

				Toast.makeText(this, "Login was successful!", Toast.LENGTH_LONG)
						.show();

			} else if (response.getResponseStatus() == ResponseHttpStatus.ResponseHttpStatus401) {
				Toast.makeText(this,
						"Unauthorized. Please check you login and password",
						Toast.LENGTH_LONG).show();

				// validation error
			} else if (response.getResponseStatus() == ResponseHttpStatus.ResponseHttpStatus422) {
				String error = response.getBody().getChildren().get(0)
						.getText();
				// Toast.makeText(this, error, Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(this, "Oops!. Something went wrong",
						Toast.LENGTH_LONG).show();
			}
			break;
		case QBQueryTypeCreateUser:
			if (response.getResponseStatus() == ResponseHttpStatus.ResponseHttpStatus202) {

				// store current user
				Store.getInstance().setCurrentUser(response.getBody());

				Toast.makeText(this, "Login was successful!", Toast.LENGTH_LONG)
						.show();

			} else if (response.getResponseStatus() == ResponseHttpStatus.ResponseHttpStatus401) {
				Toast.makeText(this,
						"Unauthorized. Please check you login and password",
						Toast.LENGTH_LONG).show();

				// validation error
			} else if (response.getResponseStatus() == ResponseHttpStatus.ResponseHttpStatus422) {
				String error = response.getBody().getChildren().get(0)
						.getText();
				// Toast.makeText(this, error, Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(this, "Oops!. Something went wrong",
						Toast.LENGTH_LONG).show();
			}
			break;
		}
	}
}
