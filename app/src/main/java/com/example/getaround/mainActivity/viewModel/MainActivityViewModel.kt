package com.example.getaround.mainActivity.viewModel

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.getaround.R
import com.example.getaround.databinding.ActivityMainBinding
import com.example.getaround.domain.repository.CarsRepository
import com.example.getaround.model.CarsModel
import java.security.AccessController.getContext

class MainActivityViewModel : CarsRepository.Callbacks{
    private val carsRepository = CarsRepository.getInstance()
    var carsList: MutableLiveData<List<CarsModel>> =
        MutableLiveData<List<CarsModel>>()
    var carRecyclerView:RecyclerView?=null
    var context:Context? = null
    var car:CarsModel? = null

    //call the Api
    fun getCars(){
        carsRepository?.getCars(this)
    }

    fun configureRecyclerView(binding: ActivityMainBinding, context: Context){
        carRecyclerView = binding.carRecyclerView
        carRecyclerView?.layoutManager = LinearLayoutManager(context)
        carRecyclerView?.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    fun getStars(star1: ImageView, star2: ImageView, star3: ImageView, star4: ImageView, star5: ImageView, rate:Double){
        val halfStar: Drawable = context?.getDrawable(R.drawable.baseline_star_half_amber_400_24dp)!!
        val emptyStar: Drawable = context?.getDrawable(R.drawable.baseline_star_border_amber_400_24dp)!!
        when {
            rate == 5.0 ->{
            }
            rate > 4.5 ->{
                star5.setImageDrawable(halfStar)
            }
            rate > 4 ->{
                star5.setImageDrawable(emptyStar)
            }
            rate > 3.5 ->{
                star4.setImageDrawable(halfStar)
                star5.setImageDrawable(emptyStar)
            }
            rate > 3 ->{
                star4.setImageDrawable(emptyStar)
                star5.setImageDrawable(emptyStar)
            }

            rate > 2.5 ->{
                star3.setImageDrawable(halfStar)
                star4.setImageDrawable(emptyStar)
                star5.setImageDrawable(emptyStar)
            }
            rate > 2 ->{
                star3.setImageDrawable(emptyStar)
                star4.setImageDrawable(emptyStar)
                star5.setImageDrawable(emptyStar)
            }
            rate > 1.5 ->{
                star2.setImageDrawable(halfStar)
                star3.setImageDrawable(emptyStar)
                star4.setImageDrawable(emptyStar)
                star5.setImageDrawable(emptyStar)
            }
            rate > 1 -> {
                star2.setImageDrawable(emptyStar)
                star3.setImageDrawable(emptyStar)
                star4.setImageDrawable(emptyStar)
                star5.setImageDrawable(emptyStar)
            }
            rate > 0.5 -> {
                star1.setImageDrawable(halfStar)
                star2.setImageDrawable(emptyStar)
                star3.setImageDrawable(emptyStar)
                star4.setImageDrawable(emptyStar)
                star5.setImageDrawable(emptyStar)
            }
        }
    }

    //response of the Api Call
    override fun onResponse(response: List<CarsModel>?) {
        carsList.value = response
    }

    override fun onFailure() {
    }
    //instance
    companion object {
        @Volatile
        private var instance: MainActivityViewModel? = null
        fun getInstance(): MainActivityViewModel? {
            val result = instance
            instance = instance?.let { result } ?: MainActivityViewModel()
            return instance
        }
    }
}