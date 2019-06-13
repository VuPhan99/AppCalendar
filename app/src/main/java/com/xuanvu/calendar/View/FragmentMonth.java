package com.xuanvu.calendar.View;

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
        dateFormatForMonth = new SimpleDateFormat( "MMM", Locale.getDefault() );
        dateFormatForNormal = new SimpleDateFormat( "EEE dd MMM yyyy", Locale.getDefault() );
        compactCalendarView = view.findViewById( R.id.compactcalendar_view );
        tv_date = getActivity().findViewById( R.id.tv_date );

        String currentDateandTime = dateFormatForNormal.format( new Date() );
        tv_date.setText( currentDateandTime );

        compactCalendarView.setListener( new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                tv_date.setText( dateFormatForNormal.format( dateClicked ) );
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                tv_date.setText( dateFormatForNormal.format( firstDayOfNewMonth ) );

            }
        } );
        return view;
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
}
