package com.example.simpleexpensemanager.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpleexpensemanager.R;
import com.example.simpleexpensemanager.database.CreateNewData;
import com.example.simpleexpensemanager.database.PaymentModel;
import com.example.simpleexpensemanager.vm.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        PaymentAdapter adapter = new PaymentAdapter(viewModel, this);

        RecyclerView recyclerView = findViewById(R.id.payment_list_recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.topbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.topbar_insert_data) {
            List<PaymentModel> paymentMList = CreateNewData.createData();
            viewModel.insertNewPaymentList(paymentMList);
        } else {
            viewModel.deleteAll();
        }
        return true;
    }
}