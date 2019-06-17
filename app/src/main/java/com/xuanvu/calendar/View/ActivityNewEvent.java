package com.xuanvu.calendar.View;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.xuanvu.calendar.Database.MyDatabase;
import com.xuanvu.calendar.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;

public class ActivityNewEvent extends AppCompatActivity implements View.OnClickListener {

    private DatePickerDialog datePicker;
    private TimePickerDialog timePickerDialog;
/*    private TextView tv_date_from;
    private TextView tv_date_to;*/
    private TextView tv_time;
    private DateFormat dateFormatForNormal;
    private DateFormat dateFormatForTime;
    private Switch btn_fulltime;
    private Button btn_back;
    private Button btn_save;
    private MyDatabase myDatabase;
    private Calendar calendar;

    final int[] hour1 = new int[1];
    final int[] minutes1 = new int[1];
    final int[] cYear = new int[1];
    final int[] cMonth = new int[1];
    final int[] cDay = new int[1];

    @BindView( R.id.edt_Title )
    EditText edt_title;
    @BindView( R.id.edt_Content )
    EditText edt_content;
    @BindView( R.id.tv_date_from )
    TextView tv_date_from;
    @BindView( R.id.edt_Content )
    TextView tv_date_to;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_new_event );
        tv_date_from = findViewById( R.id.tv_date_from );
        tv_date_to = findViewById( R.id.tv_date_to );
        /* tv_time = findViewById( R.id.tv_time );*/
        btn_fulltime = findViewById( R.id.btn_fulltime );
        btn_back = findViewById( R.id.btn_back );
        btn_save = findViewById( R.id.btn_save );

        dateFormatForTime = new SimpleDateFormat( "EEE dd MMM yyyy ", Locale.getDefault() );
        dateFormatForNormal = new SimpleDateFormat( "hh:mm EEE dd MMM yyyy ", Locale.getDefault() );
        String currentDateandTime = dateFormatForNormal.format( new Date() );
        /*String cureentTime = dateFormatForTime.format( new Date() );*/

        tv_date_from.setText( currentDateandTime );
        tv_date_to.setText( currentDateandTime );
        /*    tv_time.setText( cureentTime );*/


        tv_date_from.setOnClickListener( this );
        tv_date_to.setOnClickListener( this );
        /*    tv_time.setOnClickListener( this );*/
        btn_fulltime.setOnClickListener( this );
        btn_back.setOnClickListener( this );
        btn_save.setOnClickListener( this );

        Calendar getDate = Calendar.getInstance();
        cDay[0] = getDate.get( Calendar.DAY_OF_MONTH );
        cMonth[0] = getDate.get( Calendar.MONTH );
        cYear[0] = getDate.get( Calendar.YEAR );

        saveEvent();


    }

    private void saveEvent() {
        /*Intent intent = new Intent( this, MainActivity.class );
        if (edt_title.getText().toString().equals( "" ) && edt_content.getText().toString().equals( "" )) {
            intent.putExtra( "MESSAGE", false );
            setResult( Activity.RESULT_CANCELED, intent );
            finishActivity( 200 );
        } else {
//            currentTime = Calendar.getInstance().getTime();
            Calendar noteObjForUpdate = new Calendar( edt_title.getText().toString(), edt_content.getText().toString(),tv_date_from.getText().toString() );
            myDatabase.updateCalendar( noteObjForUpdate, calendar.getID() );
            Toast.makeText( this, "Saved your note", Toast.LENGTH_SHORT ).show();
            intent.putExtra( "MESSAGE", true );
            setResult( Activity.RESULT_OK, intent );
//            finish();
            finishActivity( 200 );
        }*/
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
                            hourOfDay -= 12;
                            AM_PM = "PM";
                        } else if (hourOfDay == 0) {
                            hourOfDay += 12;
                            AM_PM = " AM";
                        } else if (hourOfDay == 12) AM_PM = "AM";
                        else AM_PM = "AM";


                        hour1[0] = hourOfDay;
                        minutes1[0] = minute;

                        datePicker = new DatePickerDialog( ActivityNewEvent.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                cYear[0] = year;
                                cMonth[0] = month;
                                cDay[0] = dayOfMonth;

                                tv_date_from.setText( String.format( "%02d:%02d", hour1[0], minutes1[0] ) + AM_PM + "  " + cDay[0] + "/" + cMonth[0] + "/" + cYear[0] );

                            }
                        }, cYear[0], cMonth[0], cDay[0] );

                        datePicker.show();
                    }
                }, hour1[0], minutes1[00], false );

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
                            hourOfDay -= 12;
                            AM_PM = "PM";
                        } else if (hourOfDay == 0) {
                            hourOfDay += 12;
                            AM_PM = " AM";
                        } else if (hourOfDay == 12) AM_PM = "AM";
                        else AM_PM = "AM";


                        hour1[0] = hourOfDay;
                        minutes1[0] = minute;

                        DatePickerDialog datePicker = new DatePickerDialog( ActivityNewEvent.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                cYear[0] = year;
                                cMonth[0] = month;
                                cDay[0] = dayOfMonth;

                                tv_date_to.setText( String.format( "%02d:%02d", hour1[0], minutes1[0] ) + AM_PM + "  " + cDay[0] + "/" + cMonth[0] + "/" + cYear[0] );

                            }
                        }, cYear[0], cMonth[0], cDay[0] );

                        datePicker.show();
                    }
                }, hour1[0], minutes1[00], false );

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
                onBackPressed();
                break;

        }


    }
}
