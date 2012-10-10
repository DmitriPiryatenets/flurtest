package data.dao.general;

import com.example.guessmate.StartApplication;

import DatabaseConnection.DbSQLiteHelper;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

class DbConnection {
	
	private static DbConnection instance = null;
	
	/**
	 * @uml.property  name="database"
	 * @uml.associationEnd  
	 */
	private SQLiteDatabase database;
	/**
	 * @uml.property  name="dbHelper"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private DbSQLiteHelper dbHelper;

	public DbConnection() {
		dbHelper = new DbSQLiteHelper(StartApplication.getContext());
		open();
	}

	public static synchronized DbConnection getInstance() {
		if (instance == null)
			instance = new DbConnection();
		return instance;
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	/**
	 * @return
	 * @uml.property  name="database"
	 */
	public SQLiteDatabase getDatabase() {
		return database;
	}

	/**
	 * @param database
	 * @uml.property  name="database"
	 */
	public void setDatabase(SQLiteDatabase database) {
		this.database = database;
	}
}
