package com.example.kotlinstudy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinstudy.databinding.ActivityCommentBinding

class CommentActivity:AppCompatActivity() {
    lateinit var adapter: RecyclerViewAdapter
    lateinit var binding:ActivityCommentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle("한줄평 목록")

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        var userList = ArrayList<CommentData>()
        var helper = DBHelper(this)
        var db = helper.writableDatabase
        var sql = "select * from movie_review"
        var c = db.rawQuery(sql,null)
        while(c.moveToNext()){
            userList.add(CommentData(R.drawable.user1,"hhwa***",c.getString(3),c.getFloat(1),c.getString(2),7))
        }
        db.close()
        adapter = RecyclerViewAdapter(userList)
        binding.recyclerView.adapter = adapter

        binding.writingBtn.setOnClickListener(){
            var intent = Intent(this,WriteCommentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        var userList = ArrayList<CommentData>()
        var helper = DBHelper(this)
        var db = helper.writableDatabase
        var sql = "select * from movie_review"
        var c = db.rawQuery(sql,null)
        while(c.moveToNext()){
            userList.add(CommentData(R.drawable.user1,"hhwa***",c.getString(3),c.getFloat(1),c.getString(2),7))
        }
        db.close()
        adapter = RecyclerViewAdapter(userList)
        binding.recyclerView.adapter = adapter
    }
}