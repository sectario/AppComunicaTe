package com.sectario.appcomunicate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lsNews;
    Button buttonNextActiviTy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonNextActiviTy =    findViewById(R.id.buttonNextActiviTy);
        lsNews = findViewById(R.id.lsNews);

        //ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(this, R.array.list_news, android.R.layout.simple_list_item_1);

        ArrayList news = new ArrayList();

        news.add("juan");
        news.add("juan");
        news.add("juan");
        news.add("juan");
        news.add("juan");


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,news);

        lsNews.setAdapter(adapter);

        Intent i = new Intent(this, InformationActivity.class);
        buttonNextActiviTy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(i);
            }
        });

    }
}