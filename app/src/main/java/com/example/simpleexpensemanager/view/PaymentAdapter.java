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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PaymentModel> paymentList;

    public PaymentAdapter(MainViewModel viewModel, LifecycleOwner lifecycleOwner) {
        viewModel.getAllPayment().observe(lifecycleOwner, paymentData -> {
            paymentList = paymentData;
            notifyDataSetChanged();
        });
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_list_item, parent, false);
            return new PaymentViewHolder(view);
        } else  {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_header, parent, false);
            return new HeaderViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0) {
            PaymentViewHolder paymentViewHolder = (PaymentViewHolder) holder;
            paymentViewHolder.getTvDesc().setText(paymentList.get(position).getPaymentDescription());
            paymentViewHolder.getTvAmount().setText(String.valueOf(paymentList.get(position).getPaymentAmount()));
        } else {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            headerViewHolder.getTvDate().setText(headerViewHolder.getDate(paymentList.get(position).getTimeStamp()));
        }
    }

    @Override
    public int getItemCount() {
        return paymentList == null ? 0 : paymentList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return paymentList == null ? 0: paymentList.get(position).getViewType();
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

        public String getDate(long timestamp) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timestamp);
            Date date = calendar.getTime();
            return date.toString();
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
