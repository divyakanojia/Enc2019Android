package com.auribises.enc2019a;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TTSActivity extends AppCompatActivity implements View.OnClickListener{

    // Declare References to the Views
    EditText eTxtData;
    Button btnSpeak;

    TextToSpeech tts;

    void initViews(){
        eTxtData = findViewById(R.id.editTextData);
        btnSpeak = findViewById(R.id.buttonSpeak);
        btnSpeak.setOnClickListener(this);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.ERROR){
                    Toast.makeText(TTSActivity.this,"Please Enable TTS in your Settings",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts);
        initViews(); // should be done after setContentView
    }

    @Override
    public void onClick(View v) {

        String data = eTxtData.getText().toString();

        tts.speak(data,TextToSpeech.QUEUE_FLUSH,null);
        //tts.speak(data,TextToSpeech.QUEUE_FLUSH,null,null);

    }
}
