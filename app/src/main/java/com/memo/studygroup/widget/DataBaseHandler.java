package com.memo.studygroup.widget;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.memo.studygroup.vo.MemoVO;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper implements CRUDOperations {

	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "memoDb";

	// Table name
	private static final String TABLE_NAME = "memoTable";

	// Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_MEMO = "memo";
	private static final String KEY_DATE = "date";

	public DataBaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	public static DataBaseHandler getInstance(Context context) {
		return new DataBaseHandler(context.getApplicationContext());
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//DB를 새로 만든다.
		String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
			+ KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_MEMO + " TEXT,"
			+ KEY_DATE + " TEXT" + ")";
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//DB를 지우고 다시 쓴다.
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		this.onCreate(db);
	}

	//CRUDOperations Override Method

	@Override
	public void insert(MemoVO memoVO) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues contentValues = new ContentValues();
		contentValues.put(KEY_MEMO, memoVO.getMemo());
		contentValues.put(KEY_DATE, memoVO.getRegdate());

		long returnResult = db.insert(TABLE_NAME, null, contentValues);

		db.close();
	}

	@Override
	public MemoVO read(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		String[] culums = { KEY_ID, KEY_MEMO, KEY_DATE };
		Cursor cursor = db.query(TABLE_NAME, culums, " id = ?", new String[] { String.valueOf(id) }, null, null, null, null);

		if (cursor != null) {
			cursor.moveToFirst();
		}

		MemoVO memoVO = new MemoVO();
		memoVO.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
		memoVO.setMemo(cursor.getString(cursor.getColumnIndex(KEY_MEMO)));
		memoVO.setRegdate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));

		return memoVO;
	}

	@Override
	public List<MemoVO> readAll() {

		List<MemoVO> memoVOList = new ArrayList<MemoVO>();
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "SELECT * FROM " + TABLE_NAME;

		Cursor cursor = db.rawQuery(query, null);

		MemoVO memoVO = null;
		if (cursor.moveToFirst()) {
			do {
				memoVO = new MemoVO();
				memoVO.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
				memoVO.setMemo(cursor.getString(cursor.getColumnIndex(KEY_MEMO)));
				memoVO.setRegdate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));

				memoVOList.add(memoVO);
			} while (cursor.moveToNext());
		}

		return memoVOList;

	}

	@Override
	public void update(MemoVO memoVO) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_MEMO, memoVO.getMemo());
		values.put(KEY_DATE, memoVO.getRegdate());
		String[] nums = { String.valueOf(memoVO.getId()) };

		int i = db.update(TABLE_NAME,
			values,
			KEY_ID + " = ?",
			new String[] { String.valueOf(memoVO.getId()) });

		db.close();
	}

	@Override
	public void delete(MemoVO memoVO) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_NAME, KEY_ID + " = ?", new String[] { String.valueOf(memoVO.getId()) });
		db.close();
	}

}
