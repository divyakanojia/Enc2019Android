package com.auribises.enc2019a;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>{

    Context context;
    int resource;
    ArrayList<News> objects;

    public NewsRecyclerAdapter(Context context, int resource, ArrayList<News> objects) {
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(resource,parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }


    // onBindViewHolder will be execute n number of time from 0 to n-1 when n is the count whiwh we are returning
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        News news  = objects.get(position);

        // Extracting Data from News Object and Setting the data on list_item
        holder.imageView.setBackgroundResource(news.image);
        holder.txtTitle.setText(news.title);
        holder.txtUrl.setText(news.url);
    }

    @Override
    public int getItemCount() {
        return objects.size(); // how many list items we wish to have in our recycler view
    }


    // Nested Class : ViewHolder to hold Views of list_item
    class ViewHolder extends RecyclerView.ViewHolder{

        // Attributes of ViewHolder
        ImageView imageView;
        TextView txtTitle;
        TextView txtUrl;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            txtTitle = itemView.findViewById(R.id.textViewTitle);
            txtUrl = itemView.findViewById(R.id.textViewURL);
        }
    }

}
