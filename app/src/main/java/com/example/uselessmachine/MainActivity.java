package com.example.uselessmachine;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonSelfDestruct;
    private Switch switchUseless;
    private View bgelement;
    public static final String TAG = MainActivity.class.getSimpleName();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        setListeners();
        
    }

    private void wireWidgets() {
        buttonSelfDestruct = findViewById(R.id.button_main_selfdestruct);
        switchUseless = findViewById(R.id.switch_main_useless);
        bgelement = findViewById(R.id.layout);
    }

    private void setListeners() {
        //TODO self destruct button
        buttonSelfDestruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSelfDestructSequence();
            }
        });
        switchUseless.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
                    startSwitchOffTimer();
                    Toast.makeText(MainActivity.this, "On", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    Toast.makeText(MainActivity.this, "Off", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void startSelfDestructSequence() {
        // Disable the Button

        buttonSelfDestruct = (Button) findViewById(R.id.button_main_selfdestruct);
        buttonSelfDestruct.setEnabled(false);new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) millisUntilFinished / 1000;

                Toast.makeText(MainActivity.this, "Self Destructing in " + seconds, Toast.LENGTH_SHORT).show();
                if(seconds <= 5)
                {
                    if(seconds % 2 != 0)
                    {
                        bgelement.setBackgroundColor(Color.rgb(255,40,40));
                    }
                    else if(seconds % 2 == 0)
                    {
                        bgelement.setBackgroundColor(Color.rgb(255,255,255));
                    }

                }
            }

            @Override
            public void onFinish() {
                finish();
            }
        }.start();

    }

    private void startSwitchOffTimer() {
        new CountDownTimer(5000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(!switchUseless.isChecked())
                {
                    Log.d(TAG, "onTick: Canceling...");
                    cancel();

                }

            }

            @Override
            public void onFinish() {
                switchUseless.setChecked(false);
                Log.d(TAG, "onFinish: Switch set to false");
            }
        }.start();
    }


}
