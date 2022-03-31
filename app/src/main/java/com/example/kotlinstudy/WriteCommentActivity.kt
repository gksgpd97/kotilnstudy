package com.example.kotlinstudy

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinstudy.databinding.ActivityWriteCommentBinding
import java.util.*
import kotlin.properties.Delegates

class WriteCommentActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding:ActivityWriteCommentBinding = ActivityWriteCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle("한줄평 작성")

        var rating : Float = (-1.0).toFloat()
        binding.ratingBar.setOnRatingBarChangeListener { ratingBar, fl, b -> rating = fl }
        var content = binding.editText.text

        binding.saveBtn.setOnClickListener(){
            var helper = DBHelper(this)
            var db = helper.writableDatabase
            var sql = "insert into movie_review (rate, content, date) values (?, ?, ?)"
            var sdf = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            } else {
                TODO("VERSION.SDK_INT < N")
            }
            var date = sdf.format(Date())
            var array = arrayOf(rating, content, date)
            db.execSQL(sql,array)
            db.close()

            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show()
            finish()
        }
        binding.cancelBtn.setOnClickListener(){
            finish()
        }
    }

}