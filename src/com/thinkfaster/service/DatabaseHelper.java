package com.thinkfaster.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * User: Breku
 * Date: 07.10.13
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "myDB_brain_watt";
    private static final String TABLE_NAME = "HIGH_SCORES";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_LEVEL_DIFFICULTY = "LEVEL_DIFFICULTY";
    private static final String COLUMN_MATH_PARAMETER = "MATH_PARAMETER";
    private static final String COLUMN_SCORE = "SCORE";
    private static final String COLUMN_LOCKED = "LOCKED";
    private static final int DATABASE_VERSION = 23;


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    private boolean isTableExists(String tableName) {
        SQLiteDatabase db = getReadableDatabase();
        if (tableName == null || db == null || !db.isOpen()) {
            return false;
        }
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[]{"table", tableName});
        if (!cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return false;
        }
        int count = cursor.getInt(0);
        cursor.close();
        db.close();
        return count > 0;
    }

    /**
     * Called when database does not exists
     *
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "LEVEL_DIFFICULTY TEXT, " +
                "MATH_PARAMETER TEXT, " +
                "SCORE INTEGER, " +
                "LOCKED INTEGER" +
                ")");
        createDefaultHighScoreValues(sqLiteDatabase);
    }

    /**
     * Is called when DATABASE_VERSION is upgraded
     *
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }




    private void createDefaultHighScoreValues(SQLiteDatabase sqLiteDatabase) {


    }






}
