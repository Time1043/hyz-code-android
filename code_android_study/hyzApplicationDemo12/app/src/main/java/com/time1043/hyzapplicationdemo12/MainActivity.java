package com.time1043.hyzapplicationdemo12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnEdit;  //  去往编辑界面的按钮
    TextView tvUsername;
    TextView tvPassword;
    TextView tvSex;
    TextView tvBrith;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvUsername = findViewById(R.id.tv_username2);
        tvPassword = findViewById(R.id.tv_password2);
        tvSex = findViewById(R.id.tv_sex2);
        tvBrith = findViewById(R.id.tv_birth2);

        btnEdit = findViewById(R.id.bt_edit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);

                Bundle bundle = new Bundle();
                bundle.putCharSequence("username", tvUsername.getText());
                bundle.putCharSequence("password", tvPassword.getText());
                bundle.putCharSequence("sex", tvSex.getText());
                bundle.putCharSequence("brith", tvBrith.getText());
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }
}