package com.dev.mybusiness.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dev.mybusiness.db.dao.BillDao;
import com.dev.mybusiness.models.Bill;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Rusik on 14.03.2016.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "app.db";

    private BillDao billDao;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Bill.class);
        } catch (Exception e) {
            Log.e(DatabaseHelper.class.getSimpleName(), e.getMessage());
        }
    }

    public BillDao getBillDao() throws SQLException {
        if (billDao == null) {
            billDao = new BillDao(getConnectionSource(), Bill.class);
        }
        return billDao;
    }

    public void clearTable(Class className) throws SQLException {
        TableUtils.dropTable(getConnectionSource(), className, true);
        TableUtils.createTable(getConnectionSource(), className);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }
}
