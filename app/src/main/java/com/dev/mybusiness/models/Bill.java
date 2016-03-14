package com.dev.mybusiness.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Calendar;

/**
 * Created by Rusik on 14.03.2016.
 */
@DatabaseTable(tableName = "bills")
public class Bill {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField()
    private String name;

    @DatabaseField()
    private String description;

    @DatabaseField()
    private long creationDate;

    @DatabaseField
    private double money;

    public void createCurrentCreationDate() {
        creationDate = Calendar.getInstance().getTimeInMillis();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Bill(String name, String description) {
        this.name = name;
        this.description = description;
        creationDate = Calendar.getInstance().getTimeInMillis();
    }

    public Bill(String name, String description, double money) {
        this.name = name;
        this.money = money;
        this.description = description;
        creationDate = Calendar.getInstance().getTimeInMillis();
    }

    public Bill() {
    }
}
