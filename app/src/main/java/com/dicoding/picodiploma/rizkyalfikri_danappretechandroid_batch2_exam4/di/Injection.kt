package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.di

import android.app.Application
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.MainRepository
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.local.LocalRepository
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.local.room.NewsDatabase
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.remote.RemoteRepository
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.rest.ApiClient
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.rest.ApiService

object Injection {

    fun provideResource(application: Application): MainRepository {
        val apiService = ApiClient.createService(ApiService::class.java)
        val database = NewsDatabase.getInstance(application)
        val remote = RemoteRepository.getInstance(apiService!!)
        val local = LocalRepository.getInstance(database.newsDao())

        return MainRepository.getInstance(local!!, remote!!)!!
    }
}