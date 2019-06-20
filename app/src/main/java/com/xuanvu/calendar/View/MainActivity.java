package com.xuanvu.calendar.View;

import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.xuanvu.calendar.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private BottomSheetBehavior mBottomSheetBehavior;

    CompactCalendarView compactCalendarView;
    private TextView tv_date;
    private Button btn_month;
    private Button btn_week;
    private Button btn_day;
    private Button btn_event;
    private FragmentManager frmManager;
    long mCalendarId;

    @BindView(R.id.btn_change_day)
    Button btn_chang_day;
    @BindView( R.id.btn_setting )
    Button btn_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        ButterKnife.bind( this );

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

        frmManager = getSupportFragmentManager();
        FragmentTransaction frmTransaction = frmManager.beginTransaction();
        frmTransaction.add( R.id.fragment_content, new FragmentMonth(), null );
        frmTransaction.commit();

        FloatingActionButton fab = findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( getApplicationContext(), "Clicked on Mainactivity", Toast.LENGTH_SHORT ).show();
                Intent intent = new Intent( MainActivity.this, ActivityNewEvent.class );
                startActivity( intent );

               /* Calendar cal = GregorianCalendar.getInstance();
                Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
                builder.appendPath("time");
                ContentUris.appendId(builder, cal.getTimeInMillis());
                Intent intent = new Intent(Intent.ACTION_VIEW)
                        .setData(builder.build());
                startActivity(intent);*/

                /*Snackbar.make( view, "Replace with your own action", Snackbar.LENGTH_LONG ).setAction( "Action", null ).show();*/
            }
        } );

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
                //Check the current state of bottom sheet
                /*if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED)
                    //If state is in collapse mode expand it
                    mBottomSheetBehavior.setState( BottomSheetBehavior.STATE_EXPANDED );
                else
                    //else if state is expanded collapse it
                    mBottomSheetBehavior.setState( BottomSheetBehavior.STATE_COLLAPSED );*/

                BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
                bottomSheetFragment.show( getSupportFragmentManager(), bottomSheetFragment.getTag() );
                break;
            case R.id.btn_setting:
                Intent intent = new Intent( this, ActivitySetting.class );
                startActivity( intent );
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
