package com.xuanvu.calendar.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuanvu.calendar.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private TextView tv_date;
    private Button btn_sheetDialogl;

    @BindView( R.id.btnBottomSheet )
    Button btnBottomSheet;

    @BindView(R.id.bottom_sheet_top )
    LinearLayout bottomSheetLayout;
    BottomSheetBehavior sheetBehavior;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        ButterKnife.bind(MainActivity.this);
       /* Toolbar toolbar = findViewById( R.id.toolbar );
*/
        /*TextView tv_date = findViewById( R.id.tv_date );
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE dd MMMM yyyy");*//*EEE MMM d yyyy*//*
        String currentDateandTime = sdf.format(new Date());
        tv_date.setText(currentDateandTime);*/
  /*      tv_date.append( calendar.getTime()+"" );*//*
        tv_date.append( calendar.get( Calendar.DATE ) + "" );
        tv_date.append( calendar.get( Calendar.MONTH ) + "" );
        tv_date.append( calendar.get( Calendar.DAY_OF_WEEK_IN_MONTH )+"" );
        tv_date.append( calendar.get( Calendar.MONTH )+"" );*//*
*/
        /*setSupportActionBar( toolbar );
*/
        FloatingActionButton fab = findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make( view, "Replace with your own action", Snackbar.LENGTH_LONG ).setAction( "Action", null ).show();
            }
        } );



        //init bottomShet
        sheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);

        //Callback function of bottom sheet
        sheetBehavior.setBottomSheetCallback( new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                switch (i) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        btnBottomSheet.setText("Close Sheet");
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        btnBottomSheet.setText("Expand Sheet");
                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        } );

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
