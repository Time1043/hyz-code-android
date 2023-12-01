package com.example.viewdemo.viewbasics;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;


/**
 * 测试TextView
 *
 * @author CoderCao
 */
public class TextViewActivity extends BaseActivity {

    private TextView basicTextView;

    private TextView drawableTextView;

    private TextView marqueeTextView;


    private TextView htmlTextView;

    private TextView typefaceTextView;

    private TextView clickTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1、加载XML声明的布局
        setContentView(R.layout.activity_textview);



        basicTextView = findViewById(R.id.ac_textview_basic_txt);
        drawableTextView = findViewById(R.id.ac_textview_drawable_txt);

        marqueeTextView = findViewById(R.id.ac_textview_marquee_txt);

        htmlTextView = findViewById(R.id.ac_textview_html_txt);
        typefaceTextView = findViewById(R.id.ac_textview_typeface_txt);




        Drawable[] drawable = drawableTextView.getCompoundDrawables();
        // 数组下表0~3,依次是:左上右下
        drawable[1].setBounds(0, 0, 80, 80);
        drawableTextView.setCompoundDrawables(drawable[0], drawable[1], drawable[2],
                drawable[3]);


        marqueeTextView.setSelected(true);



        //使TextView可以上下滑动
        htmlTextView.setMovementMethod(ScrollingMovementMethod.getInstance());
        //点击超链接可以跳转到浏览器
        htmlTextView.setMovementMethod(LinkMovementMethod.getInstance());
        String html =  "剩余了" +
                "<font size=\"6\" color=\"red\">20%</font>的电量";
        htmlTextView.setText(Html.fromHtml(html));



        typefaceTextView.setTypeface(Typeface.createFromAsset(this.getAssets(),"STXINGKA.TTF"));



    }

}