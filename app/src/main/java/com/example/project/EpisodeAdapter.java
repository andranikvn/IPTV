package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Activities.VideoActivity;
import com.example.project.Models.EpisodeModel;
import com.example.project.Models.TvChannels;

import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<EpisodeModel> items;
    private Context context;
    private String username, password;

    public EpisodeAdapter(Context context, List<EpisodeModel> items, String username, String password) {

        this.context = context;
        this.items = items;
        this.layoutInflater = LayoutInflater.from(context);
        this.username = username;
        this.password = password;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_episodes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EpisodeModel item = items.get(position);
        holder.textView.setText( "Episode: "   + item.getEpisodeNum() + " " + item.getTitle()) ;

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends  RecyclerView.ViewHolder
    {
        final TextView textView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view_category);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = String.valueOf(items.get(getAdapterPosition()).getId());
                    Intent intent = new Intent(context, VideoActivity.class);
                    intent.putExtra("id", id);

                    v.getContext().startActivity(intent);
                }
            });


        }
    }
}
