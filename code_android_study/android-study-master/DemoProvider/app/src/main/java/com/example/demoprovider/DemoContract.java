package com.example.demoprovider;

import android.net.Uri;

public class DemoContract {

    public static final String FILE_AUTHORITY = "com.example.demoprovider.fileprovider";


    public static final String AUTHORITY = "com.example.demoprovider.provider";

    private static final Uri AUTHORITY_URI =  Uri.parse("content://" + AUTHORITY);

    public static final Uri PERSON_URI =  Uri.withAppendedPath(AUTHORITY_URI,"person");

    public static final String PERSON_TYPE = "vnd.android.cursor.dir/vnd.com.example.demoprovider.person";

    public static final String PERSON_ITEM_TYPE = "vnd.android.cursor.item/vnd.com.example.demoprovider.person";

    public static class Person {
        public static final String TAB_PERSON = "person";
        public static final String _ID = "_id";
        public static final String NAME = "name";
        public static final String PHONE = "phone";
        public static final String SEX = "sex";
        public static final String AGE = "age";
    }
}
