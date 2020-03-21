package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Activities.EpisodeActivity;
import com.example.project.Models.SeasonsModel;
import com.example.project.Models.SeriesModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeasonsAdapter extends RecyclerView.Adapter<SeasonsAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<SeasonsModel> items;
    private Context context;
    private String username, password, js;

    public SeasonsAdapter(Context context, List<SeasonsModel> items, String username, String password, String js) {

        this.context = context;
        this.items = items;
        this.layoutInflater = LayoutInflater.from(context);
        this.username = username;
        this.password = password;
        this.js = js;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new SeasonsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageView image = holder.imageView;
        SeasonsModel item = items.get(position);
        holder.textView.setText(item.getName());

        String url = item.getCover();

        try {
            Picasso.with(context).load(url).into(image);
        }
        catch(Exception e) {}


    }

    @Override
    public int getItemCount() {  return items.size();    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        final ImageView imageView;
        final TextView textView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_view);
            textView =  itemView.findViewById(R.id.text_view_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int seasonId = getAdapterPosition() + 1;
                    System.out.println(seasonId);
                    Intent intent = new Intent(context, EpisodeActivity.class);
                    intent.putExtra("js", js);
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);
                    intent.putExtra("seasonId", seasonId);
                    v.getContext().startActivity(intent);
                }
            });


        }
    }

}
