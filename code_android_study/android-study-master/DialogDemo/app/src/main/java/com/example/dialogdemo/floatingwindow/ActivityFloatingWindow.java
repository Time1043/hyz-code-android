package com.example.dialogdemo.floatingwindow;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.dialogdemo.R;


public class ActivityFloatingWindow implements View.OnTouchListener {

    private Context mContext;
    private WindowManager.LayoutParams mWindowParams;
    private WindowManager mWindowManager;


    private View rootLayout;

    private float mInViewX;
    private float mInViewY;
    private float mDownInScreenX;
    private float mDownInScreenY;
    private float mInScreenX;
    private float mInScreenY;


    boolean isMoving = false;



    public ActivityFloatingWindow(Context context) {
        this.mContext = context;
        initFloatWindow();
    }

    private void initFloatWindow() {

        rootLayout = LayoutInflater.from(mContext).
                inflate(R.layout.floatingwidow_in_activity, null);
        rootLayout.setOnTouchListener(this);

        mWindowParams = new WindowManager.LayoutParams();
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        if (Build.VERSION.SDK_INT >= 26) {
            //8.0新特性
            mWindowParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        }else{
            mWindowParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
        mWindowParams.format = PixelFormat.RGBA_8888;
        mWindowParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE ;
        mWindowParams.gravity = Gravity.START|Gravity.TOP;
        mWindowParams.width = 96;
        mWindowParams.height = 96;
        defaultPosition();
    }

    public void showFloatWindow(){
        if (rootLayout.getParent() == null){
            mWindowManager.addView(rootLayout, mWindowParams);
        }
    }


    public void remove(){
        if (rootLayout.getParent() != null){
            mWindowManager.removeView(rootLayout);
        }

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return floatLayoutTouch(event);
    }

    private boolean floatLayoutTouch(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 获取相对View的坐标，即以此View左上角为原点
                mInViewX = motionEvent.getX();
                mInViewY = motionEvent.getY();
                // 获取相对屏幕的坐标，即以屏幕左上角为原点
                mDownInScreenX = motionEvent.getRawX();
                mDownInScreenY = motionEvent.getRawY();
                mInScreenX = motionEvent.getRawX();
                mInScreenY = motionEvent.getRawY();
                isMoving = true;
                break;
            case MotionEvent.ACTION_MOVE:
                // 更新浮动窗口位置参数
                mInScreenX = motionEvent.getRawX();
                mInScreenY = motionEvent.getRawY();
                mWindowParams.x = (int) (mInScreenX- mInViewX);
                mWindowParams.y = (int) (mInScreenY - mInViewY);
                // 手指移动的时候更新小悬浮窗的位置
                mWindowManager.updateViewLayout(rootLayout, mWindowParams);
                isMoving = true;
                break;
            case MotionEvent.ACTION_UP:
                isMoving = false;
                // 如果手指离开屏幕时，xDownInScreen和xInScreen相等，且yDownInScreen和yInScreen相等，则视为触发了单击事件。
                if (mDownInScreenX  == mInScreenX && mDownInScreenY == mInScreenY){
                    Toast.makeText(mContext,"Click",Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }

    private void defaultPosition(){
        DisplayMetrics metrics = new DisplayMetrics();
        // 默认固定位置，靠屏幕左边缘的中间
        mWindowManager.getDefaultDisplay().getMetrics(metrics);
        mWindowParams.x = 0;
        mWindowParams.y = metrics.heightPixels/2-48;
    }




}
