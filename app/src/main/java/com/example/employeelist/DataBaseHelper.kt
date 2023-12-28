package com.example.employeelist

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null,
    DATABASE_VERSION
) {

    companion object {

        private val DATABASE_NAME = "employee_details.DB"
        private val DATABASE_VERSION = 1
        private val TABLE_NAME = "DETAILS"
        private val COLUMN_ID = "id"
        private val COLUMN_NAME = "name"
        private val COLUMN_ADDRESS = "address"
        private val COLUMN_DEPARTMENT = "department"
        private val COLUMN_GENDER = "gender"
        private val COLUMN_FRESHER = "fresher"
        private val COLUMN_REMARK = "remark"


    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery =
            "CREATE TABLE $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY,$COLUMN_NAME TEXT,$COLUMN_ADDRESS TEXT,$COLUMN_GENDER TEXT,$COLUMN_REMARK TEXT,$COLUMN_DEPARTMENT TEXT,$COLUMN_FRESHER)"
        db?.execSQL(createTableQuery)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
    }

    fun insertDetails(details: Employee) {
        val db = writableDatabase

        val values = ContentValues().apply {
            put(COLUMN_NAME, details.name)
            put(COLUMN_ADDRESS, details.address)
            put(COLUMN_GENDER, details.gender.toString())
            put(COLUMN_DEPARTMENT, details.department.toString())
            put(COLUMN_FRESHER, details.fresher.toString())
            put(COLUMN_REMARK, details.remarks)

        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }


    fun getAllNotes(): List<Employee> {
        val employeelist = mutableListOf<Employee>()
        val db = readableDatabase
        val query = "SELECT* FROM $TABLE_NAME"
        val cursur = db.rawQuery(query, null)

        while (cursur.moveToNext()) {
            val id = cursur.getInt(cursur.getColumnIndexOrThrow(COLUMN_ID))
            val name = cursur.getString(cursur.getColumnIndexOrThrow(COLUMN_NAME))
            val address = cursur.getString(cursur.getColumnIndexOrThrow(COLUMN_ADDRESS))
            val department = cursur.getString(cursur.getColumnIndexOrThrow(COLUMN_DEPARTMENT))
            val gender = cursur.getString(cursur.getColumnIndexOrThrow(COLUMN_GENDER))
            val fresher = cursur.getString(cursur.getColumnIndexOrThrow(COLUMN_FRESHER))
            val remark = cursur.getString(cursur.getColumnIndexOrThrow(COLUMN_REMARK))

            employeelist.add(Employee(id, name, address, gender, department, fresher,remark))
        }
        cursur.close()
        db.close()
        return employeelist
    }


}


