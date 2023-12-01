package com.example.dialogdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dialogdemo.dialog.PhotoDialog;

/**
 * 普通Dialog
 */
public class CommonDialogActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customdialog);
        findViewById(R.id.ac_customdialog_custom_btn).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ac_customdialog_custom_btn:
                showCustomDialog();
                break;
        }

    }

    /**
     * 自定义Dialog
     */
    private void showCustomDialog() {
        PhotoDialog photoDialog = new PhotoDialog(this);
        photoDialog.setPhotoCallBack(new PhotoDialog.PhotoCallBack() {
            @Override
            public void takePhoto() {
                Toast.makeText(CommonDialogActivity.this,
                        "拍照", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void choosePhoto() {
                Toast.makeText(CommonDialogActivity.this,
                        "从相册选择", Toast.LENGTH_SHORT).show();
            }
        });
        photoDialog.show();
    }

}