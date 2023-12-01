package com.example.dialogdemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.example.dialogdemo.R;

/**
 * 照片选择方式Dialog，可以拍照和从相册选择
 */
public class PhotoDialog extends Dialog implements View.OnClickListener {

    private PhotoCallBack photoCallBack;

    public PhotoDialog(@NonNull Context context) {
        super(context,R.style.Theme_PhotoDialog);
    }

    public PhotoDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_photo);

        Window window = getWindow();

        WindowManager.LayoutParams layoutParams = window.getAttributes();

        layoutParams.width = this.getContext().getResources().
                getDisplayMetrics().widthPixels;

        window.setGravity(Gravity.BOTTOM);

        window.setWindowAnimations(R.style.Theme_PhotoDialog_Animation);

        findViewById(R.id.dl_photo_take).setOnClickListener(this);
        findViewById(R.id.dl_photo_choose).setOnClickListener(this);
        findViewById(R.id.dl_photo_cancle).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dl_photo_take:
                if (photoCallBack != null){
                    photoCallBack.takePhoto();
                }
                dismiss();
                break;
            case R.id.dl_photo_choose:
                if (photoCallBack != null){
                    photoCallBack.choosePhoto();
                }
                dismiss();
                break;
            case R.id.dl_photo_cancle:
                dismiss();
                break;
        }

    }

    public void setPhotoCallBack(PhotoCallBack photoCallBack) {
        this.photoCallBack = photoCallBack;
    }

    public interface PhotoCallBack{
        void takePhoto();
        void choosePhoto();
    }
}
