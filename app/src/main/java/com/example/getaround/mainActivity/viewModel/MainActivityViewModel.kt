package com.example.getaround.mainActivity.viewModel

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.getaround.R
import com.example.getaround.databinding.ActivityMainBinding
import com.example.getaround.domain.manager.CarsManager
import com.example.getaround.domain.repository.CarsRepository
import com.example.getaround.model.CarsModel

class MainActivityViewModel : CarsRepository.Callbacks {
    private val carsManager = CarsManager.getInstance()
    var carsList: MutableLiveData<List<CarsModel>> =
        MutableLiveData<List<CarsModel>>()
    var carRecyclerView: RecyclerView? = null
    var context: Context? = null
    private var mainActivity: Activity? = null
    var car: CarsModel? = null

    //call the Api
    fun getCars(activity: Activity) {
            mainActivity = activity
            carsManager?.getCars(this)
    }

    //recyclerview
    fun configureRecyclerView(binding: ActivityMainBinding, context: Context) {
        carRecyclerView = binding.carRecyclerView
        carRecyclerView?.layoutManager = LinearLayoutManager(context)
        carRecyclerView?.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    //response of the Api Call
    override fun onResponse(response: List<CarsModel>?) {
        carsList.value = response
    }

    //response of a failed Api call
    override fun onFailure() {
        Toast.makeText(mainActivity, mainActivity?.getString(R.string.error), Toast.LENGTH_SHORT)
            .show()
        carsList.value = mutableListOf()
    }

    //instance
    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: MainActivityViewModel? = null
        fun getInstance(): MainActivityViewModel? {
            val result = instance
            instance = instance?.let { result } ?: MainActivityViewModel()
            return instance
        }
    }
}