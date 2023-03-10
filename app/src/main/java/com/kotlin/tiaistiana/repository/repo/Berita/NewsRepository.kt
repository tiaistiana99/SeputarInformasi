package com.kotlin.tiaistiana.repository.repo.Berita

import android.content.Context
import androidx.lifecycle.LiveData
import com.kotlin.tiaistiana.BuildConfig
import com.kotlin.tiaistiana.app.AppExecutors
import com.kotlin.tiaistiana.repository.api.ApiServices
import com.kotlin.tiaistiana.repository.api.network.NetworkAndDBBoundResource
import com.kotlin.tiaistiana.repository.api.network.NetworkResource
import com.kotlin.tiaistiana.repository.api.network.Resource
import com.kotlin.tiaistiana.repository.db.berita.NewsDao
import com.kotlin.tiaistiana.repository.model.Berita.News
import com.kotlin.tiaistiana.repository.model.Berita.NewsSource
import com.kotlin.tiaistiana.utils.ConnectivityUtil
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

// TODO 6 : repostory

/**
 * Repositori mengabstraksi logika pengambilan data dan mempertahankannya
 * luring. Mereka adalah sumber data sebagai satu-satunya sumber kebenaran.
 *
 */

// Pada aplikasi ini bisa dibilang tidak dapat bisa di akses kalau tidak onlien

@Singleton
class NewsRepository @Inject constructor(
    private val newsDao: NewsDao,
    private val apiServices: ApiServices,
    @ApplicationContext val context: Context,
    private val appExecutors: AppExecutors = AppExecutors()
) {
    /**
     * Ambil artikel berita dari database jika ada lagi yang diambil dari web
     * dan pertahankan di database
     */

    fun getNewsArticles(countryShortKey: String): LiveData<Resource<List<News>?>> {
        val data = HashMap<String, String>()
        data["country"] = countryShortKey
        data["apiKey"] = BuildConfig.NEWS_API_KEY

        return object : NetworkAndDBBoundResource<List<News>, NewsSource>(appExecutors) {
            override fun saveCallResult(item: NewsSource) {
                if (item.articles.isNotEmpty()) {
                    newsDao.deleteAllArticles()
                    newsDao.insertArticles(item.articles)
                }
            }

            override fun shouldFetch(data: List<News>?) =
                (ConnectivityUtil.isConnected(context))

            override fun loadFromDb() = newsDao.getNewsArticles()
                //menghubungkan ke database

            override fun createCall() =
                apiServices.getNewsSource(data)

        }.asLiveData()
    }
    /**
     * Ambil artikel berita dari database jika ada lagi yang diambil dari web
     * dan pertahankan di database
     * LiveData<Sumberdaya<SumberBerita>>
     */
    fun getNewsArticlesFromServerOnly(countryShortKey: String):
            LiveData<Resource<NewsSource>> {

        val data = HashMap<String, String>()
        data["country"] = countryShortKey
        data["apiKey"] = BuildConfig.NEWS_API_KEY

        return object : NetworkResource<NewsSource>() {
            override fun createCall(): LiveData<Resource<NewsSource>> {
                return apiServices.getNewsSource(data)
            }

        }.asLiveData()
    }

}