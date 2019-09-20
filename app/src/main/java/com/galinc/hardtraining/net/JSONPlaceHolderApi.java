package com.galinc.hardtraining.net;

import com.galinc.hardtraining.itility.Document;
import com.galinc.hardtraining.itility.Exercise;
import com.galinc.hardtraining.itility.Post;
import com.galinc.hardtraining.itility.TemplateTraining;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {
    @GET("/posts/{id}")
    public Call<Exercise> getPostWithID(@Path("id") int id);

    @GET("/posts")
    public Call<List<Exercise>> getAllPosts();

    @GET("/posts")
    public Call<List<Exercise>> getPostOfUser(@Query("userId") int id);

    @POST("/hardtraining/hs/ht/main/call/")
    public Call<List<Exercise>> postData(@Body Post data);

    @POST("/hardtraining/hs/ht/main/call/")
    public Call<List<Exercise>> postData(@Body String data);

    @POST("/hardtraining/hs/ht/main/call/")
    public Call<List<Document>> postDocuments(@Body String data);

    @POST("/hardtraining/hs/ht/main/call/")
    public Call<List<TemplateTraining>> postTemplate(@Body String data);
}
