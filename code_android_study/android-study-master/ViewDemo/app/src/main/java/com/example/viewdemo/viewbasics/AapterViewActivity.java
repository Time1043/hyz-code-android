package com.example.viewdemo.viewbasics;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.viewdemo.BaseActivity;

/**
 * 测试AapterView
 *
 * @author CoderCao
 */
public class AapterViewActivity extends BaseActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseAdapter baseAdapter;

        listView.setAdapter(new TestAdapter());

    }


    class TestAdapter extends BaseAdapter{

//        List<T> data = new ArrayList<>();

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }

}