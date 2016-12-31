package jewel.mirev.zafinlabs.com.zafingame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Rick on 2016-12-21.
 */
public class Minion {

    private String mName;
    private int mLeft;
    private int mTop;
    private int mRight;
    private int mBottom;
    private int mStatus;
    private Bitmap mFrame;
    private Bitmap mIcon;
    private int mFrameBorderSize;
    private boolean mIsActive;
    private boolean mIsDead;
    private boolean mIsSelected;
    private int mPos;

//    public Minion(String name, Bitmap frame, Bitmap icon){
//        this.mName = name;
//        this.mFrame = frame;
//        this.mIcon = icon;
//        this.mIsDead = false;
//        this.mIsActive = true;
//        this.mIsSelected = false;
//    }

//    public void init(int left, int top, int right, int bottom, int pos){
//        this.mLeft = left;
//        this.mTop = top;
//        this.mRight = right;
//        this.mBottom = bottom;
//        int width = right - left;
//        if(width > 0){
//            //10% b
//            mFrameBorderSize = width/10;
//        }
//        this.mPos = pos;
//    }
//
//    public void onDraw(Canvas canvas) {
//        if(!isDead()){
//            Paint paint = new Paint();
//            if(isSelected()) {
//                Rect frameDest = new Rect(mLeft, mTop, mRight, mBottom);
//                canvas.drawBitmap(mFrame, null, frameDest, paint);
//            }
//            Rect iconDest = new Rect(mLeft + mFrameBorderSize, mTop + mFrameBorderSize, mRight - mFrameBorderSize, mBottom - mFrameBorderSize);
//            canvas.drawBitmap(mIcon,null, iconDest, paint);
//        }
//
//    }
//
//    public int getLeft(){
//        return this.mLeft;
//    }
//
//    public int getTop(){
//        return this.mTop;
//    }
//
//    public int getRight(){
//        return this.mRight;
//    }
//
//    public int getBottom(){
//        return this.mBottom;
//    }
//
////    public void setPos(int pos){
////        this.mPos = pos;
//////    }
//
//    public int getPos(){
//        return this.mPos;
//    }
//
//    public String getName(){
//        return this.mName;
//    }
//
//    public boolean isActive(){
//        return mIsActive;
//    }
//
//    public boolean isSelected(){
//        return mIsSelected;
//    }
//
//    public boolean isDead(){
//        return mIsDead;
//    }
//
//    public void kill(){
//        this.mIsDead = true;
//        this.mIsActive = false;
//        this.mIsSelected = false;
//    }
//
//    public void select(){
//        this.mIsActive = false;
//        this.mIsSelected = true;
//    }
//
//    public void unselect(){
//        this.mIsActive = true;
//        this.mIsSelected = false;
//    }
//
//    public void setActive(){
//        this.mIsActive = true;
//        this.mIsSelected = false;
//    }
//
//    public Bitmap getFrame(){
//        return this.mFrame;
//    }
//
//    public Bitmap getIcon(){
//        return this.mIcon;
//    }
//
//    public String toString(){
//        return "name=" + mName + ",pos=" + mPos + ",[left=" + mLeft + ",top=" + mTop + ",right=" +  mRight + ",bottom=" + mBottom + "]";
//    }
}
