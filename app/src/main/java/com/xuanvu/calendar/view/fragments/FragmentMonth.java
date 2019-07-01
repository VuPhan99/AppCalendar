package com.xuanvu.calendar.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.xuanvu.calendar.R;
import com.xuanvu.calendar.view.activities.newevent.ActivityNewEvent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.ButterKnife;

public class FragmentMonth extends Fragment {
    private CompactCalendarView compactCalendarView;


    private TextView tv_date;
    private SimpleDateFormat dateFormatForMonth;
    private SimpleDateFormat dateFormatForNormal;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView( inflater, container, savedInstanceState );
        View view = inflater.inflate( R.layout.fragment_month, container, false );

        ButterKnife.bind( this, view );
        dateFormatForNormal = new SimpleDateFormat( "EEE dd MMMM yyyy", Locale.getDefault() );
        compactCalendarView = view.findViewById( R.id.compactcalendar_view );
        tv_date = getActivity().findViewById( R.id.tv_date );

        final String currentDateandTime = dateFormatForNormal.format( new Date() );
        tv_date.setText( currentDateandTime );

        compactCalendarView.setDayColumnNames(new String[]{"T2", "T3", "T4", "T5", "T6", "T7", "CN"});
        compactCalendarView.setListener( new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {

                tv_date.setText( dateFormatForNormal.format( dateClicked ) );
                Intent intent = new Intent( getActivity(), ActivityNewEvent.class );
                startActivity( intent );
            }


            @Override
            public void onMonthScroll(Date date) {
                tv_date.setText( dateFormatForNormal.format( date ) );

            }
        } );
        return view;
    }

}
