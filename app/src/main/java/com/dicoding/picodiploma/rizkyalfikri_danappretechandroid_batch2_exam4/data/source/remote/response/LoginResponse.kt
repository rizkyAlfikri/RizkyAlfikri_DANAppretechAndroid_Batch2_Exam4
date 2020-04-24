package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status")
    var status: String,
    @SerializedName("code")
    var code: Int,
    @SerializedName("message")
    var message: String
)