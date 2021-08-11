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

    private Context mContext;
//    private List<Track> list = new ArrayList<>();
    private List<MusicTrack> list;

    public TrackAdapter(Context context, List<MusicTrack> list) {
        super(context, 0 , list);
        mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        MusicTrack currentTrack = list.get(position);

        ImageView image = (ImageView)listItem.findViewById(R.id.imageView_albumArt);
        Bitmap b = currentTrack.getBitmap();
        if (b == null){
            b = BitmapFactory.decodeResource(mContext.getResources(),
                R.drawable.blank_album);
        }
        image.setImageBitmap(b);
        TextView name = (TextView) listItem.findViewById(R.id.textView_name);
        name.setText(currentTrack.getTrackName());
        TextView artist = (TextView) listItem.findViewById(R.id.textView_artist);
        artist.setText(currentTrack.getArtist());
        ImageView shareIcon = listItem.findViewById(R.id.share_button);
        shareIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String body = currentTrack.getUrl();
//                String sub = "Your Subject";
//                myIntent.putExtra(Intent.EXTRA_SUBJECT,sub);
                myIntent.putExtra(Intent.EXTRA_TEXT,body);
                mContext.startActivity(Intent.createChooser(myIntent, "Share Using"));
            }
        });
        return listItem;
    }

    public void setList(List<MusicTrack> list) {
        this.list = list;

    }
}