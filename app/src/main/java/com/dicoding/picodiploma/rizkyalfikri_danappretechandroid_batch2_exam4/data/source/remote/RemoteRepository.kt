package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.remote.response.LoginResponse
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.remote.response.NewsResponse
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.remote.response.NewsResult
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.remote.response.ProfileRequest
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.rest.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository private constructor(private val apiService: ApiService) {

    companion object {
        private var INSTANCE: RemoteRepository? = null

        fun getInstance(apiService: ApiService): RemoteRepository? {
            if (INSTANCE == null) {
                INSTANCE = RemoteRepository(apiService)
            }

            return INSTANCE
        }
    }


    fun getDataFromApi(): LiveData<ApiResponse<List<NewsResult>>> {
        val liveModel = MutableLiveData<ApiResponse<List<NewsResult>>>()

        apiService.getModelByApi().enqueue(object : Callback<NewsResponse?> {
            override fun onFailure(call: Call<NewsResponse?>, t: Throwable) {
                liveModel.postValue(ApiResponse.error(t.message, null))
            }

            override fun onResponse(
                call: Call<NewsResponse?>,
                response: Response<NewsResponse?>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        liveModel.postValue(ApiResponse.success(it.data))
                    } ?: liveModel.postValue(ApiResponse.empty(response.message(), null))
                } else {
                    liveModel.postValue(ApiResponse.error(response.message(), null))
                }
            }
        })

        return liveModel
    }

    fun requestLogin(profileRequest: ProfileRequest): LiveData<ApiResponse<LoginResponse>> {
        val loginData = MutableLiveData<ApiResponse<LoginResponse>>()

        apiService.login(profileRequest).enqueue(object : Callback<LoginResponse?> {
            override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                loginData.postValue(ApiResponse.error(t.message, null))
            }

            override fun onResponse(
                call: Call<LoginResponse?>,
                response: Response<LoginResponse?>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { loginData.postValue(ApiResponse.success(it)) }
                        ?: loginData.postValue(ApiResponse.error(null, response.body()))
                } else {
                    loginData.postValue(ApiResponse.error(null, response.body()))
                }
            }
        })

        return loginData
    }
}