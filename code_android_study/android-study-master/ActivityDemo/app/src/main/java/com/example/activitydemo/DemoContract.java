package com.example.activitydemo;

import android.net.Uri;

public class DemoContract {
    public static final String AUTHORITY = "com.example.demoprovider.provider";

    private static final Uri AUTHORITY_URI =  Uri.parse("content://" + AUTHORITY);

    public static final Uri PERSON_URI =  Uri.withAppendedPath(AUTHORITY_URI,"person");

    public static final Uri PERSON_ID_URI =  Uri.withAppendedPath(AUTHORITY_URI,"person/#");

    public static final String READ_PROVIDER = "com.example.demoprovider.provider.permission.READ_PROVIDER";



    public static class Person {
        public static final String TAB_PERSON = "person";
        public static final String _ID = "_id";
        public static final String NAME = "name";
        public static final String PHONE = "phone";
        public static final String SEX = "sex";
        public static final String AGE = "age";
    }
}
