package com.xuanvu.calendar.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.xuanvu.calendar.R;

import butterknife.BindView;

public class FragmentTop extends Fragment implements View.OnClickListener {

    private ToggleButton toggleButton;
    private LinearLayout layout_detail_view;

    @BindView(R.id.btn_change_day)
    Button btn_change_day;
    @BindView( R.id.btn_setting )
    Button btn_setting;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView( inflater, container, savedInstanceState );
        View view = inflater.inflate( R.layout.fragment_top, container, false );

        layout_detail_view = view.findViewById( R.id.layout_detail_view );

        layout_detail_view.setVisibility( View.GONE );

        btn_change_day = view.findViewById( R.id.btn_change_day );
        btn_setting = view.findViewById( R.id.btn_setting );


        btn_change_day.setVisibility( View.GONE );
        btn_setting.setVisibility( View.GONE );

        btn_setting.setOnClickListener( this );


        Button btn_top_detail = view.findViewById( R.id.btn_top_detail );
        btn_top_detail.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((ToggleButton) v).isChecked()) {
                    layout_detail_view.setVisibility( View.VISIBLE );
                    btn_change_day.setVisibility( View.VISIBLE );
                    btn_setting.setVisibility( View.VISIBLE );
                } else {
                    layout_detail_view.setVisibility( View.GONE );
                    btn_change_day.setVisibility( View.GONE );
                    btn_setting.setVisibility( View.GONE );
                }
            }
        } );



       /* Calendar calendar = Calendar.getInstance();
//        new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener) getActivity(),calendar.get( Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        SimpleDateFormat sdf = new SimpleDateFormat( "EEE dd MMMM yyyy" );*//*EEE MMM d yyyy*//*
        String currentDateandTime = sdf.format( new Date() );
        tv_date.setText( currentDateandTime );*/

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (getId()) {
            case R.id.btn_setting:
                break;
        }

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
