package com.example.demoprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.demoprovider.db.DBManager;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Person person1 = new Person();
        person1.setName("Coder");
        person1.setPhone("18629621252");
        person1.setSex("男");
        person1.setAge(34);
//
//        Person person2 = new Person();
//        person2.setName("Cyrus");
//        person2.setPhone("186****1252");
//        person2.setSex("女");
//        person2.setAge(33);
//
//        DBManager.getInstance(this).insertPerson(person1);
//        DBManager.getInstance(this).insertPerson(person2);

    }
}