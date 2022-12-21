package com.example.simpleexpensemanager.database;

import android.app.Application;
import android.os.AsyncTask;

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
        new InsertTaskAsync(paymentDao).execute(paymentModel);
    }

    public void deleteAll() {
        new DeleteAllTaskAsync(paymentDao).execute();
    }

    public void deletePayment(PaymentModel paymentModel){
        new DeleteTaskAsync(paymentDao).execute(paymentModel);
    }


    private static class InsertTaskAsync extends AsyncTask<PaymentModel, Void, Void> {

        private PaymentDao paymentDao;

        public InsertTaskAsync(PaymentDao paymentDao) {
            this.paymentDao = paymentDao;
        }

        @Override
        protected Void doInBackground(PaymentModel... paymentModels) {

            for (PaymentModel payment: paymentModels) {
                paymentDao.insertNewPayment(payment);
            }

            return null;
        }
    }

    private static class DeleteAllTaskAsync extends AsyncTask<Void, Void, Void> {

        private PaymentDao paymentDao;

        public DeleteAllTaskAsync(PaymentDao paymentDao) {
            this.paymentDao = paymentDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            paymentDao.deleteAll();
            return null;
        }
    }

    private static class DeleteTaskAsync extends AsyncTask<PaymentModel, Void, Void> {

        private PaymentDao paymentDao;

        public DeleteTaskAsync(PaymentDao paymentDao) {
            this.paymentDao = paymentDao;
        }

        @Override
        protected Void doInBackground(PaymentModel... paymentModels) {
            paymentDao.deletePayment(paymentModels[0]);
            return null;
        }
    }
}
