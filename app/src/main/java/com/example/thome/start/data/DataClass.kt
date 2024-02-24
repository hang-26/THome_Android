package com.example.thome.start.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.thome.start.data.user.UserData

class DataClass(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "thome.db"
        private const val DATABASE_VERSION = 1

    }

    /**
     * Hàm tạo bảng user
     */

//    private val CREATE_TABLE_USERS = """
//        CREATE TABLE ${UserContractClass.UserEntry.TABLE_NAME} (
//            ${UserContractClass.UserEntry.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
//            ${UserContractClass.UserEntry.COLUMN_USERNAME} TEXT,
//            ${UserContractClass.UserEntry.COLUMN_PASSWORD} TEXT,
//            ${UserContractClass.UserEntry.COLUMN_USER_TYPE} INT
//        )
//    """.trimIndent()

    private val CREATE_TABLE_USERS = "CREATE TABLE ${UserContractClass.UserEntry.TABLE_NAME} (" +
            "${UserContractClass.UserEntry.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
            "${UserContractClass.UserEntry.COLUMN_USERNAME } TEXT," +
            "${UserContractClass.UserEntry.COLUMN_PASSWORD} TEXT, " +
            "${UserContractClass.UserEntry.COLUMN_USER_TYPE} INT)"
    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${UserContractClass.UserEntry.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_USERS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    // Thêm thông tin đăng ký
    fun insertUser(user: UserData) {
        var db = writableDatabase
        val values = ContentValues().apply {
            put(UserContractClass.UserEntry.COLUMN_USERNAME, user.userName)
            put(UserContractClass.UserEntry.COLUMN_PASSWORD, user.userPass)
            put(UserContractClass.UserEntry.COLUMN_USER_TYPE, user.userType)
        }
        db.insert(UserContractClass.UserEntry.TABLE_NAME, null, values)
        db.close()
    }

    fun checkUser(typeUser: Int, userName: String, userPass: String): Boolean {
        var db = readableDatabase
        var query = "SELECT * FROM ${UserContractClass.UserEntry.TABLE_NAME } WHERE ${UserContractClass.UserEntry} =? " +
                "AND ${UserContractClass.UserEntry.COLUMN_PASSWORD}  = ?" +
                "AND ${UserContractClass.UserEntry.COLUMN_USER_TYPE} = ?"

        var cursor = db.rawQuery(query, arrayOf(typeUser.toString(), userName, userPass))
        var count = cursor.count
        cursor.close()
        return count > 0
    }

    fun checkExitUser (typeUser: Int, userName: String): Boolean {
        var db = readableDatabase
        var query = "SELECT * FROM ${UserContractClass.UserEntry.TABLE_NAME} WHERE ${UserContractClass.UserEntry.COLUMN_USER_TYPE} =? AND ${UserContractClass.UserEntry.COLUMN_USERNAME} =?"
        var cusor = db.rawQuery(query, arrayOf(typeUser.toString(), userName))
        var count = cusor.count
        cusor.close()
        if (count == 0) {
            return true
        } else
            return false

    }


}