package com.example.simpleexpensemanager.api;

import com.example.simpleexpensemanager.database.PaymentModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestDataApi {

    @GET("/posts")
    Call<PaymentModel> getPaymentModel();
}
