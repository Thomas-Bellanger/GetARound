package com.example.getaround.detailActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.getaround.R
import com.example.getaround.databinding.ActivityDetailBinding
import com.example.getaround.detailActivity.viewModel.DetailViewModel
import com.example.getaround.mainActivity.viewModel.MainActivityViewModel
import com.example.getaround.model.CarsModel

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    private val mainViewModel = MainActivityViewModel.getInstance()
    private val detailViewModel = DetailViewModel.getInstance()
    lateinit var car: CarsModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        car = mainViewModel?.car!!
        detailViewModel?.context = this
        setContentView(view)
        configureToolbar()
        detailViewModel?.changeText(car, binding)
    }

    //toolbar
    private fun configureToolbar() {
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val ab = supportActionBar
        ab!!.setDisplayHomeAsUpEnabled(true)
    }

}