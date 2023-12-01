package com.time1043.hyzapplicationdemo10;

import androidx.appcompat.app.AppCompatActivity;

import android.media.ImageWriter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import java.nio.channels.GatheringByteChannel;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageSwitcher imageSwitcher;
    private Gallery gallery;
    private List<Integer> imgIds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageSwitcher = findViewById(R.id.imgswh);
        gallery = findViewById(R.id.gl);

        // 生成图片资源的列表，从threebody6到threebody100
        for (int i = 6; i <= 13; i++) {
            String imageName = "threebody" + i;
            int resourceId = getResources().getIdentifier(imageName, "drawable", getPackageName());
            if (resourceId != 0) {
                imgIds.add(resourceId);
            }
        }

        // 设置相关属性  图片消失动画 图片显示动画
        imageSwitcher.setInAnimation(this, android.R.anim.fade_in);  // 渐入
        imageSwitcher.setOutAnimation(this, android.R.anim.fade_out);  // 渐出
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView iv = new ImageView(MainActivity.this);
                return iv;
            }
        });

        // 设置相关属性  mvc模式即使界面和后台数据分开
        gallery.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return imgIds.toArray().length;
            }

            @Override
            public Object getItem(int i) {
                return imgIds.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {  // 缓存  加载
                ImageView iv;
                if (view != null) iv = (ImageView) view;
                else {
                    iv = new ImageView(MainActivity.this);
                    iv.setAdjustViewBounds(true);  // 尺寸和布局文件匹配
                }
                iv.setImageResource(imgIds.get(i));
                return iv;
            }
        });
        gallery.setSelection(imgIds.toArray().length / 2);

        // 两个组件显示要匹配
        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                imageSwitcher.setImageResource(imgIds.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}