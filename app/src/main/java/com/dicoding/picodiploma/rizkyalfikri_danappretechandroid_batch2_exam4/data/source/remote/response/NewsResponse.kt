package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("code")
    var code: Int,
    @SerializedName("data")
    var `data`: List<NewsResult>,
    @SerializedName("message")
    var message: String,
    @SerializedName("pagination")
    var pagination: Pagination,
    @SerializedName("status")
    var status: String
)