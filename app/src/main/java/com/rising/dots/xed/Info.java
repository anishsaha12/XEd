package com.rising.dots.xed;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

/**
 * Created by anish on 31-03-2018.
 */

public class Info extends AppCompatActivity {

    TextToSpeech t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        // Set fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

        Bundle extras = getIntent().getExtras();
        int type=0;
        if(extras != null) {
            type= extras.getInt("action");
        }

        final TextView tvContent = (TextView) findViewById(R.id.tvContent);

        switch (type){
            case 1:
                tvContent.setText(getText(R.string.createAccount));
                break;
            case 2:
                tvContent.setText(getText(R.string.deposit));
                break;
            case 3:
                tvContent.setText(getText(R.string.withdraw));
                break;
            case 4:
                tvContent.setText(getText(R.string.invOpportunities));
                break;
            case 5:
                tvContent.setText(getText(R.string.doDont));
                break;
            default:
        }
        final int min = 1;
        final int max = 7;
        final int random = getRandomNumber(min,max);

        final ImageView ava = (ImageView) findViewById(R.id.ivAvatar);
        int id = getResources().getIdentifier("com.rising.dots.xed:drawable/avataaars" + random, null, null);
        ava.setImageResource(id);

        ava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int random = getRandomNumber(min,max);
                int id = getResources().getIdentifier("com.rising.dots.xed:drawable/avataaars" + random, null, null);
                ava.setImageResource(id);
                t1.speak(tvContent.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });

    }

    public void onPause(){
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }
    private int getRandomNumber(int min,int max) {
        return (new Random()).nextInt((max - min) + 1) + min;
    }


}
