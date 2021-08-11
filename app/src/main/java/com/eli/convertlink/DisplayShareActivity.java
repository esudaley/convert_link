package com.eli.convertlink;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_main);

        Intent intent = getIntent();
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        ListView listView = (ListView)findViewById(R.id.list_view);
        MusicSearch search = new MusicSearch(sharedText);
        new Thread(new Runnable() {
            @Override
            public void run() {
                SpotifyConverter.searchTrack(search, listView, DisplayShareActivity.this);
            }
        }).start();

    }






}