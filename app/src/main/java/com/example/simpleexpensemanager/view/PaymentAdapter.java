package com.example.simpleexpensemanager.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpleexpensemanager.R;
import com.example.simpleexpensemanager.database.PaymentModel;
import com.example.simpleexpensemanager.util.UtilsClass;
import com.example.simpleexpensemanager.vm.MainViewModel;

import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder> {

    private List<PaymentModel> paymentList;

    // Create a method to return a header text to the HeaderDecoration class
    public String getHeaderText(int position) {
        return UtilsClass.getDateTime(paymentList.get(position).getTimeStamp());
    }

    public PaymentAdapter(MainViewModel viewModel, LifecycleOwner lifecycleOwner) {
        viewModel.getAllPayment().observe(lifecycleOwner, paymentData -> {
            paymentList = paymentData;
            notifyDataSetChanged();
        });
    }

    @NonNull
    @Override
    public PaymentAdapter.PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_list_item, parent, false);
        return new PaymentViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PaymentAdapter.PaymentViewHolder holder, int position) {
        holder.getTvDesc().setText(paymentList.get(position).getPaymentDescription());
        holder.getTvAmount().setText(String.valueOf(paymentList.get(position).getPaymentAmount()));
    }

    @Override
    public int getItemCount() {
        return paymentList == null ? 0 : paymentList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return paymentList == null ? 0 : paymentList.get(position).getViewType();
    }

    public static class PaymentViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvDesc, tvAmount;

        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDesc = itemView.findViewById(R.id.tv_payment);
            tvAmount = itemView.findViewById(R.id.tv_amount);
        }

        public TextView getTvDesc() {
            return tvDesc;
        }

        public TextView getTvAmount() {
            return tvAmount;
        }
    }
}
