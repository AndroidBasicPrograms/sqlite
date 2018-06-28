package com.example.jayhind.lecture_app.database.sqlite.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jayhind.lecture_app.database.sqlite.DB;
import com.example.jayhind.lecture_app.database.sqlite.model.Customer;
import com.example.jayhind.lecture_app.database.sqlite.sqliteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Jay Hind on 4/16/2018.
 */

public class Customer_dao {
    SQLiteDatabase db;
    public Customer_dao(Context c)
    {
        sqliteOpenHelper s=new sqliteOpenHelper(c);
        db=s.getWritableDatabase();
    }
    public long savecust(Customer c)
    {
    ContentValues values=new ContentValues();
    values.put(DB.COL_ID,c.getId());
    values.put(DB.COL_NAME,c.getName());
    long count=db.insert(DB.TB_CUST,null,values);
    return count;
    }

    public ArrayList<Customer> getcust()
    {
        String orderBy=null;
        String groupBy=null;
        String[] selectionArgs=null;
        String selection=null;
        String[] columns={DB.COL_ID, DB.COL_NAME};
        String having=null;
        Cursor c=db.query(DB.TB_CUST,columns,selection,selectionArgs,groupBy,having,orderBy);
        ArrayList<Customer> r=new ArrayList<Customer>();
        if(c.moveToFirst())
        {
            do
            {
                Customer ci=new Customer();
                ci.setId(c.getInt(c.getColumnIndex(DB.COL_ID)));
                ci.setName(c.getString(c.getColumnIndex(DB.COL_NAME)));
                r.add(ci);
            }while(c.moveToNext());
        }
        return r;
    }

    public long del_cust(Customer c)
    {
        long count;
        String where=DB.COL_ID+"=?";
        String[] whereargs={String.valueOf(c.getId())};
        count=db.delete(DB.TB_CUST,where,whereargs);
        return count;
    }

    public long update_cust(Customer c)
    {
        long count;
        String where=DB.COL_ID+"=?";
        String[] whereargs={String.valueOf(c.getId())};
        ContentValues values=new ContentValues();
        values.put(DB.COL_ID,c.getId());
        values.put(DB.COL_NAME,c.getName());
        count=db.update(DB.TB_CUST,values,where,whereargs);
        return count;
    }
}
