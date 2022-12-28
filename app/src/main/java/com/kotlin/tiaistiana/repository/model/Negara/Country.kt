package com.kotlin.tiaistiana.repository.model.Negara

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
// TODO 9 : Entity
// digunakan untuk menentukan nama negara,tampilan nama,kuncy negara,dan url

@Entity(tableName = "countries_table")
data class Country(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @SerializedName("countryName") var countryName: String? = null,
    @SerializedName("displayName") var displayName: String? = null,
    @SerializedName("countryKey") var countryKey: String? = null,
    @SerializedName("countryFagUrl") var countryFagUrl: String? = null
)