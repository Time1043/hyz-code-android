package com.time1043.hyzapplicationdemo12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    Button btnCancel;  // 界面切换按钮
    Button btnOk;  // 界面切换按钮
    EditText etUsername;
    EditText etPassword;
    EditText etSex;
    EditText etBrith;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etUsername = findViewById(R.id.et_tv_username2);
        etPassword = findViewById(R.id.et_tv_password2);
        etSex = findViewById(R.id.et_tv_sex2);
        etBrith = findViewById(R.id.et_tv_brith2);

        // 取出intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        etUsername.setText(bundle.getCharSequence("username"));
        etPassword.setText(bundle.getCharSequence("password"));
        etSex.setText(bundle.getCharSequence("sex"));
        etBrith.setText(bundle.getCharSequence("brith"));

        btnCancel = findViewById(R.id.button_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}