package com.auribises.enc2019a;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class SpeechRecognitionActivity extends AppCompatActivity implements RecognitionListener{

    TextView txtData;
    Button btnSpeak;

    SpeechRecognizer speechRecognizer;

    ProgressDialog progressDialog;

    void initViews(){
        txtData = findViewById(R.id.textViewData);
        btnSpeak = findViewById(R.id.buttonSpeak);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Listening...");

        /*btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        btnSpeak.setOnClickListener(clickListener);

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(this);
    }

    // Anonymous Class
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = RecognizerIntent.getVoiceDetailsIntent(SpeechRecognitionActivity.this);
            speechRecognizer.startListening(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_recognition);

        initViews();
    }

    @Override
    public void onReadyForSpeech(Bundle params) {

    }

    @Override
    public void onBeginningOfSpeech() {
        progressDialog.show();
    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {
        progressDialog.dismiss();
    }

    @Override
    public void onError(int error) {

    }

    @Override
    public void onResults(Bundle results) {

        ArrayList<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        if(data!=null && data.size()>0){
            String closestMatch = data.get(0);
            txtData.setText(closestMatch);

            if(closestMatch.contains("call") && closestMatch.contains("dad")){
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String phone = "+91 99999 88888";
                intent.setData(Uri.parse("tel:"+phone));
                startActivity(intent);
            }

            if(closestMatch.contains("message") && closestMatch.contains("dad")){

                SmsManager smsManager = SmsManager.getDefault();

                String phone = "+91 99999 88888";
                String message = "This was just a demo. I am not sharing my location";
                smsManager.sendTextMessage(phone,null,message,null,null);

            }

            if(closestMatch.contains("share") && closestMatch.contains("location")){

                // send request to location manager for getting location

                // below code will be written in onLocationChanged() method
                SmsManager smsManager = SmsManager.getDefault();

                String phone = "+91 99155 71177";
                String message = "This was just a HW. This is my location";
                smsManager.sendTextMessage(phone,null,message,null,null);

            }


        }

    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }
}
