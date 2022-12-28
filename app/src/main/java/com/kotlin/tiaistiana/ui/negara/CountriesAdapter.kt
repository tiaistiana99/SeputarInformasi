package com.kotlin.tiaistiana.ui.negara

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.tiaistiana.databinding.RowCountryListBinding
import com.kotlin.tiaistiana.repository.model.Negara.Country
import com.kotlin.tiaistiana.utils.load

class CountriesAdapter(private val countries: List<Country>) :
    RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {
// TODO 3 :  ini merupakan bagian adapter yang digunakan untuk menampilkan negara dalam bentuk recycler view

    var onCountryClicked: ((Country) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            RowCountryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]
        holder.bindView(country)
    }


    override fun getItemCount(): Int = countries.size

    inner class ViewHolder(private val itemBinding: RowCountryListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        init {
            itemBinding.root.setOnClickListener {
                onCountryClicked?.invoke(countries[adapterPosition])
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + itemBinding.tvCountryName.text + "'"
        }

        fun bindView(country: Country) {
            itemBinding.tvCountryName.text = country.displayName
            itemBinding.ivCountryImage.load(
                itemBinding.root.context,
                Uri.parse(country.countryFagUrl).toString()
            // pada bagian adapater ini merupakan adapater yang memuat tampilan dari
                // daftar negara yang mana akan ditampilkan dalam bentuk recycler view
            )
        }
    }
}
