package com.rising.dots.xed;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.rising.dots.xed.R;

public class MainActivity extends AppCompatActivity {

    private GestureDetectorCompat gestureObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        gestureObject = new GestureDetectorCompat(this, new LearnGesture());

    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class LearnGesture extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX,float velocityY){
            if (event2.getX() > event1.getX())
            {
               //Left to right
                Intent myIntent = new Intent(MainActivity.this, FinanceNews.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
            }
            else
            if (event2.getX() < event1.getX())
            {
                //right to left
                Intent myIntent = new Intent(MainActivity.this, HelpDesk.class);
                startActivity(myIntent);

                overridePendingTransition(R.anim.right_in, R.anim.left_to_right);
            }
            return true;

        }
    }
    public void newAct (View view){
        Intent myIntent = new Intent(MainActivity.this, PersonalFinance.class);
        startActivity(myIntent);
        overridePendingTransition(R.anim.bottom_in,R.anim.top_out);
    }
}
