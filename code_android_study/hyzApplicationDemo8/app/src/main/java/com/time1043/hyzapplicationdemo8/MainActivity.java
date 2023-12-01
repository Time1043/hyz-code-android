package com.time1043.hyzapplicationdemo8;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btToast = findViewById(R.id.bt_toast);
        btToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "这是一条Toast消息", Toast.LENGTH_LONG).show();
            }
        });  // 点击事件监听


        Button btAlertDialog = findViewById(R.id.bt_alertdialog);
        btAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle("系统提示")
                        .setMessage("这是带按钮的对话框")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
            }
        });  // 点击事件监听


        Button btNotification = findViewById(R.id.bt_notification);
        btNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);  // 拿到指定的系统服务
                NotificationChannel channel = new NotificationChannel("0", "test", NotificationManager.IMPORTANCE_DEFAULT);
                manager.createNotificationChannel(channel);

                Notification.Builder builder = new Notification.Builder(MainActivity.this, "0");
                builder.setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentText("这是标题")
                        .setContentText("每天进步一点点")
                        .setWhen(System.currentTimeMillis());  // 立即发送

                Notification notification = builder.build();
                manager.notify(123, notification);


            }
        });  // 点击事件监听

    }
}