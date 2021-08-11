package com.eli.convertlink;

public class MusicSearch {
    private String trackName, artist, query;

    public MusicSearch(String sharedText) {
        parseShareText(sharedText);
        setQuery();
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "TrackName : " + trackName +
                "\nArtist: " + artist;
    }

    protected void parseShareText(String sharedText){
        String track, artist;
        track = sharedText.substring(sharedText.indexOf("out ") + 4,
                sharedText.indexOf(" by"));
        artist = sharedText.substring(sharedText.indexOf("by ") + 3,
                sharedText.indexOf(" on"));
        setTrackName(track);
        setArtist(artist);
    }

    protected void setQuery(){
        query = trackName + " artist:" + artist;
    }

    protected String getQuery(){ return query; }

}
