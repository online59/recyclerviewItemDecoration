package com.example.simpleexpensemanager.database;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {PaymentModel.class}, version = 1)
public abstract class PaymentDatabase extends RoomDatabase {
    private static PaymentDatabase instance;

    public abstract PaymentDao paymentDao();

    public static synchronized PaymentDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(context, PaymentDatabase.class, "payment")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    private static final RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDataAsyncTask(instance).execute();
        }
    };

    private static class PopulateDataAsyncTask extends AsyncTask<Void, Void, Void> {

        public PopulateDataAsyncTask(PaymentDatabase instance) {
            PaymentDao paymentDao = instance.paymentDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
