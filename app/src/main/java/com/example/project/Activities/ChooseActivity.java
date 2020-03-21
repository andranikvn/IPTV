package com.example.project.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.project.CategoriesAdapter;
import com.example.project.ChooseAdapter;
import com.example.project.Models.TvCategories;
import com.example.project.R;

import java.util.ArrayList;
import java.util.List;

public class ChooseActivity extends AppCompatActivity {
    String username, password;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            username = bundle.getString("username");
            password = bundle.getString("password");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        List<TvCategories> categories = new ArrayList<>();

        TvCategories live = new TvCategories();
        TvCategories vod  = new TvCategories();
        TvCategories series = new TvCategories();
        TvCategories logOut = new TvCategories();

        live.setCategoryId("1");
        live.setCategoryName("Live Channels");

        vod.setCategoryId("2");
        vod.setCategoryName("Films");

        series.setCategoryId("3");
        series.setCategoryName("Tv Series");

        logOut.setCategoryId("0");
        logOut.setCategoryName("Logout");

        categories.add(live);
        categories.add(vod);
        categories.add(series);
        categories.add(logOut);

        RecyclerView recyclerView = findViewById(R.id.rc_choose);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        ChooseAdapter  adapter = new ChooseAdapter(this , categories, username, password  );
        recyclerView.setAdapter(adapter);

    }
}
