package com.example.kotlinstudy

import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinstudy.databinding.ActivityMainBinding
import com.example.kotlinstudy.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity() {
    //build.gradle android 블럭 안에 buildFeatures블럭 안에 viewBinding true
    private lateinit var binding: ActivityMovieDetailBinding
    //private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    //Bundle은 Map클래스로 구현된 데이터 묶음. key-value로 다양한 자료형의 값 가질 수 있음
    //액티비티 중단할 때 번들 객체에 임시적으로 데이터 저장
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initListener()
        initRecyclerView()
    }

    //좋아요를 누를경우
    //1. 숫자가 증가한다
    //2. 좋아요 이미지가 변경된다.
    //3. 싫어요가 눌려있으면 싫어요 이미지를 변경하고 싫어요 수를 -1한다.
    fun initListener() {
        //작성하기 모두버기 리스너도 만들어보자
        var thumbUpValidate = false
        var thumbDownValidate = false

        var thumbUpImageView = binding.thumbUp
        var thumbDownImageView = binding.thumbDown

        var thumbUpTextView = binding.thumbUpText
        var thumbDownTextView = binding.thumbDownText

        thumbUpImageView.setOnClickListener {
            var thumbUpText = thumbUpTextView.text.toString().toInt()
            var thumbDownText = thumbDownTextView.text.toString().toInt()
            //한번 더 누르면 해제하는것도 만들어보자
            if (thumbUpValidate == false) {
                thumbUpTextView.text = (thumbUpText + 1).toString()
                thumbUpImageView.setImageResource(R.drawable.ic_thumb_up_selected)
                thumbUpValidate = true
            } else Toast.makeText(this, "이미 추천하였습니다.", Toast.LENGTH_SHORT).show()
            if (thumbDownValidate == true) {
                thumbDownValidate = false
                thumbDownImageView.setImageResource(R.drawable.ic_thumb_down)
                thumbDownTextView.text = (thumbDownText - 1).toString()
            }
        }

        if (thumbDownValidate == false) {
            binding.thumbDown.setOnClickListener {
                var thumbUpText = thumbUpTextView.text.toString().toInt()
                var thumbDownText = thumbDownTextView.text.toString().toInt()
                if (thumbDownValidate == false) {
                    thumbDownTextView.text = (thumbDownText + 1).toString()
                    thumbDownImageView.setImageResource(R.drawable.ic_thumb_down_selected)
                    thumbDownValidate = true
                } else Toast.makeText(this, "이미 비추천하였습니다.", Toast.LENGTH_SHORT).show()
                if (thumbUpValidate == true) {
                    thumbUpValidate = false
                    thumbUpImageView.setImageResource(R.drawable.ic_thumb_up)
                    thumbUpTextView.text = (thumbUpText - 1).toString()
                }
            }
        }

        binding.writeReview.setOnClickListener {
            val intent = Intent(this, WriteCommentActivity::class.java)
            startActivity(intent)
        }
        binding.expandingBtn.setOnClickListener {
            val intent = Intent(this, CommentActivity::class.java)
            startActivity(intent)
        }

    }

    fun initRecyclerView() {
        val commentList = ArrayList<CommentData>()
        commentList.add(CommentData(R.drawable.user1, "hhw**", "5분전", 1.toFloat(), "최악의 영화, 추천합니다.", 5))
        commentList.add(CommentData(R.drawable.user1, "gksgpd**", "10분전", 1.toFloat(), "그닥..", 0))
        commentList.add(
            CommentData(
                R.drawable.user1,
                "kiki**",
                "2022/01/19",
                3.toFloat(),
                "그냥 킬링타임으로 괜찮았다",
                1
            )
        )

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        //binding.recyclerview.setHasFixedSize(true)

        val adapter = CommentRecyclerViewAdapter(commentList)
        binding.recyclerview.adapter = adapter
    }
}