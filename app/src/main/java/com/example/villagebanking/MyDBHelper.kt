package com.example.villagebanking

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.text.FieldPosition


class MyDBHelper(context: Context) : SQLiteOpenHelper(context, "vbanking.db3", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE members (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "names VARCHAR, " +
                "contact_number VARCHAR, " +
                "account_info VARCHAR, " +
                "role VARCHAR, " +
                "share_post DOUBLE(10,2), " +
                "loan_app DOUBLE(10,2));"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }




}