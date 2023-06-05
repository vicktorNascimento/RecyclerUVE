package com.example.recycleruve.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseDatosHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "saludos";
    private static final int DATABASE_VERSION =1;

    public BaseDatosHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EstructuraBBDD.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int NewVersion) {
        db.execSQL(EstructuraBBDD.SQL_CREATE_ENTRIES);
        onCreate(db);
    }

    //hay que hacer una clase destroy que destruya el dbhelper. en donde lo hayamos creado. al parecer no es aqui.
}