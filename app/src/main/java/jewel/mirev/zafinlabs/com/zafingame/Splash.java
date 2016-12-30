package jewel.mirev.zafinlabs.com.zafingame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Rick on 2016-12-23.
 */
public class Splash {
    private int mWidth;
    private int mHeight;
    private Bitmap mIcon;
    private boolean mIsComplete;
    private long mLiveTime;
    private int mProgressBarSpeed = 5;
    private int mProgressBarWidth;
    private int mProgressBarHeight;
    private int mProgressBarLeft;
    private int mProgressBarRight;
    private int mProgressBarTop;
    private int mProgressBarBottom;
    private int mSpeed = 10;
    private int mUnit;
    private int mIconLeft;
    private int mIconRight;
    private int mIconTop;
    private int mIconBottom;
    private int mLevel;
    private int mIconNum;
    private int mBakPicHeight;
    private long mStartTime;
    private final static int INTERVAL = 3000;
    private long mCurTime;
    private int mCurWait = 0;
    private int mMaxWait = 3;
    private Bitmap[] mNumbers;


    public Splash(Bitmap icon, int width, int height, int level){
        this.mWidth = width;
        this.mHeight = height;
        this.mIcon = icon;
        this.mProgressBarWidth = 10;
        mProgressBarHeight = height/5;

        this.mProgressBarTop = mProgressBarHeight * 3;
        this.mProgressBarBottom = mProgressBarHeight * 4;
        this.mProgressBarLeft = 0;
        this.mProgressBarRight = width/10;
        this.mUnit = mProgressBarRight;
        this.mIsComplete = false;
        int iconBorder = mProgressBarHeight/5;
//        this.mIconTop = mProgressBarHeight * 2 + iconBorder;
//        this.mIconBottom = mProgressBarHeight * 3 - iconBorder;
//        this.mIconLeft = (width - mProgressBarHeight)/2 + iconBorder;
//        this.mIconRight = mIconLeft + mProgressBarHeight - iconBorder;
        this.mLevel = level;
        int iconWidthRatio = (1024*100)/width;
        mBakPicHeight = (iconWidthRatio * 640)/100;
        mIconNum =  height/mBakPicHeight;
        if(height%mBakPicHeight > 0){
            mIconNum = mIconNum + 1;
        }
        this.mIconTop = 0;
        this.mIconBottom = mBakPicHeight;
        this.mIconLeft = 0;
        this.mIconRight = width;
        this.mStartTime = System.currentTimeMillis();
//        this.mNumbers = numbers;
//        icon.get
    }

    private void update(){
        if(!mIsComplete){
//            mCurTime = System.currentTimeMillis();
//            if(mStartTime - mCurTime > INTERVAL){
//                mCurWait++;
//                mStartTime = System.currentTimeMillis();
//                if(mCurWait >= mMaxWait){
//                    mIsComplete = true;
//                }
//            }
            mProgressBarRight =  mProgressBarRight + mUnit;
            if(mProgressBarRight >= mWidth){
                mIsComplete = true;
                mProgressBarRight = mWidth - mUnit;
            }
        }
    }

    public boolean isComplete(){
        return this.mIsComplete;
    }

    public void onDraw(Canvas canvas) {
        if(mIsComplete){
            return;
        }
//
//        Paint colorPaint = new Paint();
//        colorPaint.setColor(Color.BLACK);
//        colorPaint.setStyle(Paint.Style.FILL);
//        Rect rec = new Rect(0, 0, mWidth, mHeight);
//        canvas.drawRect(rec,colorPaint);

        int num = mIconNum;

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        Rect desc = new Rect(0, 0, mWidth, mHeight);
        canvas.drawRect(desc, paint);

        Paint hPaint = new Paint();
//        for(int i = 0; i < mIconNum; i++){
//            int top = i*mBakPicHeight;
//            int bot = (i+1)*mBakPicHeight;
//            Rect iconDest = new Rect(mIconLeft, top, mIconRight, bot);
//            canvas.drawBitmap(mIcon, null, iconDest, hPaint);
//        }

//        Paint leftSidePaint = new Paint();
//        leftSidePaint.setColor(Color.BLUE);
//        leftSidePaint.setStyle(Paint.Style.FILL);
//        Rect leftSideDest = new Rect(mProgressBarLeft, mProgressBarTop, mProgressBarRight, mProgressBarBottom);
//
//        Paint rightSidePaint = new Paint();
//        rightSidePaint.setColor(Color.BLACK);
//        rightSidePaint.setStyle(Paint.Style.FILL);
//        Rect rightSideDest = new Rect(mProgressBarRight, mProgressBarTop, mWidth, mProgressBarBottom);
//

        int textSize = (mProgressBarHeight)/5;
        hPaint.setTextSize(textSize);
        hPaint.setColor(Color.RED);
        hPaint.setTextAlign(Paint.Align.LEFT);
        int percent = (mProgressBarRight * 100)/mWidth;
        String str = "Loading Level " + mLevel + " [" + percent +"%]";
        int textStartPos = mWidth/10;
        Rect blankDesc = new Rect(0, mProgressBarHeight, mWidth, mProgressBarHeight*2);
//        Rect iconDest = new Rect(mIconLeft, mIconTop, mIconRight, mIconBottom);
//        Paint blankPaint = new Paint();
//        blankPaint.setColor(Color.WHITE);
//        blankPaint.setStyle(Paint.Style.FILL);
//        canvas.drawRect(blankDesc,blankPaint);
        canvas.drawText(str, textStartPos, (mProgressBarHeight*3)/2, hPaint);
//        canvas.drawBitmap(mIcon, null, iconDest, hPaint);
//        canvas.drawRect(leftSideDest,leftSidePaint);
//        canvas.drawRect(rightSideDest,rightSidePaint);

//        int left =  mWidth/4;
//        int top = mProgressBarHeight * 2;
//        int right = mWidth/4 + mWidth/2;
//        int bot = top + mWidth/2;
//        Rect numberRec = new Rect(left, top, right, bot);
//        canvas.drawBitmap(mNumbers[mCurWait], null, numberRec, hPaint);
        update();
    }
}
