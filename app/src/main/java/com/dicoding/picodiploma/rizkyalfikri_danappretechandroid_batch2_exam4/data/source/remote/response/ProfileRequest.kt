package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ProfileRequest(
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String
)