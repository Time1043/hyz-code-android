package com.example.viewdemo.viewbasics;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;
import com.example.viewdemo.glide.GlideLoader;

/**
 * 测试ImageView
 *
 * @author CoderCao
 */
public class ImageViewActivity extends BaseActivity implements View.OnClickListener {

    private final String TAG = "ImageViewActivity";

    ImageView matrixImage;
    ImageView sdImageView;
    ImageView netImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1、加载XML声明的布局
        setContentView(R.layout.activity_imageview);


        matrixImage = findViewById(R.id.ac_imageView_matrix);
        sdImageView = findViewById(R.id.ac_imageView_sdcard);
        netImageView = findViewById(R.id.ac_imageView_net);

        matrixImage();

        requestPermission();


    }

    /**
     * 给ImageView设置matrix，进行变换
     */
    private void matrixImage(){
        //创建一个矩阵
        Matrix matrix = new Matrix();
        //平移x和y各100单位
//        matrix.setTranslate(100, 100);
        //顺时针旋转30度
        matrix.preRotate(30);
        //设置并应用矩阵
        matrixImage.setImageMatrix(matrix);
    }

    /**
     * 从Sd卡上加载图片
     */
    private void loadFromSdCard(){
        String filePath = Environment.getExternalStorageDirectory().getPath()+"/ATest/android12.jpg";
        Bitmap bm = BitmapFactory.decodeFile(filePath);
//        sdImageView.setImageBitmap(bm);
        GlideLoader.loadImage(this,sdImageView,"file://"+filePath);
    }

    /**
     * 从网络上加载图片
     */
    private void loadFromNet(){
        GlideLoader.laodImageRound(this,netImageView,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpicnew13.photophoto.cn%2F20190306%2Fmeilideshanshuifengguangjingse-32132144_1.jpg&refer=http%3A%2F%2Fpicnew13.photophoto.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644592752&t=1f292327f0b25d6d1c570db76337ab66");
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 申请权限
     */
    private void requestPermission() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }else {
            loadFromSdCard();

            loadFromNet();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadFromSdCard();
                    loadFromNet();
                } else {
                    Toast.makeText(this,"请打开权限",Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }


}