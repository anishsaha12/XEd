package com.rising.dots.xed;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by anish on 30-03-2018.
 */

public class MainSurface extends SurfaceView implements SurfaceHolder.Callback {

    private MainAnimThread gameThread;
    private Characters sprite1;
    private Bitmap bgBitmap, bgBitmapChat;
    private Paint paint = new Paint();
    private int alpha=0;
    private Context context;

    public MainSurface(Context context){
        super(context);
        this.context = context;

        // Make Game Surface focusable so it can handle events.
        this.setFocusable(true);

        // Sét callback.
        this.getHolder().addCallback(this);

        bgBitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.bg_help);
        bgBitmapChat = BitmapFactory.decodeResource(this.getResources(),R.drawable.bg_help_options);

    }

    public void update()  {
        this.sprite1.update();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x=  (int)event.getX();
            int y = (int)event.getY();

            if(y>=506 && y<=551 && x>=200 && x<=890){   //Create account
                Log.d("create","account");
                Intent i = new Intent(context, Info.class);
                i.putExtra("action", 1);
                context.startActivity(i);
            }else if(y>=590 && y<=680 && x>=200 && x<=890){   //Method of depositing
                Log.d("Method","deposit");
                Intent i = new Intent(context, Info.class);
                i.putExtra("action", 2);
                context.startActivity(i);
            }else if(y>=710 && y<=770 && x>=200 && x<=890){   //Withdraw Cash
                Log.d("Withdraw","cash");
                Intent i = new Intent(context, Info.class);
                i.putExtra("action", 3);
                context.startActivity(i);
            }else if(y>=820 && y<=870 && x>=200 && x<=890){   //Investment opportunities
                Log.d("Investment","opportunities");
                Intent i = new Intent(context, Info.class);
                i.putExtra("action", 4);
                context.startActivity(i);
            }else if(y>=915 && y<=980 && x>=200 && x<=890){   //Do's and Dont's
                Log.d("Do's","Dont's");
                Intent i = new Intent(context, Info.class);
                i.putExtra("action", 5);
                context.startActivity(i);
            }

            return true;
        }
        return false;
    }

    @Override
    public void draw(Canvas canvas)  {
        super.draw(canvas);

        if (sprite1.changeBG==0) {
            canvas.drawBitmap(bgBitmap, 0, 0, null);
        }else{
            /*
            alpha++;
            if (alpha>100)
                alpha=100;
            paint.setAlpha(alpha);*/
            canvas.drawBitmap(bgBitmapChat, 0, 0, null);
        }
        this.sprite1.draw(canvas);
    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Bitmap charBitmap1 = BitmapFactory.decodeResource(this.getResources(),R.drawable.sprite1);
        this.sprite1 = new Characters(this,charBitmap1,-200,1300);

        this.gameThread = new MainAnimThread(this,holder);
        this.gameThread.setRunning(true);
        this.gameThread.start();

    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry= true;
        while(retry) {
            try {
                this.gameThread.setRunning(false);

                // Parent thread must wait until the end of GameThread.
                this.gameThread.join();
                retry=false;
            }catch(InterruptedException e)  {
                e.printStackTrace();
            }
        }
    }
}
