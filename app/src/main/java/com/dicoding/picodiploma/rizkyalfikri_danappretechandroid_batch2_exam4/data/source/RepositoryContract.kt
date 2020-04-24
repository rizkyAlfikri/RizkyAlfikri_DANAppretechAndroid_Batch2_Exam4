package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.local.entity.NewsEntity
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.remote.ApiResponse
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.remote.response.LoginResponse
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.remote.response.ProfileRequest
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.vo.Resource

interface RepositoryContract {
    fun getAllData(forceRefresh: Boolean): LiveData<Resource<PagedList<NewsEntity>>>

    fun loginRequestData(profileRequest: ProfileRequest): LiveData<ApiResponse<LoginResponse>>
}