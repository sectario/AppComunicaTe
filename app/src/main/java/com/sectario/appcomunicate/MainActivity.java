package com.sectario.appcomunicate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lsNews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    }
}