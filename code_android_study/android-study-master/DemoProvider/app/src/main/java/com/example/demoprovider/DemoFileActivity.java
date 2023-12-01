package com.example.demoprovider;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;


public class DemoFileActivity extends AppCompatActivity {

    private final String TAG = "DemoFileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demofile);


        String files_path = this.getFilesDir().getPath();
        String cache_path = this.getCacheDir().getPath();
        String external_path = Environment.getExternalStorageDirectory().getPath();

        String external_files_path = null;
        File[] externalFilesDirs = ContextCompat.getExternalFilesDirs(this, null);
        if (externalFilesDirs.length > 0) {
            external_files_path = externalFilesDirs[0].getPath();
        }

        String external_cache_path = null;
        File[] externalCacheDirs = ContextCompat.getExternalCacheDirs(this);
        if (externalCacheDirs.length > 0) {
            external_cache_path = externalCacheDirs[0].getPath();
        }

        String external_media_path = null;
        File[] externalMediaDirs = this.getExternalMediaDirs();
        if (externalMediaDirs.length > 0) {
            external_media_path = externalMediaDirs[0].getPath();
        }

        Log.i(TAG,"files_path="+files_path);
        Log.i(TAG,"cache_path="+cache_path);
        Log.i(TAG,"external_path="+external_path);
        Log.i(TAG,"external_files_path="+external_files_path);
        Log.i(TAG,"external_cache_path="+external_cache_path);
        Log.i(TAG,"external_media_path="+external_media_path);


        File file = new File(files_path+File.separator+"test.txt");
        try {
            if (!file.exists()){
                file.createNewFile();
            }
            writeTxt(file,"测试FileProvider！！！");
        } catch (Exception e) {
            Log.e(TAG,"write error="+e.toString());
        }

        Uri uri = getUri(this,file);
        Log.i(TAG,"file path="+file.getPath());
        Log.i(TAG,"file uri="+uri.toString());

        findViewById(R.id.opnefile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openByOther(uri,"txt");
            }
        });

    }

    /**
     * 写入文件
     * @param file
     * @param content
     */
    private void writeTxt(File file, String content) {
        OutputStreamWriter osw = null;
        try
        {
            osw = new OutputStreamWriter(new FileOutputStream(
                    file, false), StandardCharsets.UTF_8);
            osw.write(content);

        } catch (Exception e) {
            Log.e(TAG,"writeTxt write error="+e.toString());
        }finally {
            if (osw != null){
                try {
                    osw.close();
                } catch (IOException e) {
                    Log.e(TAG,"writeTxt close error="+e.toString());
                }
            }
        }
    }



    /**
     * 构造路径URI
     * @param context
     * @param file
     * @return
     */
    public Uri getUri(Context context, File file) {
        if (file == null){
            return null;
        }
        Uri uri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = DemoFileProvider.getUriForFile(context,
                    DemoContract.FILE_AUTHORITY, file);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }

    /**
     *
     */
    private void openByOther(Uri uri,String fileType) {
        if (uri == null){
            return;
        }
        //通过扩展名找到mimeType
        String mimeType = fileType == null ? "*/*" : MimeTypeMap.getSingleton().
                getMimeTypeFromExtension(fileType);
        //构造Intent
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION |
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, mimeType);
        try {
            startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG,"openByOther error="+e.toString());
        }
    }

}