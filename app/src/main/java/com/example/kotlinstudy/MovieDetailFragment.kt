package com.example.kotlinstudy

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinstudy.databinding.ActivityMainBinding
import com.example.kotlinstudy.databinding.FragmentMovieDetailBinding
import com.example.kotlinstudy.databinding.FragmentMovieListBinding

class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is MainActivity) mainActivity = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        initRecyclerView()
    }

    fun initListener(){
        var thumbUpImage = binding.thumbUp
        var thumbDownImage = binding.thumbDown
        var likeNumInt = binding.likeNum
        var dislikeNumInt = binding.dislikeNum
        var writingReviewButton = binding.writingReview
        var seeAllreviewButton = binding.seeAllReview

        var validateThumbDown= false
        var validateThumbUp = false

        thumbUpImage.setOnClickListener{
            if(validateThumbUp == false){
                likeNumInt.text = (likeNumInt.text.toString().toInt() + 1).toString()
                thumbUpImage.setImageResource(R.drawable.ic_thumb_up_selected)
                if(validateThumbDown == true){
                    dislikeNumInt.text = (dislikeNumInt.text.toString().toInt() - 1).toString()
                    thumbDownImage.setImageResource(R.drawable.ic_thumb_down)
                }
            }
            else {
                Toast.makeText(mainActivity,"Already Liked",Toast.LENGTH_SHORT).show()
            }
            validateThumbUp = true

        }

        thumbDownImage.setOnClickListener{
            if(validateThumbDown == false){
                dislikeNumInt.text = (dislikeNumInt.text.toString().toInt() + 1).toString()
                thumbDownImage.setImageResource(R.drawable.ic_thumb_down_selected)
                if(validateThumbUp == true){
                    likeNumInt.text = (likeNumInt.text.toString().toInt() - 1).toString()
                    thumbUpImage.setImageResource(R.drawable.ic_thumb_up)
                }
            }
            else{
                Toast.makeText(mainActivity,"Already Disiked",Toast.LENGTH_SHORT).show()
            }
            validateThumbDown = true
        }
        writingReviewButton.setOnClickListener{
            var intent = Intent(mainActivity,WriteCommentActivity::class.java)
            startActivity(intent)
        }
        seeAllreviewButton.setOnClickListener{
            var intent = Intent(mainActivity,CommentActivity::class.java)
            startActivity(intent)
        }

    }
    fun initRecyclerView(){
        binding.recyclerView.layoutManager = LinearLayoutManager(mainActivity)
        val commentList = ArrayList<CommentData>()
        var helper = DBHelper(mainActivity)
        var db = helper.writableDatabase
        var sql = "select * from movie_review"
        var c = db.rawQuery(sql,null)
        while(c.moveToNext()){
            commentList.add(CommentData(R.drawable.user1,"hhwa***",c.getString(3),c.getFloat(1),c.getString(2),7))
        }
        db.close()

        val adapter = RecyclerViewAdapter(commentList)
        binding.recyclerView.adapter = adapter
    }
}

