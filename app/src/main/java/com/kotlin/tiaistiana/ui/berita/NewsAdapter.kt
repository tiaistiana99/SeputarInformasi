package com.kotlin.tiaistiana.ui.berita

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.tiaistiana.databinding.RowNewsArticleBinding
import com.kotlin.tiaistiana.repository.model.Berita.News
import com.kotlin.tiaistiana.utils.load


/**
 * Adaptor yang digunakan untuk menampilkan berita dalam daftar.
 */
class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    /**
     * Daftar artikel berita
     */
    // TODO 3 : ini merupakan bagian adapter yang digunakan untuk menampilkan berita dalam bentuk recycler view

    private var newsArticles: List<News> = emptyList()

    // Adapter difungsikan atau diguanakan untuk menampilkan data dalam bentuk daftar seperti recyler view nah terutama
    // pada adapter berita ini digunakan untuk menmapilkan berita dalam bentuk daftar

    var onNewsClicked: ((News) -> Unit)? = null

    /**
     * Mengembangkan tampilan
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val itemBinding =
            RowNewsArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsHolder(itemBinding)
    }


    /**
     * menampilkan tampilan dengan data
     */
    override fun onBindViewHolder(newsHolder: NewsHolder, position: Int) =
        newsHolder.bind(newsArticles[position])

    /**
     * Jumlah item dalam daftar untuk ditampilkan
     */
    override fun getItemCount() = newsArticles.size

    /**
     * Lihat Pola Pemegang
     */
    inner class NewsHolder(private val itemBinding: RowNewsArticleBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(newsArticle: News) = with(itemView) {
            itemBinding.ivNewsImage.load(itemBinding.root.context, newsArticle.urlToImage ?: "")
            itemBinding.root.setOnClickListener {
                onNewsClicked?.invoke(newsArticle)
            }

        }
    }

    /**
     * Tukar fungsi untuk mengatur data baru saat memperbarui
     */

    @SuppressLint("NotifyDataSetChanged")
    fun replaceItems(items: List<News>) {
        newsArticles = items
        notifyDataSetChanged()
    }
}