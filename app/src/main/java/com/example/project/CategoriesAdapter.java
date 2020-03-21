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

import com.example.project.Activities.ChannelActivity;
import com.example.project.Activities.VodActivity;
import com.example.project.Models.Items;
import com.example.project.Models.TvCategories;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<TvCategories> items;
    private Context context;
    private String username;
    private String password;
    private String categoryOfStreamId;

    public CategoriesAdapter(Context context, List<TvCategories> items, String username, String password, String categoryOfStreamId) {

        this.context = context;
        this.items = items;
        this.layoutInflater = LayoutInflater.from(context);
        this.password = password;
        this.username = username;
        this.categoryOfStreamId = categoryOfStreamId;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_categories, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TvCategories item = items.get(position);
        //holder.imageView.setImageResource(item.getImage());  // item.getIamge();
        holder.textView.setText(item.getCategoryName()); //item.getCategoryId() + ". "  +

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        //final ImageView imageView;
        final TextView textView;


        ViewHolder(View view) {
            super(view);
            //imageView = view.findViewById(R.id.image_view);
            textView =  view.findViewById(R.id.text_view_category);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
//                    Class c;
//                    if(categoryOfStreamId.equals("1"))
//                        c = ChannelActivity.class;
//                    else c = VodActivity.class;
                    Intent intent = new Intent(context, ChannelActivity.class);


                    intent.putExtra("categoryId", items.get(position).getCategoryId());
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);
                    intent.putExtra("streamId", categoryOfStreamId);
                    v.getContext().startActivity(intent);

                }
            });
        }
    }
}
