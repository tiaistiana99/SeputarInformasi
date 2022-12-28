package com.kotlin.tiaistiana.repository.model.Berita

import com.google.gson.annotations.SerializedName

// TODO 4 : CLASSS DATA
data class NewsSource(
    @SerializedName("status") var status: String = "",
    @SerializedName("source") var source: String = "",
    @SerializedName("sortBy") var sortBy: String = "",
    @SerializedName("articles") var articles: List<News> = emptyList()
)