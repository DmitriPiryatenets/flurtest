package data.dao.general;

import java.util.ArrayList;
import java.util.List;

import DatabaseConnection.TABLE_PICTURE;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import data.Picture;

public class PictureDAOImpl<E> extends TABLE_PICTURE implements
		ElementDAO<E> {

	private Class<E> elementClass;

	private String[] allColumns = { COLUMN_ID, REF_WORD_FIELD, REF_LEVEL_FIELD, LINK_TO_FILE_FIELD };

	private SQLiteDatabase database = DbConnection.getInstance().getDatabase();

	public PictureDAOImpl(Class<E> elementClass) {
		this.elementClass = elementClass;
	}

	@Override
	public void addElement(E el) {
		Picture picture = new Picture();
		try {
			picture = (Picture) el;
		} catch (Exception e) {
			e.printStackTrace();
		}
		ContentValues values = new ContentValues();
		values.put(REF_WORD_FIELD, picture.getRef_word());
		values.put(REF_LEVEL_FIELD, picture.getRef_level());
		values.put(LINK_TO_FILE_FIELD, picture.getLink_to_file());
		try {
			long insertId = database.insert(TABLE_PICTURE, null, values);
			Cursor cursor = database.query(TABLE_PICTURE, allColumns,
					COLUMN_ID + " = " + insertId, null, null, null, null);
			cursor.moveToFirst();
			Picture newComment = cursorToPicture(cursor);
			cursor.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public E getElementByID(Long elId) {
		E el = null;
		Cursor cursor = database.query(TABLE_PICTURE, allColumns, COLUMN_ID
				+ "=" + elId, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			el = (E) cursorToPicture(cursor);
			cursor.moveToNext();
		}
		cursor.close();
		return el;
	}

	@Override
	public List<E> getAllElements() {
		List<E> els = new ArrayList<E>();

		Cursor cursor = database.query(TABLE_PICTURE, allColumns, null,
				null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Picture picture = cursorToPicture(cursor);
			els.add((E) picture);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return els;
	}

	@Override
	public void deleteElement(E el) {
		Picture picture = (Picture) el;
		long id = picture.getId();
		database.delete(TABLE_PICTURE, COLUMN_ID + " = " + id, null);
	}

	private Picture cursorToPicture(Cursor cursor) {
		Picture picture = new Picture();
		picture.setId(cursor.getInt(0));
		picture.setRef_word(cursor.getInt(1));
		picture.setRef_level(cursor.getInt(2));
		picture.setLink_to_file(cursor.getString(3));
		return picture;
	}

	@Override
	public void deleteAllElements() {
		database.delete(TABLE_PICTURE, null, null);
		database.execSQL(DropTable());
		database.execSQL(createTable());
	}

}
