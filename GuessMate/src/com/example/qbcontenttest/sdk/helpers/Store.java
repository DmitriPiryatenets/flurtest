package com.example.qbcontenttest.sdk.helpers;

import org.jivesoftware.smack.XMPPConnection;

import android.location.Location;

import com.example.qbcontenttest.sdk.objects.XMLNode;

public class Store {
	
	/*
	 * Singleton
	 */
	private static Store instance;
	
	    
	public static synchronized Store getInstance() {
		if (instance == null) {
			instance = new Store();
	    } 
	    return instance;
	}
	 
	/*
	 * Fields
	 */
	private String authToken;
	private String password;
	
	private XMLNode currentUser;
	private Location currentLocation;
	private String currentStatus;
	private String jid;
	private XMPPConnection conn;
	
	
	public void setXMPPConn(XMPPConnection conn){
		this.conn = conn;
	}

	public XMPPConnection getXMPPConn(){
		return conn;
	}
	
	public void setJid(String jid){
		this.jid = jid;
	}
	 
	public String getJid(){
		return jid;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return password;
	}
	/*
     * Properties
     */
	public XMLNode getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(XMLNode currentUser) {
		this.currentUser = currentUser;
	}
	
	public Location getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
}