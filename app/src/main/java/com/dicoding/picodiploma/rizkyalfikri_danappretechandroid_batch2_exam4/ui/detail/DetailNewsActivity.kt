package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.R
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.local.entity.NewsEntity
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.databinding.ActivityDetailNewsBinding
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.utils.changeTitleColorDynamically
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.utils.setWhiteStatusBarText

class DetailNewsActivity : AppCompatActivity() {

    private val detailNewsViewModel: DetailNewsViewModel by lazy {
        ViewModelProvider(this@DetailNewsActivity).get(DetailNewsViewModel::class.java)
    }

    private lateinit var binding: ActivityDetailNewsBinding

    companion object{
        const val EXTRA_NEWS = "extra_news"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this@DetailNewsActivity, R.layout.activity_detail_news)
        setSupportActionBar(binding.toolbar)
        window.setWhiteStatusBarText(this@DetailNewsActivity)
        binding.appBar.changeTitleColorDynamically(this@DetailNewsActivity, binding.toolbarTitle)

        initData()
        loadDataToView()
        backArrowButton()
    }

    private fun initData(){
        val newsData = intent.getParcelableExtra<NewsEntity>(EXTRA_NEWS)
        newsData?.let { detailNewsViewModel.setNewsData(it) }
    }

    private fun loadDataToView() {
        detailNewsViewModel.getNewsDetail.observe(this@DetailNewsActivity, Observer {
            binding.detailNewsData.newsData = it
            Glide.with(this@DetailNewsActivity).load(it.imageUrl).into(binding.imgNews)
        })
    }

    private fun backArrowButton() {
        binding.toolbar.setNavigationOnClickListener { finish() }
    }
}
