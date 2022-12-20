package com.example.simpleexpensemanager.api;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.simpleexpensemanager.database.PaymentModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitRepository {

    private static final String TAG = "RetrofitRepository";

    public RetrofitRepository() {

    }

    public MutableLiveData<PaymentModel> requestPaymentData() {
        RequestDataApi dataApi = RetrofitClientInstance
                .getRetrofitInstance()
                .create(RequestDataApi.class);

        Call<PaymentModel> sendRequest = dataApi.getPaymentModel();

        final MutableLiveData<PaymentModel> paymentData = new MutableLiveData<>();

        sendRequest.enqueue(new Callback<PaymentModel>() {
            @Override
            public void onResponse(@NonNull Call<PaymentModel> call, @NonNull Response<PaymentModel> response) {

                if (!response.isSuccessful()) {
                    Log.e(TAG, "onResponse: " + response.code());
                    return;
                }

                paymentData.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<PaymentModel> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                paymentData.setValue(null);
            }
        });
        return paymentData;
    }
}
