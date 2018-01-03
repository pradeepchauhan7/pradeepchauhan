package com.example.pradeep.word_meaningsaver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import com.example.pradeep.word_meaningsaver.dbattributes.UserPojo;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by anilkukreti on 13/05/17.
 */

public class DBHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "user.db";
    public static final int DATABASE_VERSION = 1;
    public static final String USER_TABLE = "userdata";

    private static final String KEY_WORD = "word";
    private static final String KEY_MEANING = "meaning";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENTS_TABLE = "CREATE TABLE " + USER_TABLE + "("
                + KEY_WORD + " TEXT not null unique ," + KEY_MEANING + " TEXT not null" + ")";
        db.execSQL(CREATE_STUDENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        // Create tables again
        onCreate(db);
    }

    public void addUserData(UserPojo obj) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_WORD,obj.getWord());
        values.put(KEY_MEANING,obj.getMeaning());

        // Inserting Row
        db.insert(USER_TABLE, null, values);
        db.close(); // Closing database connection

    }

    public void removeUserData(UserPojo obj) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(USER_TABLE, KEY_WORD + " = ?",
                new String[] { String.valueOf(obj.getWord()) });
        db.close();

    }

    public int updateUserData(UserPojo obj) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_WORD,obj.getWord());
        values.put(KEY_MEANING,obj.getMeaning());

        // updating row
        return db.update(USER_TABLE, values, KEY_WORD + " = ?",
                new String[] { String.valueOf(obj.getWord()) });

    }

    public UserPojo fetchUserData(String word) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(USER_TABLE, new String[] { KEY_WORD,KEY_MEANING}, KEY_WORD + "=?",
                new String[] { word }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        UserPojo student = new UserPojo();

        //fetching data of columns word and meaning

        student.setWord(cursor.getString(cursor.getColumnIndexOrThrow(KEY_WORD)));
        student.setMeaning(cursor.getString(cursor.getColumnIndexOrThrow(KEY_MEANING)));
        return student;
    }

    public List<UserPojo> fetchAllData() {

        SQLiteDatabase db = this.getReadableDatabase();
        List<UserPojo> userPojoList = new ArrayList<>();

        Cursor cursor = db.query(USER_TABLE, new String[] { KEY_WORD,KEY_MEANING},null,
                null, null, null, null, null);
        if (cursor != null)
            while (cursor.moveToNext()) {


                UserPojo student = new UserPojo();

                student.setWord(cursor.getString(cursor.getColumnIndexOrThrow(KEY_WORD)));
                student.setMeaning(cursor.getString(cursor.getColumnIndexOrThrow(KEY_MEANING)));
                userPojoList.add(student);
            }

            return userPojoList;
    }


}
