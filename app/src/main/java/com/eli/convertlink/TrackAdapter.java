package com.eli.convertlink;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;


public class TrackAdapter extends ArrayAdapter<MusicTrack> {

    private Context context;
    private List<MusicTrack> list;

    public TrackAdapter(Context context, List<MusicTrack> musicList) {
        super(context, 0 , musicList);
        this.context = context;
        this.list = musicList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        TextView trackName = (TextView) listItem.findViewById(R.id.textView_name);
        TextView artist = (TextView) listItem.findViewById(R.id.textView_artist);
        ImageView albumArt = (ImageView)listItem.findViewById(R.id.imageView_albumArt);
        ImageView shareIcon = listItem.findViewById(R.id.share_button);

        if (listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        MusicTrack musicTrack = list.get(position);

        Bitmap bitmap = musicTrack.getBitmap();
        if (bitmap == null){
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.blank_album);
        }

        trackName.setText(musicTrack.getTrackName());
        artist.setText(musicTrack.getArtist());
        albumArt.setImageBitmap(bitmap);
        shareIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String body = musicTrack.getUrl();
//                String sub = "Your Subject";
//                myIntent.putExtra(Intent.EXTRA_SUBJECT,sub);
                myIntent.putExtra(Intent.EXTRA_TEXT,body);
                context.startActivity(Intent.createChooser(myIntent, "Share Using"));
            }
        });

        return listItem;
    }
}