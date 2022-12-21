package com.example.simpleexpensemanager.view;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpleexpensemanager.R;
import com.example.simpleexpensemanager.database.PaymentModel;
import com.example.simpleexpensemanager.vm.MainViewModel;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final int MAX = 21;
    private static String[] paymentDesc = {"Transportation", "Fuel", "Breakfast", "Lunch", "Dinner", "Shopping"};
    final static int min_a = 100;
    final static int max_a = 2000;
    final static int min_p = 0;
    final static int max_p = 6;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        createData();

        PaymentAdapter adapter = new PaymentAdapter(viewModel, this);

        RecyclerView recyclerView = findViewById(R.id.payment_list_recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    public void createData() {
        int viewType = 0;
        for (int i = 0; i < MAX; i++) {
            if (i % 4 == 0) {
                viewType = 1;
            } else {
                viewType = 0;
            }

            Log.e(TAG, "createData: " + viewType );

            int randomAmount = new Random().nextInt((max_a - min_a) + 1) + min_a;
            int randomPayment = new Random().nextInt(max_p - min_p) + min_p;

            PaymentModel paymentModel = new PaymentModel(paymentDesc[randomPayment],
                    randomAmount,
                    System.currentTimeMillis() / 1000,
                    viewType);

            viewModel.insertNewPayment(paymentModel);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}