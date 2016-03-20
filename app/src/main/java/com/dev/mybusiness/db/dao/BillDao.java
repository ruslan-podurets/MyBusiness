package com.dev.mybusiness.db.dao;

import android.os.AsyncTask;

import com.dev.mybusiness.interfaces.DataBaseRecordListener;
import com.dev.mybusiness.interfaces.DatabaseListListener;
import com.dev.mybusiness.models.Bill;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.lang.ref.SoftReference;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rusik on 14.03.2016.
 */
public class BillDao extends BaseDaoImpl<Bill, Integer> {

    public BillDao(ConnectionSource connectionSource, Class<Bill> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    private void throwResults(DatabaseListListener<Bill> listListener, List<Bill> bills) {
        if (listListener != null) {
            listListener.onResults(bills == null ? new ArrayList<Bill>() : bills);
        }
    }

    private void throwResult(DataBaseRecordListener<Bill> listListener, Bill bill) {
        if (listListener != null) {
            listListener.onResult(bill);
        }
    }

    public void loadAllRecords(DatabaseListListener<Bill> listListener) {
        final SoftReference<DatabaseListListener<Bill>> softReference = new SoftReference<>(listListener);
        new AsyncTask<Void, Void, List<Bill>>() {

            @Override
            protected List<Bill> doInBackground(Void... params) {
                List<Bill> bills;
                try {
                    bills = queryForAll();
                } catch (SQLException e) {
                    e.printStackTrace();
                    bills = new ArrayList<>();
                }
                return bills;
            }

            @Override
            protected void onPostExecute(List<Bill> bills) {
                throwResults(softReference.get(), bills);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
