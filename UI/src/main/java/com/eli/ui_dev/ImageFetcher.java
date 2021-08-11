package com.eli.ui_dev;

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

public class ImageFetcher extends AsyncTask<ArrayList<Track>, String, ArrayList<Track>> {

    private final WeakReference<Activity> weakActivity;

    ImageFetcher(Activity weakActivity) {
        this.weakActivity = new WeakReference<>(weakActivity);
    }

    protected void onPostExecute(ArrayList<Track> tracks) {
        Activity activity = weakActivity.get();
        if (activity == null
                || activity.isFinishing()
                || activity.isDestroyed()) {
            return;
        }
        ListView listView = (ListView) activity.findViewById(R.id.list_view);
        TrackAdapter mAdapter = (TrackAdapter) listView.getAdapter();
        mAdapter.setTracksList(tracks);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected ArrayList<Track> doInBackground(ArrayList<Track>... tracks) {
        ArrayList<Track> trackList = tracks[0];
        for (Track track : trackList){
            InputStream input = null;
            try {
                input = new URL(track.getImageUrl()).openStream();
                Bitmap bitmap = BitmapFactory.decodeStream(input);
                track.setBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return trackList;
    }


    
}
