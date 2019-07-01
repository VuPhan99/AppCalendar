package com.xuanvu.calendar.view.activities.editevent;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class ActivityEditEvent extends AppCompatActivity implements View.OnClickListener {
    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";
    private Event event;
    private EditText edt_title;
    private EditText edt_content;
    private TextView tv_starttime;
    private TextView tv_endtime;
    private TextView tv_startdate;
    private TextView tv_enddate;
    private Button btn_save;
    private Button btn_close;
    private Button btn_delete;
    private DatePickerDialog datePicker;
    private TimePickerDialog timePicker;
    int hourTimePicker;
    int minutesTimePicker;
    int dayDatePicker;
    int monthDatePicker;
    int monthDatePicker2;
    int yearDatePicker;
    int minute;

    MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharePreferences();
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_edit_event );
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

    private void initDialogPicker() {
        java.util.Calendar getDate = java.util.Calendar.getInstance();
        dayDatePicker = getDate.get( java.util.Calendar.DAY_OF_MONTH );
        monthDatePicker = getDate.get( java.util.Calendar.MONTH );
        yearDatePicker = getDate.get( java.util.Calendar.YEAR );
    }

    private void initView() {
        edt_title = findViewById( R.id.edt_Title );
        edt_content = findViewById( R.id.edt_Content );
        tv_starttime = findViewById( R.id.tv_starttime );
        tv_endtime = findViewById( R.id.tv_endtime );
        tv_startdate = findViewById( R.id.tv_startdate );
        tv_enddate = findViewById( R.id.tv_enddate );
        btn_save = findViewById( R.id.btn_save );
        btn_close = findViewById( R.id.btn_back );
        btn_delete = findViewById( R.id.btn_delete );

        btn_save.setOnClickListener( this );
        btn_close.setOnClickListener( this );
        tv_starttime.setOnClickListener( this );
        tv_endtime.setOnClickListener( this );
        tv_startdate.setOnClickListener( this );
        tv_enddate.setOnClickListener( this );
        btn_delete.setOnClickListener( this );

        myDatabase = new MyDatabase( this );
        Intent intent = getIntent();
        event = (Event) intent.getSerializableExtra( "calendar" );
        edt_title.setText( event.getmTitle() );
        edt_content.setText( event.getmContent() );
        tv_starttime.setText( event.getmStartTime() );
        tv_endtime.setText( event.getmEndTime() );
        tv_startdate.setText( event.getmStartDate() );
        tv_enddate.setText( event.getmEndDate() );

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_starttime:
                timePicker = new TimePickerDialog( ActivityEditEvent.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        hourTimePicker = hourOfDay;
                        minutesTimePicker = minute;
                        tv_starttime.setText( String.format( "%02d:%02d ", hourTimePicker, minutesTimePicker ) /*+ AM_PM*/ );

                    }
                }, hourTimePicker, minute, false );
                timePicker.show();

                break;
            case R.id.tv_endtime:
                timePicker = new TimePickerDialog( ActivityEditEvent.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        hourTimePicker = hourOfDay;
                        minutesTimePicker = minute;
                        tv_endtime.setText( String.format( "%02d:%02d ", hourTimePicker, minutesTimePicker ) /*+ AM_PM*/ );

                    }
                }, hourTimePicker, minute, false );
                timePicker.show();

                break;
            case R.id.tv_startdate:

                datePicker = new DatePickerDialog( ActivityEditEvent.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        java.util.Calendar calendar = java.util.Calendar.getInstance();
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
                datePicker = new DatePickerDialog( ActivityEditEvent.this, new DatePickerDialog.OnDateSetListener() {
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
            case R.id.btn_save:
                event.setmTitle( edt_title.getText().toString() );
                event.setmContent( edt_content.getText().toString() );
                event.setmStartTime( tv_starttime.getText().toString() );
                event.setmEndTime( tv_endtime.getText().toString() );
                event.setmStartDate( tv_startdate.getText().toString() );
                event.setmEndDate( tv_enddate.getText().toString() );

                if (myDatabase.updateCalendar( event )) {
                    Intent intentEdit = new Intent( ActivityEditEvent.this, MainActivity.class );
                    setResult( MainActivity.RESULT_CODE_EDIT, intentEdit );
                    /*Toast.makeText( ActivityEditEvent.this, "EDIT SUCCESS", Toast.LENGTH_SHORT ).show();*/
                    finish();
                } else {
                    Toast.makeText( ActivityEditEvent.this, "EDIT FAILE", Toast.LENGTH_SHORT ).show();
                }
                break;

            case R.id.btn_back:
                onBackPressed();
                break;
            case R.id.btn_delete:
                boolean isDeleted = myDatabase.deleteCalendar( event );
                if (isDeleted) {
                    /* Toast.makeText( getApplicationContext(), "Event Deleted", Toast.LENGTH_SHORT ).show();*/
                    Intent intent = new Intent( ActivityEditEvent.this, MainActivity.class );
                    overridePendingTransition( 0, 0 );
                    intent.putExtra( "REFRESH_STATUS", true );
                    startActivityForResult( intent, 200 );
                } else {
                    Toast.makeText( getApplicationContext(), "Error : Event not deleted", Toast.LENGTH_SHORT ).show();
                }
                break;

        }
    }
}
