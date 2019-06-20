package com.xuanvu.calendar.View;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.alamkanak.weekview.WeekViewEvent;
import com.xuanvu.calendar.Database.MyDatabase;
import com.xuanvu.calendar.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import butterknife.BindView;

public class ActivityNewEvent extends AppCompatActivity implements View.OnClickListener {

    private DatePickerDialog datePicker;
    private TimePickerDialog timePickerDialog;
    private TextView tv_date_from;
    private TextView tv_date_to;
    private TextView tv_time;
    private DateFormat dateFormatForNormal;
    private DateFormat dateFormatForTime;
    private Switch btn_fulltime;
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

    /*  private int hourOfDay;*/
    private int minute;


    @BindView(R.id.edt_Title)
    EditText edt_title;
    @BindView(R.id.edt_Content)
    EditText edt_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_new_event );
        edt_title = findViewById( R.id.edt_Title );
        edt_content = findViewById( R.id.edt_Content );
        tv_date_from = findViewById( R.id.tv_date_from );
        tv_date_to = findViewById( R.id.tv_date_to );
        /* tv_time = findViewById( R.id.tv_time );*/
        btn_fulltime = findViewById( R.id.btn_fulltime );
        btn_back = findViewById( R.id.btn_back );
        btn_save = findViewById( R.id.btn_save );
        tv_date_from = findViewById( R.id.tv_date_from );

        dateFormatForTime = new SimpleDateFormat( "EEE dd MMM yyyy ", Locale.getDefault() );
        dateFormatForNormal = new SimpleDateFormat( "hh:mm EEE dd MMM yyyy ", Locale.getDefault() );
        String currentDateandTime = dateFormatForNormal.format( new Date() );
        /*String cureentTime = dateFormatForTime.format( new Date() );*/

        tv_date_from.setText( currentDateandTime );
        tv_date_to.setText( currentDateandTime );
        /*    tv_time.setText( cureentTime );*/
        //init database
        myDatabase = new MyDatabase( this );
        newYear = 0;
        newMonth = 0;

        tv_date_from.setOnClickListener( this );
        tv_date_to.setOnClickListener( this );
        /*    tv_time.setOnClickListener( this );*/
        btn_fulltime.setOnClickListener( this );
        btn_back.setOnClickListener( this );
        btn_save.setOnClickListener( this );

        Calendar getDate = Calendar.getInstance();
        dayDatePicker = getDate.get( Calendar.DAY_OF_MONTH );
        monthDatePicker = getDate.get( Calendar.MONTH );
        yearDatePicker = getDate.get( Calendar.YEAR );
//        Log.d( "debug_xv_first_time", String.valueOf( hourTimePicker + ": " + minutesTimePicker + " - " + dayDatePicker + "/" + monthDatePicker + "/" + yearDatePicker ) );

//        saveEvent();


    }

    private void saveEvent() {
        Intent intent = new Intent( this, MainActivity.class );
        /*if (edt_title.getText().toString().equals( "" ) && edt_content.getText().toString().equals( "" )) {
            intent.putExtra( "MESSAGE", false );
            setResult( Activity.RESULT_CANCELED, intent );
            finishActivity( 200 );*/
        /* } else {*/
        clickedTime = Calendar.getInstance();
        List<WeekViewEvent> events = new ArrayList<>();
        Calendar startTime = (Calendar) clickedTime.clone();
        startTime.set( Calendar.MINUTE, 0 );
        startTime.set( Calendar.MONTH, newMonth );
        startTime.set( Calendar.YEAR, newYear );
        Calendar endTime = (Calendar) startTime.clone();
        endTime.add( Calendar.HOUR, 1 );
/*
        com.xuanvu.calendar.Model.Calendar calendar = new com.xuanvu.calendar.Model.Calendar();
        calendar.setmTitle( edt_title.getText().toString() );
        calendar.setmContent( edt_content.getText().toString() );

        calendar.setmStartTimeNewHour( Calendar.HOUR );
        calendar.setmStartTimeNewMinute( Calendar.MINUTE );
        calendar.setmStartTimeNewDay( Calendar.DAY_OF_MONTH );
        calendar.setmStartTimeNewMonth( newMonth );
        calendar.setmStartTimeNewYear( newYear );

        calendar.setmEndTimeNewHour( Calendar.HOUR );
        calendar.setmEndTimeNewMinute( Calendar.MINUTE );
        calendar.setmEndTimeNewDay( Calendar.DAY_OF_MONTH );
        calendar.setmEndTimeNewMonth( newMonth );
        calendar.setmEndTimeNewYear( newYear );

        myDatabase.addCalendar( calendar );
        int idEvent = myDatabase.getLastedIdInsert();*/

//     /*   myDatabase.addCalendar( startTime );*/
//        WeekViewEvent event = new WeekViewEvent( 1, "new eventttttt", startTime, endTime );
//        //event.setColor(getResources().getColor(R.color.event_color_02));
//        events.add( event );

        /*intent.putExtra( "DATA_WEEK_EVENT", true );
        intent.putExtra( "DATA_ID_EVENT", idEvent );
        setResult( Activity.RESULT_OK, intent );
        finish();*/
        String from = tv_date_from.getText().toString();
        String to = tv_date_to.getText().toString();

        com.xuanvu.calendar.Model.Calendar calendar = new com.xuanvu.calendar.Model.Calendar();
        calendar.setmTitle( edt_title.getText().toString() );
        calendar.setmContent( edt_content.getText().toString() );

        calendar.setmStartTimeNewHour( Integer.parseInt( from.substring( 0, 2 ) ) );
        calendar.setmStartTimeNewMinute( Integer.parseInt( from.substring( 3, 5 ) ) );
        calendar.setmStartTimeNewDay( Integer.parseInt( from.substring( 9, 11 ) ) );
        calendar.setmStartTimeNewMonth( Integer.parseInt( from.substring( 12, 14 ) ) );
        calendar.setmStartTimeNewYear( Integer.parseInt( from.substring( 15,19 )));

        calendar.setmEndTimeNewHour( Integer.parseInt( to.substring( 0, 2 ) ) );
        calendar.setmEndTimeNewMinute( Integer.parseInt( to.substring( 3, 5 ) ) );
        calendar.setmEndTimeNewDay( Integer.parseInt( to.substring( 9, 11 ) ) );
        calendar.setmEndTimeNewMonth( Integer.parseInt( to.substring( 12, 14 ) ) );
        calendar.setmEndTimeNewYear( Integer.parseInt( to.substring( 15,19 )));


    /*    Log.d( "month",   sa.substring( 12 ,14) );
        Log.d( "year",   sa.substring( 15 ,19) );*/

        if (myDatabase.addCalendar( calendar ) != 0) {
            Intent intentSendBack = new Intent( ActivityNewEvent.this, FragmentWeek.class );
            intentSendBack.putExtra( "DATA_WEEK_EVENT", calendar );
            setResult( FragmentWeek.RESULT_CODE_ADD, intentSendBack );
            finish();
        } else {
            Toast.makeText( ActivityNewEvent.this, " FAILE", Toast.LENGTH_SHORT ).show();

        }


//    }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_date_from:
              /*  final Calendar cldr = Calendar.getInstance();
                int dayfrom = cldr.get( Calendar.DAY_OF_MONTH );
                int monthfrom = cldr.get( Calendar.MONTH );
                int yearfrom = cldr.get( Calendar.YEAR );
                picker = new DatePickerDialog( ActivityNewEvent.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tv_date_from.setText( dayOfMonth + "/" + (monthOfYear + 1) + "/" + year );
                    }
                }, yearfrom, monthfrom, dayfrom );
                picker.show();*/

                timePickerDialog = new TimePickerDialog( ActivityNewEvent.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


                        final String AM_PM;

                        if (hourOfDay > 12) {
                            hourOfDay = hourOfDay;
                            AM_PM = "PM";
                        } else if (hourOfDay == 0) {
                            hourOfDay += 12;
                            AM_PM = " AM";
                        } else if (hourOfDay == 12) AM_PM = "AM";
                        else AM_PM = "AM";


                        hourTimePicker = hourOfDay;
                        minutesTimePicker = minute;

                        datePicker = new DatePickerDialog( ActivityNewEvent.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                yearDatePicker = year;
                                monthDatePicker = month;
                                monthDatePicker2 = month + 1 ;
                                dayDatePicker = dayOfMonth;

                                tv_date_from.setText( String.format( "%02d:%02d", hourTimePicker, minutesTimePicker ) + AM_PM + "  " + dayDatePicker + "/"  +String.format( "%02d", monthDatePicker2 )+ "/" + yearDatePicker );
                                Log.d( "debug_xv_sm-date", "hours: " + hourTimePicker + " - minute: " + minutesTimePicker + " - day: " + dayDatePicker + " - month: " + String.format( "%02d", monthDatePicker2 )+ " - year: " + yearDatePicker );

                            }
                        }, yearDatePicker, monthDatePicker, dayDatePicker );

                        datePicker.show();
                    }
                }, hourTimePicker, minutesTimePicker, false );

                timePickerDialog.show();
                break;
            case R.id.tv_date_to:
                /*final Calendar calendar = Calendar.getInstance();
                int dayto = calendar.get( Calendar.DAY_OF_MONTH );
                int monthto = calendar.get( Calendar.MONTH );
                int yearto = calendar.get( Calendar.YEAR );
                picker = new DatePickerDialog( ActivityNewEvent.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tv_date_to.setText( dayOfMonth + "/" + (monthOfYear + 1) + "/" + year );
                    }
                }, yearto, monthto, dayto );
                picker.show();*/

                timePickerDialog = new TimePickerDialog( ActivityNewEvent.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


                        final String AM_PM;

                        if (hourOfDay > 12) {
                            hourOfDay = hourOfDay;
                            AM_PM = "PM";
                        } else if (hourOfDay == 0) {
                            hourOfDay += 12;
                            AM_PM = " AM";
                        } else if (hourOfDay == 12) AM_PM = "AM";
                        else AM_PM = "AM";


                        hourTimePicker = hourOfDay;
                        minutesTimePicker = minute;

                        datePicker = new DatePickerDialog( ActivityNewEvent.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                yearDatePicker = year;
                                monthDatePicker = month;
                                monthDatePicker2 = month + 1 ;
                                dayDatePicker = dayOfMonth;

                                tv_date_to.setText( String.format( "%02d:%02d", hourTimePicker, minutesTimePicker ) + AM_PM + "  " + dayDatePicker + "/"  +String.format( "%02d", monthDatePicker2 )+ "/" + yearDatePicker );
                                Log.d( "debug_xv_sm-date", "hours: " + hourTimePicker + " - minute: " + minutesTimePicker + " - day: " + dayDatePicker + " - month: " + String.format( "%02d", monthDatePicker2 )+ " - year: " + yearDatePicker );

                            }
                        }, yearDatePicker, monthDatePicker, dayDatePicker );

                        datePicker.show();
                    }
                }, hourTimePicker, minutesTimePicker, false );

                timePickerDialog.show();
                break;

     /*       case R.id.tv_time:
                final Calendar timeer = Calendar.getInstance();
                int hour = timeer.get( Calendar.HOUR_OF_DAY );
                int minute = timeer.get( Calendar.MINUTE );
                timePickerDialog = new TimePickerDialog( ActivityNewEvent.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tv_time.setText(String.format( "%02d:%02d", hourOfDay, minute  ));
                    }
                }, hour, minute, false );
                timePickerDialog.show();
                break;*/

            case R.id.btn_fulltime:
                String cureentTime = dateFormatForTime.format( new Date() );
                String currentDateandTime = dateFormatForNormal.format( new Date() );
                if (btn_fulltime.isChecked()) {
                    tv_date_from.setText( cureentTime );
                    tv_date_to.setText( cureentTime );
                } else {
                    tv_date_from.setText( currentDateandTime );
                    tv_date_to.setText( currentDateandTime );
                }
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