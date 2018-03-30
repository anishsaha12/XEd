package com.rising.dots.xed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

/**
 * Created by anish on 31-03-2018.
 */

public class HelpDesk extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        MainSurface mainSurface = new MainSurface(this);
        this.setContentView(mainSurface);


        // Set fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /*
        // Set No Title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        */
    }




}