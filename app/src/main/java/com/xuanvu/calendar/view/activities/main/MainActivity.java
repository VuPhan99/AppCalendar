package com.xuanvu.calendar.view.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.xuanvu.calendar.R;
import com.xuanvu.calendar.database.MyDatabase;
import com.xuanvu.calendar.model.Event;
import com.xuanvu.calendar.view.activities.editevent.ActivityEditEvent;
import com.xuanvu.calendar.view.activities.newevent.ActivityNewEvent;
import com.xuanvu.calendar.view.activities.setting.ActivitySetting;
import com.xuanvu.calendar.view.adapter.EventAdapter;
import com.xuanvu.calendar.view.fragments.BottomSheetFragment;
import com.xuanvu.calendar.view.fragments.FragmentDay;
import com.xuanvu.calendar.view.fragments.FragmentEvent;
import com.xuanvu.calendar.view.fragments.FragmentMonth;
import com.xuanvu.calendar.view.fragments.FragmentWeek;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, EventAdapter.OnClickItemListener {

    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";

    private static final String TAG = MainActivity.class.getSimpleName();
    private BottomSheetBehavior mBottomSheetBehavior;
    List<Event> listEvent;
    EventAdapter eventAdapter;
    RecyclerView mRecyclerView;
    MyDatabase myDatabase;
    public static final int RESULT_CODE_ADD = 100;
    public static final int RESULT_CODE_EDIT = 200;
    public static final int REQUEST_CODE = 10000;

    CompactCalendarView compactCalendarView;
    private TextView tv_date;
    private Button btn_month;
    private Button btn_week;
    private Button btn_day;
    private Button btn_event;
    private FragmentManager frmManager;

    @BindView(R.id.btn_change_day)
    ImageButton btn_chang_day;
    @BindView(R.id.btn_setting)
    ImageButton btn_setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        ButterKnife.bind( this );

        FloatingActionButton fab = findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, ActivityNewEvent.class );
                startActivity( intent );
            }
        } );
        initFragmentContent();
        bottomsheetdialog();
        initView();
        initCalendar();
    }

    private void initFragmentContent() {
        frmManager = getSupportFragmentManager();
        FragmentTransaction frmTransaction = frmManager.beginTransaction();
        frmTransaction.add( R.id.fragment_content, new FragmentMonth(), null );
        frmTransaction.commit();

    }

    private void bottomsheetdialog() {
        //Find bottom Sheet ID
        View bottomSheet = findViewById( R.id.bottom_sheet );
        mBottomSheetBehavior = BottomSheetBehavior.from( bottomSheet );

        //By default set BottomSheet Behavior as Collapsed and Height 0
        mBottomSheetBehavior.setState( BottomSheetBehavior.STATE_COLLAPSED );
        mBottomSheetBehavior.setPeekHeight( 0 );

        //If you want to handle callback of Sheet Behavior you can use below code
        mBottomSheetBehavior.setBottomSheetCallback( new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.d( TAG, "State Collapsed" );
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.d( TAG, "State Dragging" );
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.d( TAG, "State Expanded" );
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.d( TAG, "State Hidden" );
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.d( TAG, "State Settling" );
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        } );
    }

    private void initView() {
        mRecyclerView = findViewById( R.id.mRecyclerView );
        btn_month = findViewById( R.id.btn_month );
        btn_week = findViewById( R.id.btn_week );
        btn_day = findViewById( R.id.btn_day );
        btn_event = findViewById( R.id.btn_event );
        btn_month.setOnClickListener( this );
        btn_week.setOnClickListener( this );
        btn_day.setOnClickListener( this );
        btn_event.setOnClickListener( this );
        btn_chang_day.setOnClickListener( this );
        btn_setting.setOnClickListener( this );
    }

    private void initCalendar() {

        listEvent = new ArrayList<Event>();
        myDatabase = new MyDatabase( this );
        listEvent = myDatabase.getAllEvent();
        eventAdapter = new EventAdapter( this, R.layout.item_event, listEvent, this );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( this );
        mRecyclerView.setLayoutManager( layoutManager );
        mRecyclerView.setAdapter( eventAdapter );
        eventAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initCalendar();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

        if (requestCode == RESULT_CODE_ADD) {
            boolean resultCreate = data.getBooleanExtra( "NEW_DATA_EVENT", false );
            if (resultCreate == true) {
                listEvent.clear();
                listEvent = myDatabase.getAllEvent();
                initCalendar();
            }
            if (requestCode == RESULT_CODE_EDIT) {
                if (data != null) {
                    listEvent.clear();
                    listEvent = myDatabase.getAllEvent();
                    eventAdapter.notifyDataSetChanged();
                    initCalendar();
                }
            }
        }
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
            case R.id.btn_change_day:
                BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
                bottomSheetFragment.show( getSupportFragmentManager(), bottomSheetFragment.getTag() );
                break;
            case R.id.btn_setting:
                Intent intent = new Intent( this, ActivitySetting.class );
                startActivity( intent );
                break;
        }

    }

    @Override
    public void onItemRecyclerClicked(int postion, int actions) {
        Event event = listEvent.get( postion );
        Intent intent = new Intent( MainActivity.this, ActivityEditEvent.class );
        intent.putExtra( "calendar", event );
        startActivityForResult( intent, REQUEST_CODE );
    }
}
