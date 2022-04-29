package com.example.getaround.detailActivity.viewModel

import android.annotation.SuppressLint
import android.content.Context
import android.widget.RatingBar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.getaround.R
import com.example.getaround.databinding.ActivityDetailBinding
import com.example.getaround.model.CarsModel

class DetailViewModel {
    var context: Context? = null

    //get info from the car and change the text with it
    fun changeText(car: CarsModel, binding: ActivityDetailBinding) {
        ("(" + car.rating?.count.toString() + ")").also { binding.carVoteNumber.text = it }
        (car.brand.toString() + " " + car.model.toString()).also { binding.carModel.text = it }
        (car.pricePerDay.toString() + "€/" + context?.getString(R.string.day)).also {
            binding.carPrice.text = it
        }
        Glide.with(binding.detailImage.context)
            .load(car.pictureUrl)
            .apply(RequestOptions.centerCropTransform())
            .into(binding.detailImage)
        Glide.with(binding.detailAvatar.context)
            .load(car.owner?.pictureUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(binding.detailAvatar)
        car.rating?.average?.let {
            getStars(
                binding.ratingCar,
                it
            )
        }
        car.owner?.rating?.average?.let {
            getStars(
                binding.ratingOwner, it
            )
        }
        binding.detailOwnerName.text = car.owner?.name
    }

    //get the rating and change the stars icon
    private fun getStars(
        ratingBar: RatingBar,
        rate: Double
    ) {
        ratingBar.rating = rate.toFloat()
    }

    //instance
    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: DetailViewModel? = null
        fun getInstance(): DetailViewModel? {
            val result = instance
            instance = instance?.let { result } ?: DetailViewModel()
            return instance
        }
    }
}