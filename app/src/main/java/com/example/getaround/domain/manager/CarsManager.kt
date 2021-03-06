package com.example.getaround.domain.manager

import com.example.getaround.domain.repository.CarsRepository

class CarsManager {

    private var carsRepository = CarsRepository.getInstance()

    //instance
    companion object {
        @Volatile
        private var instance: CarsManager? = null
        fun getInstance(): CarsManager? {
            val result = instance
            instance = instance?.let { result } ?: CarsManager()
            return instance
        }
    }

    //call the Api for cars
    fun getCars(callbacks: CarsRepository.Callbacks?) {
        carsRepository?.getCars(callbacks)
    }
}