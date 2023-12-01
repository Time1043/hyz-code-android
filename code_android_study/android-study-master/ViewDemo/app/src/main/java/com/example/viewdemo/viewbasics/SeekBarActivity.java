package com.example.viewdemo.viewbasics;

import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;

import java.util.Random;

/**
 * 测试SeekBar
 *
 * @author CoderCao
 */
public class SeekBarActivity extends BaseActivity {

    private final String TAG = "SeekBarActivity";


    private SeekBar videoSeekBar;

    private int progress = 0;

    private int secondaryProgress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1、加载XML声明的布局
        setContentView(R.layout.activity_seekbar);

        videoSeekBar = findViewById(R.id.seekbar_video_seekbar);
        Random random = new Random();
        new Thread(){
            @Override
            public void run() {
                super.run();
                while (progress <120 || secondaryProgress<120){
                    progress++;
                    secondaryProgress = secondaryProgress+ random.nextInt(6);

                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            videoSeekBar.setProgress(progress>120 ? 120:progress);
                            videoSeekBar.setSecondaryProgress(secondaryProgress>120 ? 120:secondaryProgress);
                        }
                    });
                }
            }
        }.start();


        videoSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int ps, boolean fromUser) {
                Log.e("seekbar",ps+""+"fromUser= "+fromUser);
                progress = ps;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                    if (seekBar.getSecondaryProgress()<progress){
                        secondaryProgress = progress;
                    }
            }
        });

    }

}