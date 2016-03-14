package com.dev.mybusiness.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.mybusiness.R;
import com.dev.mybusiness.activities.NewBillToolBarActivity;
import com.dev.mybusiness.db.HelperFactory;
import com.dev.mybusiness.db.dao.BillDao;
import com.dev.mybusiness.models.Bill;

import java.sql.SQLException;

/**
 * Created by Rusik on 13.03.2016.
 */
public class BillsFragment extends Fragment {

    private TextView textViewResult;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bills_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button buttonGenerateRecord = (Button) view.findViewById(R.id.buttonGenerateRecord);
        buttonGenerateRecord.setOnClickListener(generateClickListener);
        Button buttonClearDB = (Button) view.findViewById(R.id.buttonClearDB);
        Button createNewBill = (Button) view.findViewById(R.id.createNewBill);
        createNewBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), NewBillToolBarActivity.class), NewBillToolBarActivity.REQUEST_CODE);
            }
        });
        buttonClearDB.setOnClickListener(clearDBClickListener);
        textViewResult = (TextView) view.findViewById(R.id.textViewResult);
        updateCountFromDB();
    }

    private View.OnClickListener generateClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                BillDao billDao = HelperFactory.getHelper().getBillDao();
                Bill bill = new Bill("name", "descr");
                billDao.createIfNotExists(bill);
                updateCountFromDB();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    };

    private View.OnClickListener clearDBClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                HelperFactory.getHelper().clearTable(Bill.class);
                updateCountFromDB();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    };

    private void updateCountFromDB() {
        try {
            BillDao billDao = HelperFactory.getHelper().getBillDao();
            long count = billDao.countOf();
            textViewResult.setText("Count in db: " + count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case NewBillToolBarActivity.REQUEST_CODE:
                switch (resultCode) {
                    case NewBillToolBarActivity.RESULT_OK:
                        updateCountFromDB();
                        break;
                    case NewBillToolBarActivity.RESULT_CANCELED:
                        Toast.makeText(getContext(), "Canceled", Toast.LENGTH_SHORT).show();
                        break;
                }
        }
    }
}
