package com.example.simpleexpensemanager.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateNewData {

    private static final int MAX = 15;
    private static String[] paymentDesc = {"Transportation", "Fuel", "Breakfast", "Lunch", "Dinner", "Shopping"};
    final static int min = 100;
    final static int max = 2000;
    final static int random = new Random().nextInt((max - min) + 1) + min;


    public static List<PaymentModel> createData() {
        List<PaymentModel> paymentModelList = new ArrayList<>();
        int viewType = 0;
        for (int i = 0; i < MAX; i++) {
            if (i % 5 == 0) {
                viewType = 1;
            }
            paymentModelList.add(new PaymentModel(paymentDesc[i], random, System.currentTimeMillis()/1000, viewType));
        }
        return paymentModelList;
    }
}
