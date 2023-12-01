package com.example.dialogdemo;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

/**
 * 系统对话框AlertDialog
 */
public class SystemDialogActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systemdialog);

        findViewById(R.id.ac_alertdialog_normal_btn).setOnClickListener(this);
        findViewById(R.id.ac_alertdialog_list_btn).setOnClickListener(this);
        findViewById(R.id.ac_alertdialog_single_btn).setOnClickListener(this);
        findViewById(R.id.ac_alertdialog_multi_btn).setOnClickListener(this);

        findViewById(R.id.ac_alertdialog_time_btn).setOnClickListener(this);
        findViewById(R.id.ac_alertdialog_date_btn).setOnClickListener(this);

        findViewById(R.id.ac_alertdialog_progress1_btn).setOnClickListener(this);
        findViewById(R.id.ac_alertdialog_progress2_btn).setOnClickListener(this);

        findViewById(R.id.ac_alertdialog_custom_btn).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ac_alertdialog_normal_btn:
                showNormalDialog();
                break;
            case R.id.ac_alertdialog_list_btn:
                showListDialog();
                break;
            case R.id.ac_alertdialog_single_btn:
                showSingleDialog();
                break;
            case R.id.ac_alertdialog_multi_btn:
                showMultiDialog();
                break;
            case R.id.ac_alertdialog_time_btn:
                showTimeDialog();
                break;
            case R.id.ac_alertdialog_date_btn:
                showDateDialog();
                break;
            case R.id.ac_alertdialog_progress1_btn:
                showProgress1Dialog();
                break;
            case R.id.ac_alertdialog_progress2_btn:
                showProgress2Dialog();
                break;
            case R.id.ac_alertdialog_custom_btn:
                showCustomDialog();
                break;

        }

    }



    /**
     * 普通AlertDialog
     */
    private void showNormalDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this,
                R.style.Theme_AppCompat_Light_Dialog_Alert)
                .setIcon(R.drawable.ic_launcher)//设置标题的图片
                .setTitle("我是对话框")//设置对话框的标题
                .setMessage("我是对话框的内容")//设置对话框的内容
                .setNegativeButton("否定的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(SystemDialogActivity.this,
                                "点击了否定的按钮", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("正向的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(SystemDialogActivity.this,
                                "点击了正向的按钮", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setNeutralButton("中性的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SystemDialogActivity.this,
                                "点击了中性的按钮", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.setCanceledOnTouchOutside(false);

        dialog.show();

    }

    /**
     * 列表AlertDialog
     */
    private void showListDialog() {
//        final String[] items = new String[]{"item 1", "item 2", "item 3", "item 4"};
//
        final String[] items = new String[]{"item 1", "item 2", "item 3", "item 4",
                "item 5", "item 6", "item 7", "item 8",
                "item 9", "item 10", "item 11", "item 12"};

        AlertDialog.Builder listDialog =
                new AlertDialog.Builder(this);
        listDialog.setTitle("这是一个列表Dialog");
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(SystemDialogActivity.this,
                        items[which], Toast.LENGTH_SHORT).show();
            }
        });
        listDialog.show();

    }


    /**
     * 单选Dialog
     */
    private void showSingleDialog() {
        final String[] items = new String[]{"item 1", "item 2", "item 3", "item 4"};
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder(this);
        listDialog.setTitle("这是一个单选Dialog");
        listDialog.setSingleChoiceItems(items,0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(SystemDialogActivity.this,
                        items[which], Toast.LENGTH_SHORT).show();
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(SystemDialogActivity.this,
                        "点击了确定按钮", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        }) .create();
        listDialog.show();
    }


    /**
     * 多选Dialog
     */
    private void showMultiDialog() {
        final CharSequence[] items = new String[]{"item 1", "item 2", "item 3", "item 4"};
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder(this);
        listDialog.setTitle("这是一个单选Dialog");
        listDialog.setMultiChoiceItems(items, new boolean[items.length], new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Toast.makeText(SystemDialogActivity.this,
                        items[which] +" is checked="+isChecked, Toast.LENGTH_SHORT).show();
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(SystemDialogActivity.this,
                        "点击确定按钮", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        }) .create();
        listDialog.show();
    }

    /**
     * 时间选择Dialog
     */
    private void showTimeDialog() {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Toast.makeText(SystemDialogActivity.this,
                                "hourOfDay="+hourOfDay+ ", minute="+minute,
                                Toast.LENGTH_SHORT).show();
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true);

        timePickerDialog.show();
    }

    /**
     * 日期选择Dialog
     */
    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Toast.makeText(SystemDialogActivity.this,
                                "year="+year+ ", month="+(month+1)+ ", " +
                                        "dayOfMonth="+dayOfMonth,
                                Toast.LENGTH_SHORT).show();
                    }
                }, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    /**
     * 带转圈的ProgressDialog
     */
    private void showProgress1Dialog() {
        ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setTitle("这是一个带转圈进度Dialog");
        progressDialog.setMessage("请等待");
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.show();

    }

    /**
     * 带进度条的ProgressDialog
     */
    private void showProgress2Dialog() {
        final int MAX_PROGRESS = 100;
        final ProgressDialog progressDialog =new ProgressDialog(this);
        progressDialog.setProgress(0);
        progressDialog.setTitle("这是一个进度条Dialog");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(MAX_PROGRESS);
        progressDialog.show();
        new Thread(){
            int progress = 0;
            @Override
            public void run() {
                try {
                    while (progress <MAX_PROGRESS){
                        progress++;
                        progressDialog.setProgress(progress);
                        sleep(100);
                    }
                    progressDialog.cancel();
                }catch (Exception e){

                }
            }
        }.start();
    }

    /**
     * 自定义AlertDialog
     */
    private void showCustomDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this,
                R.style.Theme_AlertDialog_Custom)
//                .setIcon(R.drawable.ic_launcher)//设置标题的图片
//                .setTitle("我是对话框")//设置对话框的标题
//                  .setMessage("我是对话框的内容")//设置对话框的内容
//                .setNegativeButton("否定的", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        Toast.makeText(SystemDialogActivity.this,
//                                "点击了否定的按钮", Toast.LENGTH_SHORT).show();
//                        dialog.dismiss();
//                    }
//                })
//                .setPositiveButton("正向的", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        Toast.makeText(SystemDialogActivity.this,
//                                "点击了正向的按钮", Toast.LENGTH_SHORT).show();
//                        dialog.dismiss();
//                    }
//                })
//                .setNeutralButton("中性的", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(SystemDialogActivity.this,
//                                "点击了中性的按钮", Toast.LENGTH_SHORT).show();
//                        dialog.dismiss();
//                    }
//                })
                //设置自定义View
                .setView(R.layout.alertdialog_custom)
                .create();
        dialog.setCanceledOnTouchOutside(true);

        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);

        //动画
        window.setWindowAnimations(R.style.Theme_AlertDialog_Custom_Animation);

        dialog.show();

        //window属性设置
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = 480;
        window.setAttributes(layoutParams);
    }


}