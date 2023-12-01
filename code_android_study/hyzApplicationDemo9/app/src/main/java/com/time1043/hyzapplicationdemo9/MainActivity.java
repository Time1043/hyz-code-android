package com.time1043.hyzapplicationdemo9;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tvOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOne = findViewById(R.id.tv_one);  // 找到组件
        registerForContextMenu(tvOne);  // 在组件加上下文菜单
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {  // 要创建的菜单  长按哪个组件  菜单信息
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.context_menu, menu);
        menu.setHeaderTitle("请选择文字颜色");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {  // 响应
        if (item.getItemId() == R.id.color1) {
            tvOne.setTextColor(Color.RED);
        } else if (item.getItemId() == R.id.color2) {
            tvOne.setTextColor(Color.GREEN);
        } else if (item.getItemId() == R.id.color3) {
            tvOne.setTextColor(Color.BLUE);
        } else if (item.getItemId() == R.id.color4) {
            tvOne.setTextColor(Color.BLACK);
        }
        return super.onContextItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  // 创建菜单
        /*// 添加菜单项
        menu.add("item1");
        menu.add("item2");
        menu.add(1, 11, 3, "item3");  // 分组 id 序号
        menu.add(1, 12, 4, "item4");
        menu.add(2, 21, 5, "item5");
        menu.add(2, 22, 6, "item6");
        // 设置菜单项
        menu.setGroupCheckable(1, true, false);  // 第一个分组  带单选框  缺省不打勾
        menu.setGroupEnabled(2, false);  // 第二个分组  变灰*/

        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {  // 菜单做出响应
        Toast.makeText(this, item.getTitle() + ", " + item.getItemId(), Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }
}