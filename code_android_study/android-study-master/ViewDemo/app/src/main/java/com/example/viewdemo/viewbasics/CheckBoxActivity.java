package com.example.viewdemo.viewbasics;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;


/**
 * 测试CheckBox
 *
 * @author CoderCao
 */
public class CheckBoxActivity extends BaseActivity {

    private final String TAG = "CheckBoxActivity";


    private CheckBox[][] checkBoxes = new CheckBox[2][4];

    private Button submit;

    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1、加载XML声明的布局
        setContentView(R.layout.activity_checkbox);

        CheckBox oneBt = findViewById(R.id.ac_checkbox_onebt);

        oneBt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(CheckBoxActivity.this,
                            "勾选", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(CheckBoxActivity.this,
                            "去勾选", Toast.LENGTH_SHORT).show();
                }
            }
        });

        checkBoxes[0][0] = findViewById(R.id.ac_checkbox_1a);
        checkBoxes[0][1] = findViewById(R.id.ac_checkbox_1b);
        checkBoxes[0][2] = findViewById(R.id.ac_checkbox_1c);
        checkBoxes[0][3] = findViewById(R.id.ac_checkbox_1d);

        checkBoxes[1][0] = findViewById(R.id.ac_checkbox_2a);
        checkBoxes[1][1] = findViewById(R.id.ac_checkbox_2b);
        checkBoxes[1][2] = findViewById(R.id.ac_checkbox_2c);
        checkBoxes[1][3] = findViewById(R.id.ac_checkbox_2d);

        submit = findViewById(R.id.ac_checkbox_submit);
        result = findViewById(R.id.ac_checkbox_result);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultInfo = "选择结果:\n";
                for (int i = 0;i<checkBoxes.length;i++){
                    String one = (i+1)+"、";
                    for (int j=0;j<4;j++){
                        if (checkBoxes[i][j].isChecked()){
                            one = one+getTAGnswer(j);
                        }
                    }
                    if (one.length()==2){
                        one = one+"未选择";
                    }
                    resultInfo = resultInfo+one+"\n";
                }

                result.setText(resultInfo);
            }
        });
    }

    private String getTAGnswer(int index){
        String re = "";
        switch (index){
            case 0:
                re = "A";
                break;
            case 1:
                re = "B";
                break;
            case 2:
                re = "C";
                break;
            case 3:
                re = "D";
                break;

        }
        return re;
    }

}