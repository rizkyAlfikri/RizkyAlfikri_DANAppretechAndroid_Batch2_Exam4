package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.vo

import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.vo.Status.*

class Resource<T>(val status: Status, val body: T?, val message: String?) {
    companion object{
        fun <T> success(body: T, message: String? = null): Resource<T> {
            return Resource(SUCCESS, body, null)
        }

        fun <T> error(body: T?, message: String?): Resource<T> {
            return Resource(ERROR, body, message)
        }

        fun <T> loading(body: T?, message: String?): Resource<T> {
            return Resource(LOADING, body, message)
        }
    }

}