package com.example.finunsize.presentation.activity;

import java.util.List;

import integration.AuthRequest;
import integration.AuthResponse;
import persistence.models.CashierModel;
import persistence.models.ProductModel;

import persistence.models.UserModel;
import persistence.models.CompanyModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {

    @GET("https://finunsize.onrender.com/product/")
    Call<List<ProductModel>> getProductList(@Header("Authorization") String token);

    @GET("https://finunsize.onrender.com/cashier/")
    Call<List<CashierModel>> getCashierList(@Header("Authorization") String token);

    @POST("https://finunsize.onrender.com/user/signup")
    Call<Void> cadastrarUsuário(@Body UserModel userModel);

    @POST("https://finunsize.onrender.com/user/login")
    Call<AuthResponse> authenticateUser(@Body AuthRequest authRequest);

    @POST("https://finunsize.onrender.com/company/create")
    Call<Void> cadastrarEmpresa(@Body CompanyModel companyModel);
}

