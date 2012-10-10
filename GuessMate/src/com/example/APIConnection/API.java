package com.example.APIConnection;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import com.example.qbcontenttest.sdk.definitions.ActionResultDelegate;
import com.example.qbcontenttest.sdk.definitions.QBQueries;
import com.example.qbcontenttest.sdk.definitions.QueryMethod;
import com.example.qbcontenttest.sdk.helpers.Query;
import com.example.qbcontenttest.sdk.helpers.Store;

public class API {
	private static API api;
	
	public static API getInstance(){
		if (api == null) api = new API();
		return api;
	}
	
	public void authorizeApp(final ActionResultDelegate delegate){
		Query.authorizeApp(delegate);
	}
	
	public void login(final ActionResultDelegate delegate, String login, String pass){

		// create entity
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("login", login));
		formparams.add(new BasicNameValuePair("password", pass));
		formparams.add(new BasicNameValuePair("token", Store.getInstance()
				.getAuthToken()));
		UrlEncodedFormEntity postEntity = null;
		try {
			postEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		// make query
		Query.performQueryAsync(QueryMethod.Post, QBQueries.LOGIN_USER_QUERY,
				postEntity, null, delegate,
				QBQueries.QBQueryType.QBQueryTypeLoginUser);
	}
	
	public void registerByEmail(final ActionResultDelegate delegate, String email, String pass){
		// create entity
		List<NameValuePair> formparamsUser = new ArrayList<NameValuePair>();
		formparamsUser.add(new BasicNameValuePair("user[email]", email));
		formparamsUser.add(new BasicNameValuePair("user[login]", email));
		formparamsUser.add(new BasicNameValuePair("user[password]", pass));
		formparamsUser.add(new BasicNameValuePair("token", Store.getInstance().getAuthToken()));

		UrlEncodedFormEntity postEntityUser = null;
		try {
			postEntityUser = new UrlEncodedFormEntity(formparamsUser, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		// make query for creating a user
		Query.performQueryAsync(QueryMethod.Post, QBQueries.CREATE_USER_QUERY, postEntityUser, null, 
				delegate, QBQueries.QBQueryType.QBQueryTypeCreateUser);
	}
}
