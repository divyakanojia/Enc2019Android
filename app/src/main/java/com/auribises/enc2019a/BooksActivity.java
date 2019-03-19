package com.auribises.enc2019a;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class BooksActivity extends AppCompatActivity {

    ListView listView;
    String webServiceUrl;
    BookFetchTask task;
    //BookFetchThread thread;
    ArrayList<Book> bookList;

    StringBuffer response;

    BooksAdapter adapter;

    ProgressDialog progressDialog;

    void initViews(){
        listView = findViewById(R.id.listView);
        webServiceUrl = "http://www.json-generator.com/api/json/get/chQLxhBjaW?indent=2";

        //thread = new BookFetchThread();
        //thread.start();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);

        task = new BookFetchTask();
        task.execute();


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

}
