package com.auribises.enc2019a;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/*
class Point{
    int x;
    int y;

    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}
*/

public class NewsAdapter extends ArrayAdapter<News>{

    Context context;
    int resource;
    ArrayList<News> objects;

    public NewsAdapter(Context context, int resource, ArrayList<News> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    // position value shall vary from 0 to n-1
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // view is a ref var which is pointing to list_item
        View view = LayoutInflater.from(context).inflate(resource,parent,false);

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView txtTitle = view.findViewById(R.id.textViewTitle);
        TextView txtUrl = view.findViewById(R.id.textViewURL);

        News news  = objects.get(position);

        // Extracting Data from News Object and Setting the data on list_item
        imageView.setBackgroundResource(news.image);
        txtTitle.setText(news.title);
        txtUrl.setText(news.url);

        return view;
    }
}
