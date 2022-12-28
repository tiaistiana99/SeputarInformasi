package com.kotlin.tiaistiana.ui.negara

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.kotlin.tiaistiana.base.BaseFragment
import com.kotlin.tiaistiana.databinding.FragmentCountryListBinding
import com.kotlin.tiaistiana.repository.model.Negara.Country
import com.kotlin.tiaistiana.ui.berita.NewsActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CountriesFragment : BaseFragment<FragmentCountryListBinding>() {
    Inject annotated fields will be provided by Dagger
     */
    private val countriesViewModel: CountriesViewModel by activityViewModels()

    private var columnCount = 1
    private lateinit var countriesAdapter: CountriesAdapter
    private var listOfCountries = ArrayList<Country>()

    /**
     * membuat Binding
     */
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCountryListBinding = FragmentCountryListBinding.inflate(inflater, container, false)

    /**
     *
     */
    companion object {
        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            CountriesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    /**
     *
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        load()
        observeCountries()
    }

    /**
     *
     */
    private fun load() {
        binding?.recyclerviewCountries?.layoutManager =
            if (columnCount <= 1) LinearLayoutManager(context) else GridLayoutManager(
                context,
                columnCount
            )
        countriesAdapter = CountriesAdapter(listOfCountries)
        binding?.recyclerviewCountries?.adapter = countriesAdapter

        countriesAdapter.onCountryClicked = { country ->
            val intent = Intent(context, NewsActivity::class.java)
            intent.putExtra(NewsActivity.KEY_COUNTRY_SHORT_KEY, country.countryKey)
            startActivity(intent)
        }
    }

    /**
     *
     */
    @SuppressLint("NotifyDataSetChanged")
    private fun observeCountries() {
        countriesViewModel.getCountries().observe(viewLifecycleOwner) {
            // You'll get list of countries here
            it?.let {
                listOfCountries.clear()
                listOfCountries.addAll(it)
                countriesAdapter.notifyDataSetChanged()
            }
        }
    }
}
