package com.example.project.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.Api;
import com.example.project.ErrorAlert;
import com.example.project.Models.SeasonsModel;
import com.example.project.Models.SeriesModel;
import com.example.project.R;
import com.example.project.SeasonsAdapter;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoActivity extends AppCompatActivity {

    private Api api;
    private String seriesId, username, password;
    private  Retrofit retrofit;
    List<SeasonsModel> seasonsModels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

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
            seriesId = bundle.getString("seriesId");


        }

        getSeriesInfo();



    }

    private void getSeriesInfo()
    {

        String action;

        action = "get_series_info";
        Call<ResponseBody> call = api.getSeriesInfo(username, password, "get_series_info", seriesId);


        System.out.println(username);
        System.out.println(password);
        System.out.println(seriesId);

        call.enqueue(new Callback<ResponseBody>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(!response.isSuccessful())
                {
                    System.out.println(response.code());
                }

                try {
                    String js = response.body().string();
                    System.out.println("5555555555555" + js);

                    JSONObject jsonObject = new JSONObject(js);
                    System.out.println(jsonObject.toString());
                    JSONObject info = jsonObject.getJSONObject("info");

                    JSONArray seasons = jsonObject.getJSONArray("seasons");
                    if(seasons.length() > 1)
                    {
                        System.out.println("000000000000000");

                        for(int i = 0; i < seasons.length(); i++)
                        {
                            JSONObject obj = seasons.getJSONObject(i);
                            SeasonsModel sm = new SeasonsModel();
                            sm.setName(obj.getString("name"));
                            sm.setCover(obj.getString("cover"));
                            sm.setSeasonId(obj.getInt("season"));

                            seasonsModels.add(sm);

                        }

                        RecyclerView recyclerView = findViewById(R.id.rv_seasons);
                        SeasonsAdapter adapter = new SeasonsAdapter(InfoActivity.this,seasonsModels, username, password, js );
                        recyclerView.setAdapter(adapter);

                    }
                    else
                    {
                        JSONObject obj = new JSONObject(js);
                        JSONObject episodes = obj.getJSONObject("episodes");
                        System.out.println("1111111111111" );

                       List<JSONArray> seasonsArr = new ArrayList<>();
                       for(int i = 1; i < 1000; i++)
                       {
                           try {
                               if(episodes.getJSONArray(String.valueOf(i)) != null)
                               {
                                   seasonsArr.add(episodes.getJSONArray(String.valueOf(i)));
                               }
                               else
                                   break;

                           }
                           catch (Exception e) { }


                       }

                        if( seasonsArr.size() > 0)
                        {
                            for(int i = 0; i < seasonsArr.size(); i++)
                            {
                                SeasonsModel sm = new SeasonsModel();
                                sm.setName("Season " + String.valueOf(i+1));
                                JSONObject object = obj.getJSONObject("info");

                                sm.setCover(object.getString("cover"));

                                seasonsModels.add(sm);
                            }
                            RecyclerView recyclerView = findViewById(R.id.rv_seasons);
                            SeasonsAdapter adapter = new SeasonsAdapter(InfoActivity.this, seasonsModels, username, password, js );
                            recyclerView.setAdapter(adapter);

                        }

                    }


                    TextView title, year, rating, rating5Based, genre, director, plot;
                    String url;

                    ImageView cover;

                    SeriesModel series = new SeriesModel();

                    title = findViewById(R.id.name_of_series);
                    year = findViewById(R.id.text_of_year);
                    rating = findViewById(R.id.rating);
                    rating5Based = findViewById(R.id.rating_5_based);
                    genre = findViewById(R.id.text_of_genre);
                    director = findViewById(R.id.text_of_director);
                    plot = findViewById(R.id.plot);
                    cover = findViewById(R.id.cover_of_series);

                    title.setText(info.getString("name"));
                    year.setText(year.getText() +  info.getString("releaseDate"));
                    rating.setText(rating.getText() +  info.getString("rating"));
                    genre.setText(genre.getText()  +  info.getString("genre"));
                    director.setText(director.getText() +  info.getString("director"));
                    plot.setText(plot.getText()  +  info.getString("plot"));
                    url = info.getString("cover");

                    Picasso.with(InfoActivity.this).load(url).into(cover);




                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }




            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ErrorAlert alert = new ErrorAlert(InfoActivity.this);
                alert.Alert("Failure", "Failure on InfoActivity");



            }
        });

    }
}
