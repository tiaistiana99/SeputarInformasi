package com.kotlin.tiaistiana.ui.berita

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kotlin.tiaistiana.repository.api.network.Resource
import com.kotlin.tiaistiana.repository.model.Berita.News
import com.kotlin.tiaistiana.repository.repo.Berita.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
/**
 * Penampung untuk data terkait [Berita] untuk ditampilkan di UI.
 */
@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    // todo 2 : Pada bagian ini merupakan bagian view model yang digunakan unuk menmapilkan tampilan bagian berita

    /**
     * Memuat artikel berita dari internet dan database
     */
    private fun newsArticles(countryKey: String): LiveData<Resource<List<News>?>> =
        newsRepository.getNewsArticles(countryKey)


    fun getNewsArticles(countryKey: String) = newsArticles(countryKey)

    /**
     * Memuat artikel berita dari internet saja
     */

    private fun newsArticlesFromOnlyServer(countryKey: String) =
        newsRepository.getNewsArticlesFromServerOnly(countryKey)

    fun getNewsArticlesFromServer(countryKey: String) = newsArticlesFromOnlyServer(countryKey)

}