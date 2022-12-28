package com.kotlin.tiaistiana.di.modules

import android.content.Context
import androidx.room.Room
import com.kotlin.tiaistiana.BuildConfig
import com.kotlin.tiaistiana.repository.api.ApiServices
import com.kotlin.tiaistiana.repository.api.network.LiveDataCallAdapterFactoryForRetrofit
import com.kotlin.tiaistiana.repository.db.AppDatabase
import com.kotlin.tiaistiana.repository.db.Negara.negaraDao
import com.kotlin.tiaistiana.repository.db.berita.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    /**
     * Provides ApiServices client for Retrofit
     */
    @Singleton
    @Provides
    fun provideNewsService(): ApiServices {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactoryForRetrofit())
            .build()
            .create(ApiServices::class.java)
    }


    /**
     * Provides app AppDatabase
     */
    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "news-db")
            .fallbackToDestructiveMigration().build()


    /**
     * Provides NewsArticlesDao an object to access NewsArticles table from Database
     */
    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase): NewsDao = db.newsArticlesDao()

    /**
     * Provides CountriesDao an object to access Countries table from Database
     */
    @Singleton
    @Provides
    fun provideCountriesDao(db: AppDatabase): negaraDao = db.countriesDao()
}
