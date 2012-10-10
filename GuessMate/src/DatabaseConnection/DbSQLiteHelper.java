package DatabaseConnection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbSQLiteHelper extends SQLiteOpenHelper {

	public static final String COLUMN_COMMENT = "comment";

	private static final String DATABASE_NAME = "guessmate.db";
	private static final int DATABASE_VERSION = 2;

	public DbSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(TABLE_SINGLE_GAME.createTable());
		database.execSQL(TABLE_LEVEL.createTable());
		database.execSQL(TABLE_PICTURE.createTable());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.v("1", "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL(TABLE_SINGLE_GAME.DropTable());
		db.execSQL(TABLE_LEVEL.DropTable());
		db.execSQL(TABLE_PICTURE.DropTable());
		onCreate(db);
	}

}
