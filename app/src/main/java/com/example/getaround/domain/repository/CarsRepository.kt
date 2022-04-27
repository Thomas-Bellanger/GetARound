package com.example.getaround.domain.repository

import com.example.getaround.model.CarsModel
import com.example.getaround.utils.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.ref.WeakReference

class CarsRepository {
    //instance
    companion object {
        @Volatile
        private var instance: CarsRepository? = null
        fun getInstance(): CarsRepository? {
            val result = instance
            instance = instance?.let { result } ?: CarsRepository()
            return instance
        }
    }

    //call the Api for cars
    fun getCars(callbacks: Callbacks?) {
        //Create a weak reference to callback (avoid memory leaks)
        val callbacksWeakReference = WeakReference(callbacks)
        // get retrofit instance
        val apiService: ApiService =
            ApiService.retrofit.create(ApiService::class.java)
        // create the call on the API
        val liveDataCall: Call<List<CarsModel>> = apiService.cars
        // 2.4 - Start the call
        liveDataCall.enqueue(object : Callback<List<CarsModel>> {
            override fun onResponse(
                liveDataCall: Call<List<CarsModel>>,
                response: Response<List<CarsModel>>
            ) {
                // Call the proper callback used in controller (MainFragment)
                if (callbacksWeakReference.get() != null)
                    callbacksWeakReference.get()!!
                        .onResponse(response.body())
            }

            override fun onFailure(call: Call<List<CarsModel>>, t: Throwable) {
                // 2.5 - Call the proper callback used in controller (MainFragment)
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get()!!.onFailure()
            }
        })
    }

    //Callbacks used for Call
    interface Callbacks {
        fun onResponse(response: List<CarsModel>?)
        fun onFailure()
    }
}