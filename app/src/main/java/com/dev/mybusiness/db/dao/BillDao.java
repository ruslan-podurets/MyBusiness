package com.dev.mybusiness.db.dao;

import com.dev.mybusiness.models.Bill;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * Created by Rusik on 14.03.2016.
 */
public class BillDao extends BaseDaoImpl<Bill, Integer> {

    public BillDao(ConnectionSource connectionSource, Class<Bill> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }
}
