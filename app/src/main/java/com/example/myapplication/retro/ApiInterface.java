package com.example.myapplication.retro;

import com.example.myapplication.response.Datum;
import com.example.myapplication.response.UserListR;
import com.example.myapplication.response.userpost.UserPostResponse;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("public-api/users?")
    Call<UserListR> getUserList(@Query("page") String page);


    @GET("public-api/users/{id}/posts")
    Call<UserPostResponse> getUserPost(@Path("id") String page);

}

