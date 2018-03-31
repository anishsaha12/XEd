package com.rising.dots.xed;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    List<trans> Hist;
    ListInfo i;
    SQLiteDatabase mDatabase;
    ListView History;
    HistAdapter histAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // Set fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        History = (ListView)findViewById(R.id.HistListView);
        Hist = new ArrayList<>();
        mDatabase = openOrCreateDatabase("TH.db", MODE_PRIVATE, null);
    }
    public void viewHist(View view)
    {
        showHistory();
    }
    public void Transact (View view)
    {
        EditText ED1;
        EditText ED2;
        EditText ED3;
        ED1 = (EditText)findViewById(R.id.AmtET);
        ED2 = (EditText)findViewById(R.id.ReasonET);
        ED3 = (EditText)findViewById(R.id.DatET);
        String amt = ED1.getText().toString();
        String Reason = ED2.getText().toString();
        String Dat = ED3.getText().toString();

        trans Trans = new trans();
        Trans.setAmount(amt);
        Trans.setReason(Reason);
        Trans.setDat(Dat);
        Hist.add(Trans);
        showHistory();
    }
    private void showHistory() {
        histAdapter = new HistAdapter(this, R.layout.list_layout, Hist);
        History.setAdapter(histAdapter);
    }


}
