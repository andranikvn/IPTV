package com.example.project;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("player_api.php?")
    Call<ResponseBody> getPosts(
            @Query("username") String username,
            @Query("password") String password

    );

    @GET("player_api.php?")
    Call<ResponseBody> getCategories(
            @Query("username") String username,
            @Query("password") String password,
            @Query("action") String action
    );

    @GET("player_api.php?")
    Call<ResponseBody> getChanels(
            @Query("username") String username,
            @Query("password") String password,
            @Query("action") String action,
            @Query("category_id") String categoryId
    );

    @GET("player_api.php?")
    Call<ResponseBody> getVods(
            @Query("username") String username,
            @Query("password") String password,
            @Query("action") String action,
            @Query("category_id") String categoryId
    );

    @GET("player_api.php?")
    Call<ResponseBody> getSeriesInfo(
            @Query("username") String username,
            @Query("password") String password,
            @Query("action") String action,
            @Query("series_id") String seriesId
    );
}
