package com.auribises.enc2019a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/*
Lifecycle Methods of Activity
onCreate
	Object of Activity is created in memory.
	will be acting as a constructor.
	this will be executed only once !!
onStart
	When Object of Activity is pushed in Stack !!
onResume
	When Object of Activity is Visible to User and User can interact with Activity
onPause
	When Activity is Visible to User, but User cannot Interact with it
onStop
	When Activity is Not Visible to User, and User cannot Interact with it
onDestroy
	When user press back key or executes a finish() method
	will be acting as a destructor.
	this will be executed only once !!

UC1
	User clicks on App Icon and Launches Activity1 (A1)
	A1: C > S > R

UC2
	User clicks on App Icon and Launches Activity1 (A1)
	A1: C > S > R
	From Activity1 (A1) User Launches Activity2	(A2)
	A1: P > St
	A2: C > S > R

UC3
	User clicks on App Icon and Launches Activity1 (A1)
	A1: C > S > R
	From Activity1 (A1) User Launches Activity2	(A2)
	A1: P > St
	A2: C > S > R
	User Now Presses Back Key
	A1: S > R
	A2: P > St > D

 */

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Binding the Layout on Activity
        // MainActivity is an Empty Frame which shall look like the layout you have designed
        setContentView(R.layout.activity_main);

        //System.out.println("(A1) : onCreate");
        Log.i(TAG,"==onCreate==");

    }

    @Override
    protected void onStart() {
        super.onStart();
        //System.out.println("(A1) : onStart");
        Log.i(TAG,"==onStart==");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //System.out.println("(A1) : onResume");
        Log.i(TAG,"==onResume==");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //System.out.println("(A1) : onPause");
        Log.i(TAG,"==onPause==");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //System.out.println("(A1) : onStop");
        Log.i(TAG,"==onStop==");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //System.out.println("(A1) : onDestroy");
        Log.i(TAG,"==onDestroy==");
    }

    // clickHandler name can be any name
    public void clickHandler(View view){

        // Implicit Intent
        //Intent intent = new Intent("com.auribises.enc2019a.homeactivity");

        // Explicit Intent
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
    }

}
