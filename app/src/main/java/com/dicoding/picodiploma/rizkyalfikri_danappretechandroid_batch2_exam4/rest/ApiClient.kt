package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.rest

import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.utils.API_KEY
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.utils.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private var retrofit: Retrofit? = null

    private fun getClient(): Retrofit? {
        if (retrofit == null) {
            val requestInterceptor = Interceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", API_KEY)
                    .build()
                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()

            retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit
    }

    fun <T> createService(apiService: Class<T>): T? {
        return getClient()?.create(apiService)
    }
}
