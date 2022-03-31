package com.example.kotlinstudy

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (context: Context):SQLiteOpenHelper(context,"Movie.db",null,1){
    override fun onCreate(p0: SQLiteDatabase?) {
        val sql = "create table movie_review (id integer primary key autoincrement, rate real not null, content text not null, date date not null)"
        p0?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}