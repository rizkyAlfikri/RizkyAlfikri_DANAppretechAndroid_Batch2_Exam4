package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.local

import androidx.paging.DataSource
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.local.entity.NewsEntity
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.local.room.NewsDao

class LocalRepository private constructor(private val newsDao: NewsDao){

    companion object{
        private var INSTANCE: LocalRepository? = null

        fun getInstance(newsDao: NewsDao): LocalRepository? {
            if (INSTANCE == null) {
                INSTANCE = LocalRepository(newsDao)
            }

            return INSTANCE
        }
    }

    fun getAllData(): DataSource.Factory<Int, NewsEntity> {
        return newsDao.getAllData()
    }

    fun insertAllData(listNews: List<NewsEntity>) = newsDao.insertAllData(listNews)

    fun deleteAllData() = newsDao.deleteAll()

}