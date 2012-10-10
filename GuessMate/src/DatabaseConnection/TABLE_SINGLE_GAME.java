package DatabaseConnection;

public class TABLE_SINGLE_GAME extends Tables{
	protected static String ID_LEVEL_CURRENT_FIELD = "idLevelCur";
	public static String PICTURE_OPEN_COUNT = "pictureOpenCount";
	public static String IS_LETTER_REMOVED = "isLetterRemoved";
	protected static String createTable(){
		String sql = "create table " + TABLE_SINGLE_GAME + "( ";
		sql += COLUMN_ID + " integer primary key autoincrement, ";
		sql += ID_LEVEL_CURRENT_FIELD + " integer not null, ";
		sql += PICTURE_OPEN_COUNT + " integer not null, ";
		sql += IS_LETTER_REMOVED + " integer not null";
		sql += ");";
		return sql;
	}
	protected static String DropTable(){
		System.out.println("in TABLE_SINGLE_GAME 1");
		String sql = DropTable(Tables.TABLE_SINGLE_GAME);
		System.out.println("in TABLE_SINGLE_GAME 2");
		return sql;
	}
}
