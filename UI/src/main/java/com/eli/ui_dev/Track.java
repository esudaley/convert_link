package com.eli.ui_dev;

import android.graphics.Bitmap;

public class Track {
    private String name, artist, imageUrl;
    private Bitmap bitmap;
    private static String url = "google.com";

    public Track(String name, String artist, String imageUrl) {
        this.name = name;
        this.artist = artist;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
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

    public static String getUrl() {
        return url;
    }
}

