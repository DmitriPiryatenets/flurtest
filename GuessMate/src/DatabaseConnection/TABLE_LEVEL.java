package DatabaseConnection;

public class TABLE_LEVEL extends Tables{
	protected static String NAME_FIELD = "name";
	protected static String createTable(){
		String sql = "create table " + TABLE_LEVEL + "( ";
		sql += COLUMN_ID + " integer primary key autoincrement, ";
		sql += NAME_FIELD + " text not null";
		sql += ");";
		return sql;
	}
	protected static String DropTable(){
		System.out.println("in TABLE_LEVEL 1");
		String sql = DropTable(Tables.TABLE_LEVEL);
		System.out.println("in TABLE_LEVEL 2");
		return sql;
	}
}
