package com.example.contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "Contact_Manager";
    private static final String TABLE_NAME = "Contact";
    private static final String ID = "Contact_Id";
    private static final String FULL_NAME = "Contact_Full_Name";
    private static final String COMPANY = "Contact_Company";
    private static final String TITLE = "Contact_Title";
    private static final String MOBILE = "Contact_Mobile";
    private static final String EMAIL = "Contact_Email";
    private static final String CREATED_AT = "Contact_Created_At";
    private static final String AVATAR = "Contact_Avatar";
    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String script = " CREATE TABLE " + TABLE_NAME + " ( " +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FULL_NAME + " TEXT, "+
                COMPANY + " TEXT, "+
                TITLE + " TEXT, " +
                MOBILE + " TEXT, "+
                EMAIL + " TEXT, "+
                CREATED_AT + " TEXT " + " )";
        db.execSQL(script);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int il) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public int addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FULL_NAME, contact.getName());
        values.put(COMPANY, contact.getCompany());
        values.put(TITLE, contact.getTitle());
        values.put(MOBILE, contact.getMobile());
        values.put(EMAIL, contact.getEmail());
        values.put(CREATED_AT, contact.getCreateAt());
        db.insert(TABLE_NAME, null, values);
        db = this.getReadableDatabase();
        String id = " SELECT * FROM " + TABLE_NAME + " ORDER BY " + ID + " DESC LIMIT 1";
        Cursor cursor = db.rawQuery(id, null);
        if (cursor != null)
            cursor.moveToFirst();
        db.close();
        return Integer.parseInt(cursor.getString(0));
    }
    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> contactArrayList = new ArrayList<Contact>();
        String selectQuery = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setCompany(cursor.getString(2));
                contact.setTitle(cursor.getString(3));
                contact.setMobile(cursor.getString(4));
                contact.setEmail(cursor.getString(5));
                contact.setCreateAt(cursor.getString(6));
                contactArrayList.add(contact);
            } while (cursor.moveToNext());
        }
        return contactArrayList;
    }
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID + " =?",
                new String[]{String.valueOf(contact.getId())});
        db.close();
    }
}