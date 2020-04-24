package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.MainRepository

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val isRefresh = MutableLiveData(false)
    private var _getModelLiveData =
        Transformations.switchMap(isRefresh) { refresh ->
            mainRepository.getAllData(refresh)
        }

    val getModelLiveData
        get() = _getModelLiveData

    init {
        isRefresh.value = false
    }

    fun setRefresh(isRefreshing: Boolean) {
        isRefresh.value = isRefreshing
    }
}
