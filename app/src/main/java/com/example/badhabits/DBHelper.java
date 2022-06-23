package com.example.badhabits;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "badhabits.sql", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table users" + "(id integer primary key AUTOINCREMENT, username text, email text, password text)");
        sqLiteDatabase.execSQL("create table bad_habits" + "(id integer primary key AUTOINCREMENT, name text)");
        sqLiteDatabase.execSQL("create table user_habits" + "(id_user integer references users(id), id_habit integer references bad_habits(id), start_date date) ");
        sqLiteDatabase.execSQL("create table user_rewards" + "(id_reward integer references users(id), reward text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");
        sqLiteDatabase.execSQL("drop table if exists bad_habits");
        sqLiteDatabase.execSQL("drop table if exists user_habits");
        sqLiteDatabase.execSQL("drop table if exists user_rewards");
    }

    public boolean insertUser (UserModel userModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",userModel.getUsername());
        contentValues.put("email", userModel.getEmail());
        contentValues.put("password", userModel.getPassword());
        db.insert("users", null,contentValues);
        return true;
    }
}