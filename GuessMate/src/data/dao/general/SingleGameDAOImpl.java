package data.dao.general;

import java.util.ArrayList;
import java.util.List;

import DatabaseConnection.TABLE_SINGLE_GAME;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import data.SingleGame;

public class SingleGameDAOImpl<E> extends TABLE_SINGLE_GAME implements
		ElementDAO<E> {

	private Class<E> elementClass;

	private String[] allColumns = { COLUMN_ID, ID_LEVEL_CURRENT_FIELD,
			PICTURE_OPEN_COUNT, IS_LETTER_REMOVED };

	private SQLiteDatabase database = DbConnection.getInstance().getDatabase();

	public SingleGameDAOImpl(Class<E> elementClass) {
		this.elementClass = elementClass;
	}

	@Override
	public void addElement(E el) {
		SingleGame sg = new SingleGame();
		try {
			sg = (SingleGame) el;
		} catch (Exception e) {
			e.printStackTrace();
		}
		ContentValues values = new ContentValues();
//		values.put(COLUMN_ID, sg.getId());
		values.put(ID_LEVEL_CURRENT_FIELD, sg.getIdLevelCur());
		values.put(PICTURE_OPEN_COUNT, sg.getPictureCount());
		values.put(IS_LETTER_REMOVED, (sg.isLetterRemoved() ? 1 : 0));
		try {
			long insertId = database.insert(TABLE_SINGLE_GAME, null, values);
			Cursor cursor = database.query(TABLE_SINGLE_GAME, allColumns,
					COLUMN_ID + " = " + insertId, null, null, null, null);
			cursor.moveToFirst();
			SingleGame newComment = cursorToSingleGame(cursor);
			cursor.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public E getElementByID(Long elId) {
		E el = null;
		Cursor cursor = database.query(TABLE_SINGLE_GAME, allColumns, COLUMN_ID
				+ "=" + elId, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			el = (E) cursorToSingleGame(cursor);
			cursor.moveToNext();
		}
		cursor.close();
		return el;
	}

	@Override
	public List<E> getAllElements() {
		List<E> els = new ArrayList<E>();

		Cursor cursor = database.query(TABLE_SINGLE_GAME, allColumns, null,
				null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			SingleGame sg = cursorToSingleGame(cursor);
			els.add((E) sg);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return els;
	}

	@Override
	public void deleteElement(E el) {
		SingleGame sg = (SingleGame) el;
		long id = sg.getId();
		database.delete(TABLE_SINGLE_GAME, COLUMN_ID + " = " + id, null);
	}

	private SingleGame cursorToSingleGame(Cursor cursor) {
		SingleGame sg = new SingleGame();
		sg.setId(cursor.getInt(0));
		sg.setIdLevelCur(cursor.getInt(1));
		sg.setPictureCount(cursor.getInt(2));
		sg.setLetterRemoved(cursor.getInt(3) > 0);
		return sg;
	}

	@Override
	public void deleteAllElements() {
		database.delete(TABLE_SINGLE_GAME, null, null);
		database.execSQL(DropTable());
		database.execSQL(createTable());
	}

}
