package com.goodarzi.kishvira.activites.fire.ui

import android.annotation.TargetApi
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.goodarzi.kishvira.R
import com.baoyachi.stepview.VerticalStepView
import com.goodarzi.kishvira.activites.fire.viewModel.FireViewModel
import com.goodarzi.kishvira.adapters.StepIndicatorAdapter
import com.goodarzi.kishvira.model.fire.Fire
import com.goodarzi.kishvira.viewModel.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_fire.*
import javax.inject.Inject


class FireActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var apiKey: String

    lateinit var fireViewModel: FireViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fire)
        showProgressBar(true)
        fireViewModel = ViewModelProvider(this, providerFactory).get(FireViewModel::class.java)
        val url = "FireDamageRequest/Tracking?requestId=3&nationalCode=2279487799"
        fireViewModel.callApiMethod(url, 1, apiKey)
        fireDetail()
        listenToError()
        fire_finish.setOnClickListener { finish() }

    }

    private fun listenToError() {
        fireViewModel.error.observe(this, Observer {
            if (it != null) {
                showProgressBar(false)
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun fireDetail() {
        fireViewModel.fireDetail.observe(this, Observer {
            if (it != null) {
                showProgressBar(false)
                initSteps(it)
                initView(it)
            }
        })
    }

    private fun initView(fire: Fire) {
        cure_created_date.text = fire.data.policyNumber
        cure_final_price.text = fire.data.damageDate
        cure_city.text = fire.data.cityName
        cure_address.text = fire.data.address

    }

    private fun initSteps(fire: Fire) {
        steps_rv.adapter = StepIndicatorAdapter(this, fire)
        steps_rv.layoutManager = LinearLayoutManager(this)
    }


    fun showProgressBar(isLoading: Boolean) {
        if (isLoading)
            fire_progressbar.visibility = View.VISIBLE
        else fire_progressbar.visibility = View.GONE
    }

}

