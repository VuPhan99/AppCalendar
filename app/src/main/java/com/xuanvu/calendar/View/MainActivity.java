package com.xuanvu.calendar.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xuanvu.calendar.R;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private TextView tv_date;
    private Button btn_sheetDialogl;

/*    @BindView( R.id.btnBottomSheet )
    Button btnBottomSheet;

    @BindView(R.id.bottom_sheet_top )
    LinearLayout bottomSheetLayout;
    BottomSheetBehavior sheetBehavior;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        ButterKnife.bind( MainActivity.this );
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
