package com.time1043.hyzapplicationdemo5;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MyActivity extends Activity {
    @Override
    protected void onCreate(Bundle saveInstance) {  // Activity提供用户界面和与用户的交互  生命周期onCreate
        super.onCreate(saveInstance);  // 调用父类  初始化

        /*TextView tv_one = new TextView(this);  // 创建控件对象  参数给出控件所在容器
        tv_one.setText("这是一个控件");
        setContentView(tv_one);  // 把控件放到界面上*/

        setContentView(R.layout.main_layout);  // 加载R资源下的xml布局文件

        // R下的xml布局文件  用id抓取对象
        TextView tv_o = findViewById(R.id.tv_one);
        tv_o.setTextColor(0xFFFF0000);  // 16进制4字节 透明 RGB
    }
}
