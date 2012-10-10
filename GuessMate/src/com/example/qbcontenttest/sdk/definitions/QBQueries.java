package com.example.qbcontenttest.sdk.definitions;

public class QBQueries {

	// Applications settings
	//
//	public static final String APPLICATION_ID = "99";
//	public static final String AUTH_KEY = "63ebrp5VZt7qTOv";
//	public static final String AUTH_SECRET = "YavMAxm5T59-BRw";

    public static final String APPLICATION_ID = "949";
    public static final String AUTH_KEY = "3NcFuQLcSWgAadm";
    public static final String AUTH_SECRET = "7cnffRENy5Su4wH";


	// Server
	//
	public static final String SERVER_ZONE = "api.quickblox.com";

	// Queries
	//

	// AUTH service
	//
	public static final String GET_AUTH_TOKEN_QUERY = String.format("https://%s/session", SERVER_ZONE);

	// USERS service
	// add user
	public static final String CREATE_USER_QUERY = String.format("http://%s/users", SERVER_ZONE);


	// authenticate user
	public static final String LOGIN_USER_QUERY = String.format("http://%s/login", SERVER_ZONE);


	//get all users
	public static final String GET_ALL_USERS_QUERY = String.format("http://%s/users.xml", SERVER_ZONE);
	
	//create file
	public static final String CREATE_FILE_QUERY = String.format("http://%s/blobs.xml", SERVER_ZONE);
	
	//declare file uploaded
	public static final String DECLARE_FILE_UPLOADED_QUERY = String.format("http://%s/blobs/", SERVER_ZONE)  + "%s/complete.xml";
	
	//get file list
	public static final String GET_FILE_LIST_QUERY = String.format("http://%s/blobs.xml", SERVER_ZONE);

	
	// Types of queries. They must match queries above
	public static enum QBQueryType{
		// AUTH
		QBQueryTypeGetAuthToken,
		// USERS service
		QBQueryTypeGetAllUsers,		
		QBQueryTypeCreateUser,
		QBQueryTypeLoginUser,
		
		QBQueryTypeCreateFile,
		
		QBQueryTypeUploadFile,
		
		QBQueryTypeDeclareFileUploaded,
		
		QBQueryTypeGetFileList

		
	}
}