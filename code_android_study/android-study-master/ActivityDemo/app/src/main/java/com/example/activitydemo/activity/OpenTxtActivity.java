package com.example.activitydemo.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.activitydemo.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * 接收txt文件
 */
public class OpenTxtActivity extends AppCompatActivity {

    private final String TAG = "OpenTxtActivity";

    private TextView contetnTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opentxt);
        contetnTxt = findViewById(R.id.ac_opentxt_contetn);
        receiveData();

    }

    private void receiveData() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getAction().equals(Intent.ACTION_VIEW)) {
                //从Intent里获取uri
                Uri uri = intent.getData();
                String content = readFile(uri);
                if (!TextUtils.isEmpty(content)) {
                    contetnTxt.setText(content);
                }else {
                    contetnTxt.setText("打开文件失败！");
                }
            }
        }
    }

    private String readFile(Uri uri) {
        if (uri == null) {
            return null;
        }
        String scheme = uri.getScheme();
        if (TextUtils.isEmpty(scheme)) {
            return null;
        }
        InputStream inputStream = null;
        String result = null;
        try {
            //从uri构造流
            inputStream = getContentResolver().openInputStream(uri);
            byte[] content = new byte[inputStream.available()];
            inputStream.read(content);
            result =  new String(content, StandardCharsets.UTF_8);
        } catch (Exception e) {
            Log.e(TAG,"readFile error="+e.toString());
        }finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e(TAG,"readFile close error="+e.toString());
                }
            }
        }
        return result;
    }


}