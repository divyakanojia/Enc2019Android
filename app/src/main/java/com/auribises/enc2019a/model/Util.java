package com.auribises.enc2019a.model;

import android.net.Uri;

public class Util {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Customers.db";

    public static final String TAB_NAME = "Customer";

    public static final String COL_ID = "_ID";
    public static final String COL_NAME = "name";
    public static final String COL_PHONE = "phone";
    public static final String COL_EMAIL = "email";

    public static final String CREATE_TAB_QUERY = "create table Customer(" +
            " _ID integer primary key autoincrement," +
            " name varchar(256)," +
            " phone varchar(20)," +
            " email varchar(256)" +
            " )";

    public static final Uri CUSTOMER_URI = Uri.parse("content://com.auribises.enc2019a.mycp/"+TAB_NAME);

    /*
        create table Customer(
            _ID int primary key autoincrement,
            name varchar(256),
            phone varchar(256),
            email varchar(256)
        )
     */

}
