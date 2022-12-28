package com.kotlin.tiaistiana.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kotlin.tiaistiana.repository.db.Negara.negaraDao
import com.kotlin.tiaistiana.repository.db.berita.NewsDao
import com.kotlin.tiaistiana.repository.model.Negara.Country
import com.kotlin.tiaistiana.repository.model.Berita.News

// TODO 7 : ROOM DB

/**
 * Basis Data Aplikasi
 * Tentukan semua entitas dan akses doa di sini/ Setiap entitas adalah tabel.
 */

@Database(entities = [News::class, Country::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsArticlesDao(): NewsDao

    abstract fun countriesDao(): negaraDao
}
