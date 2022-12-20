package com.example.simpleexpensemanager.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PaymentRepository {
    private static PaymentRepository instance;
    private final PaymentDao paymentDao;

    private PaymentRepository(Application application) {
        PaymentDatabase paymentDatabase = PaymentDatabase.getInstance(application.getApplicationContext());
        this.paymentDao = paymentDatabase.paymentDao();
    }

    public static PaymentRepository getInstance(Application application) {
        if (instance == null) {
            instance = new PaymentRepository(application);
        }
        return instance;
    }

    public LiveData<List<PaymentModel>> getAllPayment() {
        return paymentDao.getAllPayments();
    }

    public LiveData<List<PaymentModel>> getLastTenPayment() {
        return paymentDao.getLastTenPayment();
    }

    public void insertNewPayment(PaymentModel paymentModel){
        new Thread(() -> paymentDao.insertNewPayment(paymentModel));
    }

    public void deleteAll() {
        new Thread(paymentDao::deleteAll);
    }

    public void deletePayment(PaymentModel paymentModel){
        new Thread(() -> paymentDao.deletePayment(paymentModel));
    }
}
