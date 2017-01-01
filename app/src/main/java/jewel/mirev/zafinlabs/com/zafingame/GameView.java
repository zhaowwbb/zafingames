package jewel.mirev.zafinlabs.com.zafingame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.util.Log;

/**
 * Created by Rick on 2016-12-05.
 */
public class GameView extends SurfaceView {
    private GameLoopThread gameLoopThread;

    private long lastClick;
    private long mStartTime;
    private Bitmap mContinue;
//    private Bitmap mReset;
//
    private Bitmap mFrame;
    private Bitmap mFramefocus;
    private Bitmap mGameOverImg;
    private Bitmap mLOL001Img;
    private Bitmap mLOL002Img;
    private Bitmap mLOL003Img;
    private Bitmap mLOL004Img;
    private Bitmap mLOL005Img;
    private Bitmap mLOL006Img;
    private Bitmap mLOL007Img;
    private Bitmap mLOL008Img;
    private Bitmap mLOL009Img;
    private Bitmap mLOL010Img;
    private Bitmap mLOL011Img;
    private Bitmap mLOL012Img;
    private Bitmap mLOL013Img;
    private Bitmap mLOL014Img;
    private Bitmap mLOL015Img;
    private Bitmap mLOL016Img;
    private Bitmap mLOL017Img;
    private Bitmap mLOL018Img;


    private Bitmap mNumber0Img;
    private Bitmap mNumber1Img;
    private Bitmap mNumber2Img;
    private Bitmap mNumber3Img;
    private Bitmap mNumber4Img;
    private Bitmap mNumber5Img;
    private Bitmap mNumber6Img;
    private Bitmap mNumber7Img;
    private Bitmap mNumber8Img;
    private Bitmap mNumber9Img;
    private Bitmap mToLeftImg;
    private Bitmap mLevelUpImg;
    private Bitmap mLevelImg;
    private Bitmap mScoreImg;
    private Bitmap mBackground0Img;
    private Bitmap mBackground1Img;
    private Bitmap mBackground2Img;
    private Bitmap mBackground3Img;
    private Bitmap mBackground4Img;
    private Bitmap mBackground5Img;
    private Bitmap mBackground6Img;
    private Bitmap mBackground7Img;
    private Bitmap mBackground8Img;
    private Bitmap mBackground9Img;
    private Bitmap mBackgroundGameoverImg;

    private Bitmap mAjuImg;
    private Bitmap mCliffImg;
    private Bitmap mDavidImg;
    private Bitmap mDenishImg;
    private Bitmap mFrankImg;
    private Bitmap mJenstonImg;
    private Bitmap mKristoperImg;
    private Bitmap mHaoImg;
    private Bitmap mKyleImg;
    private Bitmap mLloydImg;
    private Bitmap mRossImg;
    private Bitmap mMonaImg;
    private Bitmap mNeerajImg;
    private Bitmap mPaulImg;
    private Bitmap mRickImg;
    private Bitmap mRitchImg;
    private Bitmap mShijuImg;
    private Bitmap mXueqianImg;



    private boolean mIsGameOver;

    private int mLevel;
    private boolean mAllLevelPassFlag;
    private Minion minions[];
    private String TAG = "GameView";
    private int mNextLevelCount = 0;
    private int mToCompleteCount = 0;
    private long mLevelRemainedTime = 0;
//    private final static int MINION_SIZE = 20;

    private final static long LEVEL_TIME = 500*1000;
    private Bitmap[] mNumberImgArray = new Bitmap[10];
    private TopBar mTopBar;
    private int mTopBarSize;
    private ProgressBar mProgressBar;
    private LevelUpSplash mLevelUpSplash;
    private int mCandyAreaTop;
    private LoadingPage mLoading;
    private int mTopBarHeight;
    private int mProgressBarHeight;
    private int mTotalMinions = 20;
    private boolean mLevelUpInProgress;
    private final static int TOTAL_BACKGROUND_PIC = 10;
    private Bitmap[] mBackgroundPics = new Bitmap[TOTAL_BACKGROUND_PIC];

    private List<Minion> mZafinPeople = new ArrayList<Minion>();

    private class LoadingPage{
        private boolean mIsComplete;
        private int mWidth;
        private int mHeight;

        private int mProgressBarHeight;
//        private int mProgressBarLeft;
        private int mProgressBarRight;

        private int mUnit;

        public LoadingPage(int width, int height){
            this.mWidth = width;
            this.mHeight = height;
//            this.mProgressBarWidth = 10;
            mProgressBarHeight = height/5;

//            this.mProgressBarTop = mProgressBarHeight * 3;
//            this.mProgressBarBottom = mProgressBarHeight * 4;
//            this.mProgressBarLeft = 0;
            this.mProgressBarRight = width/20;
            this.mUnit = mProgressBarRight;
            this.mIsComplete = false;
        }

        public boolean isComplete(){
            return this.mIsComplete;
        }

        public void onDraw(Canvas canvas) {
            if(mIsComplete){
                return;
            }

            Paint paint = new Paint();
//            paint.setColor(Color.BLACK);
            Rect desc = new Rect(0, 0, mWidth, mHeight);
//            canvas.drawRect(desc, paint);
            canvas.drawBitmap(mBackground0Img,null, desc, paint);

            Paint hPaint = new Paint();

            int textSize = (mProgressBarHeight)/5;
            hPaint.setTextSize(textSize);
            hPaint.setColor(Color.RED);
            hPaint.setTextAlign(Paint.Align.LEFT);
            int percent = (mProgressBarRight * 100)/mWidth;
            String str = "Loading Game [" + percent +"%]";
            int textStartPos = mWidth/10;
            canvas.drawText(str, textStartPos, (mProgressBarHeight*3)/2, hPaint);

            if(!mIsComplete){
                mProgressBarRight =  mProgressBarRight + mUnit;
                if(mProgressBarRight >= mWidth){
                    mIsComplete = true;
                    mProgressBarRight = mWidth - mUnit;
                }
            }
        }
    }

    private class LevelUpSplash{
        private int mIconLeft;
        private int mIconRight;
        private int mIconTop;
        private int mIconBottom;
        private int mLevel;
        private int mIconTopBoder;
        private int mSpeed = 50;
        private boolean mIsComplete;
        private int mHeight;
        private boolean mNeedRenderBottom;

        public LevelUpSplash(int width, int height, int level){
            this.mLevel = level;
            int unit = width/8;
            this.mIconLeft = unit;
            this.mIconRight = width - unit;
            this.mIconTop =  height;
            this.mIconBottom = height + unit*2;
            this.mIconTopBoder = height - width;
            this.mIsComplete = false;
            this.mHeight = height;
        }

        public boolean isComplete(){
            return this.mIsComplete;
        }

        public void onDraw(Canvas canvas) {
            mTopBar.onDraw(canvas);

            this.mIconTop = this.mIconTop - mSpeed;
            if(mIconTop < mIconTopBoder){
                this.mIsComplete = true;
                goLevel(mLevel);
            }else{
                //render background
                Paint paint = new Paint();
                paint.setColor(Color.WHITE);
//                Paint paint = new Paint();
                this.mIconBottom = this.mIconBottom - mSpeed;

//                Rect leftDesc = new Rect(0, mIconTop, mIconLeft, mIconBottom);
//                canvas.drawRect(leftDesc, paint);
//                Rect rightDesc = new Rect(mIconRight, mIconTop, getWidth(), mIconBottom);
//                canvas.drawRect(rightDesc, paint);
                Rect iconDest = new Rect(mIconLeft, mIconTop, mIconRight, mIconBottom);
                canvas.drawBitmap(mLevelUpImg, null, iconDest, paint);
            }
        }
    }

    private class ProgressBar{
        int mSize = mTopBarSize;
        int mProgressUnit = 3;
        int mProgressBarRight;
        int mTop;
        int mBorder;
        int mBottom;
        boolean mIsComplete = false;

        public ProgressBar(int size, int top, int bottom, int width){
            this.mSize = size;
            this.mTop = top;
            this.mBottom = bottom;
            this.mProgressBarRight = size;
            this.mBorder = width - mProgressUnit;
        }

        public void onDraw(Canvas canvas) {

            //render background
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            Rect desc = new Rect(0, mTop, getWidth(), mBottom);
            canvas.drawRect(desc, paint);

            if(mLevelUpSplash != null && !mLevelUpSplash.isComplete()){
                return;
            }

            Paint leftSidePaint = new Paint();
            leftSidePaint.setColor(Color.CYAN);
            leftSidePaint.setStyle(Paint.Style.FILL);
            int border = 4;
            Rect leftSideDest = new Rect(0 + border, mSize + border, mProgressBarRight, mSize*2 - border);

            Paint rightSidePaint = new Paint();
            rightSidePaint.setColor(Color.WHITE);
            rightSidePaint.setStyle(Paint.Style.FILL);
            Rect rightSideDest = new Rect(mProgressBarRight, mSize + border, getWidth() - border, mSize*2 - border);

            canvas.drawRect(leftSideDest,leftSidePaint);
            canvas.drawRect(rightSideDest,rightSidePaint);
            update();
        }

        public void update(){
            if(mProgressBarRight > mBorder){
                mIsGameOver = true;
                mIsComplete = true;
            }else{
                mProgressBarRight = mProgressBarRight + mProgressUnit;
            }
        }

    }

    private class TopBar{
        int mSize = mTopBarSize;
        int mTop;
        int mBottom;
        public TopBar(int size, int top){
            this.mSize = size;
            this.mTop = top;
            this.mBottom = mSize;
        }

        //allow 3 digit
        private void printNumber(Canvas canvas, List<Integer> levelImage, int left){
            Paint paint = new Paint();
            Rect targetDest = null;
            int numLeft = left;
            int numTop = mTop;
            int numRight;
            int numBot = mBottom;
            int pos;

            for(int i = 0; i < levelImage.size(); i++){
                numRight = numLeft + mSize;

                pos = levelImage.get(i);
//                Log.i(TAG, "TopBar rec, image index=" + pos + ", [left=" + numLeft + ",top=" + numTop + ",right=" +  numRight + ",bottom=" + numBot + "]");
                targetDest = new Rect(numLeft, numTop, numRight, numBot);
                canvas.drawBitmap(mNumberImgArray[pos], null, targetDest, paint);
                numLeft = numRight;
            }

        }

        public void onDraw(Canvas canvas) {
            int left = 0;
            int top = 0;
            int right = mSize;
            int bot = mSize;
            //render background
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            Rect desc = new Rect(0, top, getWidth(), bot);
            canvas.drawRect(desc, paint);

            Rect targetDest = new Rect(left, top, right, bot);
            canvas.drawBitmap(mLevelImg, null, targetDest, paint);
            left = left + right;
//            Log.i(TAG, "TopBar mLevel=" + mLevel);

            List<Integer> levelImage = getNumberImage(mLevel);

            printNumber(canvas, levelImage, left);

            int count = getmToCompleteCount();
            int total = getNextLevelCount();
            String levelStr = String.valueOf(mLevel);
            String countStr = String.valueOf(count);
            String totalStr = String.valueOf(total);
            int blankLeftPos = left + mSize * levelStr.length();
            int nextLevelPos = getWidth() - mSize*(1 + countStr.length() + 1 + totalStr.length());
            int countPos = nextLevelPos + mSize;
            int splitPos = countPos + mSize * (countStr.length());
            int totalPos = splitPos + mSize;
            //fill blank
            Paint blankPaint = new Paint();
            blankPaint.setColor(Color.WHITE);
            blankPaint.setStyle(Paint.Style.FILL);
            left = blankLeftPos;
            Rect blankDest = new Rect(left, top, nextLevelPos, bot);

            //move 5 (3 number + 2 space)
            left = nextLevelPos;
            right = left + mSize;
//            Log.i(TAG, "TopBar mNextLevelImg, [left=" + left + ",top=" + top + ",right=" +  right + ",bottom=" + bot + "]");
            targetDest = new Rect(left, top, right, bot);
            canvas.drawBitmap(mScoreImg, null, targetDest, paint);

            left = countPos;
//            levelImage = getNumberImage(getNextLevelCount());
//            Log.i(TAG, "getNextLevelCount()=" + getNextLevelCount() + ",levelImage=" + levelImage);
            levelImage = getNumberImage(count);
//            Log.i(TAG, "getmToCompleteCount()=" + count + ",levelImage=" + levelImage);
            printNumber(canvas, levelImage, left);
//            for(int i = 0; i < levelImage.size(); i++){
//                targetDest = new Rect(left + (i+1)*mSize, top, right + (i+1)*mSize, bot);
//                canvas.drawBitmap(levelImage.get(i), null, targetDest, paint);
//            }

            left = splitPos;
            right = left + mSize;
//            Log.i(TAG, "TopBar mToLeftImg, [left=" + left + ",top=" + top + ",right=" +  right + ",bottom=" + bot + "]");
            targetDest = new Rect(left, top, right, bot);
            canvas.drawBitmap(mToLeftImg, null, targetDest, paint);


            left = totalPos;
            levelImage = getNumberImage(getNextLevelCount());
//            Log.i(TAG, "getNextLevelCount()=" + total + ",levelImage=" + levelImage);
            printNumber(canvas, levelImage, left);
        }
    }

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
        private int mAnimationSpeed = 30;
        private int mAnimationTop;

        public Minion(String name, Bitmap frame, Bitmap icon){
            this.mName = name;
            this.mFrame = frame;
            this.mIcon = icon;
            this.mIsDead = false;
            this.mIsActive = false;
            this.mIsSelected = false;
        }

        public void init(int left, int top, int right, int bottom, int pos){
            this.mLeft = left;
            this.mTop = top;
            this.mRight = right;
            this.mBottom = bottom;
            int width = right - left;
            if(width > 0){
                //10% b
                mFrameBorderSize = width/10;
            }
            this.mPos = pos;
            this.mAnimationTop = mTop;
        }

        public void onDraw(Canvas canvas) {
            if(!isDead()){
                Paint paint = new Paint();
                Rect frameDest = new Rect(mLeft, mTop, mRight, mBottom);
                if(isSelected()) {
                    canvas.drawBitmap(mFramefocus, null, frameDest, paint);
                }else{
                    canvas.drawBitmap(mFrame, null, frameDest, paint);
                }
                Rect iconDest = new Rect(mLeft + mFrameBorderSize, mTop + mFrameBorderSize, mRight - mFrameBorderSize, mBottom - mFrameBorderSize);
                canvas.drawBitmap(mIcon,null, iconDest, paint);
            }else{
                if(isActive()){
                    mAnimationTop = mAnimationTop + mAnimationSpeed;
                    if(mAnimationTop >= mBottom){
                        mIsActive = false;
                    }else{
                        Paint paint = new Paint();
                        Rect frameDest = new Rect(mLeft, mAnimationTop, mRight, mBottom);
                        canvas.drawBitmap(mFramefocus, null, frameDest, paint);

                        Rect iconDest = new Rect(mLeft + mFrameBorderSize, mAnimationTop + mFrameBorderSize, mRight - mFrameBorderSize, mBottom - mFrameBorderSize);
                        canvas.drawBitmap(mIcon,null, iconDest, paint);
                    }
                }else{
                    //skip
                }
            }
        }

        public int getLeft(){
            return this.mLeft;
        }

        public int getTop(){
            return this.mTop;
        }

        public int getRight(){
            return this.mRight;
        }

        public int getBottom(){
            return this.mBottom;
        }

        public int getPos(){
            return this.mPos;
        }

        public String getName(){
            return this.mName;
        }

        public boolean isActive(){
            return this.mIsActive;
        }

        public boolean isSelected(){
            return mIsSelected;
        }

        public boolean isDead(){
            return mIsDead;
        }

        public void kill(){
            this.mIsDead = true;
            this.mIsActive = true;
            this.mIsSelected = true;
        }

        public void select(){
//            this.mIsActive = false;
            this.mIsSelected = true;
        }

        public void unselect() {
//            this.mIsActive = true;
            this.mIsSelected = false;
        }

        public Bitmap getFrame(){
            return this.mFrame;
        }

        public Bitmap getIcon(){
            return this.mIcon;
        }

        public String toString(){
            return "name=" + mName + ",pos=" + mPos + ",mIsActive=" + mIsActive + ",mIsSelected=" + mIsSelected + ",mIsDead=" + mIsDead +",[left=" + mLeft + ",top=" + mTop + ",right=" +  mRight + ",bottom=" + mBottom + "]";
        }
    }

    public GameView(Context context) {
        super(context);
        gameLoopThread = new GameLoopThread(this);
        getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry) {
                    try {
                        gameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e) {}
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                init();
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
                mStartTime = System.currentTimeMillis();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });

        loadImage();
    }

    private void loadImage()
    {
        mFrame = BitmapFactory.decodeResource(getResources(), R.drawable.framegolden);
        mFramefocus = BitmapFactory.decodeResource(getResources(), R.drawable.framepink);
        mContinue = BitmapFactory.decodeResource(getResources(), R.drawable.continuelevel);
//        mReset = BitmapFactory.decodeResource(getResources(), R.drawable.resetlevel);
        mGameOverImg = BitmapFactory.decodeResource(getResources(), R.drawable.gameover2);
        mLOL001Img = BitmapFactory.decodeResource(getResources(), R.drawable.lol001);
        mLOL002Img = BitmapFactory.decodeResource(getResources(), R.drawable.lol002);
        mLOL003Img = BitmapFactory.decodeResource(getResources(), R.drawable.lol003);
        mLOL004Img = BitmapFactory.decodeResource(getResources(), R.drawable.lol004);
        mLOL005Img = BitmapFactory.decodeResource(getResources(), R.drawable.lol005);
        mLOL006Img = BitmapFactory.decodeResource(getResources(), R.drawable.lol006);
        mLOL007Img = BitmapFactory.decodeResource(getResources(), R.drawable.lol007);
        mLOL008Img = BitmapFactory.decodeResource(getResources(), R.drawable.lol008);
        mLOL009Img = BitmapFactory.decodeResource(getResources(), R.drawable.lol009);
        mLOL010Img = BitmapFactory.decodeResource(getResources(), R.drawable.lol010);
        mLOL011Img = BitmapFactory.decodeResource(getResources(), R.drawable.lol011);
        mLOL012Img = BitmapFactory.decodeResource(getResources(), R.drawable.lol012);
        mLOL013Img = BitmapFactory.decodeResource(getResources(), R.drawable.lol013);
        mLOL014Img = BitmapFactory.decodeResource(getResources(), R.drawable.lol014);
        mLOL015Img = BitmapFactory.decodeResource(getResources(), R.drawable.lol015);
        mLOL016Img = BitmapFactory.decodeResource(getResources(), R.drawable.lol016);
        mLOL017Img = BitmapFactory.decodeResource(getResources(), R.drawable.lol017);
        mLOL018Img = BitmapFactory.decodeResource(getResources(), R.drawable.lol018);

        mNumber0Img = BitmapFactory.decodeResource(getResources(), R.drawable.number0);
        mNumber1Img = BitmapFactory.decodeResource(getResources(), R.drawable.number1);
        mNumber2Img = BitmapFactory.decodeResource(getResources(), R.drawable.number2);
        mNumber3Img = BitmapFactory.decodeResource(getResources(), R.drawable.number3);
        mNumber4Img = BitmapFactory.decodeResource(getResources(), R.drawable.number4);
        mNumber5Img = BitmapFactory.decodeResource(getResources(), R.drawable.number5);
        mNumber6Img = BitmapFactory.decodeResource(getResources(), R.drawable.number6);
        mNumber7Img = BitmapFactory.decodeResource(getResources(), R.drawable.number7);
        mNumber8Img = BitmapFactory.decodeResource(getResources(), R.drawable.number8);
        mNumber9Img = BitmapFactory.decodeResource(getResources(), R.drawable.number9);
        mToLeftImg = BitmapFactory.decodeResource(getResources(), R.drawable.toleft);
        mLevelUpImg = BitmapFactory.decodeResource(getResources(), R.drawable.levelup);
        mLevelImg = BitmapFactory.decodeResource(getResources(), R.drawable.level);
        mScoreImg = BitmapFactory.decodeResource(getResources(), R.drawable.score);
        mBackgroundGameoverImg = BitmapFactory.decodeResource(getResources(), R.drawable.gameoverbackground);

        mBackground0Img = BitmapFactory.decodeResource(getResources(), R.drawable.background0);
        mBackground1Img = BitmapFactory.decodeResource(getResources(), R.drawable.background1);
        mBackground2Img = BitmapFactory.decodeResource(getResources(), R.drawable.background2);
        mBackground3Img = BitmapFactory.decodeResource(getResources(), R.drawable.background3);
        mBackground4Img = BitmapFactory.decodeResource(getResources(), R.drawable.background4);
        mBackground5Img = BitmapFactory.decodeResource(getResources(), R.drawable.background5);
        mBackground6Img = BitmapFactory.decodeResource(getResources(), R.drawable.background6);
        mBackground7Img = BitmapFactory.decodeResource(getResources(), R.drawable.background7);
        mBackground8Img = BitmapFactory.decodeResource(getResources(), R.drawable.background8);
        mBackground9Img = BitmapFactory.decodeResource(getResources(), R.drawable.background9);
        mBackgroundPics[0] = mBackground0Img;
        mBackgroundPics[1] = mBackground1Img;
        mBackgroundPics[2] = mBackground2Img;
        mBackgroundPics[3] = mBackground3Img;
        mBackgroundPics[4] = mBackground4Img;
        mBackgroundPics[5] = mBackground5Img;
        mBackgroundPics[6] = mBackground6Img;
        mBackgroundPics[7] = mBackground7Img;
        mBackgroundPics[8] = mBackground8Img;
        mBackgroundPics[9] = mBackground9Img;


        mNumberImgArray[0] = mNumber0Img;
        mNumberImgArray[1] = mNumber1Img;
        mNumberImgArray[2] = mNumber2Img;
        mNumberImgArray[3] = mNumber3Img;
        mNumberImgArray[4] = mNumber4Img;
        mNumberImgArray[5] = mNumber5Img;
        mNumberImgArray[6] = mNumber6Img;
        mNumberImgArray[7] = mNumber7Img;
        mNumberImgArray[8] = mNumber8Img;
        mNumberImgArray[9] = mNumber9Img;

        //zafin people
        mAjuImg = BitmapFactory.decodeResource(getResources(), R.drawable.aju);
        mCliffImg = BitmapFactory.decodeResource(getResources(), R.drawable.cliff);
        mDenishImg = BitmapFactory.decodeResource(getResources(), R.drawable.dinesh);
        mFrankImg = BitmapFactory.decodeResource(getResources(), R.drawable.frank);
        mJenstonImg = BitmapFactory.decodeResource(getResources(), R.drawable.jenston);
        mKristoperImg = BitmapFactory.decodeResource(getResources(), R.drawable.kristophermoran);
        mHaoImg = BitmapFactory.decodeResource(getResources(), R.drawable.kuanghao);
        mKyleImg = BitmapFactory.decodeResource(getResources(), R.drawable.kyle);
        mLloydImg = BitmapFactory.decodeResource(getResources(), R.drawable.lloyd);
        mRossImg = BitmapFactory.decodeResource(getResources(), R.drawable.michaelross);
        mMonaImg = BitmapFactory.decodeResource(getResources(), R.drawable.mona);
        mNeerajImg = BitmapFactory.decodeResource(getResources(), R.drawable.neeraj);
        mPaulImg = BitmapFactory.decodeResource(getResources(), R.drawable.paul);
        mRickImg = BitmapFactory.decodeResource(getResources(), R.drawable.rickzhao);
        mRitchImg = BitmapFactory.decodeResource(getResources(), R.drawable.ritch);
        mShijuImg = BitmapFactory.decodeResource(getResources(), R.drawable.shiju);
        mXueqianImg = BitmapFactory.decodeResource(getResources(), R.drawable.xueqianwang);

    }

    public List<Integer> getNumberImage(int n){
//        Log.i(TAG, "getNumberImage(), n=" + n);
        List<Integer> list = new ArrayList<Integer>();
        String str = String.valueOf(n);
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            int pos = Character.getNumericValue(c);
//            Log.i(TAG, "getNumberImage(), c=" + c + ",pos=" + pos);
            list.add(pos);
        }
        return list;
    }

    private int rndInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    private int rndInt(int max) {
        return rndInt(0, max);
    }

    private void init()
    {
        this.mLevelUpInProgress = false;
        this.mTopBarHeight = getWidth()/15;
        int minionWidth = getWidth()/4;
        int rows = (getHeight() - mTopBarHeight)/minionWidth;
        int progressHeight = getHeight() - mTopBarHeight - rows*minionWidth;
        if(progressHeight < 10){
            progressHeight = progressHeight + minionWidth;
            rows = rows - 1;
        }
        this.mTotalMinions = rows * 4;
        this.mProgressBarHeight = progressHeight;

        this.mZafinPeople.add(new Minion("aju", mFrame, mAjuImg));
        this.mZafinPeople.add(new Minion("cliff", mFrame, mCliffImg));
        this.mZafinPeople.add(new Minion("denish", mFrame, mDenishImg));
        this.mZafinPeople.add(new Minion("frank", mFrame, mFrankImg));
        this.mZafinPeople.add(new Minion("jenston", mFrame, mJenstonImg));
        this.mZafinPeople.add(new Minion("kristoper", mFrame, mKristoperImg));
        this.mZafinPeople.add(new Minion("kyle", mFrame, mKyleImg));
        this.mZafinPeople.add(new Minion("lloyd", mFrame, mLloydImg));
        this.mZafinPeople.add(new Minion("ross", mFrame, mRossImg));
        this.mZafinPeople.add(new Minion("mona", mFrame, mMonaImg));
        this.mZafinPeople.add(new Minion("neerag", mFrame, mNeerajImg));
        this.mZafinPeople.add(new Minion("paul", mFrame, mPaulImg));
        this.mZafinPeople.add(new Minion("rick", mFrame, mRickImg));
        this.mZafinPeople.add(new Minion("ritch", mFrame, mRitchImg));
        this.mZafinPeople.add(new Minion("shiju", mFrame, mShijuImg));
        this.mZafinPeople.add(new Minion("xueqian", mFrame, mXueqianImg));

        mLoading = new LoadingPage(getWidth(), getHeight());
//        mSplash = new Splash(mGalaxyImg, getWidth(), getHeight(), mLevel);
        goLevel(1);
    }

    public void setGameOver(boolean isOver)
    {
        this.mIsGameOver = isOver;
    }

    public boolean isGameOver()
    {
        return this.mIsGameOver;
    }

    private List<Minion> addMinions(List<Minion> list){
        List<Minion> allList = new ArrayList<Minion>();

        if(mLevel < 5 || mLevel > 10 ){
            for(Minion people: mZafinPeople){
                allList.add(people);
            }
        }else{
            allList.add(new Minion("lol001", mFrame, mLOL001Img));
            allList.add(new Minion("lol002", mFrame, mLOL002Img));
            allList.add(new Minion("lol003", mFrame, mLOL003Img));
            allList.add(new Minion("lol004", mFrame, mLOL004Img));
            allList.add(new Minion("lol005", mFrame, mLOL005Img));
            allList.add(new Minion("lol006", mFrame, mLOL006Img));
            allList.add(new Minion("lol007", mFrame, mLOL007Img));
            allList.add(new Minion("lol008", mFrame, mLOL008Img));
            allList.add(new Minion("lol009", mFrame, mLOL009Img));
            allList.add(new Minion("lol010", mFrame, mLOL010Img));
            allList.add(new Minion("lol011", mFrame, mLOL011Img));
            allList.add(new Minion("lol012", mFrame, mLOL012Img));
            allList.add(new Minion("lol013", mFrame, mLOL013Img));
            allList.add(new Minion("lol014", mFrame, mLOL014Img));
            allList.add(new Minion("lol015", mFrame, mLOL015Img));
            allList.add(new Minion("lol016", mFrame, mLOL016Img));
            allList.add(new Minion("lol017", mFrame, mLOL017Img));
            allList.add(new Minion("lol018", mFrame, mLOL018Img));
        }

        int uniqueMinions = mTotalMinions/2;
        //add 20 entities
        for(int i = 0; i < uniqueMinions; i++){
            Log.i(TAG, "i: " + i + ", allList.size()=" + allList.size() + ",list:" + list);
            int pos = rndInt(allList.size() - 1);
            Minion minion = allList.remove(pos);
            list.add(minion);
            list.add(minion);
        }
        return list;
    }

    private void spawnMinions(){
        List<Minion> list = new ArrayList<Minion>();
        addMinions(list);

        Log.i(TAG, "first candy list: " + list);
        if(minions == null){
            minions = new Minion[mTotalMinions];
        }
        int index = 0;
        while(list.size() > 0){
            int pos = rndInt(list.size() - 1);
            Minion c = list.remove(pos);
            minions[index++] = new Minion(c.getName(), c.getFrame(), c.getIcon());
        }

        int size = getWidth()/4;
        int startPos = mTopBarHeight + mProgressBarHeight;
        int top;
        int left = 0;
        int right = left + size;
        int bottom;

        for(int i = 0; i < minions.length; i++){
            Minion c = minions[i];
            int row = i/4;
            int column = i%4;
            left = size*column;
            right = size*(1 + column);
            top = startPos + (row * size);
            bottom = startPos + (row + 1)*size;

//            c.init(left,top, right, bottom);
            Log.i(TAG, "[rick]i:" + i + ",[left=" + left + ",top=" + top + ",right=" +  right + ",bottom=" + bottom + "]");
//            c.setPos(i);
//            candies[i] = c;

            minions[i].init(left,top, right, bottom, i);
        //    Log.i(TAG, "candies[i]:" + candies[i]);
        }
        //print log
        Log.i(TAG, "############## Candy list ##############");
        for(int i = 0; i < minions.length; i++){
            Minion c = minions[i];
         //   Log.i(TAG, " [1]Candy: " + c);
       //     Log.i(TAG, " [2]Candy: " + candies[i]);
        }
    }

    private boolean isMinionsAnimationComplete(){
        boolean isComplete = true;
        for(Minion c : minions) {
            if (c.isDead()) {
                if(c.isActive()){
                    isComplete = false;
                    break;
                }
            }
        }
        return isComplete;
    }

    private void renderMinions(Canvas canvas){
        Paint paint = new Paint();
        Rect desc = new Rect(0, mTopBar.mBottom, getWidth(), getHeight());
        int backgroundPicIndex = mLevel % TOTAL_BACKGROUND_PIC;
        Bitmap backgroundPic = mBackgroundPics[backgroundPicIndex];
        canvas.drawBitmap(backgroundPic, null, desc, paint);
        boolean isAllDead = true;
        boolean isAllDeadInactive = true;
        for(Minion c : minions){
            if(!c.isDead()){
                c.onDraw(canvas);
                isAllDead = false;
            }else{
                //handle dead animation
                if(c.isActive()){
                    c.onDraw(canvas);
                    isAllDeadInactive = false;
                }
            }
        }
        if(isAllDead){
            if(isAllDeadInactive){
                spawnMinions();
            }
        }
    }

    private void levelUp(int level){
        int newLevel = level + 1;
        this.mLevelUpSplash = new LevelUpSplash(getWidth(), getHeight(), newLevel);

    }

    private void goLevel(int level){
        mIsGameOver = false;

        mStartTime = System.currentTimeMillis();
        mLevel = level;
        mNextLevelCount = mLevel * 2 + 2;
        mToCompleteCount = 0;


        //top bar
        mTopBar = new TopBar(mTopBarHeight, 0);

        //progress bar
        int progressBarHeight = mTopBarHeight;
        int progressBarTop = mTopBar.mBottom;
        int progressBarBottom = progressBarTop + mProgressBarHeight;
        this.mCandyAreaTop = progressBarBottom;
        mProgressBar = new ProgressBar(progressBarHeight, progressBarTop, progressBarBottom, getWidth());
        spawnMinions();
        mLevelUpInProgress = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(!mLoading.isComplete()){
            mLoading.onDraw(canvas);
            return;
        }
        if(mLevelUpSplash != null && !mLevelUpSplash.isComplete()){
            //wait animation complete
            renderMinions(canvas);
            if(isMinionsAnimationComplete()){
                mLevelUpSplash.onDraw(canvas);
            }
            return;
        }

        if(mIsGameOver){
            gameOverMode(canvas);
        }else{
            if(mToCompleteCount == mNextLevelCount){
                levelUp(mLevel);
            }else{
                renderMinions(canvas);
                mProgressBar.onDraw(canvas);
                mTopBar.onDraw(canvas);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//
//        }
        if (System.currentTimeMillis() - lastClick > 300) {
            lastClick = System.currentTimeMillis();
            synchronized (getHolder()) {
                float x = event.getX();
                float y = event.getY();
                int touchX = (int)y;
                int touchY = (int)x;
                Log.i(TAG, "touch [touchX=" + touchX + ",touchY=" + touchY);
                if(mIsGameOver){
                    checkGameOverTouch(touchX, touchY);
//                    checkReset(touchX, touchY);
//                    checkContinue(touchX, touchY);
                }else {
                    if(mLoading != null && !mLoading.isComplete()){
                        return true;
                    }
                    //wait animation effect
                    if(mLevelUpInProgress){
                        return true;
                    }
                    if(mLevelUpSplash != null && !mLevelUpSplash.isComplete()){
                        return true;
                    }
                    checkTouchAction(touchX, touchY);
                }
            }
        }
        return true;
    }

    public void checkTouchAction(int touchX, int touchY){

        Minion c = getTouchedCandy(touchX, touchY);
        Log.i(TAG, "getTouchedCandy(), c =" + c);
        if(c != null && !c.isDead()){
            String name = c.getName();
            Minion peer = null;
            for(int i = 0; i < minions.length; i++) {
                Minion searchedTarget = minions[i];
                if(c.getName().equals(searchedTarget.getName())){
                    if(c.getTop() == searchedTarget.getTop() && c.getLeft() == searchedTarget.getLeft()){
                        //itself
                    }else{
                        peer = searchedTarget;
                        break;
                    }
                }
            }
            Log.i(TAG, "peerCandy=" + peer);
            if(peer != null){
                //kill pair
                if(peer.isSelected()){
                    Log.i(TAG, "kill pair, c =" + c);
                    Log.i(TAG, "kill pair, peerCandy =" + peer);
                    minions[peer.getPos()].kill();
                    minions[c.getPos()].kill();
//                    mTarget.pushBack();
                    mToCompleteCount++;
                    if(mToCompleteCount == mNextLevelCount){
                        mLevelUpInProgress = true;
                    }
                }else{
                    if(c.isSelected()){
                        minions[c.getPos()].unselect();
                        Log.i(TAG, "c.unselect(), c =" + c);
                    }else{
                        boolean isAnotherSelected = false;
                        Minion anotherTarget = null;

                        for(int i = 0; i < minions.length; i++) {
                            Minion searchedTarget = minions[i];

                            if(c.getTop() == searchedTarget.getTop() && c.getLeft() == searchedTarget.getLeft()){
                                //itself
                            }else{
                                if(searchedTarget.isSelected()){
                                    isAnotherSelected = true;
                                    anotherTarget = searchedTarget;
                                    break;
                                }
                            }
                        }
                        if(isAnotherSelected){
                          //  Log.i(TAG, "another Candy already be selected, c =" + anotherCandy);
                            minions[anotherTarget.getPos()].unselect();
                            Log.i(TAG, "anotherCandy.unselect(), anotherCandy =" + anotherTarget);
                        }
                        minions[c.getPos()].select();
                        Log.i(TAG, "c.select(), c =" + c);

                    }
                }
            }

//            boolean isAllDead = true;
//            for(int i = 0; i < minions.length; i++) {
//                Minion obj = minions[i];
//                if(!obj.isDead()){
//                    isAllDead = false;
//                    break;
//                }
//            }
//            if(isAllDead){
//                spawnMinions();
//            }
        }
    }

    public Minion getTouchedCandy(int touchX, int touchY){
        Minion obj = null;
        for(int i = 0; i < minions.length; i++) {
            Minion c = minions[i];
            if(touchX >= c.getTop() && touchX <= c.getBottom() && touchY >= c.getLeft() && touchY <= c.getRight()){
                obj = c;
                break;
            }
        }
        return obj;
    }

    private void checkGameOverTouch(int x, int y){
        int startX = this.getHeight()/4;
        int size = this.getWidth()/4;

        int xPos = startX + size * 2;
        int yPos = size;

        //continue
        if(x <= xPos + size && x >= xPos && y <= yPos + size && y >= yPos)
        {
            goLevel(mLevel);
        }
        yPos = size * 2;
        //reset
        if(x <= xPos + size && x >= xPos && y <= yPos + size && y >= yPos)
        {
            goLevel(mLevel);
        }
    }

    private void gameOverMode(Canvas canvas)
    {
//        Paint colorPaint = new Paint();
//        colorPaint.setColor(Color.BLACK);
//        colorPaint.setStyle(Paint.Style.FILL);
        Rect rec = new Rect(0, 0, getWidth(), getHeight());
        canvas.drawBitmap(mBackgroundGameoverImg, null, rec, null);
//        canvas.drawRect(rec,colorPaint);

        int startX = this.getHeight()/4;
        int size = this.getWidth()/4;
        int startY = size;

        Rect dst = new Rect(startY, startX, startY + size * 2, startX + size * 2);
        canvas.drawBitmap(mGameOverImg, null, dst, null);

        Rect continueDst = new Rect(startY, startX + size * 2, startY + size * 2, startX + size * 3);
        canvas.drawBitmap(mContinue, null, continueDst, null);
//
//
//        Rect repeatDst = new Rect(startY, startX + size * 2, startY + size, startX + size * 3);
//        canvas.drawBitmap(mContinue, null, repeatDst, null);
//        Rect quitDst = new Rect(startY + size, startX + size * 2, startY + size * 2, startX + size * 3);
//        canvas.drawBitmap(mReset, null, quitDst, null);

        mTopBar.onDraw(canvas);
    }

    public int getNextLevelCount(){
        return this.mNextLevelCount;
    }

    public int getmToCompleteCount(){
        return this.mToCompleteCount;
    }

}