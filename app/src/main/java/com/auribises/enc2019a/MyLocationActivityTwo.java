package com.auribises.enc2019a;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


public class MyLocationActivityTwo extends AppCompatActivity {

    TextView txtLocation;
    Button btnFetchLocation;

    ProgressDialog progressDialog;

    FusedLocationProviderClient fusedLocationProviderClient;

    double latitude, longitude;

    void initViews() {
        txtLocation = findViewById(R.id.textViewLocation);
        btnFetchLocation = findViewById(R.id.buttonFetch);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching Location...");

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        btnFetchLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchLocation();
            }
        });
    }

    void fetchLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this,"Please Grant Permissions and Try Again",Toast.LENGTH_LONG).show();
        }else {
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();

                            // Reverse Geocoding to fetch Address
                            float speed = location.getSpeed(); // mps
                            //float distance = location.distanceTo(someOtherLocation);
                        }
                    });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_location);
    }
}
