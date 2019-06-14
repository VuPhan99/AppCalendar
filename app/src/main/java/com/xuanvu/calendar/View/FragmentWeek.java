package com.xuanvu.calendar.View;

import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.xuanvu.calendar.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class FragmentWeek extends Fragment implements WeekView.EventClickListener, WeekView.EventLongPressListener, WeekView.EmptyViewLongPressListener {
    private WeekView mWeekView;
    private Calendar clickedTime;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView( inflater, container, savedInstanceState );
        View view = inflater.inflate( R.layout.fragment_week, container, false );

        //init weekview
        mWeekView = (WeekView) view.findViewById( R.id.weekView );

        mWeekView.setOnEventClickListener( this );
        mWeekView.setMonthChangeListener( mMonthChangeListener );
        mWeekView.setEventLongPressListener( this );
        mWeekView.setEmptyViewLongPressListener( this );

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
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /*
     * Handle Event Weekly
     * */
    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {

    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {

    }

    @Override
    public void onEmptyViewLongPress(Calendar time) {

    }

    public WeekView getWeekView() {
        return mWeekView;
    }


    MonthLoader.MonthChangeListener mMonthChangeListener = new MonthLoader.MonthChangeListener() {
        @Override
        public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {
            // Populate the week view with some events.
            List<WeekViewEvent> events = new ArrayList<>();
            Calendar startTime = (Calendar) clickedTime.clone();
            startTime.set( Calendar.MINUTE, 0 );
            startTime.set( Calendar.MONTH, newMonth );
            startTime.set( Calendar.YEAR, newYear );
            Calendar endTime = (Calendar) startTime.clone();
            endTime.add( Calendar.HOUR, 1 );
            WeekViewEvent event = new WeekViewEvent( 1, "Event Name", startTime, endTime );
            //event.setColor(getResources().getColor(R.color.event_color_02));
            events.add( event );
            return events;
        }
    };

}
