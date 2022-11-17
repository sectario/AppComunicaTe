package com.sectario.appcomunicate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class InformationActivity extends AppCompatActivity {


    Button buttonNextActiviTy2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        buttonNextActiviTy2 = findViewById(R.id.buttonNextActiviTy2);


        Intent i = new Intent(this, Calendar.class);
        buttonNextActiviTy2.setOnClickListener(view -> startActivity(i));





    }
}