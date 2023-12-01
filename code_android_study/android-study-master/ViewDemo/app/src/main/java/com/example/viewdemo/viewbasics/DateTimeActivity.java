package com.example.viewdemo.viewbasics;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;

import java.util.Calendar;

/**
 * 测试Date&Time
 *
 * @author CoderCao
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class DateTimeActivity extends BaseActivity implements View.OnClickListener {

    private final String TAG = "DateTimeActivity";

    private DatePicker datePicker;
    private TimePicker timePicker;

    private Button dateTimeConfir;
    private TextView dateTimeResult;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    private Button chooseDate;
    private Button chooseTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1、加载XML声明的布局
        setContentView(R.layout.activity_datetime);
        datePicker = findViewById(R.id.date_picker);
        timePicker = findViewById(R.id.time_picker);
        dateTimeConfir = findViewById(R.id.datetime_confirm);
        dateTimeResult = findViewById(R.id.datetime_result);

        chooseDate = findViewById(R.id.datetime_datedialog);
        chooseDate.setOnClickListener(this);
        chooseTime = findViewById(R.id.datetime_timedialog);
        chooseTime.setOnClickListener(this);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
//                @Override
//                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//
//                }
//            });
//        }

        dateTimeConfir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int day = datePicker.getDayOfMonth();

                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                dateTimeResult.setText(year+"年"+(month+1)+"月"+day+"日"+hour+"时"+minute+"分");


            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.datetime_datedialog:
                showDateDialog();
                break;
            case R.id.datetime_timedialog:
                showTimeDialog();
                break;
        }
    }

    //7.0
    private void showDateDialog(){
        Calendar calendar= Calendar.getInstance();
        if (datePickerDialog == null){
            datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    chooseDate.setText(year+"年"+(month+1)+"月"+dayOfMonth+"日");
                }
            },calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        }
        datePickerDialog.updateDate(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void showTimeDialog(){
        Calendar calendar= Calendar.getInstance();
        if (timePickerDialog == null){
            timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    chooseTime.setText(hourOfDay+"时"+minute+"分");
                }
            },calendar.HOUR_OF_DAY,calendar.MINUTE,true);
        }
        timePickerDialog.updateTime(calendar.HOUR_OF_DAY,calendar.MINUTE);
        timePickerDialog.show();
    }
}