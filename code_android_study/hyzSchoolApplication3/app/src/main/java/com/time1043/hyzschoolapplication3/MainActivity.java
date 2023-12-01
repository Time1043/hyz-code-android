package com.time1043.hyzschoolapplication3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    // 获取组件 全局
    private ImageView downloadImg = null;
    private Button threadStartBtn = null;

    // 常量
    private static final int DOWNLOAD_IMG_SUCCESS = 1;
    private static final int DOWNLOAD_IMG_FAILED = 2;

    // 全局变量
    private Handler mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            /*if (message.what == DOWNLOAD_IMG_SUCCESS) {
                // 显示图片
                Bitmap bitmap = BitmapFactory.decodeFile((String) message.obj);
                downloadImg.setImageBitmap(bitmap);
                return true; // 不再回消息队列
            }
            if (message.what == DOWNLOAD_IMG_FAILED) {
                // 返回用户提示信息
                Toast.makeText(MainActivity.this,
                        R.string.toast_download_img_failed,
                        Toast.LENGTH_LONG).show();
                return true; // 不再返回消息队列
            }
            return false;*/

            switch (message.what) {
                case DOWNLOAD_IMG_SUCCESS:
                    // 显示图片
                    Bitmap bitmap = BitmapFactory.decodeFile((String) message.obj);
                    downloadImg.setImageBitmap(bitmap);
                    return true; // 不再回消息队列
                case DOWNLOAD_IMG_FAILED:
                    // 返回用户提示信息
                    Toast.makeText(MainActivity.this,
                            R.string.toast_download_img_failed,
                            Toast.LENGTH_LONG).show();
                default:
                    return false;

            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取组件
        threadStartBtn = findViewById(R.id.btn_thread_start);
        downloadImg = findViewById(R.id.img_download);

        // 线程 点击
        threadStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "开启线程");
                        HttpURLConnection conn = null;
                        InputStream input = null;
                        FileOutputStream output = null;
                        try {
                            URL url = new URL("https://pluspng.com/img-png/dragon-ball-png-dragon-ball-super-png-hd-750.png");
                            conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("GET");
                            conn.setConnectTimeout(6 * 1000);

                            // 获取服务器的输入流  APP角度 - 服务器
                            input = conn.getInputStream();
                            // 将数据写入到文件  APP角度 - 文件
                            File saveFile = new File(getExternalCacheDir(), "img4.png");
                            Log.d(TAG, saveFile.getAbsolutePath());
                            output = new FileOutputStream(saveFile);

                            // 缓冲区 字节流
                            byte[] buffer = new byte[100 * 1024]; // 100KB
                            int len = 0;  // 记录该文件字节长度
                            // 获得两个流后：读 写
                            /*len = input.read(buffer);  // 放到缓冲区  有可能需要读多次
                            output.write(buffer, 0, len);  // 由缓冲区来*/
                            while ((len = input.read(buffer)) > 0) {
                                output.write(buffer, 0, len);
                            }
                            Log.d(TAG, "图片下载成功");

                            // 发消息 通知主线程可以显示图片
                            Message msg = new Message(); // 消息对象
                            msg.what = DOWNLOAD_IMG_SUCCESS;
                            msg.obj = saveFile.getAbsolutePath();
                            mHandler.sendMessage(msg);  // 发送消息

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                            Log.d(TAG, "url异常");

                            // 发消息 图片下载失败
                            mHandler.sendEmptyMessage(DOWNLOAD_IMG_FAILED);
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.d(TAG, "io异常");

                            // 发消息 图片下载失败
                            mHandler.sendEmptyMessage(DOWNLOAD_IMG_FAILED);
                        } finally {
                            // 先开后关
                            if (output != null) {
                                try {
                                    output.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (input != null) {
                                try {
                                    input.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (conn != null) {
                                conn.disconnect();  // 关闭连接
                            }
                        }
                        Log.d(TAG, "线程结束");
                    }
                });
                thread.start();
            }
        });

    }
}