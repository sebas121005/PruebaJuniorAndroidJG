package com.juansebastian.pruebajuniorandroid.presenter.vehiculo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class DbVehiculo (context: Context) : SQLiteOpenHelper(context, "DB_VEHICULO", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
        db.execSQL(SQL_CREATE_ENTRIES_PERFIL)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    object FeedEntry : BaseColumns {
        const val TABLE_NAME = "VEHICULO"
        const val COLUMN_NAME_USUARIO = "USUARIO"
        const val COLUMN_NAME_DATA = "DATA"
    }

    object Perfil : BaseColumns {
        const val TABLE_NAME_PERFIL = "PERFIL"
        const val COLUMN_NAME_USUARIO = "USUARIO"
        const val COLUMN_NAME_DATA = "DATA"
        const val COLUMN_NAME_PERFIL = "PERFIL"
    }

    companion object {
        private const val SQL_CREATE_ENTRIES =
                "CREATE TABLE ${FeedEntry.TABLE_NAME} (" +
                        "${FeedEntry.COLUMN_NAME_USUARIO} VARCHAR(30)," +
                        "${FeedEntry.COLUMN_NAME_DATA} TEXT)"

        private const val SQL_CREATE_ENTRIES_PERFIL =
                "CREATE TABLE ${Perfil.TABLE_NAME_PERFIL} (" +
                        "${Perfil.COLUMN_NAME_USUARIO} VARCHAR(30)," +
                        "${Perfil.COLUMN_NAME_DATA} TEXT," +
                        "${Perfil.COLUMN_NAME_PERFIL} VARCHAR(5))"
    }



}