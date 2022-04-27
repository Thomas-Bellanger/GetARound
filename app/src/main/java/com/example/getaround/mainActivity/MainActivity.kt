package com.example.getaround.mainActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.getaround.R
import com.example.getaround.databinding.ActivityMainBinding
import com.example.getaround.detailActivity.DetailActivity
import com.example.getaround.mainActivity.adapter.CarsRecyclerViewAdapter
import com.example.getaround.mainActivity.viewModel.MainActivityViewModel
import com.example.getaround.model.CarsModel
import com.example.getaround.utils.ItemClickSupport

class MainActivity : AppCompatActivity() {
    private val viewModel = MainActivityViewModel.getInstance()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel?.getCars()
        viewModel?.context = this
        viewModel?.configureRecyclerView(binding, this)
        viewModel?.carsList?.observe(this, this::initCarsList)
    }

    private fun initCarsList(list: List<CarsModel>){
        viewModel?.carRecyclerView?.adapter = CarsRecyclerViewAdapter(list)
        configureOnClickRecyclerView(list)
    }

    // Configure item click on RecyclerView
    private fun configureOnClickRecyclerView(cars: List<CarsModel>) {
        ItemClickSupport.addTo(viewModel?.carRecyclerView!!, R.layout.car_item)
            .setOnItemClickListener { _, position, _ ->
                viewModel.car = cars[position]
                val intent = Intent(this, DetailActivity::class.java)
                startActivity(intent)
            }
    }

}