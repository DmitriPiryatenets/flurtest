package DatabaseConnection;

public class Tables {
	public static final String COLUMN_ID = "id";
	public static final String TABLE_SINGLE_GAME = "singlegame";
	public static final String TABLE_LEVEL = "level";
	public static final String TABLE_PICTURE = "picture";
	protected static String DropTable(String column_name){
		System.out.println("in TABLES 1");
		String sql = "DROP TABLE IF EXISTS " + column_name;
		System.out.println("in TABLES 2");
		return sql;
	}
}
