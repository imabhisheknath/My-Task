package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.adapter.UserListAdapter;
import com.example.myapplication.dialog.ProgressDialog;


import com.example.myapplication.response.Datum;
import com.example.myapplication.response.UserListR;
import com.example.myapplication.retro.ApiClient;
import com.example.myapplication.retro.ApiInterface;

import java.util.List;


public class UserListActivity extends AppCompatActivity {
    RecyclerView rUserList;
    int initial_page=1;
    ProgressDialog progressDialog;
    Context mContext;
    boolean isUserScrolling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=UserListActivity.this;
        progressDialog=new ProgressDialog(mContext);
        rUserList = (RecyclerView) findViewById(R.id.rUserList);

        //load Data
        loadUserList(initial_page+"");




    }

    private void loadUserList(String page) {
        progressDialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserListR> call = apiService.getUserList(page);
        call.enqueue(new Callback<UserListR>() {
            @Override
            public void onResponse(Call<UserListR> call, Response<UserListR> response) {

                Log.e("rmytag", response.body()+"");
                try {
                    if (response.code() ==200) {
                        try {
                            progressDialog.dissmiss();
                            UserListR userListR=response.body();
                            List<Datum> list=userListR.getData();
                            //set adapter here
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                            rUserList.setLayoutManager(linearLayoutManager);
                            rUserList.setAdapter(new UserListAdapter(list,mContext));
                            //last item detect recyclerview
                            loadNextPages();

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
            public void onFailure(Call<UserListR> call, Throwable t) {
                progressDialog.dissmiss();
                Log.e("rmytag", t.getMessage() + "");
            }
        });

    }

    private void loadNextPages() {
        rUserList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {

                    try {
                        initial_page++;
                        loadUserList(initial_page+"");
                    }catch (Exception e){

                    }

                }
            }
        });
    }
}
