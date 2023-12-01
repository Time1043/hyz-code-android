package com.example.dialogdemo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

/**
 * Android对话框概念和分类
 */
public class BasisActivity extends AppCompatActivity implements View.OnClickListener {

    private Button dialogBtn;
    private Button popupwindowBtn;
    private Button dialogfragmentBtn;
    private Button testBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basis);

        dialogBtn = findViewById(R.id.ac_basis_dialog_btn);
        popupwindowBtn = findViewById(R.id.ac_basis_popupwindow_btn);
        dialogfragmentBtn = findViewById(R.id.ac_basis_dialogfragment_btn);
        testBtn = findViewById(R.id.ac_basis_test_btn);

        dialogBtn.setOnClickListener(this);
        popupwindowBtn.setOnClickListener(this);
        dialogfragmentBtn.setOnClickListener(this);
        testBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ac_basis_dialog_btn:
                showDialog();
                break;
            case R.id.ac_basis_popupwindow_btn:
                showPopupwindow();
                break;
            case R.id.ac_basis_dialogfragment_btn:
                showDialogfragment();
                break;
            case R.id.ac_basis_test_btn:
                Toast.makeText(this, "其他按钮被点击了",
                        Toast.LENGTH_SHORT).show();
                break;
        }

    }

    /**
     * 显示Dialog
     */
    private void showDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_launcher)//设置标题的图片
                .setTitle("我是对话框")//设置对话框的标题
                .setMessage("我是对话框的内容")//设置对话框的内容
                //设置对话框的按钮
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(BasisActivity.this,
                                "点击了取消按钮", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(BasisActivity.this,
                                "点击了确定的按钮", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    /**
     * 显示Popupwindow
     */
    private void showPopupwindow() {

        // 获取自定义布局文件pop.xml的视图
        View view = getLayoutInflater().inflate(R.layout.popupwindow_demo,
                null, false);


        PopupWindow popupWindow = new PopupWindow(view,
                400, 350);

        View ok = view.findViewById(R.id.pop_demo_ok);
        View cancle = view.findViewById(R.id.pop_demo_cancle);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BasisActivity.this,
                        "PopupWindow OK", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BasisActivity.this,
                        "PopupWindow Cancle", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });
        //设置点击窗口外边窗口消失:默认
        popupWindow.setOutsideTouchable(true);

        // 设置此参数获得焦点，否则无法点击:默认
        popupWindow.setFocusable(false);
        popupWindow.showAsDropDown(
                popupwindowBtn, 50, 0);

    }

    /**
     * 显示Dialogfragment
     */
    private void showDialogfragment() {
        MyDialogFragment myDialogFragment = new MyDialogFragment();
        myDialogFragment.show(getSupportFragmentManager(), "mydialog");
    }


    public static class MyDialogFragment extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog dialog =  new AlertDialog.Builder(getActivity())
                    .setTitle("DialogFragment示例")
                    .setMessage("我是DialogFragment示例对话框的内容")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create();
            return dialog;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.dialogfragment_demo, null);
        }
    }
}