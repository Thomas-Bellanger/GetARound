package com.example.getaround.utils;

import com.example.getaround.model.CarsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiService {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/drivy/jobs/master/mobile/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    // For research
    @GET("cars.json")
    Call<List<CarsModel>> getCars();
}
