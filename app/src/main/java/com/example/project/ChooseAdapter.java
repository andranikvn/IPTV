package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Activities.CategoryActivity;
import com.example.project.Activities.ChannelActivity;
import com.example.project.Activities.MainActivity;
import com.example.project.Models.TvCategories;

import java.util.List;

public class ChooseAdapter extends RecyclerView.Adapter<ChooseAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<TvCategories> items;
    private Context context;
    private String username, password;
    private int[] image = new int[] {R.drawable.channels, R.drawable.films, R.drawable.series, R.drawable.logout};



    public ChooseAdapter(Context context, List<TvCategories> items, String username, String password) {

        this.context = context;
        this.items = items;
        this.layoutInflater = LayoutInflater.from(context);
        this.username = username;
        this.password = password;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_category_of_stream, parent, false);
        return new ChooseAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TvCategories item = items.get(position);
        holder.textView.setText(item.getCategoryName());
        holder.imageView.setImageResource(image[position]);



    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textView;
        final ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            int imageNum = 0;
            textView = itemView.findViewById(R.id.text_view_item_cof);
            imageView = itemView.findViewById(R.id.image_view_cof);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Class c = CategoryActivity.class;
                    if(position == 3)
                        c = MainActivity.class;


                    Intent intent = new Intent(context, c);
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);
                    intent.putExtra("id", items.get(position).getCategoryId());
                    v.getContext().startActivity(intent);



                }
            });

        }

    }
}


