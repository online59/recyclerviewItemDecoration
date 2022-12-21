package com.example.simpleexpensemanager.database;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateNewData {

    private static final int MAX = 24;
    private static String[] paymentDesc = {"Transportation", "Fuel", "Breakfast", "Lunch", "Dinner", "Shopping"};
    final static int min_a = 100;
    final static int max_a = 2000;
    final static int min_p = 0;
    final static int max_p = 6;


    public static List<PaymentModel> createData() {
        return new CreateDataAsync().doInBackground();
    }

    private static class CreateDataAsync extends AsyncTask<Void, Void, List<PaymentModel>> {

        public CreateDataAsync() {
        }

        @Override
        protected List<PaymentModel> doInBackground(Void... voids) {
            List<PaymentModel> paymentModelList = new ArrayList<>();
            int viewType = 0;
            for (int i = 0; i < MAX; i++) {

                int randomAmount = new Random().nextInt((max_a - min_a) + 1) + min_a;
                int randomPayment = new Random().nextInt(max_p - min_p) + min_p;

                if (i % 5 == 0) {
                    viewType = 1;
                }
                paymentModelList.add(new PaymentModel(paymentDesc[randomPayment], randomAmount, System.currentTimeMillis()/1000, viewType));
            }
            return paymentModelList;
        }
    }
}
