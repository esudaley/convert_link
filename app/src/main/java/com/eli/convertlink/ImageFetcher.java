package com.eli.convertlink;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;

import static com.eli.convertlink.SpotifyConverter.print;

public class ImageFetcher extends AsyncTask<ArrayList<MusicTrack>, String, ArrayList<MusicTrack>> {

    private final WeakReference<Activity> weakActivity;

    ImageFetcher(Activity weakActivity) {
        this.weakActivity = new WeakReference<>(weakActivity);
    }
    @Override
    protected void onPostExecute(ArrayList<MusicTrack> trackList) {
        Activity activity = weakActivity.get();
//        if (activity == null
//                || activity.isFinishing()
//                || activity.isDestroyed()) {
//            return;
//        }
        ListView listView = (ListView) activity.findViewById(R.id.list_view);
        TrackAdapter adpater = (TrackAdapter) listView.getAdapter();
        print("here at the end");
//        trackList.forEach(a -> print(a.getBitmap().toString()));
        adpater.setList(trackList);
        adpater.notifyDataSetChanged();
    }

    @Override
    protected ArrayList<MusicTrack> doInBackground(ArrayList<MusicTrack>... tracks) {
        ArrayList<MusicTrack> spotifyList = tracks[0];


        for (MusicTrack currentTrack : spotifyList){
            InputStream input = null;
            try {
                input = new URL(currentTrack.getImageUrl()).openStream();
                print("here" + currentTrack.getImageUrl());
                Bitmap bitmap = BitmapFactory.decodeStream(input);
                currentTrack.setBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return spotifyList;
    }



}
