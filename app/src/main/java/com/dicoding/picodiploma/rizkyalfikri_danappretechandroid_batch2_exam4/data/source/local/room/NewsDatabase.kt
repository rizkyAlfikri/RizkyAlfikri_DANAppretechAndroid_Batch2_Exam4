package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.local.entity.NewsEntity

@Database(entities = [NewsEntity::class], exportSchema = false, version = 1)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao

    companion object {
        private var INSTANCE: NewsDatabase? = null

        fun getInstance(context: Context): NewsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    "news_database"
                ).fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()

                INSTANCE = instance
                return instance
            }
        }

        private val roomCallback = object :RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateFirstData(INSTANCE).execute()
            }
        }
    }
}