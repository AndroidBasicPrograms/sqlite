package com.example.jayhind.lecture_app.database.sqlite.Fragements;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jayhind.lecture_app.Adapter.SqliteAdapter;
import com.example.jayhind.lecture_app.R;
import com.example.jayhind.lecture_app.database.sqlite.DAO.Customer_dao;
import com.example.jayhind.lecture_app.database.sqlite.model.Customer;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SqliteDataDisplayFragment extends Fragment implements SqliteAdapter.UserClickListener {


    Context context;
    RecyclerView rv;
    ArrayList<Customer> ans;
    Customer_dao cd;


    public SqliteDataDisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sqlite_data_display, container, false);
        context=getActivity();
        rv=view.findViewById(R.id.rview);
        rv.setLayoutManager(new LinearLayoutManager(context));
        cd=new Customer_dao(context);
        setadapter();
        return view;
    }

    private void setadapter() {
        ans=cd.getcust();
        SqliteAdapter adapter=new SqliteAdapter(ans,this);
        rv.setAdapter(adapter);
    }

    @Override
    public void onUserClick(final Customer customer) {
        new AlertDialog.Builder(context).setTitle("Select operation").setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Fragment fragment=new sqliteFragment();
                Bundle bundle=new Bundle();
                bundle.putParcelable("Customer",customer);
                fragment.setArguments(bundle);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.s_frm_id,fragment)
                        .addToBackStack(sqliteFragment.class.getName())
                        .commit();
            }
        }).setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                long c=cd.del_cust(customer);
                if(c>0)
                {
                    Toast.makeText(context, "Deleted succssfully", Toast.LENGTH_SHORT).show();
                    setadapter();
                }

            }
        }).setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();
    }
}
