package com.example.fragmentdemo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentResultListener;

import com.example.fragmentdemo.R;
import com.example.fragmentdemo.fragment.CmmFragmentA;
import com.example.fragmentdemo.fragment.CmmFragmentB;
import com.example.fragmentdemo.fragment.CmmFragmentNew;
import com.example.fragmentdemo.fragment.CmmFragmentOld;

/**
 * 与Fragment的通信
 */
public class FragmentCommunicationActivity extends AppCompatActivity {

    private static final String TAG = "CommunicationActivity";

    private CmmFragmentOld frgmentOld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmentcommunication);

        //旧方式fragment与宿主间的通信
        initCmmFragmentOld();

        //新方式fragment与宿主间的通信
        initCmmFragmentNew();

        //新方式fragment与fragment间的通信
        initCmmFragmentAB();

    }


    /**
     * 旧方式fragment与宿主间的通信
     */
    private void initCmmFragmentOld() {

        frgmentOld = new CmmFragmentOld();
        //加载的时候设置数据
        Bundle bundle = new Bundle();
        bundle.putString("init","init data from host");
        frgmentOld.setArguments(bundle);

        frgmentOld.setiDataFragment(new CmmFragmentOld.IDataFragment() {
            @Override
            public void dataCallBack(Bundle bundle) {
                String result = bundle.getString("from");
                Log.i(TAG,"data from "+result);
            }
        });

        getSupportFragmentManager().beginTransaction()
                .add(R.id.ac_fragmentcmm_containerold, frgmentOld)
                .commit();

        //“老方式发送到fragment” 安妮点击事件
        findViewById(R.id.ac_fragmentcmm_senddata_old).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle result = new Bundle();
                        result.putString("from","Host CmmunicationActivity by old");
                        frgmentOld.setData(result);
                    }
                });



    }

    /**
     * 新方式fragment与宿主间的通信
     */
    private void initCmmFragmentNew() {


        getSupportFragmentManager().beginTransaction()
                .add(R.id.ac_fragmentcmm_containernew, CmmFragmentNew.class, null)
                .commit();
        getSupportFragmentManager().setFragmentResultListener("cmmnew_to_host",
                this, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey,
                                                 @NonNull Bundle bundle) {
                        String result = bundle.getString("from");
                        Log.i(TAG, "data from " + result);
                    }
        });


        //“新方式发送到fragment” 安妮点击事件
        findViewById(R.id.ac_fragmentcmm_senddata_new).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle result = new Bundle();
                        result.putString("from","Host CmmunicationActivity by new");
                        getSupportFragmentManager().setFragmentResult("host_to_cmmnew",
                                result);
                    }
                });
    }

    /**
     * 新方式fragment与fragment间的通信
     */
    private void initCmmFragmentAB(){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.ac_fragmentcmm_containera, CmmFragmentA.class,null)
                .commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.ac_fragmentcmm_containerb, CmmFragmentB.class,null)
                .commit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        getSupportFragmentManager().clearFragmentResultListener("cmmnew_to_host");
    }
}