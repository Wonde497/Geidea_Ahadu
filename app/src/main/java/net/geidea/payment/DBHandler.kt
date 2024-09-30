package net.geidea.payment

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import net.geidea.payment.transaction.model.TransData

class DBHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object {
        private const val DATABASE_NAME = "UsersDatabase.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_ADMIN ="admintable"
        private const val TABLE_TID ="terminalID"
        private const val TABLE_MID ="merchantID"
        private const val TABLE_MERCHANT_NAME ="merchantName"
        private const val TABLE_MERCHANT_ADDRESS ="merchantAddress"
        //private const val TABLE_ADMIN ="admintable"
        //private const val TABLE_ADMIN ="admintable"
        //private const val TABLE_ADMIN ="admintable"
        //private const val TABLE_ADMIN ="admintable"
        //private const val TABLE_ADMIN ="admintable"
        private const val COLUMN_ID ="id"
        private const val COLUMN_USERNAME ="username"
        private const val COLUMN_PASSWORD ="password"
        private const val COLUMN_TID ="TID"
        private const val COLUMN_MID ="MID"
        private const val COLUMN_MERCHANT_NAME ="merchantName"
        private const val COLUMN_MERCHANT_ADDRESS ="merchantAddress"
        //Queries to create table
        private const val CREATE_ADMIN =
            "CREATE TABLE $TABLE_ADMIN (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY," +
                    "$COLUMN_USERNAME TEXT," +
                    "$COLUMN_PASSWORD INTEGER)"

        private const val CREATE_TID =
            "CREATE TABLE $TABLE_TID (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY," +
                    "$COLUMN_TID TEXT)"

        private const val CREATE_MID =
            "CREATE TABLE $TABLE_MID (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY," +
                    "$COLUMN_MID TEXT)"

        private const val CREATE_MERCHANT_NAME =
            "CREATE TABLE $TABLE_MERCHANT_NAME (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY," +
                    "$COLUMN_MERCHANT_NAME TEXT)"

        private const val CREATE_MERCHANT_ADDRESS =
            "CREATE TABLE $TABLE_MERCHANT_ADDRESS (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY," +

                    "$COLUMN_MERCHANT_ADDRESS TEXT)"

//Queries to delete table
        private const val DELETE_ADMIN = "DROP TABLE IF EXISTS $TABLE_ADMIN"
        private const val DELETE_TID = "DROP TABLE IF EXISTS $TABLE_TID"
        private const val DELETE_MID = "DROP TABLE IF EXISTS $TABLE_MID"
        private const val DELETE_MERCHANT_NAME = "DROP TABLE IF EXISTS $TABLE_MERCHANT_NAME"
        private const val DELETE_MERCHANT_ADDRESS = "DROP TABLE IF EXISTS $TABLE_MERCHANT_ADDRESS"

    }
    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL(CREATE_ADMIN)
            db.execSQL(CREATE_TID)
            db.execSQL(CREATE_MID)
            db.execSQL(CREATE_MERCHANT_NAME)
            db.execSQL(CREATE_MERCHANT_ADDRESS)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        if (db != null) {
            db.execSQL(DELETE_ADMIN)
            db.execSQL(DELETE_TID)
            db.execSQL(DELETE_MID)
            db.execSQL(DELETE_MERCHANT_NAME)
            db.execSQL(DELETE_MERCHANT_ADDRESS)


        }
        onCreate(db)
    }
    fun registerAdmin(userName:String,password:String){
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, userName)
            put(COLUMN_PASSWORD, password)
        }

        db.insert(TABLE_ADMIN, null, values)
        db.close()
    }
    fun registerTID(terminalID:String){
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TID, terminalID)
        }
        db.insert(TABLE_TID, null, values)
        db.close()
    }
    fun registerMID(merchantID:String){
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_MID, merchantID)
        }
        db.insert(TABLE_MID, null, values)
        db.close()
    }
    fun registerMerchantName(merchantName:String){
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_MERCHANT_NAME, merchantName)
        }
        db.insert(TABLE_MERCHANT_NAME, null, values)
        db.close()
    }
    fun registerMerchantAddress(merchantAddress:String){
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_MERCHANT_ADDRESS, merchantAddress)
        }
        db.insert(TABLE_MERCHANT_ADDRESS, null, values)
        db.close()
    }
    fun checkAdmin():Boolean{
        val db = this.readableDatabase

        val cursor = db.rawQuery("SELECT * FROM ${TABLE_ADMIN} ", null)

        val isRegistered = cursor.count > 0
        cursor.close()
        db.close()

        return isRegistered

    }
    fun checkTID():Boolean{
        val db = this.readableDatabase

        val cursor = db.rawQuery("SELECT * FROM ${TABLE_TID} ", null)

        val isRegistered = cursor.count > 0
        cursor.close()
        db.close()

        return isRegistered

    }
    fun checkMID():Boolean{
        val db = this.readableDatabase

        val cursor = db.rawQuery("SELECT * FROM ${TABLE_MID} ", null)

        val isRegistered = cursor.count > 0
        cursor.close()
        db.close()

        return isRegistered

    }
    fun checkMerchantName():Boolean{
        val db = this.readableDatabase

        val cursor = db.rawQuery("SELECT * FROM ${TABLE_MERCHANT_NAME} ", null)

        val isRegistered = cursor.count > 0
        cursor.close()
        db.close()

        return isRegistered

    }
    fun checkMerchantAddress():Boolean{
        val db = this.readableDatabase

        val cursor = db.rawQuery("SELECT * FROM ${TABLE_MERCHANT_ADDRESS} ", null)

        val isRegistered = cursor.count > 0
        cursor.close()
        db.close()

        return isRegistered

    }
    fun getTID(): String {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT $COLUMN_TID FROM $TABLE_TID", null)
        var tid = ""

        if (cursor.moveToFirst()) {
            tid = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TID))
        }
        cursor.close()

        return tid
    }
    fun getMID():String{

        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT $COLUMN_MID FROM $TABLE_MID", null)
        var mid=""
        if(cursor.moveToFirst()){
            mid = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MID))
        }
        cursor.close()

        return mid
    }


}