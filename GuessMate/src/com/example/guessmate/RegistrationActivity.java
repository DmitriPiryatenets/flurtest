package com.example.guessmate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.APIConnection.API;
import com.example.qbcontenttest.sdk.definitions.ActionResultDelegate;
import com.example.qbcontenttest.sdk.definitions.ResponseHttpStatus;
import com.example.qbcontenttest.sdk.definitions.QBQueries.QBQueryType;
import com.example.qbcontenttest.sdk.helpers.Store;
import com.example.qbcontenttest.sdk.helpers.ValidationManager;
import com.example.qbcontenttest.sdk.objects.RestResponse;

public class RegistrationActivity extends Activity implements ActionResultDelegate{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration_activity);
	}

	
	public void register(View view){
		EditText email = (EditText) findViewById(R.id.editText1);
		EditText pass = (EditText) findViewById(R.id.editText2);
		EditText pass1 = (EditText) findViewById(R.id.editText3);

		switch (ValidationManager.checkInputParameters(email.getText().toString(), pass.getText().toString(), pass.getText().toString())) {
		case ValidationManager.ALERT_EMAIL:
			System.out.println("ALERT_EMAIL");
			break;
		case ValidationManager.ALERT_LOGIN:
			System.out.println("ALERT_LOGIN");
			break;
		case ValidationManager.ALERT_PASSWORD:
			System.out.println("ALERT_PASSWORD");
			break;
		case ValidationManager.ALERT_PASSWORD_REPEAT:
			System.out.println("ALERT_PASSWORD_REPEAT");
			break;
		case ValidationManager.ALERT_OK:
			System.out.println("ALERT_OK");
			API.getInstance().registerByEmail(this, email.getText().toString(), pass.getText().toString());
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
