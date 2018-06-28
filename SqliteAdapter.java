package com.example.jayhind.lecture_app.Adapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jayhind.lecture_app.R;
import com.example.jayhind.lecture_app.database.sqlite.model.Customer;

import java.util.ArrayList;
/**
 * Created by Jay Hind on 4/19/2018.
*/
public class SqliteAdapter extends RecyclerView.Adapter<SqliteAdapter.SqliteViewHolder>{
    ArrayList<Customer> customers;
    UserClickListener listener;

    public SqliteAdapter(ArrayList<Customer> customers,UserClickListener listener) {
        this.customers=customers;
        this.listener=listener;
    }

    @Override
    public SqliteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_row,parent,false);
        SqliteViewHolder s=new SqliteViewHolder(view);
        return s;
    }

    @Override
    public void onBindViewHolder(SqliteViewHolder holder, int position) {
        final Customer c=customers.get(position);
        holder.id.setText(c.getId().toString());
        holder.name.setText(c.getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    listener.onUserClick(c);
            }
        });
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }


    public class SqliteViewHolder extends RecyclerView.ViewHolder {
        TextView id,name;
        View view;
        public SqliteViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            id=itemView.findViewById(R.id.cid);
            name=itemView.findViewById(R.id.cname);
        }
    }
    public interface UserClickListener
    {
        public void onUserClick(Customer customer);
    }
}
