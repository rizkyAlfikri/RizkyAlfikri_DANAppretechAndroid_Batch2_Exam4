package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.MainRepository
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.di.Injection
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.ui.detail.DetailNewsViewModel
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.ui.login.LoginViewModel
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.ui.main.MainViewModel

class ViewModelFactory private constructor(private val mainRepository: MainRepository)
    : ViewModelProvider.NewInstanceFactory(){

    companion object{
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory? {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java){
                    if (INSTANCE == null) {
                        INSTANCE = ViewModelFactory(Injection.provideResource(application))
                    }
                }
            }

            return INSTANCE
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{

            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(mainRepository) as T

            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(mainRepository) as T

            else -> throw IllegalArgumentException("Unknown ViewModel Class ${modelClass::class.java.simpleName}")
        }
    }
}