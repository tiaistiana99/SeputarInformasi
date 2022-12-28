package com.kotlin.tiaistiana.ui.berita

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.tiaistiana.base.BaseActivity
import com.kotlin.tiaistiana.databinding.ActivityNewsArticlesBinding
import com.kotlin.tiaistiana.utils.extensions.load
import com.kotlin.tiaistiana.utils.extensions.toast
import dagger.hilt.android.AndroidEntryPoint
// todo 1 : pada bagian ini merupakan bagian view atau data binding

@AndroidEntryPoint
class NewsActivity : BaseActivity<ActivityNewsArticlesBinding>() {

    companion object {
        const val KEY_COUNTRY_SHORT_KEY: String = "COUNTRY_SHORT_KEY"
    }

    private lateinit var adapter: NewsAdapter

    private val newsArticleViewModel: NewsViewModel by viewModels()

    /**
     * Create Binding
     */

    override fun createBinding(): ActivityNewsArticlesBinding {
        return ActivityNewsArticlesBinding.inflate(layoutInflater)
    }

    /**
     * Saat Membuat Aktivitas
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.newsList.setEmptyView(binding.progressView.progressView)
        binding.newsList.setProgressView(binding.emptyView.emptyView)

        adapter = NewsAdapter()
        adapter.onNewsClicked = {

        //TODO Item berita Anda klik dipanggil di sini
            // fungsi adapter onklik adalah ketika kita mengklik nya kita langsung ke halaman berita
        }

        binding.newsList.adapter = adapter
        binding.newsList.layoutManager = LinearLayoutManager(this)

        getNewsOfCountry(intent?.getStringExtra(KEY_COUNTRY_SHORT_KEY) ?: "")
    }

    /**
     * Kita mendapatkan berita negara menggunakan Network & DB Bound Resource
     * Lalu kita Mengamati perubahan data dari DB dan Jaringan Keduanya
     */

    private fun getNewsOfCountry(countryKey: String) {
        newsArticleViewModel.getNewsArticles(countryKey).observe(this) {
            when {
                it.status.isLoading() -> {
                    binding.newsList.showProgressView()
                }
                it.status.isSuccessful() -> {
                    it?.load(binding.newsList) { news ->
                        adapter.replaceItems(news ?: emptyList())
                    }
                }
                it.status.isError() -> {
                    toast(it.errorMessage ?: "Something went wrong.")
                    finish()
                    // bisa dibilang pada bagian ini menampilkan activitas berita yang dimuat
                }
            }
        }
    }
}
