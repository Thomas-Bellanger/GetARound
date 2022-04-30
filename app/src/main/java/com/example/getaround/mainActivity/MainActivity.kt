package com.example.getaround.mainActivity

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.getaround.R
import com.example.getaround.databinding.ActivityMainBinding
import com.example.getaround.detailActivity.DetailActivity
import com.example.getaround.mainActivity.adapter.CarsRecyclerViewAdapter
import com.example.getaround.mainActivity.viewModel.MainActivityViewModel
import com.example.getaround.model.CarsModel
import com.example.getaround.utils.ItemClickSupport


class MainActivity : AppCompatActivity() {
    private val viewModel = MainActivityViewModel.getInstance()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel?.context = this
        viewModel?.configureRecyclerView(binding, this)
        isInternetAvailable()
        viewModel?.carsList?.observe(this, this::initCarsList)
        binding.retryBtn.setOnClickListener { isInternetAvailable()
        }
    }

    //init the recyclerview
    private fun initCarsList(list: List<CarsModel>) {
        binding.progressBar.visibility = GONE
        binding.noInfoText.visibility = GONE
        binding.retryBtn.visibility = GONE
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

    private fun isInternetAvailable() {
        binding.progressBar.visibility = VISIBLE
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork
            if(networkCapabilities!=null){
                viewModel?.getCars(this)
            }
            else{
                binding.progressBar.visibility = GONE
                Toast.makeText(this,getString(R.string.error_internet),Toast.LENGTH_SHORT).show()
            }
        }
    }
}