package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.local.entity.NewsEntity

class DetailNewsViewModel : ViewModel() {

    private val _getNewsDetail = MutableLiveData<NewsEntity>()
    val getNewsDetail
        get() = _getNewsDetail

    fun setNewsData(data: NewsEntity) {
        _getNewsDetail.value = data
    }

}