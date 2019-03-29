package com.auribises.enc2019a.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.auribises.enc2019a.R;
import com.auribises.enc2019a.listener.OnRecyclerItemClickListener;
import com.auribises.enc2019a.model.Customer;

import java.util.ArrayList;

public class CustomersAdapter extends RecyclerView.Adapter<CustomersAdapter.ViewHolder>{

    Context context;
    int resource;
    ArrayList<Customer> objects;

    OnRecyclerItemClickListener recyclerItemClickListener;

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener recyclerItemClickListener){
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    public CustomersAdapter(Context context, int resource, ArrayList<Customer> objects) {
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public CustomersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(resource,parent,false);
        final CustomersAdapter.ViewHolder holder = new CustomersAdapter.ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(holder.getAdapterPosition());
            }
        });

        return holder;
    }


    // onBindViewHolder will be execute n number of time from 0 to n-1 when n is the count whiwh we are returning
    @Override
    public void onBindViewHolder(@NonNull CustomersAdapter.ViewHolder holder, int position) {

        Customer customer  = objects.get(position);

        // Extracting Data from News Object and Setting the data on list_item
        holder.txtTitle.setText(customer.name);
        holder.txtUrl.setText(customer.phone);
    }

    @Override
    public int getItemCount() {
        return objects.size(); // how many list items we wish to have in our recycler view
    }


    // Nested Class : ViewHolder to hold Views of list_item
    class ViewHolder extends RecyclerView.ViewHolder{

        // Attributes of ViewHolder
        TextView txtTitle;
        TextView txtUrl;

        public ViewHolder(View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.textViewTitle);
            txtUrl = itemView.findViewById(R.id.textViewURL);
        }
    }

}
