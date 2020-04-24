package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.local.LocalRepository
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.local.entity.NewsEntity
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.remote.ApiResponse
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.remote.RemoteRepository
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.remote.response.LoginResponse
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.remote.response.NewsResult
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.remote.response.ProfileRequest
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.vo.Resource

class MainRepository private constructor(
    private val local: LocalRepository,
    private val remote: RemoteRepository
) : RepositoryContract {

    companion object {
        private var INSTANCE: MainRepository? = null

        fun getInstance(local: LocalRepository, remote: RemoteRepository): MainRepository? {
            if (INSTANCE == null) {
                INSTANCE = MainRepository(local, remote)
            }

            return INSTANCE
        }
    }

    override fun getAllData(forceRefresh: Boolean): LiveData<Resource<PagedList<NewsEntity>>> {
        return object : NetworkBoundResource<PagedList<NewsEntity>, List<NewsResult>>() {
            override fun loadFromDB(): LiveData<PagedList<NewsEntity>> {
                return LivePagedListBuilder(local.getAllData(), 10).build()
            }

            override fun shouldFetch(data: PagedList<NewsEntity>?): Boolean {
                return forceRefresh
            }

            override fun createCall(): LiveData<ApiResponse<List<NewsResult>>> {
                return remote.getDataFromApi()
            }

            override fun saveCallResult(data: List<NewsResult>) {
                local.deleteAllData()

                val listNews = mutableListOf<NewsEntity>()
                data.forEach {
                    listNews.add(
                        NewsEntity(
                            null,
                            it.category,
                            it.content,
                            it.imageUrl,
                            it.title
                        )
                    )
                }

                local.insertAllData(listNews)
            }
        }.asLiveData()
    }

    override fun loginRequestData(profileRequest: ProfileRequest): LiveData<ApiResponse<LoginResponse>> {
        return remote.requestLogin(profileRequest)
    }


}