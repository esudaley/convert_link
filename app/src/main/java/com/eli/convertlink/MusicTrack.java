package com.eli.convertlink;

import android.graphics.Bitmap;

import kaaes.spotify.webapi.android.models.Track;

public class MusicTrack {
    private String trackName, artist, imageUrl;
    private Bitmap bitmap;
    private String url;


    public MusicTrack(Track track) {
        this.trackName = track.name;
        this.artist = track.artists.get(0).name;
        this.imageUrl = track.album.images.get(0).url;
        this.url = track.external_urls.get("spotify");
    }

    public String getTrackName() {
        return trackName;
    }

    public String getArtist() {
        return artist;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setBitmap(Bitmap image) {
        this.bitmap = image;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setUrl(String url) { this.url = url; }

    public String getUrl() { return url; }

}

