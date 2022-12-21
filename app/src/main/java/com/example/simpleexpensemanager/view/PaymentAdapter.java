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
import com.example.simpleexpensemanager.vm.MainViewModel;

import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PaymentModel> paymentList;

    public PaymentAdapter(MainViewModel viewModel, LifecycleOwner lifecycleOwner) {
        viewModel.getAllPayment().observe(lifecycleOwner, paymentData -> {
            paymentList = paymentData;
            notifyDataSetChanged();
            System.out.println("DataSet" + paymentList);
        });
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        System.out.println("onCreateViewHolder");
        if (viewType == 0) {
            System.out.println("onCreateViewHolder1");
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_list_item, parent, false);
            return new PaymentViewHolder(view);
        } else  {
            System.out.println("onCreateViewHolder2");
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_header, parent, false);
            return new HeaderViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        System.out.println("onBindViewHolder");
        if (holder.getItemViewType() == 0) {
            System.out.println("onBindViewHolder1");
            PaymentViewHolder paymentViewHolder = (PaymentViewHolder) holder;
            paymentViewHolder.getTvDesc().setText(paymentList.get(position).getPaymentDescription());
            paymentViewHolder.getTvAmount().setText(paymentList.get(position).getPaymentAmount());
        } else {
            System.out.println("onBindViewHolder2");
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            headerViewHolder.getTvDate().setText(String.valueOf(paymentList.get(position).getTimeStamp()));
        }
    }

    @Override
    public int getItemCount() {
        return paymentList == null ? 0 : paymentList.size();
    }

    private static class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvDate;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tv_payments_date);
        }

        public TextView getTvDate() {
            return tvDate;
        }
    }

    private static class PaymentViewHolder extends RecyclerView.ViewHolder {

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
