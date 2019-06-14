package com.xuanvu.calendar.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.xuanvu.calendar.R;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CompactCalendarView compactCalendarView;
    private TextView tv_date;
    private Button btn_month;
    private Button btn_week;
    private Button btn_day;
    private Button btn_event;
    private Button btn_change_day;
    private Button btn_setting;
    private FragmentManager frmManager;


    @BindView(R.id.btn_change_day)
    Button btnBottomSheet;

/*
    @BindView(R.id.bottom_sheet_cay)
    LinearLayout layoutBottomSheet;

    BottomSheetBehavior sheetBehavior;
*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        btn_month = findViewById( R.id.btn_month );
        btn_week = findViewById( R.id.btn_week );
        btn_day = findViewById( R.id.btn_day );
        btn_event = findViewById( R.id.btn_event );
        btn_change_day = findViewById( R.id.btn_change_day );
        btn_setting = findViewById( R.id.btn_setting );

        btn_month.setOnClickListener( this );
        btn_week.setOnClickListener( this );
        btn_day.setOnClickListener( this );
        btn_event.setOnClickListener( this );
       /* btn_change_day.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    sheetBehavior.setState( BottomSheetBehavior.STATE_EXPANDED );
                    btnBottomSheet.setText( "Close sheet" );
                } else {
                    sheetBehavior.setState( BottomSheetBehavior.STATE_COLLAPSED );
                    btnBottomSheet.setText( "Expand sheet" );
                }
            }
        } );*/


        frmManager = getSupportFragmentManager();
        FragmentTransaction frmTransaction = frmManager.beginTransaction();
        frmTransaction.add( R.id.fragment_content, new FragmentMonth(), null );
        frmTransaction.commit();

        FloatingActionButton fab = findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, ActivityNewEvent.class );
                startActivity( intent );
                /*Snackbar.make( view, "Replace with your own action", Snackbar.LENGTH_LONG ).setAction( "Action", null ).show();*/
            }
        } );
        /*ButterKnife.bind( this );*/
        /*sheetBehavior = BottomSheetBehavior.from( layoutBottomSheet );

        sheetBehavior.setBottomSheetCallback( new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        btnBottomSheet.setText( "Close Sheet" );
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        btnBottomSheet.setText( "Expand Sheet" );
                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        } );
*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_month:
                FragmentTransaction frmTransactionMonth = frmManager.beginTransaction();
                frmTransactionMonth.replace( R.id.fragment_content, new FragmentMonth(), null );
                frmTransactionMonth.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_OPEN );
                frmTransactionMonth.addToBackStack( null );
                frmTransactionMonth.commit();
                break;
            case R.id.btn_week:
                FragmentTransaction frmTransactionWeek = frmManager.beginTransaction();
                frmTransactionWeek.replace( R.id.fragment_content, new FragmentWeek(), null );
                frmTransactionWeek.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_OPEN );
                frmTransactionWeek.addToBackStack( null );
                frmTransactionWeek.commit();
                break;
            case R.id.btn_day:
                FragmentTransaction frmTransactionDay = frmManager.beginTransaction();
                frmTransactionDay.replace( R.id.fragment_content, new FragmentDay(), null );
                frmTransactionDay.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_OPEN );
                frmTransactionDay.addToBackStack( null );
                frmTransactionDay.commit();
                break;
            case R.id.btn_event:
                FragmentTransaction frmTransactionEvent = frmManager.beginTransaction();
                frmTransactionEvent.replace( R.id.fragment_content, new FragmentEvent(), null );
                frmTransactionEvent.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_OPEN );
                frmTransactionEvent.addToBackStack( null );
                frmTransactionEvent.commit();
                break;
        }

    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.menu_main, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected( item );
    }*/
}
