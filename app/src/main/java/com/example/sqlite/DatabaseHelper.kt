package com.example.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build.ID

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "people.db", null, 1)
{

    val  Table_Name = "people_table"
    val col2 = "NAME"
    val col3 = "EMAIL"

    private val DROP_USER_TABLE = "DROP TABLE IF EXISTS $Table_Name"

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "Create Table " + Table_Name + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " NAME TEXT, EMAIL TEXT, TVSHOW TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_USER_TABLE)
        onCreate(db)
    }

    fun addData (name : String?, email: String?) : Boolean
    {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(col2, name)
        contentValues.put(col3, email)
        val result = db.insert(Table_Name, null, contentValues)
        return result != -1L

    }

    fun showData() : Cursor?
    {
        val db = this.writableDatabase
        return db.rawQuery("SELECT * FROM $Table_Name", null)
    }

}