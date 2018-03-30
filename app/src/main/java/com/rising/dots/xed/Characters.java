package com.rising.dots.xed;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

/**
 * Created by anish on 30-03-2018.
 */

public class Characters extends GameObject {

    private int mode = 1;   //character walks till desk
    private int newX=0;

    private static final int ROW_RIGHT_TO_LEFT = 1;
    private static final int ROW_LEFT_TO_RIGHT = 0;

    // Row index of Image are being used.
    private int rowUsing = ROW_LEFT_TO_RIGHT;

    private int colUsing;

    private Bitmap[] leftToRights;
    private Bitmap[] rightToLefts;

    // Velocity of game character (pixel/millisecond)
    public static final float VELOCITY = 0.3f;

    private int movingVectorX = 10;
    private int movingVectorY = 0;

    private long lastDrawNanoTime =-1;

    private MainSurface mainSurface;

    public int changeBG =0;

    public Characters(MainSurface gameSurface, Bitmap image, int x, int y) {
        super(image, 2, 8, x, y);

        this.mainSurface= gameSurface;

        this.rightToLefts = new Bitmap[colCount];
        this.leftToRights = new Bitmap[colCount];

        for(int col = 0; col< this.colCount; col++ ) {
            this.rightToLefts[col]  = this.createSubImageAt(ROW_RIGHT_TO_LEFT, col);
            this.leftToRights[col] = this.createSubImageAt(ROW_LEFT_TO_RIGHT, col);
        }

    }

    public Bitmap[] getMoveBitmaps()  {
        switch (rowUsing)  {
            case ROW_LEFT_TO_RIGHT:
                return this.leftToRights;
            case ROW_RIGHT_TO_LEFT:
                return this.rightToLefts;
            default:
                return null;
        }
    }

    public Bitmap getCurrentMoveBitmap()  {
        Bitmap[] bitmaps = this.getMoveBitmaps();
        return bitmaps[this.colUsing];
    }


    public void update()  {
        if(mode==1){

            // Current time in nanoseconds
            long now = System.nanoTime();

            // Never once did draw.
            if (lastDrawNanoTime == -1) {
                lastDrawNanoTime = now;
            }
            // Change nanoseconds to milliseconds (1 nanosecond = 1000000 milliseconds).
            int deltaTime = (int) ((now - lastDrawNanoTime) / 1000000);

            // Distance moves
            float distance = VELOCITY * deltaTime;

            double movingVectorLength = Math.sqrt(movingVectorX * movingVectorX + movingVectorY * movingVectorY);

            newX = (x+(int) (distance * movingVectorX / movingVectorLength));
            if(newX<360){
                this.colUsing++;
                if (colUsing >= this.colCount) {
                    this.colUsing = 0;
                }

                this.x = newX;
            }

        }else {
            this.colUsing++;
            if (colUsing >= this.colCount) {
                this.colUsing = 0;
            }
            // Current time in nanoseconds
            long now = System.nanoTime();

            // Never once did draw.
            if (lastDrawNanoTime == -1) {
                lastDrawNanoTime = now;
            }
            // Change nanoseconds to milliseconds (1 nanosecond = 1000000 milliseconds).
            int deltaTime = (int) ((now - lastDrawNanoTime) / 1000000);

            // Distance moves
            float distance = VELOCITY * deltaTime;

            double movingVectorLength = Math.sqrt(movingVectorX * movingVectorX + movingVectorY * movingVectorY);

            // Calculate the new position of the game character.
            this.x = x + (int) (distance * movingVectorX / movingVectorLength);
            this.y = y + (int) (distance * movingVectorY / movingVectorLength);

            // When the game's character touches the edge of the screen, then change direction

            if (this.x < 0) {
                this.x = 0;
                this.movingVectorX = -this.movingVectorX;
            } else if (this.x > this.mainSurface.getWidth() - width) {
                this.x = this.mainSurface.getWidth() - width;
                this.movingVectorX = -this.movingVectorX;
            }

            if (this.y < 0) {
                this.y = 0;
                this.movingVectorY = -this.movingVectorY;
            } else if (this.y > this.mainSurface.getHeight() - height) {
                this.y = this.mainSurface.getHeight() - height;
                this.movingVectorY = -this.movingVectorY;
            }

            // rowUsing
            if (movingVectorX > 0) {
                this.rowUsing = ROW_LEFT_TO_RIGHT;
            } else {
                this.rowUsing = ROW_RIGHT_TO_LEFT;
            }
        }
    }

    public void draw(Canvas canvas)  {
        Bitmap bitmap = this.getCurrentMoveBitmap();
        canvas.drawBitmap(bitmap,x, y, null);

        if (mode==1){       //walk till desk mode
            if (newX>=360){        //reach the desk
                changeBG=1;
                //Log.d("desk","reached");
            }
        }

        this.lastDrawNanoTime= System.nanoTime();
    }

    public void setMovingVector(int movingVectorX, int movingVectorY)  {
        this.movingVectorX= movingVectorX;
        this.movingVectorY = movingVectorY;
    }
}

