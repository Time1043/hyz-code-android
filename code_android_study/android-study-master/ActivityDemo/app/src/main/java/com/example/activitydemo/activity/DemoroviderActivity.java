package com.example.activitydemo.activity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.activitydemo.DemoContract;
import com.example.activitydemo.R;

/**
 * 测试自定义ContentProvider程序Activity
 */
public class DemoroviderActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "DemoroviderActivity";

    private static final String[] person = new String[]{
            DemoContract.Person._ID,
            DemoContract.Person.NAME,
            DemoContract.Person.PHONE,
            DemoContract.Person.SEX,
            DemoContract.Person.AGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demoprovider);

        Button queryBtn = findViewById(R.id.ac_demoprovider_query);
        queryBtn.setOnClickListener(this);

        Button queryByIdBtn = findViewById(R.id.ac_demoprovider_querybyid);
        queryByIdBtn.setOnClickListener(this);

        Button insertBtn = findViewById(R.id.ac_demoprovider_insert);
        insertBtn.setOnClickListener(this);

        Button deleteBtn = findViewById(R.id.ac_demoprovider_delete);
        deleteBtn.setOnClickListener(this);

        Button deleteByIdBtn = findViewById(R.id.ac_demoprovider_deletebyid);
        deleteByIdBtn.setOnClickListener(this);

        Button updateBtn = findViewById(R.id.ac_demoprovider_update);
        updateBtn.setOnClickListener(this);

        Button updateByIdBtn = findViewById(R.id.ac_demoprovider_updatebyid);
        updateByIdBtn.setOnClickListener(this);

        Button mimetypeBtn = findViewById(R.id.ac_demoprovider_mimetype);
        mimetypeBtn.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{DemoContract.READ_PROVIDER},0);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ac_demoprovider_query:
                query();
                break;
            case R.id.ac_demoprovider_querybyid:
                queryById(2);
                break;
            case R.id.ac_demoprovider_insert:
                insert();
                break;
            case R.id.ac_demoprovider_delete:
                delete();
                break;
            case R.id.ac_demoprovider_deletebyid:
                deleteById(2);
                break;
            case R.id.ac_demoprovider_update:
                update();
                break;
            case R.id.ac_demoprovider_updatebyid:
                updateById(5);
                break;
            case R.id.ac_demoprovider_mimetype:
                mimetype();
                break;
        }
    }

    /**
     * 查询mime类型
     */
    private void mimetype() {
        ContentResolver resolver = this.getContentResolver();
//        Uri uri = DemoContract.PERSON_URI;
        Uri uri = Uri.withAppendedPath(DemoContract.PERSON_URI,"1");
        Log.i(TAG, "mimetype type="+resolver.getType(uri));
    }

    /**
     * 根据id更新provider数据
     *
     * @param id 需要更新的id
     */
    private void updateById(int id) {
        ContentResolver resolver = this.getContentResolver();
        Uri uri = Uri.withAppendedPath(DemoContract.PERSON_URI,id+"");
        ContentValues contentValues = new ContentValues();
        contentValues.put(DemoContract.Person.NAME,"修改byId的name");
        contentValues.put(DemoContract.Person.PHONE,"87654321");
        int count = resolver.update(uri,contentValues,
                null,
                null);
        Log.i(TAG, "updateById count="+count);
    }

    /**
     * 更新provider数据
     */
    private void update() {
        ContentResolver resolver = this.getContentResolver();
        Uri uri = DemoContract.PERSON_URI;
        ContentValues contentValues = new ContentValues();
        contentValues.put(DemoContract.Person.NAME,"修改后的name");
        contentValues.put(DemoContract.Person.PHONE,"87654321");
        int count = resolver.update(uri,contentValues,
                DemoContract.Person.NAME+" = ?",
                new String[]{"tester"});
        Log.i(TAG, "update count="+count);
    }

    /**
     * 根据id删除provider数据
     *
     * @param id 需要删除的id
     */
    private void deleteById(int id) {
        ContentResolver resolver = this.getContentResolver();
        Uri uri = Uri.withAppendedPath(DemoContract.PERSON_URI,id+"");
        int count = resolver.delete(uri,
                null,
                null);
        Log.i(TAG, "deleteById count="+count);
    }

    /**
     * 根据条件删除provider数据
     */
    private void delete() {
        ContentResolver resolver = this.getContentResolver();
        Uri uri = DemoContract.PERSON_URI;
        int count = resolver.delete(uri,
                DemoContract.Person.NAME+" = ?",
                new String[]{"tester"});
        Log.i(TAG, "delete count="+count);
    }

    /**
     * 向provider新增数据
     */
    private void insert() {
        ContentResolver resolver = this.getContentResolver();
        Uri uri = DemoContract.PERSON_URI;
        ContentValues contentValues = new ContentValues();
        contentValues.put(DemoContract.Person.NAME,"tester");
        contentValues.put(DemoContract.Person.PHONE,"12345678");
        contentValues.put(DemoContract.Person.SEX,"男");
        contentValues.put(DemoContract.Person.AGE,23);
        resolver.insert(uri,contentValues);
    }

    /**
     * 根据id查询provider数据
     *
     * @param id 需要查询的id
     */
    private void queryById(int id) {
        ContentResolver resolver = this.getContentResolver();

        Uri uri = Uri.withAppendedPath(DemoContract.PERSON_URI,id+"");
        Cursor phoneCursor = resolver.query(
                uri,
                person,
                null,
                null,
                null);

        try {
            readDataFromCursor(phoneCursor);
        }finally {
            if (phoneCursor != null) {
                phoneCursor.close();
            }
        }
    }

    /**
     * 查询provider数据
     */
    private void query() {
        ContentResolver resolver = this.getContentResolver();

        Uri uri = DemoContract.PERSON_URI;

        Cursor phoneCursor = resolver.query(
                uri,
                person,
                null,
                null,
                null);

        try {
            readDataFromCursor(phoneCursor);
        }finally {
            if (phoneCursor != null) {
                phoneCursor.close();
            }
        }
    }

    /**
     * 从Cursor读取数据
     *
     * @param cursor provider Cursor
     */
    private void readDataFromCursor(Cursor cursor) {
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(
                        cursor.getColumnIndex(
                                DemoContract.Person._ID));
                String name = cursor.getString(
                        cursor.getColumnIndex(
                                DemoContract.Person.NAME));
                String phone = cursor.getString(
                        cursor.getColumnIndex(
                                DemoContract.Person.PHONE));
                String sex = cursor.getString(
                        cursor.getColumnIndex(
                                DemoContract.Person.SEX));
                String age = cursor.getString(
                        cursor.getColumnIndex(
                                DemoContract.Person.AGE));
                Log.i(TAG, id + " - " + name+ " - " + phone+ " - " + sex+ " - " + age);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

}