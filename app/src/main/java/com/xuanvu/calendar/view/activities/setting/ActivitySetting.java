package com.xuanvu.calendar.view.activities.setting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.xuanvu.calendar.R;


public class ActivitySetting extends AppCompatActivity implements View.OnClickListener {
    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";

    private Button btn_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        SharedPreferences preferences = getSharedPreferences( PREFS_NAME, MODE_PRIVATE );
        boolean useDarkTheme = preferences.getBoolean( PREF_DARK_THEME, false );
        if (useDarkTheme) {
            setTheme( R.style.AppTheme_Dark_NoActionBar );
        }
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_setting );

        Switch nightmode = findViewById( R.id.nightmode );
        nightmode.setChecked( useDarkTheme );
        nightmode.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                toggle( ischecked );
            }
        } );
        innitView();
    }

    private void innitView() {
        btn_back = findViewById( R.id.btn_back );
        btn_back.setOnClickListener( this );
    }

    private void toggle(final boolean darkTheme) {

        SharedPreferences.Editor editor = getSharedPreferences( PREFS_NAME, MODE_PRIVATE ).edit();
        editor.putBoolean( PREF_DARK_THEME, darkTheme );
        editor.apply();

        Intent intent = getIntent();
        finish();
        startActivity( intent );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                onBackPressed();
                break;
        }
    }
}
