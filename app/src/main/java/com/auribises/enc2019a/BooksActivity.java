package com.auribises.enc2019a;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


// Assignments:
// 1. Parse JSON Data with a Library called Google GSON
// 2. Volley -> OkHttp | Retrofit
// 3. Create a BroadcastReceiver which Receives Battery LOW Actions and tells the amount of battery in phone
// 4. Create a BroadcastReceiver which Receives SMS and shows the data


public class BooksActivity extends AppCompatActivity {

    ListView listView;
    String webServiceUrl;
    BookFetchTask task;
    //BookFetchThread thread;
    ArrayList<Book> bookList;

    StringBuffer response;

    BooksAdapter adapter;

    ProgressDialog progressDialog;

    BookReceiver bookReceiver;


    RequestQueue requestQueue;
    StringRequest stringRequest;


    void initViews(){
        listView = findViewById(R.id.listView);
        webServiceUrl = "http://www.json-generator.com/api/json/get/chQLxhBjaW?indent=2";

        //thread = new BookFetchThread();
        //thread.start();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);

        //task = new BookFetchTask();
        //task.execute();


        /*
        bookReceiver = new BookReceiver();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("json.data.received"); // Custom Action
        //intentFilter.addAction(Intent.ACTION_BATTERY_LOW); // Built In Action
        //intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);

        // Registration/Subscription of Events ot Actions
        LocalBroadcastManager.getInstance(this).registerReceiver(bookReceiver,intentFilter);


        Intent intent = new Intent(BooksActivity.this, MyIntentService.class);
        intent.putExtra(Util.KEY_URL,webServiceUrl);
        startService(intent);
        progressDialog.show();

        //stopService(intent);
        */


        fetchBooks();

    }

    void fetchBooks(){

        progressDialog.show();

        requestQueue = Volley.newRequestQueue(this);

        stringRequest = new StringRequest(Request.Method.GET, webServiceUrl,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Parse JSON Data
                        try{

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("bookstore");

                            bookList = new ArrayList<>();

                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jObj = jsonArray.getJSONObject(i);

                                // JSON Object is now represented as a Java Object
                                Book book = new Book();
                                book.name = jObj.getString("name");
                                book.author = jObj.getString("author");
                                book.price = jObj.getString("price");

                                bookList.add(book);
                            }

                            adapter = new BooksAdapter(BooksActivity.this,R.layout.book_list_item,bookList);
                            listView.setAdapter(adapter);

                            progressDialog.dismiss();

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(BooksActivity.this,"Some Error",Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }
        );

        requestQueue.add(stringRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        initViews();
    }


    /*class BookFetchThread extends Thread{
        @Override
        public void run() {

        }
    }*/

    // Create a Nested Class -> AsyncTask
    class BookFetchTask extends AsyncTask{


        @Override
        protected void onPreExecute() {
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Object o) {
            //Toast.makeText(BooksActivity.this,response.toString(),Toast.LENGTH_LONG).show();

            // Parse JSON Data
            try{

                JSONObject jsonObject = new JSONObject(response.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("bookstore");

                bookList = new ArrayList<>();

                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jObj = jsonArray.getJSONObject(i);

                    // JSON Object is now represented as a Java Object
                    Book book = new Book();
                    book.name = jObj.getString("name");
                    book.author = jObj.getString("author");
                    book.price = jObj.getString("price");

                    bookList.add(book);
                }

                adapter = new BooksAdapter(BooksActivity.this,R.layout.book_list_item,bookList);
                listView.setAdapter(adapter);

                progressDialog.dismiss();

            }catch (Exception e){
                e.printStackTrace();
            }


        }

        //fetch data from WebService shall go in doInBackground method
        @Override
        protected Object doInBackground(Object[] objects) {

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

            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }
    }


    class BookReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();

            if(action.equals("json.data.received")){

                String jsonDataFromService = intent.getStringExtra(Util.KEY_RESPONSE);

                // Parse JSON Data
                try{

                    JSONObject jsonObject = new JSONObject(jsonDataFromService);
                    JSONArray jsonArray = jsonObject.getJSONArray("bookstore");

                    bookList = new ArrayList<>();

                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jObj = jsonArray.getJSONObject(i);

                        // JSON Object is now represented as a Java Object
                        Book book = new Book();
                        book.name = jObj.getString("name");
                        book.author = jObj.getString("author");
                        book.price = jObj.getString("price");

                        bookList.add(book);
                    }

                    adapter = new BooksAdapter(BooksActivity.this,R.layout.book_list_item,bookList);
                    listView.setAdapter(adapter);

                    progressDialog.dismiss();

                }catch (Exception e){
                    e.printStackTrace();
                }

            }


            /*
            if(action.equals(Intent.ACTION_BATTERY_LOW)){

            }*/

        }
    }

}
