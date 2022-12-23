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
import com.example.simpleexpensemanager.util.Utils;
import com.example.simpleexpensemanager.vm.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;
    List<PaymentModel> paymentList = new ArrayList<>();

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

        HeaderDecoration decoration = new HeaderDecoration(true, getHeaderCallback(paymentList));
        recyclerView.addItemDecoration(decoration);
    }

    private HeaderDecoration.HeaderCallback getHeaderCallback(final List<PaymentModel> paymentList) {

        return new HeaderDecoration.HeaderCallback() {
            @Override
            public boolean isHeader(int position) {

                // Because the payment list maybe null, so we check for null
                if (paymentList == null) {
                    return false;
                }

                // If there is no data, not drawing header
                if (paymentList.size() == 0) {
                    return false;
                }

                // At the first item of the list, make it the header
                if (position == 0) {
                    return true;
                }

                String currentHeader = Utils.getDate(paymentList.get(position).getTimeStamp());
                String previousHeader = Utils.getDate(paymentList.get(position - 1).getTimeStamp());

                // If the current header name match the previous header, not drawing header, else draw header
                return !currentHeader.equalsIgnoreCase(previousHeader);
            }

            @Override
            public String getHeader(int position) {

                if (paymentList.size() == 0) {
                    return "";
                }

                // Text to put as a header
                return Utils.getDate(paymentList.get(position).getTimeStamp());
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.topbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        List<PaymentModel> listItem;
        if (item.getItemId() == R.id.topbar_insert_data) {
            listItem = CreateNewData.createData();
            paymentList.addAll(listItem);
            viewModel.insertNewPaymentList(listItem);
        } else {
            if (!paymentList.isEmpty()) {
                paymentList.clear();
            }
            viewModel.deleteAll();
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.deleteAll();
    }
}