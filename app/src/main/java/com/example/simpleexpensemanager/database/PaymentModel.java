package com.example.simpleexpensemanager.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PaymentModel {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "payment_desc")
    private String paymentDescription;

    @ColumnInfo(name = "payment_total")
    private int paymentAmount;

    @ColumnInfo(name = "payment_date")
    private long timeStamp;

    @ColumnInfo(name = "view_type")
    private int viewType;

    public String getPaymentDescription() {
        return paymentDescription;
    }

    public PaymentModel(String paymentDescription, int paymentAmount, long timeStamp, int viewType) {
        this.paymentDescription = paymentDescription;
        this.paymentAmount = paymentAmount;
        this.timeStamp = timeStamp;
        this.viewType = viewType;
    }

    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
