package com.example.kotlinstudy

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlinstudy.databinding.FragmentMovieListBinding

class MovieListFragment : Fragment() {

    lateinit var binding: FragmentMovieListBinding
    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is MainActivity) mainActivity = context
    } //mainactivity에 접근

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var arraylistOfMovie = ArrayList<MovieListData>()
        arraylistOfMovie.add(MovieListData(R.drawable.image1,"1.군도"))
        arraylistOfMovie.add(MovieListData(R.drawable.image2,"2.공조"))
        binding.viewPagerMovie.adapter = ViewpagerAdapter(arraylistOfMovie)
        binding.viewPagerMovie.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.btnDetail.setOnClickListener(){
            mainActivity.supportFragmentManager.beginTransaction().replace(R.id.frame_layout_movie,MovieDetailFragment()).commit()
        }
    }
}