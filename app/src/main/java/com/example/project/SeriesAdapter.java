package com.example.project;

import android.annotation.SuppressLint;
import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.project.Activities.InfoActivity;
import com.example.project.Models.SeriesModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<SeriesModel> items;
    private Context context;
    private String username, password;

    public SeriesAdapter(Context context, List<SeriesModel> items, String username, String password) {

        this.context = context;
        this.items = items;
        this.layoutInflater = LayoutInflater.from(context);
        this.username = username;
        this.password = password;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageView image = holder.imageView;

        SeriesModel item = items.get(position);
        holder.textView.setText(item.getNum() + ". "  +item.getName());

        String url = item.getCover();

        try {
            Picasso.with(context).load(url).into(image);
        }
        catch(Exception e) {}



    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView textView;


        ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.image_view);
            textView =  view.findViewById(R.id.text_view_item);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


//                    VideoView videoView = view.findViewById(R.id.videoView);
//                    MediaController mc = new MediaController(this);
//                    mc.setAnchorView(videoView);
//                    mc.setMediaPlayer(videoView);
//
//                    Uri video = Uri.parse("http://www.youtube.com/watch?v=qvtCk1wZ7LM&feature=player_detailpage");


//                    ErrorAlert alert = new ErrorAlert(context);
//
//                    alert.Alert("title", String.valueOf(items.get(getAdapterPosition()).getSeriesId()));

                    Intent intent =  new Intent(context, InfoActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);
                    intent.putExtra("seriesId", String.valueOf(items.get(getAdapterPosition()).getSeriesId()));
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
