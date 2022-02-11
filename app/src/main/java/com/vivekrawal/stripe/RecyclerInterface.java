package com.vivekrawal.stripe;
import retrofit2.Call;
import retrofit2.http.GET;
public interface RecyclerInterface {
    String JSONURL = "https://api.themoviedb.org/3/movie/568124/";

    @GET("similar?api_key=ca85b8143600be0bfefbbdd406fb9385&language=en-US&page=1")
    Call<String> getString();


}
