package com.example.viewdemo.viewbasics;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;


/**
 * 测试EditText
 *
 * @author CoderCao
 */
public class EditTextActivity extends BaseActivity {

    private final String TAG = "EditTextActivity";
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1、加载XML声明的布局
        setContentView(R.layout.activity_edittext);
        editText = findViewById(R.id.edit_text);

        //禁止输入
//        editText.setKeyListener(null);

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "beforeTextChanged: s = " + s + ", start = " + start +
                        ", count = " + count + ", after = " + after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged: s = " + s + ", start = " + start +
                        ", before = " + before + ", count = " + count);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "afterTextChanged: " + s.toString());
            }

        });

        Button setBtn = findViewById(R.id.ac_edittext_set);
        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "18502951252";
                editText.setText("18502951252");
                editText.setSelection(phone.length());
            }
        });



    }

}