package com.example.getaround.detailActivity.viewModel

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
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
                binding.carStar1,
                binding.carStar2,
                binding.carStar3,
                binding.carStar4,
                binding.carStar5,
                it
            )
        }
        car.owner?.rating?.average?.let {
            getStars(
                binding.ownerStar1,
                binding.ownerStar2,
                binding.ownerStar3,
                binding.ownerStar4,
                binding.ownerStar5,
                it
            )
        }
        binding.detailOwnerName.text = car.owner?.name
    }

    //get the rating and change the stars icon
    fun getStars(
        star1: ImageView,
        star2: ImageView,
        star3: ImageView,
        star4: ImageView,
        star5: ImageView,
        rate: Double
    ) {
        val halfStar: Drawable =
            context?.getDrawable(R.drawable.baseline_star_half_amber_400_24dp)!!
        val emptyStar: Drawable =
            context?.getDrawable(R.drawable.baseline_star_border_amber_400_24dp)!!
        when {
            rate == 5.0 -> {
            }
            rate > 4.5 -> {
                star5.setImageDrawable(halfStar)
            }
            rate > 4 -> {
                star5.setImageDrawable(emptyStar)
            }
            rate > 3.5 -> {
                star4.setImageDrawable(halfStar)
                star5.setImageDrawable(emptyStar)
            }
            rate > 3 -> {
                star4.setImageDrawable(emptyStar)
                star5.setImageDrawable(emptyStar)
            }

            rate > 2.5 -> {
                star3.setImageDrawable(halfStar)
                star4.setImageDrawable(emptyStar)
                star5.setImageDrawable(emptyStar)
            }
            rate > 2 -> {
                star3.setImageDrawable(emptyStar)
                star4.setImageDrawable(emptyStar)
                star5.setImageDrawable(emptyStar)
            }
            rate > 1.5 -> {
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

    //instance
    companion object {
        @Volatile
        private var instance: DetailViewModel? = null
        fun getInstance(): DetailViewModel? {
            val result = instance
            instance = instance?.let { result } ?: DetailViewModel()
            return instance
        }
    }
}