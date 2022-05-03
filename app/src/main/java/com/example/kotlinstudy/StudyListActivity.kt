package com.example.kotlinstudy

import android.os.Bundle
import android.os.PersistableBundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinstudy.databinding.ActivityStudyListBinding

class StudyListActivity: AppCompatActivity() {
    private lateinit var binding: ActivityStudyListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudyListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        loadData()

        val studyListData = ArrayList<StudyListData>()
        studyListData.add(StudyListData("한혜원", 24, "개발자"))
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = StudyListAdapter(studyListData)

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = WebViewClient()
        binding.webView.webChromeClient = WebChromeClient()
        binding.webView.loadUrl("https://www.naver.com")
    }

    private fun loadData() {
        val pref = getSharedPreferences("pref", 0)
        binding.textView.setText(pref.getString("name", "null")) //defValue: 키 값이 널일 경우 대체값
    }

    private fun saveData() {
        val pref = getSharedPreferences("pref", 0)
        val edit = pref.edit()
        edit.putString("name", binding.textView.text.toString())
        edit.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        saveData()
    }

    override fun onBackPressed() {
        if(binding.webView.canGoBack())
            binding.webView.goBack()
        else
            super.onBackPressed()
    }
}