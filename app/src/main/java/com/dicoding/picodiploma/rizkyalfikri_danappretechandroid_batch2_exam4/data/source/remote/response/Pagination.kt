package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("limit")
    var limit: Int,
    @SerializedName("page")
    var page: Int,
    @SerializedName("total")
    var total: Int
)