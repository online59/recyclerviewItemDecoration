package com.example.simpleexpensemanager.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.simpleexpensemanager.database.PaymentModel;
import com.example.simpleexpensemanager.database.PaymentRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    PaymentRepository paymentRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        paymentRepository = PaymentRepository.getInstance(application);
    }

    public LiveData<List<PaymentModel>> getAllPayment() {
        return paymentRepository.getAllPayment();
    }

    public LiveData<List<PaymentModel>> getLastTenPayment() {
        return paymentRepository.getLastTenPayment();
    }

    public void insertNewPayment(PaymentModel paymentModel) {
        paymentRepository.insertNewPayment(paymentModel);
    }

    public void deleteAll() {
        paymentRepository.deleteAll();
    }

    public void deletePayment(PaymentModel paymentModel){
        paymentRepository.deletePayment(paymentModel);
    }
}
