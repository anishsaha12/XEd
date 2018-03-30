package com.rising.dots.xed;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by anish on 30-03-2018.
 */

public class MainAnimThread extends Thread {

    private boolean running;
    private MainSurface gameSurface;
    private SurfaceHolder surfaceHolder;

    public MainAnimThread(MainSurface gameSurface, SurfaceHolder surfaceHolder)  {
        this.gameSurface= gameSurface;
        this.surfaceHolder= surfaceHolder;
    }

    @Override
    public void run()  {
        long startTime = System.nanoTime();

        while(running)  {
            Canvas canvas= null;
            try {
                // Get Canvas from Holder and lock it.
                canvas = this.surfaceHolder.lockCanvas();

                // Synchronized
                synchronized (canvas)  {
                    this.gameSurface.update();
                    this.gameSurface.draw(canvas);

                }
            }catch(Exception e)  {
                // Do nothing.
            } finally {
                if(canvas!= null)  {
                    // Unlock Canvas.
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            long now = System.nanoTime() ;
            // Interval to redraw game
            // (Change nanoseconds to milliseconds)
            long waitTime = (now - startTime)/100000;
            if(waitTime < 10)  {
                waitTime= 10; // Millisecond.
            }
            System.out.print(" Wait Time="+ waitTime);

            try {
                // Sleep.
                this.sleep(waitTime);
            } catch(InterruptedException e)  {

            }
            startTime = System.nanoTime();
            System.out.print(".");
        }
    }

    public void setRunning(boolean running)  {
        this.running= running;
    }
}