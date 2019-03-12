package com.auribises.enc2019a;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MyFragmentsActivity extends AppCompatActivity {


    UpperFragment upperFragment;
    LowerFragment lowerFragment;
    FragmentManager fragmentManager;

    void initViews(){

        upperFragment = new UpperFragment();
        lowerFragment = new LowerFragment();

        // FragmentManager will help to manage fragments in Activity
        // eg: to add/delete/update fragment in Activity
        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().add(R.id.upperFrame,upperFragment).commit();
        fragmentManager.beginTransaction().add(R.id.lowerFrame,lowerFragment).commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragments);
        initViews();
    }
}
