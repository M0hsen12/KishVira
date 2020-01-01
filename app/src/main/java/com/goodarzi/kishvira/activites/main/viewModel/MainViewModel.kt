package com.goodarzi.kishvira.activites.main.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.goodarzi.kishvira.network.main.MainApi
import javax.inject.Inject

class MainViewModel @Inject constructor(var MainApi: MainApi) : ViewModel() {
    init {
        Log.e("eee", "main viewModel is working")
    }


    // can get array list from server , and that why we inject main api into this view model
    fun getInSurranceType(): Array<String> {
        return arrayOf("انتخاب کنید ...", "آتش سوزی", "درمان")
    }
}