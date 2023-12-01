package com.example.viewdemo.viewbasics;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;

/**
 * 测试RatingBar
 *
 * @author CoderCao
 */
public class RatingBarActivity extends BaseActivity {

    private final String TAG = "RatingBarActivity";

    private RatingBar ratingBar;

    private TextView reslutTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1、加载XML声明的布局
        setContentView(R.layout.activity_ratingbar);

        ratingBar = findViewById(R.id.ratingbar_tating);
        reslutTxt = findViewById(R.id.ratingbar_result);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                reslutTxt.setText("您给的评分是："+rating+"分");
            }
        });

    }

}