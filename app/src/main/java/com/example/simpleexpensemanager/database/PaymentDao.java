package com.example.simpleexpensemanager.database;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@androidx.room.Dao
public interface PaymentDao {

    @Query("SELECT * FROM paymentmodel")
    LiveData<List<PaymentModel>> getAllPayments();

    @Query("SELECT * FROM paymentmodel ORDER BY payment_date DESC LIMIT 10")
    LiveData<List<PaymentModel>> getLastTenPayment();

    @Insert(entity = PaymentModel.class)
    void insertNewPayment(PaymentModel paymentModel);

    @Query("DELETE FROM paymentmodel")
    void deleteAll();

    @Delete
    void deletePayment(PaymentModel paymentModel);
}
