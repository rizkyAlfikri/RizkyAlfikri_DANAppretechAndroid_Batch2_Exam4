package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.remote.ApiResponse
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.remote.StatusResponse
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.vo.Resource
import kotlinx.coroutines.*

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result = MediatorLiveData<Resource<ResultType>>()
    private val exception = CoroutineExceptionHandler { _, throwable ->
        Log.e(NetworkBoundResource::class.java.simpleName, "${throwable.message}")
    }

    init {
        result.value = Resource.loading(null, null)

        @Suppress("LeakingThis")
        val dbSource = loadFromDB()

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    result.value = Resource.success(newData)
                }
            }
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        result.addSource(dbSource) { newData ->
            result.value = Resource.loading(newData, null)
        }

        result.addSource(apiResponse) { response ->
            result.removeSource(dbSource)
            result.removeSource(apiResponse)

            when (response.status) {
                StatusResponse.SUCCESS -> {
                    CoroutineScope(Dispatchers.IO + exception).launch {
                        response.body?.let { saveCallResult(it) }
                        withContext(Dispatchers.Main + exception) {
                            result.addSource(loadFromDB()) { newData ->
                                result.value = Resource.success(newData)
                            }
                        }
                    }
                }

                StatusResponse.EMPTY -> {
                    result.addSource(loadFromDB()) { newData ->
                        result.value = Resource.success(newData, response.message)
                    }
                }

                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        result.value = Resource.error(newData, response.message)
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> {
        return result
    }

    protected fun onFetchFailed() {}

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)
}