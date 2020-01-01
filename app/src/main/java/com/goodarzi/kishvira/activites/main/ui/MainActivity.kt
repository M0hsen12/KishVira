package com.goodarzi.kishvira.activites.main.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import com.goodarzi.kishvira.R
import com.goodarzi.kishvira.activites.treatment.ui.TreatmentActivity
import com.goodarzi.kishvira.activites.fire.ui.FireActivity
import com.goodarzi.kishvira.adapters.CustomDropDownAdapter
import com.goodarzi.kishvira.activites.main.viewModel.MainViewModel
import com.goodarzi.kishvira.viewModel.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var mainViewModel: MainViewModel
    var isActResumed = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isActResumed = false
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this, providerFactory).get(MainViewModel::class.java)
        initSpinner()

    }

    private fun initSpinner() {
        spinner.adapter = CustomDropDownAdapter(this, mainViewModel.getInSurranceType())
        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0) {
                    when (position) {
                        1 -> {
                            startActivity(Intent(this@MainActivity, FireActivity::class.java))
                        }
                        2 -> {
                            startActivity(Intent(this@MainActivity, TreatmentActivity::class.java))
                        }
                    }

                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }


    }

    override fun onResume() {
        super.onResume()
        if (isActResumed) {
            spinner.setSelection(0)
        } else isActResumed = true
    }

}

