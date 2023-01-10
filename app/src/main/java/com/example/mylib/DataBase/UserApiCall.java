package com.example.mylib.DataBase;


import com.example.mylib.Objects.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserApiCall {
    @GET("api/users/{parameter}")
    Call<User> getUser(@Path("parameter") String username);
}
