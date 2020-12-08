package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myapplication.dialog.ProgressDialog;
import com.example.myapplication.response.userpost.Datum;
import com.example.myapplication.response.userpost.UserPostResponse;
import com.example.myapplication.retro.ApiClient;
import com.example.myapplication.retro.ApiInterface;
import com.google.gson.Gson;


import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserPostActivity extends AppCompatActivity {
    RecyclerView rUserList;
    String id="";
    ProgressDialog progressDialog;
    Context mContext;
    TextView tvUserID,tvTitle,tvBody,tvCreateDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        mContext= UserPostActivity.this;
        progressDialog=new ProgressDialog(mContext);
        rUserList = (RecyclerView) findViewById(R.id.rUserList);
        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        tvTitle=(TextView) findViewById(R.id.tvTitle);
        tvCreateDate=(TextView) findViewById(R.id.tvCreateDate);
        tvBody=(TextView) findViewById(R.id.tvBody);
        //load Data
        loadUserPost(id);

    }

    private void loadUserPost(String page) {
        progressDialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserPostResponse> call = apiService.getUserPost(page);
        call.enqueue(new Callback<UserPostResponse>() {
            @Override
            public void onResponse(Call<UserPostResponse> call, Response<UserPostResponse> response) {
                Gson gson=new Gson();
                try {
                    if (response.code() ==200) {
                        try {
                            progressDialog.dissmiss();
                            UserPostResponse UserPostResponse=response.body();
                            List<Datum> list=UserPostResponse.getData();
                            tvTitle.setText(list.get(0).getTitle());
                            tvBody.setText(list.get(0).getBody());
                            tvCreateDate.setText("Created At : "+list.get(0).getCreatedAt());
                        } catch (Exception e) {
                            progressDialog.dissmiss();
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    progressDialog.dissmiss();
                }

            }

            @Override
            public void onFailure(Call<UserPostResponse> call, Throwable t) {
                progressDialog.dissmiss();
                Log.e("rmytag", t.getMessage() + "");
            }
        });

    }


}
