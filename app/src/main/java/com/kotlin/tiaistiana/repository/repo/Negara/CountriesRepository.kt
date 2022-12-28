package com.kotlin.tiaistiana.repository.repo.Negara

import android.content.Context
import com.kotlin.tiaistiana.repository.db.Negara.negaraDao
import com.kotlin.tiaistiana.repository.model.Negara.Country
import com.kotlin.tiaistiana.utils.CountryNameMapping
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
// TODO 6 : Repository

/**
 * Repositori mengabstraksi logika pengambilan data dan mempertahankannya
 * offline. Mereka adalah sumber data sebagai satu-satunya sumber kebenaran.
 *
 */

@Singleton
class CountriesRepository @Inject constructor(
    private val negaraDao: negaraDao,
    @ApplicationContext val context: Context
) {

    /**
     * Ambil artikel berita dari database jika ada lagi yang diambil dari web
     * dan pertahankan di database
     */
    suspend fun getCountries(): List<Country> {
        withContext(Dispatchers.IO) {
            val list: List<String>? = getListFromAssets()
            val listOfCountries = ArrayList<Country>()
            list?.forEach { item ->
                val country = Country().apply {
                    countryName = item
                    displayName = getDisplayName(item)
                    countryFagUrl = getFlagUrl(item)
                    countryKey = CountryNameMapping.getCountryKey(item)
                }
                listOfCountries.add(country)
            }
            negaraDao.deleteAllCountries()
            negaraDao.insertCountries(listOfCountries)
        }

        return negaraDao.getCountries()
    }


    private suspend fun getListFromAssets(): List<String>? = withContext(Dispatchers.IO) {
        val asList = context.assets.list("countries")?.asList<String>()
        asList
    }


    private fun getDisplayName(name: String): String =
        name.replace("_", " ").replace(".png", "")


    private fun getFlagUrl(name: String): String = "file:///android_asset/countries/$name"

}