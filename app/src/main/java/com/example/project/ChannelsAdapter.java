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
import com.example.project.Activities.VideoActivity;
import com.example.project.Models.TvChannels;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChannelsAdapter extends RecyclerView.Adapter<ChannelsAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<TvChannels> items;
    private Context context;
    private String username, password;

    public ChannelsAdapter(Context context, List<TvChannels> items, String username, String password) {

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

        TvChannels item = items.get(position);
        holder.textView.setText(item.getNum() + ". "  +item.getChannelName());

        String url = item.getStreamIcon();

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

                    String id = String.valueOf(items.get(getAdapterPosition()).getStreamId());
                     Intent intent = new Intent(context, VideoActivity.class);
                     intent.putExtra("id", id);

                     v.getContext().startActivity(intent);





                }
            });
        }
    }
}
