package com.example.finunsize.presentation.activity;

import persistence.models.CompanyModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("https://finunsize.onrender.com/company")
    Call<Void> cadastrarEmpresa(@Body CompanyModel companyModel);
}
