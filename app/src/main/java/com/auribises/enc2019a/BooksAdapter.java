package com.auribises.enc2019a;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BooksAdapter extends ArrayAdapter<Book> {

    Context context;
    int resource;
    ArrayList<Book> objects;

    public BooksAdapter(Context context, int resource, ArrayList<Book> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(resource,parent,false);

        TextView txtBookName = view.findViewById(R.id.textViewName);
        TextView txtAuthor = view.findViewById(R.id.textViewAuthor);
        TextView txtPrice = view.findViewById(R.id.textViewPrice);

        Book book = objects.get(position);
        txtBookName.setText(book.name);
        txtAuthor.setText(book.author);
        txtPrice.setText(book.price);

        return view;

    }
}
