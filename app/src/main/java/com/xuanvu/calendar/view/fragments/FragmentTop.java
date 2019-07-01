package com.xuanvu.calendar.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.xuanvu.calendar.R;

import butterknife.BindView;

public class FragmentTop extends Fragment {

    private ToggleButton toggleButton;
    private LinearLayout layout_detail_view;

    @BindView(R.id.btn_change_day)
    ImageButton btn_change_day;
    @BindView(R.id.btn_setting)
    ImageButton btn_setting;


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


        final Button btn_top_detail = view.findViewById( R.id.btn_top_detail );
        btn_top_detail.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((ToggleButton) v).isChecked()) {
                    btn_top_detail.setBackgroundResource( R.drawable.ic_arrow_drop_up_black_24dp );
                    layout_detail_view.setVisibility( View.VISIBLE );
                    btn_change_day.setVisibility( View.VISIBLE );
                    btn_setting.setVisibility( View.VISIBLE );
                } else {
                    btn_top_detail.setBackgroundResource( R.drawable.ic_arrow_drop_down_black_24dp );
                    layout_detail_view.setVisibility( View.GONE );
                    btn_change_day.setVisibility( View.GONE );
                    btn_setting.setVisibility( View.GONE );
                }
            }
        } );

        return view;
    }

}
