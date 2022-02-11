package com.vivekrawal.stripe;

import com.vivekrawal.stripe.model.ModelListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Interfacesimilar {


    String JSONURL = "https://api.themoviedb.org/3/movie/";

    @GET("similar?api_key=ca85b8143600be0bfefbbdd406fb9385&language=en-US&page=1")
    Call<List<ModelListView>> geImgData();
}
