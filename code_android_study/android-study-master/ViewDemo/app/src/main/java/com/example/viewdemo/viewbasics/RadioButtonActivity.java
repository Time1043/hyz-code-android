package com.example.viewdemo.viewbasics;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;


/**
 * 测试RadioButton
 *
 * @author CoderCao
 */
public class RadioButtonActivity extends BaseActivity {

    private final String TAG = "RadioButtonActivity";

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1、加载XML声明的布局
        setContentView(R.layout.activity_radiobutton);

        RadioButton radioButton = findViewById(R.id.ac_radiobutton_onebt);

        Button opBtn = findViewById(R.id.ac_radiobutton_onebt_op);
        opBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton.setChecked(!radioButton.isChecked());
                Toast.makeText(RadioButtonActivity.this,
                        "单个RadioButton是否选中？"+radioButton.isChecked(),
                        Toast.LENGTH_SHORT).show();
            }
        });


        radioGroup = findViewById(R.id.ac_radiobutton_group_default);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String info = null;
                switch (checkedId){
                    case R.id.ac_radiobutton_dfbt0:
                        info = "选择了A";
                        break;
                    case R.id.ac_radiobutton_dfbt1:
                        info = "选择了B";
                        break;
                    case R.id.ac_radiobutton_dfbt2:
                        info = "选择了C";
                        break;
                    case R.id.ac_radiobutton_dfbt3:
                        info = "选择了D";
                        break;

                }
                Toast.makeText(RadioButtonActivity.this,
                        info,Toast.LENGTH_SHORT).show();
            }
        });

    }

}