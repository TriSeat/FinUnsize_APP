package com.example.finunsize.presentation.activity;

import persistence.models.UserModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("https://finunsize.onrender.com/user")
    Call<Void> registerUser(@Body UserModel userModel);
}