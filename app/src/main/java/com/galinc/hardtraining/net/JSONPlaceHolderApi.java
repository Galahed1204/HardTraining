package com.galinc.hardtraining.net;

import com.galinc.hardtraining.itility.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {
    @GET("/posts/{id}")
    public Call<Post> getPostWithID(@Path("id") int id);

    @GET("/posts")
    public Call<List<Post>> getAllPosts();

    @GET("/posts")
    public Call<List<Post>> getPostOfUser(@Query("userId") int id);

    @POST("/hardtraining/hs/ht/main/call/")
    public Call<List<Post>> postData(@Body Post data);
}
