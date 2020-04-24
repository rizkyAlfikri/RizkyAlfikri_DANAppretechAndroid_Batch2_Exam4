package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.local.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.local.entity.NewsEntity

@Dao
interface NewsDao {

    @Query("SELECT * FROM tb_news ORDER by id")
    fun getAllData(): DataSource.Factory<Int, NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllData(listNews: List<NewsEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: NewsEntity)

    @Query("DELETE FROM tb_news")
    fun deleteAll()
}