package com.auribises.enc2019a;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class LowerFragment extends Fragment implements AdapterView.OnItemClickListener{


    ListView listView;
    ArrayAdapter<String> adapter;

    // Declare Reference Variable of MyListener
    MyListener myListener;

    public LowerFragment() {
        // Required empty public constructor
    }

    public void registerMyListener(UpperFragment upperFragment){
        myListener = upperFragment; // RTP Statement
        // myListener Ref Variable is pointing to object of UpperFragment
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lower, container, false);

        listView = view.findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);

        adapter.add("NDTV");        //0
        adapter.add("CNN");
        adapter.add("ZEE");
        adapter.add("AAJ TAK");
        adapter.add("PTC");         //n-1

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(),"You Clicked on "+position,Toast.LENGTH_LONG).show();
        myListener.newsHandler(position); // Execution of Method
    }
}
