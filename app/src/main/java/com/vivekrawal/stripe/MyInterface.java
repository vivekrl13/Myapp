package com.vivekrawal.stripe;
import retrofit2.Call;
import retrofit2.http.GET;
public interface MyInterface {
    String JSONURL = "http://api.themoviedb.org/3/discover/";

    @GET("movie?sort_by=popularity.desc&api_key=ca85b8143600be0bfefbbdd406fb9385")
    Call<String> getString();

}
