package com.xuanvu.calendar.view.activities.newevent;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.xuanvu.calendar.R;
import com.xuanvu.calendar.database.MyDatabase;
import com.xuanvu.calendar.model.Event;
import com.xuanvu.calendar.view.activities.main.MainActivity;
import com.xuanvu.calendar.view.fragments.FragmentWeek;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;

public class ActivityNewEvent extends AppCompatActivity implements View.OnClickListener {
    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";
    private DatePickerDialog datePicker;
    private TimePickerDialog timePicker;
    private TextView tv_starttime;
    private TextView tv_endtime;
    private TextView tv_startdate;
    private TextView tv_enddate;
    private TextView tv_time;
    private DateFormat dateFormatForNormal;
    private DateFormat dateFormatForTime;
    private Button btn_back;
    private Button btn_save;
    private MyDatabase myDatabase;
    private Calendar calendar;

    int hourTimePicker;
    int minutesTimePicker;
    int dayDatePicker;
    int monthDatePicker;
    int monthDatePicker2;
    int yearDatePicker;

    private Calendar clickedTime;
    private int newYear;
    private int newMonth;
    private int minute;


    @BindView(R.id.edt_Title)
    EditText edt_title;
    @BindView(R.id.edt_Content)
    EditText edt_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        sharePreferences();

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_new_event );
        initView();
        initDialogPicker();
    }

    private void sharePreferences() {
        SharedPreferences preferences = getSharedPreferences( PREFS_NAME, MODE_PRIVATE );
        boolean useDarkTheme = preferences.getBoolean( PREF_DARK_THEME, false );
        if (useDarkTheme) {
            setTheme( R.style.AppTheme_Dark_NoActionBar );
        }
    }

    private void initView() {
        edt_title = findViewById( R.id.edt_Title );
        edt_content = findViewById( R.id.edt_Content );
        tv_starttime = findViewById( R.id.tv_starttime );
        tv_endtime = findViewById( R.id.tv_endtime );
        tv_startdate = findViewById( R.id.tv_startdate );
        tv_enddate = findViewById( R.id.tv_enddate );
        btn_back = findViewById( R.id.btn_back );
        btn_save = findViewById( R.id.btn_save );

        tv_starttime.setOnClickListener( this );
        tv_endtime.setOnClickListener( this );
        tv_startdate.setOnClickListener( this );
        tv_enddate.setOnClickListener( this );
        btn_back.setOnClickListener( this );
        btn_save.setOnClickListener( this );


        dateFormatForTime = new SimpleDateFormat( "HH:mm", Locale.getDefault() );
        dateFormatForNormal = new SimpleDateFormat( "dd/MM/yyyy ", Locale.getDefault() );
        String currentDateandTime = dateFormatForTime.format( new Date() );
        String currentDateandDate = dateFormatForNormal.format( new Date() );

        tv_starttime.setText( currentDateandTime );
        tv_endtime.setText( currentDateandTime );
        tv_startdate.setText( currentDateandDate );
        tv_enddate.setText( currentDateandDate );
    }

    private void initDialogPicker() {
        myDatabase = new MyDatabase( this );
        /*newYear = 0;
        newMonth = 0;*/
        Calendar getDate = Calendar.getInstance();
        dayDatePicker = getDate.get( Calendar.DAY_OF_MONTH );
        monthDatePicker = getDate.get( Calendar.MONTH );
        yearDatePicker = getDate.get( Calendar.YEAR );
    }


    private void saveEvent() {
        Intent intent = new Intent( this, MainActivity.class );
        Event event = new Event();
        event.setmTitle( edt_title.getText().toString() );
        event.setmContent( edt_content.getText().toString() );
        event.setmStartTime( tv_starttime.getText().toString() );
        event.setmEndTime( tv_endtime.getText().toString() );
        event.setmStartDate( tv_startdate.getText().toString() );
        event.setmEndDate( tv_enddate.getText().toString() );


        if (myDatabase.addCalendar( event ) != 0) {
            intent.putExtra( "NEW_DATA_EVENT", event );
            setResult( FragmentWeek.RESULT_CODE_ADD, intent );
            finish();
        } else {
            Toast.makeText( ActivityNewEvent.this, " FAILE", Toast.LENGTH_SHORT ).show();

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_starttime:
                timePicker = new TimePickerDialog( ActivityNewEvent.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        hourTimePicker = hourOfDay;
                        minutesTimePicker = minute;
                        tv_starttime.setText( String.format( "%02d:%02d ", hourTimePicker, minutesTimePicker ) );

                    }
                }, hourTimePicker, minute, false );
                timePicker.show();

                break;
            case R.id.tv_endtime:
                timePicker = new TimePickerDialog( ActivityNewEvent.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                 /*       final String AM_PM;
                        if (hourOfDay > 12) {
                            hourOfDay = hourOfDay;
                            AM_PM = "PM";
                        } else if (hourOfDay == 0) {
                            hourOfDay += 12;
                            AM_PM = " AM";
                        } else if (hourOfDay == 12) AM_PM = "AM";
                        else AM_PM = "AM";          */

                        hourTimePicker = hourOfDay;
                        minutesTimePicker = minute;
                        tv_endtime.setText( String.format( "%02d:%02d ", hourTimePicker, minutesTimePicker ) /*+ AM_PM*/ );

                    }
                }, hourTimePicker, minute, false );
                timePicker.show();

                break;
            case R.id.tv_startdate:
                datePicker = new DatePickerDialog( ActivityNewEvent.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        yearDatePicker = year;
                        monthDatePicker = month;
                        monthDatePicker2 = month + 1;
                        dayDatePicker = dayOfMonth;
                        tv_startdate.setText( dayDatePicker + "/" + String.format( "%02d", monthDatePicker2 ) + "/" + yearDatePicker );
                    }
                }, yearDatePicker, monthDatePicker, dayDatePicker );
                datePicker.show();

                break;
            case R.id.tv_enddate:
                datePicker = new DatePickerDialog( ActivityNewEvent.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        yearDatePicker = year;
                        monthDatePicker = month;
                        monthDatePicker2 = month + 1;
                        dayDatePicker = dayOfMonth;
                        tv_enddate.setText( dayDatePicker + "/" + String.format( "%02d", monthDatePicker2 ) + "/" + yearDatePicker );
                    }
                }, yearDatePicker, monthDatePicker, dayDatePicker );
                datePicker.show();

                break;

            case R.id.btn_back:
                onBackPressed();
                break;
            case R.id.btn_save:
                saveEvent();
                break;

        }

    }
}