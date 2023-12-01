package com.example.activitydemo.activity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activitydemo.R;

/**
 * 测试Service跨进程Messager通信Activity
 */
public class ServiceMessengerIpcActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "MessengerIpcActivity";

    private Messenger messengerProxy;

    private int messageid = 0;

    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            Log.i(TAG,"收到远程服务数据："+bundle.getString("service"));
        }
    };

    private Messenger clientMessenger = new Messenger(handler);

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            Log.i(TAG,"onServiceConnected：");
            messengerProxy = new Messenger(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG,"onServiceDisconnected：");
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_messenger_ipc);

        Button bindRemoteService = findViewById(R.id.ac_messengeripcservice_bind);
        bindRemoteService.setOnClickListener(this);

        Button unbindRemoteService = findViewById(R.id.ac_messengeripcservice_unbind);
        unbindRemoteService.setOnClickListener(this);

        Button sendRemoteService = findViewById(R.id.ac_messengeripcservice_send);
        sendRemoteService.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ac_messengeripcservice_bind:
                bindRemoteService();
                break;
            case R.id.ac_messengeripcservice_unbind:
                if (messengerProxy != null){
                    unbindService(conn);
                    messengerProxy = null;
                }
                break;
            case R.id.ac_messengeripcservice_send:
                sendData();
                break;
        }
    }

    private void sendData(){
        if (messengerProxy == null){
            return;
        }
        Message message = new Message();
        message.replyTo = clientMessenger;
        Bundle bundle = new Bundle();
        bundle.putString("client","这是客户端发送数据："+messageid);
        message.setData(bundle);
        try {
            messengerProxy.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onDestroy() {
        Log.i(TAG,"onDestroy");
        if (messengerProxy != null){
            unbindService(conn);
            messengerProxy = null;
        }
        super.onDestroy();

    }

    private void bindRemoteService(){
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.example.aidltest",
                "com.example.aidltest.MessengerService"));
        boolean isBind = bindService(intent,conn, Service.BIND_AUTO_CREATE);
        Log.i(TAG,"bindRemoteService isBind="+isBind);
    }
}