package net.geidea.payment

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import net.geidea.payment.transaction.model.TransData
//test commit
class DBHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object {
        private const val DATABASE_NAME = "UsersDatabase.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_ADMIN ="admintable"
        private const val TABLE_TID ="terminalID"
        private const val TABLE_MID ="merchantID"
        private const val TABLE_MERCHANT_NAME ="merchantName"
        private const val TABLE_MERCHANT_ADDRESS ="merchantAddress"
        private const val TABLE_TXN_DATA ="txn_data_table"
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
        private const val COLUMN_MTI ="mti"
        private const val COLUMN_BITMAP ="bitmap"
        private const val COLUMN_PAN ="pan"
        private const val COLUMN_FIELD02 ="field02"
        private const val COLUMN_FIELD03 ="field03"
        private const val COLUMN_FIELD04 ="field04"
        private const val COLUMN_FIELD07 ="field07"
        private const val COLUMN_FIELD11 ="field11"
        private const val COLUMN_FIELD12 ="field12"
        private const val COLUMN_FIELD14 ="field14"
        private const val COLUMN_FIELD22 ="field22"
        private const val COLUMN_FIELD24 ="field24"
        private const val COLUMN_FIELD25 ="field25"
        private const val COLUMN_FIELD35 ="field35"
        private const val COLUMN_FIELD37 ="field37"
        private const val COLUMN_FIELD38 ="field38"
        private const val COLUMN_FIELD39 ="field39"
        private const val COLUMN_FIELD41 ="field41"
        private const val COLUMN_FIELD42 ="field42"
        private const val COLUMN_FIELD49 ="field49"
        private const val COLUMN_FIELD52 ="field52"
        private const val COLUMN_FIELD55 ="field55"
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
        private const val CREATE_TXN_DATA =
            "CREATE TABLE $TABLE_TXN_DATA (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY," +
                    "$COLUMN_MTI TEXT,"+
                    "$COLUMN_BITMAP TEXT,"+
                    "$COLUMN_FIELD02 TEXT,"+
                    "$COLUMN_FIELD03 TEXT,"+
                    "$COLUMN_FIELD04 TEXT,"+
                    "$COLUMN_FIELD07 TEXT,"+
                    "$COLUMN_FIELD11 TEXT,"+
                    "$COLUMN_FIELD12 TEXT,"+
                    "$COLUMN_FIELD14 TEXT,"+
                    "$COLUMN_FIELD22 TEXT,"+
                    "$COLUMN_FIELD24 TEXT,"+
                    "$COLUMN_FIELD25 TEXT,"+
                    "$COLUMN_FIELD35 TEXT,"+
                    "$COLUMN_FIELD37 TEXT,"+
                    "$COLUMN_FIELD38 TEXT,"+
                    "$COLUMN_FIELD39 TEXT,"+
                    "$COLUMN_FIELD41 TEXT,"+
                    "$COLUMN_FIELD42 TEXT,"+
                    "$COLUMN_FIELD49 TEXT,"+
                    "$COLUMN_FIELD52 TEXT,"+
                    "$COLUMN_FIELD55 TEXT)"

//Queries to delete table
        private const val DELETE_ADMIN = "DROP TABLE IF EXISTS $TABLE_ADMIN"
        private const val DELETE_TID = "DROP TABLE IF EXISTS $TABLE_TID"
        private const val DELETE_MID = "DROP TABLE IF EXISTS $TABLE_MID"
        private const val DELETE_MERCHANT_NAME = "DROP TABLE IF EXISTS $TABLE_MERCHANT_NAME"
        private const val DELETE_MERCHANT_ADDRESS = "DROP TABLE IF EXISTS $TABLE_MERCHANT_ADDRESS"
        private const val DELETE_TXN_DATA = "DROP TABLE IF EXISTS $TABLE_TXN_DATA"

    }
    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL(CREATE_ADMIN)
            db.execSQL(CREATE_TID)
            db.execSQL(CREATE_MID)
            db.execSQL(CREATE_MERCHANT_NAME)
            db.execSQL(CREATE_MERCHANT_ADDRESS)
            db.execSQL(CREATE_TXN_DATA)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        if (db != null) {
            db.execSQL(DELETE_ADMIN)
            db.execSQL(DELETE_TID)
            db.execSQL(DELETE_MID)
            db.execSQL(DELETE_MERCHANT_NAME)
            db.execSQL(DELETE_MERCHANT_ADDRESS)
            db.execSQL(DELETE_TXN_DATA)


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
    fun registerTxnData(mti:String,bitmap:String,field02:String,
                        field03:String,field04:String,field07:String,
                        field11:String, field12:String,field14:String,
                        field22:String,field24:String,field25:String,
                        field35:String,field37:String,field38:String,
                        field39:String, field41:String,field42:String,
                        field49:String,field52:String,field55:String){
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_MTI, mti)
            put(COLUMN_BITMAP,bitmap)
            put(COLUMN_FIELD02, field02)
            put(COLUMN_FIELD03, field03)
            put(COLUMN_FIELD04, field04)
            put(COLUMN_FIELD07, field07)
            put(COLUMN_FIELD11, field11)
            put(COLUMN_FIELD12, field12)
            put(COLUMN_FIELD14, field14)
            put(COLUMN_FIELD22, field22)
            put(COLUMN_FIELD24, field24)
            put(COLUMN_FIELD25, field25)
            put(COLUMN_FIELD35, field35)
            put(COLUMN_FIELD37, field37)
            put(COLUMN_FIELD38, field38)
            put(COLUMN_FIELD39, field39)
            put(COLUMN_FIELD41, field41)
            put(COLUMN_FIELD42, field42)
            put(COLUMN_FIELD49, field49)
            put(COLUMN_FIELD52, field52)
            put(COLUMN_FIELD55, field55)
        }
        db.insert(TABLE_TXN_DATA, null, values)
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
            do{
                tid = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TID))

            }while(cursor.moveToNext())
        }
        cursor.close()

        return tid
    }
    fun getMID():String{

        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT $COLUMN_MID FROM $TABLE_MID", null)
        var mid=""
        if(cursor.moveToFirst()){
            do {
                mid = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MID))

                }while (cursor.moveToNext())
        }
        cursor.close()

        return mid
    }
    fun getMerchantAddress():String{

        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT $COLUMN_MERCHANT_ADDRESS FROM $TABLE_MERCHANT_ADDRESS", null)
        var merchantAddress=""
        if(cursor.moveToFirst()){
            do {
                merchantAddress = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MERCHANT_ADDRESS))

            }while (cursor.moveToNext())
        }
        cursor.close()

        return merchantAddress
    }
    fun isAdminExists(username: String, password: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.query(TABLE_ADMIN, arrayOf(COLUMN_ID), "$COLUMN_USERNAME=? AND $COLUMN_PASSWORD=?", arrayOf(username, password), null, null, null)
        val count = cursor.count
        cursor.close()
        db.close()
        return count > 0
    }



}
