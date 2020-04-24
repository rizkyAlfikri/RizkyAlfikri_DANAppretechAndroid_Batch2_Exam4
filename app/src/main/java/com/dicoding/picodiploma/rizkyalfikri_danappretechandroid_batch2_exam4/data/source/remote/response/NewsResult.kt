package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class NewsResult(
    @SerializedName("category")
    var category: String,
    @SerializedName("content")
    var content: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("image_url")
    var imageUrl: String,
    @SerializedName("title")
    var title: String
)