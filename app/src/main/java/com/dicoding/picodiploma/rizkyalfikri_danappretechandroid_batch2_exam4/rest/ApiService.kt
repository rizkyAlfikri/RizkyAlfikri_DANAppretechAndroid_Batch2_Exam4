package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.rest

import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.remote.response.LoginResponse
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.remote.response.NewsResponse
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.remote.response.ProfileRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {

    @GET("api/news")
    fun getModelByApi(): Call<NewsResponse>

    @POST("api/login")
    fun login(@Body profile: ProfileRequest): Call<LoginResponse>
}