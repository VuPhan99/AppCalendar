package com.xuanvu.calendar.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.xuanvu.calendar.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Fragment_Top extends Fragment {

    private TextView tv_date;

    Unbinder unbinder;

    @BindView( R.id.btn_sheetDialog )
    Button btn_sheetDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView( inflater, container, savedInstanceState );
        View view = inflater.inflate( R.layout.fragment_top,container,false );
        TextView tv_date = view.findViewById( R.id.tv_date );
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat( "EEE dd MMMM yyyy" );/*EEE MMM d yyyy*/
        String currentDateandTime = sdf.format( new Date() );
        tv_date.setText( currentDateandTime );

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

}
