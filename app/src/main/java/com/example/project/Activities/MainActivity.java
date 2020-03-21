package com.example.project.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.Api;
import com.example.project.ErrorAlert;
import com.example.project.R;
import com.example.project.Models.TvCategories;
import com.example.project.Models.UserInfo;

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

public class MainActivity extends AppCompatActivity {
    private Api api;
    private EditText loginText;
    private EditText passwordText;
    private Button loginBtn;
    private TextView text;

    private String login = "";
    private  String password = "";

    JSONObject jsonObject;

    Retrofit retrofit;
    List<TvCategories> categories;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.gradient_end_color));

        ImageView icon;
        icon = findViewById(R.id.SignInIcon);
        icon.setImageResource(R.drawable.signinicon);

        System.out.println(getResources().getString(R.string.baseUrl));

        retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);

        loginBtn = findViewById(R.id.login_button);
        loginText = findViewById(R.id.login_id);
        passwordText = findViewById(R.id.password_id);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login = loginText.getText().toString();
                password = passwordText.getText().toString();

                login = "bravo";
                password = "bravo";
                getPosts();



            }
        });

    }
    public void getPosts(){

        categories = new ArrayList<>();


        text = findViewById(R.id.textView);
//        UserInfo userInfo = new UserInfo();
//        if(login.equals("") || password.equals(""))
//        {
//            ErrorAlert alert = new ErrorAlert(MainActivity.this);
//            alert.Alert("Error", "Enter Login and Password");
//            return;
//        }

        api = retrofit.create(Api.class);
        Call<ResponseBody> call = api.getPosts(login, password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()){

                    ErrorAlert alert = new ErrorAlert(MainActivity.this);

                    if(response.code() == 401)
                        alert.Alert("ERROR", "invalid login or Password");

                    else
                        text.setText("code: " + response.code());


                    return;
                }

                Class c = ChooseActivity.class;





                Intent intent = new Intent(MainActivity.this, c);
                intent.putExtra("username", login);
                intent.putExtra("password", password);
                MainActivity.this.startActivity(intent);



//                try {
//                    JSONObject json = new JSONObject(response.body().string());
//                    JSONObject jsonUser = new JSONObject(json.get("user_info").toString());
//
//                    userInfo.setUsername(jsonUser.get("username").toString());
//                    userInfo.setPassword(jsonUser.get("password").toString());
//                    userInfo.setAuth((int)jsonUser.get("auth"));
//                    userInfo.setActive_cons(jsonUser.get("active_cons").toString());
//                    userInfo.setAllowed_output_formats(json.getJSONArray("allowed_output_formats"));
//                    userInfo.setCreated_at("created_at");
//                    userInfo.setExp_date("exp_date");
//                    userInfo.setIs_trial("is_trial");
//                    userInfo.setMessage("message");
//                    userInfo.setStatus("status");
//                    userInfo.setMax_connections("max_connections");
//
//
//
//
//
////                    JSONObject jsonUser = new JSONObject(json.get("user_info").toString());
////
////                    text.setText(jsonUser.get("username").toString());
//
//
//
//
//                    //text.setText(json.get("user_info").toString());
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                text.setText(response.headers().toString());

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ErrorAlert alert = new ErrorAlert(MainActivity.this);
                alert.Alert("Failure", "Failure on MAINACTIVITY connection");



            }
        });



    }
}
