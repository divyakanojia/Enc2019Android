package com.auribises.enc2019a;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MyBookService extends Service {

    String webServiceUrl;
    StringBuffer response;

    public MyBookService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Log.i("MyBookService","==onCreate==");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        webServiceUrl = intent.getStringExtra(Util.KEY_URL);
        Log.i("MyBookService","==onStartCommand=="+webServiceUrl);

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


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i("MyBookService","==onDestroy==");
        super.onDestroy();
    }
}
