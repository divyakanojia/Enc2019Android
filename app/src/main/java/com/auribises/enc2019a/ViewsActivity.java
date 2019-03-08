package com.auribises.enc2019a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

public class ViewsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    //1. Declare References to the Views which you want to use in your Java File
    CheckBox cbC, cbJava, cbPython;
    RadioButton rbMale, rbFemale;
    Spinner spCity;
    RatingBar ratingBar;
    EditText eTxtName, eTxtPhone;
    Button btnSubmit;

    ArrayAdapter<String> adapter;

    Enquiry eRef;

    //2. Initialize all the Reference Variables
    void initViews(){

        eRef = new Enquiry();

        cbC = findViewById(R.id.checkBoxC);
        cbJava = findViewById(R.id.checkBoxJava);
        cbPython = findViewById(R.id.checkBoxPython);

        rbMale = findViewById(R.id.radioButtonMale);
        rbFemale = findViewById(R.id.radioButtonFemale);

        spCity = findViewById(R.id.spinnerCity);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item);
        adapter.add("==Select City=="); //0
        adapter.add("Ludhiana");
        adapter.add("Chandigarh");
        adapter.add("Delhi");
        adapter.add("Bengaluru");
        adapter.add("Pune");
        adapter.add("Hyderabad"); // n-1

        spCity.setAdapter(adapter);

        ratingBar = findViewById(R.id.ratingBar);

        eTxtName = findViewById(R.id.editTextName);
        eTxtPhone = findViewById(R.id.editTextPhone);

        btnSubmit = findViewById(R.id.buttonSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                eRef.name = eTxtName.getText().toString();
                eRef.phone = eTxtPhone.getText().toString();

                Toast.makeText(ViewsActivity.this,"You Entered:"+eRef.name+" | "+eRef.phone,Toast.LENGTH_LONG).show();

                // Assignment:
                // Open a New Activity and Display the data entered by User
                // Enquiry Object has to be passed to other Activity

            }
        });

        spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position!=0){
                    String city = adapter.getItem(position);
                    Toast.makeText(ViewsActivity.this,"You Selected:"+city,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(ViewsActivity.this,"You Gave "+rating+" rating",Toast.LENGTH_LONG).show();
            }
        });

        cbC.setOnCheckedChangeListener(this);
        cbJava.setOnCheckedChangeListener(this);
        cbPython.setOnCheckedChangeListener(this);
        rbMale.setOnCheckedChangeListener(this);
        rbFemale.setOnCheckedChangeListener(this);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_views);
        initViews();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        int id = buttonView.getId();

        switch (id){
            case R.id.checkBoxC:
                if(isChecked) {
                    Toast.makeText(ViewsActivity.this, "You Selected C", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ViewsActivity.this, "You UnSelected C", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.checkBoxJava:
                if(isChecked) {
                    Toast.makeText(ViewsActivity.this, "You Selected Java", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ViewsActivity.this, "You UnSelected Java", Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.checkBoxPython:
                if(isChecked) {
                    Toast.makeText(ViewsActivity.this, "You Selected Python", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ViewsActivity.this, "You UnSelected Python", Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.radioButtonMale:
                if(isChecked) {
                    Toast.makeText(ViewsActivity.this, "You Selected Male", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.radioButtonFemale:
                if(isChecked) {
                    Toast.makeText(ViewsActivity.this, "You Selected Female", Toast.LENGTH_LONG).show();
                }
                break;

        }
    }
}
