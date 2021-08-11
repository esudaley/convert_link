package com.eli.convertlink;

import android.app.Activity;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.TracksPager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

final class SpotifyConverter extends AppCompatActivity {
    final static SpotifyApi API = new SpotifyApi();
    final static SpotifyService SPOTIFY = API.getService();

    static Token token = Authenticator.generateToken();

    private SpotifyConverter(){}

    public static void searchTrack(MusicSearch search, ListView listView, Activity context) {
        if (token.expired()){
            token = Authenticator.generateToken();
        }

        print("We got the token " + token.getAccessToken());
        API.setAccessToken(token.getAccessToken());

        print("Searching for: " + search.getQuery());
        SPOTIFY.searchTracks(search.getQuery(), new Callback<TracksPager>() {
            @Override
            public void success(TracksPager tracksPager, Response response) {
                ArrayList<MusicTrack> musicList = new ArrayList<MusicTrack>();
                MusicTrack musicTrack;
                List<Track> spotifyResults = tracksPager.tracks.items;

                if (spotifyResults.isEmpty()){
                    //figure out how to report back to View in Activity
                    return;
                }

                for (Track track : spotifyResults) {
                    musicList.add(new MusicTrack(track));
                    printDebugInfo(track);
                }

                TrackAdapter mAdapter = new TrackAdapter(context, musicList);
                listView.setAdapter(mAdapter);

                new ImageFetcher(context).execute(musicList);
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
    }

    private static void printDebugInfo(Track track) {
        print("Name: " + track.name);
        print("Artist: " + track.artists.get(0).name);
        print("Url: " + track.external_urls.get("spotify"));
    }

    static void print(String text) {
        Log.d("mytag", text);
    }

    static void print(int num){
        print(String.valueOf(num));
    }
}
