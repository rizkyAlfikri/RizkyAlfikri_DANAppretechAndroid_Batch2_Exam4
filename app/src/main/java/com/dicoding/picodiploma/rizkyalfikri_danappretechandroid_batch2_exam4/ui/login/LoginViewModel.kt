package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.MainRepository
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.remote.response.ProfileRequest

class LoginViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val profileLive = MutableLiveData<ProfileRequest>()
    private val _getLoginResponse =
        Transformations.switchMap(profileLive) {
            mainRepository.loginRequestData(it)
        }

    val getLoginRequest
        get() = _getLoginResponse

    fun setLoginRequest(data: ProfileRequest) {
        profileLive.value = data
    }
}