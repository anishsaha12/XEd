package com.rising.dots.xed;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TipsScrollingActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private ListView lv;

    // URL to get contacts JSON
    private static String url = "https://salutem-frontida.firebaseio.com/data.json";

    ArrayList<HashMap<String, String>> newsList;

    String[] listNewsStuff = new String[6];

    CustomList listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_scrolling);

        // Set fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        newsList = new ArrayList<>();

        lv = (ListView) findViewById(R.id.list);


        new GetNews().execute();


    }

    private class GetNews extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    String desc="";
                    HashMap<String, String> listStuff;

                    // Getting JSON Array node
                    JSONArray JA = jsonObj.getJSONArray("stock");
                    desc="";
                    listStuff = new HashMap<>();
                    // looping through All BTC
                    for (int i = 0; i < JA.length(); i++) {
                        String c = JA.getString(i);//.getJSONObject(i);

                        desc += c.toString()+"\n";
                        // tmp hash map for single contact

                    }
                    listNewsStuff[0]=desc;
                    listStuff.put("stock",desc);
                    newsList.add(listStuff);

                    // Getting JSON Array node
                    JA = jsonObj.getJSONArray("performance");
                    desc="";
                    listStuff = new HashMap<>();
                    // looping through All BTC
                    for (int i = 0; i < JA.length(); i++) {
                        String c = JA.getString(i);//.getJSONObject(i);

                        desc += c.toString()+"\n";
                        // tmp hash map for single contact

                    }
                    listNewsStuff[1]=desc;
                    listStuff.put("performance",desc);
                    newsList.add(listStuff);

                    // Getting JSON Array node
                    JA = jsonObj.getJSONArray("usd");
                    desc="";
                    listStuff = new HashMap<>();
                    // looping through All BTC
                    for (int i = 0; i < JA.length(); i++) {
                        String c = JA.getString(i);//.getJSONObject(i);

                        desc += c.toString()+"\n";
                        // tmp hash map for single contact

                    }
                    listNewsStuff[2]=desc;
                    listStuff.put("usd",desc);
                    newsList.add(listStuff);

                    // Getting JSON Array node
                    JA = jsonObj.getJSONArray("gbp");
                    desc="";
                    listStuff = new HashMap<>();
                    // looping through All BTC
                    for (int i = 0; i < JA.length(); i++) {
                        String c = JA.getString(i);//.getJSONObject(i);

                        desc += c.toString()+"\n";
                        // tmp hash map for single contact

                    }
                    listNewsStuff[3]=desc;
                    listStuff.put("gbp",desc);
                    newsList.add(listStuff);

                    // Getting JSON Array node
                    JA = jsonObj.getJSONArray("eur");
                    desc="";
                    listStuff = new HashMap<>();
                    // looping through All BTC
                    for (int i = 0; i < JA.length(); i++) {
                        String c = JA.getString(i);//.getJSONObject(i);

                        desc += c.toString()+"\n";
                        // tmp hash map for single contact

                    }
                    listNewsStuff[4]=desc;
                    listStuff.put("eur",desc);
                    newsList.add(listStuff);

                    // Getting JSON Array node
                    JA = jsonObj.getJSONArray("btc");
                    desc="";
                    listStuff = new HashMap<>();
                    // looping through All BTC
                    for (int i = 0; i < JA.length(); i++) {
                        String c = JA.getString(i);//.getJSONObject(i);

                        desc += c.toString()+"\n";
                        // tmp hash map for single contact

                    }
                    listNewsStuff[5]=desc;
                    listStuff.put("btc",desc);
                    newsList.add(listStuff);

                    Log.d("OP",listNewsStuff[2].toString());


                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);



            listAdapter = new CustomList(TipsScrollingActivity.this, listNewsStuff);
            lv.setAdapter(listAdapter);
        }

    }
}
