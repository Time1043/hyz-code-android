package com.example.viewdemo.viewbasics;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;


/**
 * 测试ProgressBar
 *
 * @author CoderCao
 */
public class ProgressBarActivity extends BaseActivity {

    private final String TAG = "ProgressBarActivity";

    private ProgressBar progressBar;
    private ImageView progressImage;

    private int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1、加载XML声明的布局
        setContentView(R.layout.activity_progressbar);
        progressBar = findViewById(R.id.progress_horizontal);
        progressImage = findViewById(R.id.progress_image);



        new Thread(){
            @Override
            public void run() {
                super.run();
                while (progress<100){
                    progress++;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progress);
                        }
                    });
                    try {
                        Thread.sleep(120);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        AnimationDrawable ad = (AnimationDrawable) progressImage.getDrawable();
        progressImage.postDelayed(new Runnable() {
            @Override
            public void run() {
                ad.start();
            }
        }, 100);
    }

}