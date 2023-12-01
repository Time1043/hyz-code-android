package com.example.dialogdemo;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;


/**
 * PopupWindow
 */
public class PopupWindowActivity extends AppCompatActivity implements View.OnClickListener {


    private View constructorBtn;
    private View showBtn;

    private View showAsDropDownBtn;
    private View showAtLocationBtn;

    private View updateBtn;


    private final String TAG = "PopupWindowActivity";

    private PopupWindow updatePopupWindow;

    private int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popupwindow);

        constructorBtn = findViewById(R.id.ac_popupwindow_constructor_btn);
        showBtn = findViewById(R.id.ac_popupwindow_show_btn);
        showAsDropDownBtn = findViewById(R.id.ac_popupwindow_showasdropdown_btn);
        showAtLocationBtn = findViewById(R.id.ac_popupwindow_showatlocation_btn);
        updateBtn = findViewById(R.id.ac_popupwindow_update_btn);

        constructorBtn.setOnClickListener(this);
        showBtn.setOnClickListener(this);
        showAsDropDownBtn.setOnClickListener(this);
        showAtLocationBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ac_popupwindow_constructor_btn:
                popupConstructor();
                break;
            case R.id.ac_popupwindow_show_btn:
                popupShow();
                break;
            case R.id.ac_popupwindow_showasdropdown_btn:
                showAsDropDown();
                break;
            case R.id.ac_popupwindow_showatlocation_btn:
                showAtLocation();
                break;
            case R.id.ac_popupwindow_update_btn:
                update();
                break;
        }

    }

    private void update() {
        if (updatePopupWindow == null){
            View view = getLayoutInflater().inflate(R.layout.popupwindow_update,
                    null, false);

            updatePopupWindow = new PopupWindow(view,400,360);
            updatePopupWindow.setOutsideTouchable(true);

            View moveV = view.findViewById(R.id.pop_update_move);
            moveV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    x = x+5;
                    updatePopupWindow.update(x,0,400,360);
//                    updatePopupWindow.update(updateBtn,x,0,400,360);
                }
            });
        }
        x=0;
        updatePopupWindow.showAtLocation(updateBtn,
                Gravity.TOP|Gravity.LEFT,x,0);
//        updatePopupWindow.showAsDropDown(updateBtn);
    }

    private void showAtLocation() {
        View view = getLayoutInflater().inflate(R.layout.popupwindow_show,
                null, false);
        PopupWindow popupWindow = new PopupWindow(view,300,360);


        popupWindow.setOutsideTouchable(true);

        popupWindow.showAtLocation(constructorBtn, Gravity.TOP|Gravity.LEFT,0,0);
    }

    private void showAsDropDown() {
        View view = getLayoutInflater().inflate(R.layout.popupwindow_show,
                null, false);
        PopupWindow popupWindow = new PopupWindow(view,300,360);


        popupWindow.setOutsideTouchable(true);

        popupWindow.showAsDropDown(showAsDropDownBtn,0,0,Gravity.LEFT);
    }

    private void popupShow() {
        View view = getLayoutInflater().inflate(R.layout.popupwindow_show,
                null, false);
        PopupWindow popupWindow = new PopupWindow(view,300,360);


        popupWindow.setOutsideTouchable(true);



        popupWindow.showAsDropDown(showBtn);
    }

    private void popupConstructor() {

        XmlResourceParser parser = getResources().getXml(R.xml.popupatts);
        AttributeSet attributes = null;
        int type;
        try {
            while ((type = parser.getEventType()) != XmlPullParser.START_TAG
                    && type != XmlPullParser.END_DOCUMENT) {
                parser.next();
            }
            attributes = Xml.asAttributeSet(parser);
        }catch (Exception e){
            Log.e(TAG,e.toString());
        }



        PopupWindow popupWindow = new PopupWindow(this,attributes,
              /* R.attr.PopUpAttr*/0,/*R.style.DefPopUpStyle*/0);

        View view = getLayoutInflater().inflate(R.layout.popupwindow_constructor,
                null, false);

        popupWindow.setContentView(view);

        
        popupWindow.setOutsideTouchable(true);

        popupWindow.showAsDropDown(constructorBtn);


        if (parser != null){
            parser.close();
        }
    }


}