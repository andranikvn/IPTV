package com.example.project.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.project.Api;
import com.example.project.CategoriesAdapter;
import com.example.project.ErrorAlert;
import com.example.project.Models.Items;
import com.example.project.R;
import com.example.project.Models.TvCategories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryActivity extends AppCompatActivity {

    List<TvCategories> categories = new ArrayList<>();

    Api api;
    String username;
    String password;
    String categoryId;
    Context context = CategoryActivity.this;




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);







        Retrofit retrofit;




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
            categoryId = bundle.getString("id");

            System.out.println(username);
            System.out.println(password);
        }
        getCategories();





//
//        RecyclerView recyclerView = findViewById(R.id.RecyclerViewList);
//        CategoriesAdapter adapter = new CategoriesAdapter(CategoryActivity.this, categories );
//        recyclerView.setAdapter(adapter);
    }

    private void getCategories()
    {
        String action;
        if(categoryId.equals("1"))
        {
            action = "get_live_categories";
        }
        else if(categoryId.equals("2"))
            action = "get_vod_categories";
        else action = "get_series_categories";

        Call<ResponseBody> call = api.getCategories(username, password, action );


        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(!response.isSuccessful())
                {
                    ErrorAlert alert = new ErrorAlert(CategoryActivity.this);
                    alert.Alert("error", "error");
                }


                try {

                    String js = response.body().string();
                    JSONArray jsonArray = new JSONArray(js);
                    System.out.println("XXXXXXXXXXXXXX "+ js);

                    for(int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject j = jsonArray.getJSONObject(i);
                        TvCategories tv = new TvCategories();

                        tv.setCategoryId(j.getString("category_id"));
                        tv.setCategoryName(j.getString("category_name"));
                        tv.setParent_id(j.getInt("parent_id"));



                        categories.add(tv);



                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                RecyclerView recyclerView = findViewById(R.id.RecyclerViewList);
                CategoriesAdapter adapter = new CategoriesAdapter(context , categories, username, password, categoryId  );
                recyclerView.setAdapter(adapter);



            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ErrorAlert alert = new ErrorAlert(CategoryActivity.this);
                alert.Alert("Failure", "Failure on connection");

            }
        });



    }


}


