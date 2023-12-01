package com.example.demoprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.demoprovider.db.DBHelper;


public class DemoProvider extends ContentProvider {

    private DBHelper dbHelper;
    private SQLiteDatabase db;

    private static final int PERSONS = 1;
    private static final int PERSON_ID = 2;

    private static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);


    static {
        matcher.addURI(DemoContract.AUTHORITY, "person", PERSONS);
        matcher.addURI(DemoContract.AUTHORITY, "person/#", PERSON_ID);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection, @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        db = dbHelper.getWritableDatabase();
        switch (matcher.match(uri)) {
            case PERSONS:
                // 查询
                return db.query(DemoContract.Person.TAB_PERSON,
                        projection, selection, selectionArgs, null, null,
                        sortOrder);
            case PERSON_ID:
                // 解析所要查询记录的 ID
                long id = ContentUris.parseId(uri);
                // 拼接 where 语句
                StringBuilder where = new StringBuilder(DemoContract.Person._ID);
                where.append("=").append(id);
                if (!TextUtils.isEmpty(selection)) {
                    where.append(" and ").append(selection);
                }
                return db.query(DemoContract.Person.TAB_PERSON, projection,
                        where.toString(), selectionArgs, null, null, sortOrder);
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri.toString());
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (matcher.match(uri)) {
            case PERSONS: // 数据是多项记录
                return DemoContract.PERSON_TYPE;
            case PERSON_ID: // 数据是单项记录
                return DemoContract.PERSON_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri.toString());
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        db = dbHelper.getWritableDatabase();
        switch (matcher.match(uri)) {
            case PERSONS:
                long id = db.insert(DemoContract.Person.TAB_PERSON,
                        DemoContract.Person._ID,contentValues);
                Uri resultUri = Uri.withAppendedPath(DemoContract.PERSON_URI,id+"");
                //getContext().getContentResolver().notifyChange(resultUri, null);
                return resultUri;
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri.toString());
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String whereClause,
                      @Nullable String[] whereArgs) {
        db = dbHelper.getWritableDatabase();
        switch (matcher.match(uri)) {
            case PERSONS:
                 return db.delete(DemoContract.Person.TAB_PERSON,
                         whereClause,whereArgs);
            case PERSON_ID:
                long id = ContentUris.parseId(uri);
                StringBuilder whereBuilder = new StringBuilder(DemoContract.Person._ID);
                whereBuilder.append("=").append(id);
                if (!TextUtils.isEmpty(whereClause)) {
                    whereBuilder.append(" and ").append(whereClause);
                }
                return db.delete(DemoContract.Person.TAB_PERSON,
                        whereBuilder.toString(), whereArgs);
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri.toString());
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues,
                      @Nullable String whereClause, @Nullable String[] whereArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (matcher.match(uri)) {
            case PERSONS:
                return db.update(DemoContract.Person.TAB_PERSON, contentValues,
                        whereClause, whereArgs);
            case PERSON_ID:
                // 解析所要修改记录的 ID
                long id = ContentUris.parseId(uri);
                // 拼接 where 语句
                StringBuilder where = new StringBuilder(DemoContract.Person._ID);
                where.append("=").append(id);
                if (!TextUtils.isEmpty(whereClause)) {
                    where.append(" and ").append(whereClause);
                }
                return db.update(DemoContract.Person.TAB_PERSON, contentValues,
                        where.toString(), whereArgs);
             default:
               throw new IllegalArgumentException("unknown Uri: " + uri);
        }
    }
}
