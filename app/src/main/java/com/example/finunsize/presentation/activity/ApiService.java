package com.example.finunsize.presentation.activity;

<<<<<<< HEAD
import persistence.models.UserModel;
=======
import persistence.models.CompanyModel;
>>>>>>> bd9709ce993a7d804ac028a8c5d510ef86a7034d
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

<<<<<<< HEAD
    @POST("https://finunsize.onrender.com/user")
    Call<Void> registerUser(@Body UserModel userModel);
}
=======
    @POST("https://finunsize.onrender.com/company")
    Call<Void> cadastrarEmpresa(@Body CompanyModel companyModel);
}
>>>>>>> bd9709ce993a7d804ac028a8c5d510ef86a7034d
