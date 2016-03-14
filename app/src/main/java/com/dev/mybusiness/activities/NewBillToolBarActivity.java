package com.dev.mybusiness.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dev.mybusiness.R;
import com.dev.mybusiness.db.HelperFactory;
import com.dev.mybusiness.db.dao.BillDao;
import com.dev.mybusiness.models.Bill;

import java.sql.SQLException;

/**
 * Created by Rusik on 14.03.2016.
 */
public class NewBillToolBarActivity extends BaseToolBarActivity {

    public static final int REQUEST_CODE = 100;

    private EditText editTextName;
    private EditText editTextMoney;
    private EditText editTextDescription;

    private Bill currentBill = new Bill();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_bill_layout);

        editTextName = (EditText) getContentPane().findViewById(R.id.editTextName);
        editTextMoney = (EditText) getContentPane().findViewById(R.id.editTextMoney);
        editTextDescription = (EditText) getContentPane().findViewById(R.id.editTextDescription);

        Button buttonSave = (Button) getContentPane().findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBillValid()) {
                    try {
                        BillDao billDao = HelperFactory.getHelper().getBillDao();
                        billDao.create(currentBill);
                        setResult(RESULT_OK);
                        finish();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        Toast.makeText(getBaseContext(), getString(R.string.db_error), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getBaseContext(), getString(R.string.fill_valid_data), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean isBillValid() {
        String name = editTextName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            return false;
        }
        currentBill.setName(name);
        currentBill.setDescription(editTextDescription.getText().toString().trim());
        double money;
        try {
            money = Double.valueOf(editTextMoney.getText().toString().trim());
        } catch (Exception e) {
            return false;
        }
        if (money < 0) {
            return false;
        }
        currentBill.setMoney(money);
        currentBill.createCurrentCreationDate();
        return true;
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
}
