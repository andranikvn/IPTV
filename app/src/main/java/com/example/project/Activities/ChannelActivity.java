package com.example.project.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.project.Api;
import com.example.project.CategoriesAdapter;
import com.example.project.ChannelsAdapter;
import com.example.project.ErrorAlert;
import com.example.project.Models.Items;
import com.example.project.Models.SeriesModel;
import com.example.project.Models.TvChannels;
import com.example.project.R;
import com.example.project.SeriesAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChannelActivity extends AppCompatActivity {
    Retrofit retrofit;
    Api api;
    String categoryId, categoryOfStreamId;
    List<TvChannels> tvChannels = new ArrayList<>();
    List<SeriesModel> seriesModels = new ArrayList<>();
    String username, password;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);



        retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
            categoryId = bundle.getString("categoryId");

        getChannels();





    }
    private void getJsonTv(Response<ResponseBody> response)
    {
        try {

            String js = response.body().string();
            System.out.println("sdfdddddddd  "   + js + "\n categoryID: " + categoryId);

            JSONArray jsonArray = new JSONArray(js);

            System.out.println("SIZE OF JSONARRAY " + jsonArray.length());
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject j = jsonArray.getJSONObject(i);
                TvChannels tv = new TvChannels();

                tv.setChannelName(j.getString("name"));
                tv.setStreamIcon(j.getString("stream_icon"));
                tv.setNum(j.getInt("num"));
                tv.setStreamId(j.getInt("stream_id"));


                tvChannels.add(tv);


            }



        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = findViewById(R.id.RecyclerViewListChannels);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        ChannelsAdapter adapter = new ChannelsAdapter(ChannelActivity.this , tvChannels, username, password  );
        recyclerView.setAdapter(adapter);
    }

    private void getJsonSeries(Response<ResponseBody> response)
    {
        try {

            String js = response.body().string();
            System.out.println("sdfdddddddd  "   + js + "\n categoryID: " + categoryId);

            JSONArray jsonArray = new JSONArray(js);

            System.out.println("SIZE OF JSONARRAY " + jsonArray.length());
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject j = jsonArray.getJSONObject(i);
                SeriesModel tv = new SeriesModel();

                tv.setName(j.getString("name"));
                tv.setCover(j.getString("cover"));
                tv.setNum(j.getInt("num"));
                tv.setSeriesId(j.getInt("series_id"));



                seriesModels.add(tv);




            }



        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = findViewById(R.id.RecyclerViewListChannels);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        SeriesAdapter adapter = new SeriesAdapter(ChannelActivity.this , seriesModels, username, password  );
        recyclerView.setAdapter(adapter);
    }


    private void getChannels(){

        Bundle bundle = getIntent().getExtras();

        if(bundle != null)
        {
            username = bundle.getString("username");
            password = bundle.getString("password");
            categoryId = bundle.getString("categoryId");
            categoryOfStreamId = bundle.getString("streamId");
        }
        else {
            System.out.println("password error");
            username = "";
            password = "";
        }



        String action;

        if (categoryOfStreamId.equals("1"))

            action = "get_live_streams";

        else if (categoryOfStreamId.equals("2"))
            action = "get_vod_streams";
        else action = "get_series";

        Call<ResponseBody> call = api.getChanels(username, password, action,categoryId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(!response.isSuccessful())
                {
                    ErrorAlert alert = new ErrorAlert(ChannelActivity.this);
                    alert.Alert("error", "error" + response.code());
                    return;
                }

                if (categoryOfStreamId.equals("3"))
                    getJsonSeries(response);
                else getJsonTv(response);













            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ErrorAlert alert = new ErrorAlert(ChannelActivity.this);
                alert.Alert("Failure", "Failure on connection  \n Channel activity");

            }
        });

    }
}
