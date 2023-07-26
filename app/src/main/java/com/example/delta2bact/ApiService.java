package com.example.delta2bact;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("users")
    Call<List<User>> getUsers();

    @POST("user")
    Call<ResponseBody> createUser(@Body User user);
}
