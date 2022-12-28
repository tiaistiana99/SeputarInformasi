package com.kotlin.tiaistiana.ui.negara

import android.os.Bundle
import com.kotlin.tiaistiana.R
import com.kotlin.tiaistiana.base.BaseActivity
import com.kotlin.tiaistiana.databinding.ActivityCountiresBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountriesActivity : BaseActivity<ActivityCountiresBinding>() {

    // TODO 1 : pada bagian ini merupakan bagian view atau data binding
    /**
     * membuat Binding
     */
    override fun createBinding(): ActivityCountiresBinding =
        ActivityCountiresBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, CountriesFragment.newInstance(1))
                .commit()
        }

       // Pada file countries activity ini difungsikan untuk menmapilkan aktivitas daftar negara
    }
}
