package com.example.dialogdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.ac_main_basis_btn).setOnClickListener(this);
        findViewById(R.id.ac_main_systemdialog_btn).setOnClickListener(this);
        findViewById(R.id.ac_main_commondialog_btn).setOnClickListener(this);
        findViewById(R.id.ac_main_popupwindow_btn).setOnClickListener(this);
        findViewById(R.id.ac_main_dialogfragment_btn).setOnClickListener(this);
        findViewById(R.id.ac_main_floatingwindow_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ac_main_basis_btn:
                goBasisActivity();
                break;
            case R.id.ac_main_systemdialog_btn:
                goSystemDialogActivity();
                break;
            case R.id.ac_main_commondialog_btn:
                goCustomDialogActivity();
                break;
            case R.id.ac_main_popupwindow_btn:
                goPopupWindowActivity();
                break;
            case R.id.ac_main_dialogfragment_btn:
                goDialogFragmentActivity();
                break;
            case R.id.ac_main_floatingwindow_btn:
                goFloatingWindowActivity();
                break;
        }
    }

    private void goFloatingWindowActivity() {
        Intent intent = new Intent(this, FloatingWindowActivity.class);
        startActivity(intent);
    }

    private void goDialogFragmentActivity() {
        Intent intent = new Intent(this, DialogFragmentActivity.class);
        startActivity(intent);
    }

    private void goPopupWindowActivity() {
        Intent intent = new Intent(this, PopupWindowActivity.class);
        startActivity(intent);
    }

    private void goCustomDialogActivity() {
        Intent intent = new Intent(this, CommonDialogActivity.class);
        startActivity(intent);
    }


    private void goSystemDialogActivity() {
        Intent intent = new Intent(this, SystemDialogActivity.class);
        startActivity(intent);
    }

    private void goBasisActivity() {
        Intent intent = new Intent(this,BasisActivity.class);
        startActivity(intent);
    }
}