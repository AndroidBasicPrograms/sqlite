package com.example.jayhind.lecture_app.database.sqlite.Fragements;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jayhind.lecture_app.R;
import com.example.jayhind.lecture_app.database.sqlite.DAO.Customer_dao;
import com.example.jayhind.lecture_app.database.sqlite.model.Customer;

/**
 * A simple {@link Fragment} subclass.
 */
public class sqliteFragment extends Fragment implements View.OnClickListener {
    EditText id,name;
    Button ins,disp;
    Context context;
    int udp=-1;

    public sqliteFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_sqlite, container, false);
        context=getContext();
        id=view.findViewById(R.id.etid);
        name=view.findViewById(R.id.etname);
        ins=view.findViewById(R.id.btnins);
        disp=view.findViewById(R.id.btndisp);
        ins.setOnClickListener(this);
        disp.setOnClickListener(this);
        Bundle b=getArguments();
        if(b!=null)
        {
        Customer c=b.getParcelable("Customer");
        id.setText(String.valueOf(c.getId()));
        name.setText(c.getName());
        ins.setText("Update");
        udp=0;
        }
        return view;
    }

    @Override
    public void onClick(View view) {
        Customer_dao cd = new Customer_dao(context);
        if (view.getId() == R.id.btnins) {
            if(udp==-1) {
                Customer c = new Customer();
                c.setId(Integer.parseInt(id.getText().toString()));
                c.setName(name.getText().toString());
                long count = cd.savecust(c);
                if (count > 0) {
                    Toast.makeText(context, "inserted", Toast.LENGTH_SHORT).show();
                }
            } else {
                Customer c = new Customer();
                c.setId(Integer.parseInt(id.getText().toString()));
                c.setName(name.getText().toString());
                long count = cd.update_cust(c);
                if (count > 0) {
                    Toast.makeText(context, "updated", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else if(view.getId()==R.id.btndisp)
        {
            Fragment f=new SqliteDataDisplayFragment();
            getFragmentManager().beginTransaction().replace(R.id.s_frm_id,f).addToBackStack(databaseActivity.class.getName()).commit();
        }
    }
}
