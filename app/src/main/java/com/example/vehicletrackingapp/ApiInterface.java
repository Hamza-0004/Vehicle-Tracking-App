package com.example.vehicletrackingapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("email/drop-time/all")
    Call<Pojo>getData();

}
