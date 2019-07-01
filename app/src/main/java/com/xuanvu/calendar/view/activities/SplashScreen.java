package com.xuanvu.calendar.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xuanvu.calendar.R;
import com.xuanvu.calendar.view.activities.main.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {
    long delayTime = 1200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_splash_screen );
        initSPlash();
    }

    private void initSPlash() {
        Timer timeRunSpFlash = new Timer();
        TimerTask showSPFlash = new TimerTask() {
            @Override
            public void run() {
                finish();
                Intent intent = new Intent( SplashScreen.this, MainActivity.class );
                intent.addFlags( Intent.FLAG_ACTIVITY_NO_ANIMATION );
                intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                overridePendingTransition( 0, 0 );
                startActivity( intent );
            }
        };

        timeRunSpFlash.schedule( showSPFlash, delayTime );
    }
}
