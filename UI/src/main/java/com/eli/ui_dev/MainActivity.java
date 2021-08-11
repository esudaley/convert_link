package com.eli.ui_dev;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_main);

        ArrayList<Track> trackList = new ArrayList<Track>();

        trackList.add(new Track("Modeh Ani", "Pumpidisa", "https://i.scdn.co/image/ab67616d0000b27339528228f8777d3ec622dc70"));
        trackList.add(new Track("Modeh Ani", "Moshe Tischler", "https://i.scdn.co/image/ab67616d0000b2732b64b82cccf39f77e2ea3aef"));
        trackList.add(new Track("Modeh Ani","Uri Davidi","https://i.scdn.co/image/ab67616d0000b2731c7a14e34636f8b22f9fe104"));
        trackList.add(new Track("Modeh Ani","Nefesh Mountain","https://i.scdn.co/image/ab67616d0000b273499e29a29b1e5ef86fbc3f64"));
        trackList.add(new Track("Modeh Ani","Soulful Acappella","https://i.scdn.co/image/ab67616d0000b273c09d97ccddb18134338a5504"));
        trackList.add(new Track("Modeh Ani","Shalsheles Junior","https://i.scdn.co/image/ab67616d0000b273ec4fc13266e2fedc7c869043"));
        trackList.add(new Track("Modeh Ani", "Pumpidisa", "https://i.scdn.co/image/ab67616d0000b27339528228f8777d3ec622dc70"));
        trackList.add(new Track("Modeh Ani", "Moshe Tischler", "https://i.scdn.co/image/ab67616d0000b2732b64b82cccf39f77e2ea3aef"));
        trackList.add(new Track("Modeh Ani","Uri Davidi","https://i.scdn.co/image/ab67616d0000b2731c7a14e34636f8b22f9fe104"));


        ListView listView = (ListView) findViewById(R.id.list_view);
        TrackAdapter mAdapter = new TrackAdapter(this, trackList);
        listView.setAdapter(mAdapter);

        new ImageFetcher(this).execute(trackList);
    }
}