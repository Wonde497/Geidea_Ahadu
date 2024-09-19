package net.geidea.payment

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object {
        private const val DATABASE_NAME = "UsersDatabase.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_NAME ="admintable"
        private const val COLUMN_ID ="id"
        private const val COLUMN_USERNAME ="username"
        private const val COLUMN_PASSWORD ="password"

        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE $TABLE_NAME (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY," +
                    "$COLUMN_USERNAME TEXT," +
                    "$COLUMN_PASSWORD INTEGER)"

        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL(SQL_CREATE_ENTRIES)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        if (db != null) {
            db.execSQL(SQL_DELETE_ENTRIES)

        }
        onCreate(db)
    }
    fun registerAdmin(userName:String,password:String){
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("username", userName)
            put("password", password)
        }

        db.insert(TABLE_NAME, null, values)
        db.close()
    }
}