package com.example.viewdemo.viewbasics;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;


/**
 * 测试布局的两种编写方式
 *
 * @author CoderCao
 */
public class TestXmlActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1、加载XML声明的布局
        setContentView(R.layout.activity_testxml);



        //2、加载代码实现的布局
//        View view = getView();
//        setContentView(view);


    }

    /**
     * 代码实现布局，效果同 activity_testxml
     *
     * @return View
     */
    private View getView(){
        LinearLayout linearLayout = new LinearLayout(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);


        //初始化Button
        Button button = new Button(this);
        button.setText("Hello, I am a Button from view");
        button.setAllCaps(false);
        LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.addView(button,buttonLayoutParams);

        //初始化TextView
        TextView textView = new TextView(this);
        textView.setText("Hello, I am a TextView from view");
        LinearLayout.LayoutParams textViewLayoutParams = new LinearLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.addView(textView,textViewLayoutParams);

        return linearLayout;
    }


}