package com.xuanvu.calendar.view.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.xuanvu.calendar.database.MyDatabase;
import com.xuanvu.calendar.R;
import com.xuanvu.calendar.model.Event;
import com.xuanvu.calendar.view.activities.newevent.ActivityNewEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class FragmentWeek extends Fragment implements WeekView.EventClickListener, WeekView.EventLongPressListener, WeekView.EmptyViewClickListener {
    public static final int RESULT_CODE_ADD = 1;
    private WeekView mWeekView;
    private Calendar clickedTime;
    private MyDatabase myDatabase;
    private int newYear;
    private int newMonth;
    private Event eventModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView( inflater, container, savedInstanceState );
        View view = inflater.inflate( R.layout.fragment_week, container, false );

        //init Database
        myDatabase = new MyDatabase( getActivity() );
        //init weekview
        mWeekView = (WeekView) view.findViewById( R.id.weekView );

        mWeekView.setOnEventClickListener( this );
        mWeekView.setMonthChangeListener( mMonthChangeListener );
        mWeekView.setEventLongPressListener( this );
        mWeekView.setEmptyViewClickListener( this );

        clickedTime = Calendar.getInstance();
        setupDateTimeInterpreter( false );
        return view;
    }

    private void setupDateTimeInterpreter(final boolean shortDate) {
        mWeekView.setDateTimeInterpreter( new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat( "EEE", Locale.getDefault() );
                String weekday = weekdayNameFormat.format( date.getTime() );
                SimpleDateFormat format = new SimpleDateFormat( " M/d", Locale.getDefault() );

                if (shortDate) weekday = String.valueOf( weekday.charAt( 0 ) );
                return weekday.toUpperCase() + format.format( date.getTime() );
            }

            @Override
            public String interpretTime(int hour) {
                return hour == 12 ? hour + "PM" : hour > 11 ? (hour - 12) + " PM" : (hour == 0 ? "0 AM" : hour + " AM");

            }
        } );
    }


    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 200) {
            if (resultCode == FragmentWeek.RESULT_CODE_ADD) {
//                // Nhận dữ liệu từ Intent trả về
//                ArrayList<WeekViewEvent> weekViewEvents = new ArrayList<>(  );
//                weekViewEvents = (ArrayList<WeekViewEvent>) data.getSerializableExtra("DATA_WEEK_EVENT"  );
//                Log.e( "i4Event", weekViewEvents.get( 0 ).getName());
                if (data.getBooleanExtra( "DATA_WEEK_EVENT", false ) == true) {
                    //do something
                    eventModel = myDatabase.getEventWithId( data.getIntExtra( "DATA_ID_EVENT", 0 ) );
                    Log.d( "debug_xv", "This case" );
                    mWeekView.setMonthChangeListener( mMonthChangeListener );


                } else {
                    //do something
                    Log.d( "debug_xv", "Nope case" );

                }
            } else {
                // không thành công, không có data trả về.
            }
        }

    }

    @Override
    public void onEmptyViewClicked(Calendar time) {
        Intent intent = new Intent( getActivity(), ActivityNewEvent.class );
        startActivityForResult( intent, 200 );


        clickedTime = (Calendar) time.clone();
        mWeekView.notifyDatasetChanged();
        Log.i( "msg", "Empty box has been filled successfully." );

    }

    public WeekView getWeekView() {
        return mWeekView;
    }


    MonthLoader.MonthChangeListener mMonthChangeListener = new MonthLoader.MonthChangeListener() {
        @Override
        public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {

            List<WeekViewEvent> events = new ArrayList<>();
            try {

                Calendar startTime = (Calendar) clickedTime.clone();
                startTime.set( Calendar.MINUTE, 0 );
                startTime.set( Calendar.MONTH, newMonth );
                startTime.set( Calendar.YEAR, newYear );
                Calendar endTime = (Calendar) startTime.clone();
                endTime.add( Calendar.HOUR, 1 );
                WeekViewEvent event = new WeekViewEvent( 1, "Event Name", startTime, endTime );
                events.add( event );
                return events;

            } catch (NullPointerException e) {
                Log.e( "errors", e.getMessage() );
            }
            return events;

        }
    };


}
