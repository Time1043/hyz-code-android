package com.example.aidltest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button bindService;
    private Button addPerson;
    private Button getPerson;
    private Button unBindService;
    private Intent intent;
    ArrayList<Person> persons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindService = (Button) findViewById(R.id.bindService);
        addPerson = (Button) findViewById(R.id.addPerson);
        getPerson = (Button) findViewById(R.id.getPerson);
        unBindService = (Button) findViewById(R.id.unBindService);
        bindService.setOnClickListener(this);
        addPerson.setOnClickListener(this);
        getPerson.setOnClickListener(this);
        unBindService.setOnClickListener(this);
        intent = new Intent();
        intent.setComponent(new ComponentName("com.example.aidltest",
                "com.example.aidltest.PersonAIDLService"));

    }

    private IPersonInterface iPersonManager;
    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("TAG", "onServiceConnected: ");
            iPersonManager = IPersonInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            iPersonManager = null;

        }
    };


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bindService:
                bindService(intent, conn, BIND_AUTO_CREATE);
                break;
            case R.id.addPerson:
                if (iPersonManager == null){
                    return;
                }
                try {
                    iPersonManager.addPerson(new Person(24, "yqf"));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.getPerson:
                if (iPersonManager == null){
                    return;
                }
                try {
                    persons = (ArrayList<Person>) iPersonManager.getPersonList();
                    Log.d("TAG", " 获取的数据" + persons.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.unBindService:
                unbindService(conn);
                iPersonManager = null;
                break;
            default:
                break;
        }
    }
}