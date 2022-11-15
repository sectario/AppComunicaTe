package com.sectario.appcomunicate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Long delay = 5000l;
        Timer timer = new Timer("timer");
        Intent i = new Intent(this, MainActivity.class);

        Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(i);
            }
        };

        timer.schedule(task, delay);

    }
}