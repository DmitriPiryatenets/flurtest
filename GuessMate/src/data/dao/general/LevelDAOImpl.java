package data.dao.general;

import java.util.ArrayList;
import java.util.List;

import DatabaseConnection.TABLE_LEVEL;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import data.Level;

public class LevelDAOImpl<E> extends TABLE_LEVEL implements
		ElementDAO<E> {

	private Class<E> elementClass;

	private String[] allColumns = { COLUMN_ID, NAME_FIELD };

	private SQLiteDatabase database = DbConnection.getInstance().getDatabase();

	public LevelDAOImpl(Class<E> elementClass) {
		this.elementClass = elementClass;
	}

	@Override
	public void addElement(E el) {
		Level level = new Level();
		try {
			level = (Level) el;
		} catch (Exception e) {
			e.printStackTrace();
		}
		ContentValues values = new ContentValues();
		values.put(NAME_FIELD, level.getName());
		try {
			long insertId = database.insert(TABLE_LEVEL, null, values);
			Cursor cursor = database.query(TABLE_LEVEL, allColumns,
					COLUMN_ID + " = " + insertId, null, null, null, null);
			cursor.moveToFirst();
			Level newComment = cursorToLevel(cursor);
			cursor.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public E getElementByID(Long elId) {
		E el = null;
		Cursor cursor = database.query(TABLE_LEVEL, allColumns, COLUMN_ID
				+ "=" + elId, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			el = (E) cursorToLevel(cursor);
			cursor.moveToNext();
		}
		cursor.close();
		return el;
	}

	@Override
	public List<E> getAllElements() {
		List<E> els = new ArrayList<E>();

		Cursor cursor = database.query(TABLE_LEVEL, allColumns, null,
				null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Level level = cursorToLevel(cursor);
			els.add((E) level);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return els;
	}

	@Override
	public void deleteElement(E el) {
		Level level = (Level) el;
		long id = level.getId();
		database.delete(TABLE_LEVEL, COLUMN_ID + " = " + id, null);
	}

	private Level cursorToLevel(Cursor cursor) {
		Level level = new Level();
		level.setId(cursor.getInt(0));
		level.setName(cursor.getString(1));
		return level;
	}

	@Override
	public void deleteAllElements() {
		database.delete(TABLE_LEVEL, null, null);
		database.execSQL(DropTable());
		database.execSQL(createTable());
	}

}
