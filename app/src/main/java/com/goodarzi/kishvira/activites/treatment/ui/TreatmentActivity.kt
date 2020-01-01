package com.goodarzi.kishvira.activites.treatment.ui

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.goodarzi.kishvira.R
import com.goodarzi.kishvira.activites.treatment.viewModel.TreatmentViewModel
import com.goodarzi.kishvira.adapters.StepIndicatorAdapter
import com.goodarzi.kishvira.adapters.TreatmentAdapter
import com.goodarzi.kishvira.model.Treatment.Treatment
import com.goodarzi.kishvira.utilz.Interfaces
import com.goodarzi.kishvira.viewModel.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_cure.*
import javax.inject.Inject

class TreatmentActivity : DaggerAppCompatActivity(), Interfaces.getDialog {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var apiKey: String

    lateinit var treatmentViewModel: TreatmentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cure)
        showLoading(true)
        treatmentViewModel =
            ViewModelProvider(this, providerFactory).get(TreatmentViewModel::class.java)
        val url = "DamageTreatmentRequest/TrackingFile?fileId=18&nationalCode=1531197280"
        treatmentViewModel.callApiMethod(url, 1, apiKey, false)
        listenToError()
        getTreatmentDetail()
        cure_finish.setOnClickListener { finish() }
    }

    private fun showLoading(isVisible: Boolean) {

        if (isVisible)
            cure_progressBar.visibility = View.VISIBLE
        else cure_progressBar.visibility = View.GONE

    }

    private fun getTreatmentDetail() {
        treatmentViewModel.treatmentDetail.observe(this, Observer {
            if (it != null) {
                showLoading(false)
                loadTreatmentRv(it)
                initDetail(it)
            }
        })
    }

    private fun initDetail(treatment: Treatment) {

        cure_created_date.text = treatment.data.createDateTime
        cure_status.text = treatment.data.statusTitle
        if (treatment.data.finalCost == 0) cure_final_price.text = "تعیین نشده"
        else cure_final_price.text = treatment.data.finalCost.toString()

    }

    private fun loadTreatmentRv(treatment: Treatment) {
        val adapter = TreatmentAdapter(this)
        cure_rv.adapter = adapter
        cure_rv.layoutManager = LinearLayoutManager(this)
        adapter.submitList(treatment.data.requestsList)
    }


    private fun listenToError() {
        treatmentViewModel.error.observe(this, Observer {
            if (it != null) {
                showLoading(false)
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun setUpDialog(id: Int) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.cutsom_dialog)
        val title = dialog.findViewById(R.id.dialog_title) as TextView
        title.text = " تاریخچه در خواست شماره $id"
        val dialogRv = dialog.findViewById(R.id.dialog_rv) as RecyclerView
        val dialogBtn = dialog.findViewById(R.id.dialog_btn) as Button
        val dialogProgressBar = dialog.findViewById(R.id.dialog_probar) as ProgressBar
        initRv(dialogRv, dialogProgressBar)
        dialogBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun initRv(dialogRv: RecyclerView, dialogProgressBar: ProgressBar) {
        val url = "FireDamageRequest/Tracking?requestId=3&nationalCode=2279487799"
        treatmentViewModel.callApiMethod(url, 1, apiKey, true)
        treatmentViewModel.fireDetail.observe(this, Observer {
            if (it != null) {
                dialogProgressBar.visibility = View.GONE
                Log.e("qqq", "$it")
                dialogRv.adapter = StepIndicatorAdapter(this, it)
                dialogRv.layoutManager = LinearLayoutManager(this)
            }
        })
    }

}
