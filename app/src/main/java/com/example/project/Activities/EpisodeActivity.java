package com.example.project.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.project.Api;
import com.example.project.EpisodeAdapter;
import com.example.project.Models.EpisodeModel;
import com.example.project.R;
import com.example.project.SeasonsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EpisodeActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private Api api;
    private String username, password, seasonId, js;
    List<EpisodeModel> episodes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode);

        episodes = new ArrayList<>();

        retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            username = bundle.getString("username");
            password = bundle.getString("password");
            seasonId = String.valueOf(bundle.getInt("seasonId"));
            js = bundle.getString("js");

        }

        getEpisodes();


    }

    private void getEpisodes() {
        try {
            JSONObject obj = new JSONObject(js);



            JSONObject jsonObject = obj.getJSONObject("episodes");
            System.out.println("1234565 " + jsonObject.toString());

            JSONArray arr = jsonObject.getJSONArray(seasonId);
            System.out.println("98888888" + arr);
            for(int i = 0; i < arr.length(); i++)
            {
                EpisodeModel episode = new EpisodeModel();

                JSONObject j = arr.getJSONObject(i);

                episode.setId(j.getString("id"));
                episode.setEpisodeNum(j.getInt("episode_num"));
                episode.setTitle(j.getString("title"));

                episodes.add(episode);

            }




        } catch (JSONException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = findViewById(R.id.rv_episodes);
        EpisodeAdapter adapter = new EpisodeAdapter(EpisodeActivity.this,episodes, username, password );
        recyclerView.setAdapter(adapter);




    }
}