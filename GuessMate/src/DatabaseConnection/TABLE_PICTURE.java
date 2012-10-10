package DatabaseConnection;

public class TABLE_PICTURE extends Tables{
	protected static String REF_WORD_FIELD = "ref_word";
	protected static String REF_LEVEL_FIELD = "ref_level";
	protected static String LINK_TO_FILE_FIELD = "link_to_file";
	protected static String createTable(){
		String sql = "create table " + TABLE_PICTURE + "( ";
		sql += COLUMN_ID + " integer primary key autoincrement, ";
		sql += REF_WORD_FIELD + " integer not null, ";
		sql += REF_LEVEL_FIELD + " integer not null";
		sql += ");";
		return sql;
	}
	protected static String DropTable(){
		System.out.println("in TABLE_PICTURE 1");
		String sql = DropTable(Tables.TABLE_PICTURE);
		System.out.println("in TABLE_PICTURE 2");
		return sql;
	}
}
