package com.example.activitydemo.activity;

import android.content.ContentResolver;
import android.content.SearchRecentSuggestionsProvider;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.UserDictionary;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.activitydemo.R;

import java.util.Arrays;

/**
 * 测试 ContentProvider基础知识的Activity
 */
public class ContentProviderBasicActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "ContentProviderActivity";

    private static final String[] phoneContact = new String[]{
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Photo.PHOTO_ID};

    private Button contactsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contentproviderbasic);
        contactsBtn = findViewById(R.id.ac_contentproviderbasic_contacts);
        contactsBtn.setOnClickListener(this);


        LoaderManager.getInstance(this).initLoader(0, null, new LoaderCallback());

    }

    private class LoaderCallback implements LoaderManager.LoaderCallbacks<Cursor> {

        @Override
        public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
            Log.i(TAG, "onCreateLoader:" + Thread.currentThread().getName());
            return new CursorLoader(
                    ContentProviderBasicActivity.this,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    phoneContact,
                    null,
                    null,
                    ContactsContract.Contacts.DISPLAY_NAME
                            + " COLLATE LOCALIZED ASC");
        }

        @Override
        public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
            readDataFromCursor(data);
            Log.i(TAG, "onLoadFinished:" + Thread.currentThread().getName());
        }

        @Override
        public void onLoaderReset(@NonNull Loader<Cursor> loader) {
            Log.i(TAG, "onLoaderReset:" + Thread.currentThread().getName());
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ac_contentproviderbasic_contacts:
                readContacts();
                break;
        }
    }

    private void readContacts() {
        ContentResolver resolver = this.getContentResolver();

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Log.i(TAG, "getContacts-uri:" + uri.toString());

        String[] streamTypes = resolver.getStreamTypes(uri,"");
        Log.i(TAG, "getContacts-streamTypes:" + Arrays.toString(streamTypes));

        String types = resolver.getType(uri);
        Log.i(TAG, "getContacts-types:" + types);

        Cursor phoneCursor = resolver.query(
                uri,
                phoneContact,
                ContactsContract.Contacts.DISPLAY_NAME+" = ?",
                new String[]{"A Test"},
                ContactsContract.Contacts.DISPLAY_NAME
                        + " COLLATE LOCALIZED ASC");

        try {
            readDataFromCursor(phoneCursor);
        }finally {
            if (phoneCursor != null) {
                phoneCursor.close();
            }
        }
    }

    private void readDataFromCursor(Cursor cursor) {
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(
                        cursor.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(
                        cursor.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                Log.i(TAG, name + " : " + number);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

}