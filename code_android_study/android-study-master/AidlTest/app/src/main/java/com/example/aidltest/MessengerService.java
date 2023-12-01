package com.example.aidltest;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MessengerService extends Service {

    private final String TAG = "MessengerService";

    private Handler messengerHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //取出客户端的消息内容
            Bundle bundle = msg.getData();
            String clientMsg = bundle.getString("client");
            Log.i(TAG, "来自客户端的消息：" + clientMsg);
            //新建一个Message对象，作为回复客户端的对象
            Message message = Message.obtain();
            Bundle bundle1 = new Bundle();
            bundle1.putString("service", clientMsg+",服务端收到");
            message.setData(bundle1);
            try {
                msg.replyTo.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };
    
    //创建服务端Messenger
    private final Messenger mMessenger = new Messenger(messengerHandler);
        
    @Override
    public IBinder onBind(Intent intent) {
        //向客户端返回IBinder对象，客户端利用该对象访问服务端
        return mMessenger.getBinder();
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate" );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }
}
