package com.kotlin.tiaistiana.repository.db.berita

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kotlin.tiaistiana.repository.model.Berita.News

// TODO 7 : ROOMDB

// Untuk mengkofigurasi database

/**
 * Abstrak akses ke database berita
 */

@Dao
interface NewsDao {
    // TODO 8 : DAO QUERY

    /**
     * Masukkan artikel ke dalam database
     */

    // Digunkan ketika kita memasukkan query maka akan langsung masuk ke database

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: List<News>): List<Long>

    /**
     * Dapatkan semua artikel dari database
     */

    @Query("SELECT * FROM news_table")
    fun getNewsArticles(): LiveData<List<News>>

    @Query("DELETE FROM news_table")
    abstract fun deleteAllArticles()
}