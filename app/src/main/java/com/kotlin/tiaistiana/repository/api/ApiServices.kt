package com.kotlin.tiaistiana.repository.api

import androidx.lifecycle.LiveData
import com.kotlin.tiaistiana.repository.api.network.Resource
import com.kotlin.tiaistiana.repository.model.Berita.NewsSource
import retrofit2.http.GET
import retrofit2.http.QueryMap
// TODO 5 : Sintak berikut merupakan bagian API


interface ApiServices {
 /
    @GET("top-headlines")
    fun getNewsSource(@QueryMap options: Map<String, String>): LiveData<Resource<NewsSource>>

}
