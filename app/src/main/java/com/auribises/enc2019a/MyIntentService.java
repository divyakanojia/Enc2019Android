package com.auribises.enc2019a;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService extends IntentService {

    String webServiceUrl;
    StringBuffer response;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

//    @Override
//    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
//        return super.onStartCommand(intent, flags, startId);
//    }

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        webServiceUrl = intent.getStringExtra(Util.KEY_URL);
        Log.i("MyIntentService","==onHandleIntent=="+webServiceUrl);

        try{

            // URL to Web Service
            URL url = new URL(webServiceUrl);

            // Create a Connection between Client and Server
            URLConnection urlConnection = url.openConnection();

            // Read Data from WebService on Server
            InputStream inputStream = urlConnection.getInputStream();

            // To Read Data Line By Line
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            // response contains JSON Data from WebService
            response = new StringBuffer();

            String line = "";
            while ((line = bufferedReader.readLine())!=null){
                response.append(line);
            }

            Intent intent1 = new Intent("json.data.received");
            intent1.putExtra(Util.KEY_RESPONSE,response.toString());
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent1);

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
