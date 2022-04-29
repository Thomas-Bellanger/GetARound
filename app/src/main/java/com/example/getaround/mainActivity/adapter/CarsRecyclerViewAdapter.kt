package com.example.getaround.mainActivity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.getaround.R
import com.example.getaround.databinding.CarItemBinding
import com.example.getaround.mainActivity.viewModel.MainActivityViewModel
import com.example.getaround.model.CarsModel

class CarsRecyclerViewAdapter(private val cars: List<CarsModel>) :
    RecyclerView.Adapter<CarsRecyclerViewAdapter.ViewHolder>() {

    private var mContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.car_item, parent, false)
        mContext = view.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.update(cars[position])
    }

    override fun getItemCount(): Int {
        return cars.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var binding: CarItemBinding = CarItemBinding.bind(itemView)
        private val viewModel = MainActivityViewModel.getInstance()

        //get info from the car and change the text with it
        fun update(car: CarsModel) {
            val rate = car.rating?.average
            (car.brand.toString() + " " + car.model.toString()).also {
                binding.carItemName.text = it
            }
            (car.pricePerDay.toString() + "€" + "/" + viewModel?.context?.getString(R.string.day)).also {
                binding.carItemPrice.text = it
            }
            Glide.with(binding.carItemImageView.context)
                .load(car.pictureUrl)
                .apply(RequestOptions.centerCropTransform())
                .into(binding.carItemImageView)
            ("(" + car.rating?.count + ")").also { binding.carItemVoteNumber.text = it }
            if (rate != null) {
                binding.ratingBarItem.rating = rate.toFloat()
            }
        }
    }
}