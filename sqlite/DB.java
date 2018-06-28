package com.example.jayhind.lecture_app.database.sqlite;

/**
 * Created by Jay Hind on 4/16/2018.
 */

public interface DB {
    String DB_NAME = "user.db";
    Integer VERSION = 1;



    String TB_CUST = "Customer";
    String COL_ID = "Id";
    String COL_NAME = "Name";
    String CREATE_CUST="Create table Customer ("+COL_ID+" Integer Primary Key,"+COL_NAME+" Text)";
}
