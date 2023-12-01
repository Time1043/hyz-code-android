package com.example.viewdemo.viewbasics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;


public class ViewBasicsHomeActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1、加载activity_main.xml布局
        setContentView(R.layout.activity_viewbasicshome);

        //根据id找到xml里面的Button
        Button button = findViewById(R.id.ac_main_test_btn);
        //给Button设置上点击事件
        button.setOnClickListener(this);

        //根据id找到xml里面的Button
        Button attributeButton = findViewById(R.id.ac_main_testattribute_btn);
        //给Button设置上点击事件
        attributeButton.setOnClickListener(this);

        Button framelayoutButton = findViewById(R.id.ac_main_testframelayout_btn);
        //给Button设置上点击事件
        framelayoutButton.setOnClickListener(this);

        Button linearlayoutButton = findViewById(R.id.ac_main_testlinearlayout_btn);
        //给Button设置上点击事件
        linearlayoutButton.setOnClickListener(this);

        Button relativelayoutButton = findViewById(R.id.ac_main_testrelativelayout_btn);
        //给Button设置上点击事件
        relativelayoutButton.setOnClickListener(this);

        Button textviewButton = findViewById(R.id.ac_main_testtextview_btn);
        //给Button设置上点击事件
        textviewButton.setOnClickListener(this);

        Button edittextButton = findViewById(R.id.ac_main_testedittext_btn);
        //给Button设置上点击事件
        edittextButton.setOnClickListener(this);

        Button buttonButton = findViewById(R.id.ac_main_testbutton_btn);
        //给Button设置上点击事件
        buttonButton.setOnClickListener(this);

        Button imageButton = findViewById(R.id.ac_main_testimageview_btn);
        //给Button设置上点击事件
        imageButton.setOnClickListener(this);


        Button radiobutto = findViewById(R.id.ac_main_testradiobutton_btn);
        //给Button设置上点击事件
        radiobutto.setOnClickListener(this);

        Button checkbox = findViewById(R.id.ac_main_testcheckbox_btn);
        //给Button设置上点击事件
        checkbox.setOnClickListener(this);

        Button switchBtn = findViewById(R.id.ac_main_testswitch_btn);
        //给Button设置上点击事件
        switchBtn.setOnClickListener(this);

        Button progressbarBtn = findViewById(R.id.ac_main_testprogressbar_btn);
        //给Button设置上点击事件
        progressbarBtn.setOnClickListener(this);

        Button seekbarBtn = findViewById(R.id.ac_main_testseekbar_btn);
        //给Button设置上点击事件
        seekbarBtn.setOnClickListener(this);

        Button ratingbarBtn = findViewById(R.id.ac_main_testratingbar_btn);
        //给Button设置上点击事件
        ratingbarBtn.setOnClickListener(this);

        Button datetimeBtn = findViewById(R.id.ac_main_testdatetime_btn);
        //给Button设置上点击事件
        datetimeBtn.setOnClickListener(this);


        Button scrollViewBtn = findViewById(R.id.ac_main_testscrollview_btn);
        //给Button设置上点击事件
        scrollViewBtn.setOnClickListener(this);

        Button horizontalScrollViewBtn = findViewById(R.id.ac_main_testhorizontalscrollview_btn);
        //给Button设置上点击事件
        horizontalScrollViewBtn.setOnClickListener(this);

        Button listViewBtn = findViewById(R.id.ac_main_testlistview1_btn);
        //给Button设置上点击事件
        listViewBtn.setOnClickListener(this);

        Button listViewBtn2 = findViewById(R.id.ac_main_testlistview2_btn);
        //给Button设置上点击事件
        listViewBtn2.setOnClickListener(this);

        Button listViewBtn3 = findViewById(R.id.ac_main_testlistview3_btn);
        //给Button设置上点击事件
        listViewBtn3.setOnClickListener(this);

        Button gridViewBtn = findViewById(R.id.ac_main_testgridview_btn);
        //给Button设置上点击事件
        gridViewBtn.setOnClickListener(this);

        Button spinnerBtn = findViewById(R.id.ac_main_testspinner_btn);
        //给Button设置上点击事件
        spinnerBtn.setOnClickListener(this);

        Button expandablelistviewBtn = findViewById(R.id.ac_main_testsexpandablelistview_btn);
        //给Button设置上点击事件
        expandablelistviewBtn.setOnClickListener(this);


    }

    /**
     * 跳转到FramelayoutActivity
     */
    private void goFramelayoutActivity() {
        Intent intent = new Intent(ViewBasicsHomeActivity.this,FrameLayoutActivity.class);
        ViewBasicsHomeActivity.this.startActivity(intent);
    }


    /**
     * 跳转到TestXmlActivity
     */
    private void goTestXmlActivity() {
        Intent intent = new Intent(ViewBasicsHomeActivity.this,TestXmlActivity.class);
        ViewBasicsHomeActivity.this.startActivity(intent);
    }

    /**
     * 跳转到AttributeActivity
     */
    private void goAttributeActivity() {
        Intent intent = new Intent(ViewBasicsHomeActivity.this,AttributeActivity.class);
        ViewBasicsHomeActivity.this.startActivity(intent);
    }

    /**
     * 跳转到LinearlayoutActivity
     */
    private void goLinearlayoutActivity() {
        Intent intent = new Intent(ViewBasicsHomeActivity.this,LinearLayoutActivity.class);
        ViewBasicsHomeActivity.this.startActivity(intent);
    }

    /**
     * 跳转到RelativeLayoutActivity
     */
    private void goRelativeLayoutActivity() {
        Intent intent = new Intent(ViewBasicsHomeActivity.this,RelativeLayoutActivity.class);
        ViewBasicsHomeActivity.this.startActivity(intent);
    }

    /**
     * 跳转到TextViewActivity
     */
    private void goTextViewActivity() {
        Intent intent = new Intent(ViewBasicsHomeActivity.this,TextViewActivity.class);
        ViewBasicsHomeActivity.this.startActivity(intent);
    }

    /**
     * 跳转到EditTextActivity
     */
    private void goEditTextActivity() {
        Intent intent = new Intent(ViewBasicsHomeActivity.this,EditTextActivity.class);
        ViewBasicsHomeActivity.this.startActivity(intent);
    }

    /**
     * 跳转到ButtonActivity
     */
    private void goButtonActivity(){
        Intent intent = new Intent(ViewBasicsHomeActivity.this,ButtonActivity.class);
        ViewBasicsHomeActivity.this.startActivity(intent);
    }

    /**
     * 跳转到ImageViewActivity
     */
    private void goImageViewActivity(){
        Intent intent = new Intent(ViewBasicsHomeActivity.this,ImageViewActivity.class);
        ViewBasicsHomeActivity.this.startActivity(intent);
    }

    /**
     * 跳转到RadioButtonActivity
     */
    private void goRadioButtonActivity(){
        Intent intent = new Intent(ViewBasicsHomeActivity.this,RadioButtonActivity.class);
        ViewBasicsHomeActivity.this.startActivity(intent);
    }

    /**
     * 跳转到CheckBoxActivity
     */
    private void goCheckBoxActivity(){
        Intent intent = new Intent(ViewBasicsHomeActivity.this,CheckBoxActivity.class);
        ViewBasicsHomeActivity.this.startActivity(intent);
    }
    /**
     * 跳转到SwitchActivity
     */
    private void goSwitchActivity(){
        Intent intent = new Intent(ViewBasicsHomeActivity.this,SwitchActivity.class);
        ViewBasicsHomeActivity.this.startActivity(intent);
    }
    /**
     * 跳转到ProgressBarActivity
     */
    private void goProgressbarActivity(){
        Intent intent = new Intent(ViewBasicsHomeActivity.this,ProgressBarActivity.class);
        ViewBasicsHomeActivity.this.startActivity(intent);
    }

    /**
     * 跳转到ProgressBarActivity
     */
    private void goSeekbarActivity(){
        Intent intent = new Intent(ViewBasicsHomeActivity.this,SeekBarActivity.class);
        ViewBasicsHomeActivity.this.startActivity(intent);
    }
    /**
     * 跳转到RatingBarActivity
     */
    private void goRatingBarActivity(){
        Intent intent = new Intent(ViewBasicsHomeActivity.this,RatingBarActivity.class);
        ViewBasicsHomeActivity.this.startActivity(intent);
    }
    /**
     * 跳转到DateTimeActivity
     */
    private void goDateTimeActivity(){
        Intent intent = new Intent(ViewBasicsHomeActivity.this,DateTimeActivity.class);
        ViewBasicsHomeActivity.this.startActivity(intent);
    }
    /**
     * 跳转到ScrollViewActivity
     */
    private void goScrollviewActivity(){
        Intent intent = new Intent(ViewBasicsHomeActivity.this,ScrollViewActivity.class);
        ViewBasicsHomeActivity.this.startActivity(intent);
    }
    /**
     * 跳转到ScrollViewActivity
     */
    private void goHorizontalscrollviewActivity(){
        Intent intent = new Intent(ViewBasicsHomeActivity.this,
                HorizontalScrollViewActivity.class);
        ViewBasicsHomeActivity.this.startActivity(intent);
    }

    /**
     * 跳转到ListView1Activity
     */
    private void goListView1Activity(){
        Intent intent = new Intent(ViewBasicsHomeActivity.this,
                ListViewActivity1.class);
        ViewBasicsHomeActivity.this.startActivity(intent);
    }

    /**
     * 跳转到ListView1Activity
     */
    private void goListView2Activity(){
        Intent intent = new Intent(ViewBasicsHomeActivity.this,
                ListViewActivity2.class);
        ViewBasicsHomeActivity.this.startActivity(intent);
    }

    /**
     * 跳转到ListViewActivity3
     */
    private void goListView3Activity(){
        Intent intent = new Intent(ViewBasicsHomeActivity.this,
                ListViewActivity3.class);
        ViewBasicsHomeActivity.this.startActivity(intent);
    }

    /**
     * 跳转到GridViewActivity
     */
    private void goGridViewActivity(){
        Intent intent = new Intent(ViewBasicsHomeActivity.this,
                GridViewActivity.class);
        ViewBasicsHomeActivity.this.startActivity(intent);
    }
    /**
     * 跳转到SpinnerActivity
     */
    private void goSpinnerActivity(){
        Intent intent = new Intent(ViewBasicsHomeActivity.this,
                SpinnerActivity.class);
        ViewBasicsHomeActivity.this.startActivity(intent);
    }

    /**
     * 跳转到ExpandableListViewActivity
     */
    private void goExpandableListViewActivity(){
        Intent intent = new Intent(ViewBasicsHomeActivity.this,
                ExpandableListViewActivity.class);
        ViewBasicsHomeActivity.this.startActivity(intent);
    }
    //
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.ac_main_test_btn:
                //跳转到TestXmlActivity界面
                goTestXmlActivity();
                break;
            case R.id.ac_main_testattribute_btn:
                //跳转到AttributeActivity
                goAttributeActivity();
                break;
            case R.id.ac_main_testframelayout_btn:
                //跳转到FramelayoutActivity
                goFramelayoutActivity();
                break;
            case R.id.ac_main_testlinearlayout_btn:
                //跳转到LinearlayoutActivity
                goLinearlayoutActivity();
                break;
            case R.id.ac_main_testrelativelayout_btn:
                //跳转到RelativeLayoutActivity
                goRelativeLayoutActivity();
                break;
            case R.id.ac_main_testtextview_btn:
                //跳转到TextViewActivity
                goTextViewActivity();
                break;
            case R.id.ac_main_testedittext_btn:
                //跳转到EditTextActivity
                goEditTextActivity();
                break;
            case R.id.ac_main_testbutton_btn:
                //跳转到ButtonActivity
                goButtonActivity();
                break;
            case R.id.ac_main_testimageview_btn:
                //跳转到ImageViewActivity
                goImageViewActivity();
                break;
            case R.id.ac_main_testradiobutton_btn:
                //跳转到RadioButtonActivity
                goRadioButtonActivity();
                break;
            case R.id.ac_main_testcheckbox_btn:
                //跳转到CheckBoxActivity
                goCheckBoxActivity();
                break;
            case R.id.ac_main_testswitch_btn:
                //跳转到SwitchActivity
                goSwitchActivity();
                break;
            case R.id.ac_main_testprogressbar_btn:
                //跳转到ProgressbarActivity
                goProgressbarActivity();
                break;
            case R.id.ac_main_testseekbar_btn:
                //跳转到SeekbarActivity
                goSeekbarActivity();
                break;
            case R.id.ac_main_testratingbar_btn:
                //跳转到RatingBarActivity
                goRatingBarActivity();
                break;
            case R.id.ac_main_testdatetime_btn:
                //跳转到DateTimeActivity
                goDateTimeActivity();
                break;
            case R.id.ac_main_testscrollview_btn:
                //跳转到RatingBarActivity
                goScrollviewActivity();
                break;
            case R.id.ac_main_testhorizontalscrollview_btn:
                //跳转到DateTimeActivity
                goHorizontalscrollviewActivity();
                break;
            case R.id.ac_main_testlistview1_btn:
                //跳转到ListView1Activity
                goListView1Activity();
                break;
            case R.id.ac_main_testlistview2_btn:
                //跳转到ListView2Activity
                goListView2Activity();
                break;
            case R.id.ac_main_testlistview3_btn:
                //跳转到ListView3Activity
                goListView3Activity();
                break;
            case R.id.ac_main_testgridview_btn:
                //跳转到GridViewActivity
                goGridViewActivity();
                break;//
            case R.id.ac_main_testspinner_btn:
                //跳转到SpinnerActivity
                goSpinnerActivity();
                break;//
            case R.id.ac_main_testsexpandablelistview_btn:
                //跳转到ExpandableListViewActivity
                goExpandableListViewActivity();
                break;//
        }
    }
}