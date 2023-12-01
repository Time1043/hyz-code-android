package com.example.dialogdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dialogdemo.dialog.TestDialogFragment;

/**
 * DialogFragment
 */
public class DialogFragmentActivity extends AppCompatActivity implements
        View.OnClickListener {

    private View showDialogBtn;
    private View showDialogFragmentBtn;

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogfragment);
        showDialogBtn = findViewById(R.id.ac_df_dialog_show_btn);
        showDialogFragmentBtn = findViewById(R.id.ac_df_dialogfragment_show_btn);

        showDialogBtn.setOnClickListener(this);
        showDialogFragmentBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ac_df_dialog_show_btn:
                showDialog();
                break;
            case R.id.ac_df_dialogfragment_show_btn:
                showDialogFragment();
                break;

        }

    }

    TestDialogFragment dialogFragment;
    private void showDialogFragment() {
        if (dialogFragment == null){
            dialogFragment = new TestDialogFragment();
//            dialogFragment.setCancelable(false);
        }
        dialogFragment.show(getSupportFragmentManager(), "dialogFragment");
    }

    /**
     * 显示Dialog
     */
    private void showDialog() {
        if (dialog == null){
            dialog = new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_launcher)//设置标题的图片
                    .setTitle("普通对话框")//设置对话框的标题
                    .setMessage("这是一个普通对话框")//设置对话框的内容
                    //设置对话框的按钮
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(DialogFragmentActivity.this,
                                    "点击了取消按钮", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    })
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(DialogFragmentActivity.this,
                                    "点击了确定的按钮", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }).create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    Toast.makeText(DialogFragmentActivity.this,
                            "onDismiss", Toast.LENGTH_SHORT).show();
                }
            });
        }
        dialog.show();

    }


}