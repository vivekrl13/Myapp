package com.vivekrawal.stripe;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.vivekrawal.stripe.model.ModelRecycler;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import com.vivekrawal.stripe.model.ModelListView;
import com.vivekrawal.stripe.model.RoundedTransformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import com.vivekrawal.stripe.adapter.RetrofitAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MovieDetails extends Activity {

    ImageView imgv;
    private RetrofitAdapter retrofitAdapter;
    TextView name, lang, release, detailsm;
    RatingBar rt;
    RecyclerView rcl;
    JSONObject obj;
    JSONArray dataArray;

    JSONObject dataobj, dataobjs;
    ModelListView modelListView;
    Bundle bundle;
    String Baseimage="https://image.tmdb.org/t/p/w500";
    String url="https://api.themoviedb.org/3/movie/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moviedetails);
       bundle = getIntent().getExtras();

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(MovieDetails.this, LinearLayoutManager.HORIZONTAL, false);

        rcl=(RecyclerView) findViewById(R.id.recycl);
        rcl.setLayoutManager(layoutManager);
    imgv=(ImageView) findViewById(R.id.myim);
    name=(TextView) findViewById(R.id.movname);
    lang=(TextView) findViewById(R.id.lng);

        release=(TextView) findViewById(R.id.reldate);
        detailsm=(TextView) findViewById(R.id.modetails);
        rt=(RatingBar) findViewById(R.id.Ratingbar);
        details();

fetchJSON();


    }

    public void details()
    {
        if (bundle!=null) {
            String imagepath = bundle.getString("imageurl");
            String movname = bundle.getString("namemovie");
            String mdate = bundle.getString("datemov");
            String mrating = bundle.getString("ratingm");
            String mlang = bundle.getString("langm");
            String details = bundle.getString("mdetails");
            String ids = bundle.getString("id");
           url=(url+ids+"/");

            Picasso.get().load(imagepath).transform(new RoundedTransformation(30, 0)).into(imgv);

            name.setText(movname);
            lang.setText(mlang);
            release.setText(mdate);
            detailsm.setText(details);

            rt.setRating(Float.parseFloat(mrating));


        }
    }

    private void fetchJSON(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        RecyclerInterface api = retrofit.create(RecyclerInterface.class);

        Call<String> call = api.getString();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        writeRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void writeRecycler(String response){

        try {
            //getting the whole json object from the response
            JSONObject obj = new JSONObject(response);


            ArrayList<ModelRecycler> modelRecyclerArrayList = new ArrayList<>();
            JSONArray dataArray  = obj.getJSONArray("results");

            for (int i = 0; i < dataArray.length(); i++) {

                ModelRecycler modelRecycler = new ModelRecycler();
                JSONObject dataobj = dataArray.getJSONObject(i);

                modelRecycler.setImgURL(Baseimage+(dataobj.getString("poster_path")));
                modelRecycler.setName(dataobj.getString("original_title"));


                modelRecyclerArrayList.add(modelRecycler);

            }

            retrofitAdapter = new RetrofitAdapter(this,modelRecyclerArrayList);
            rcl.setAdapter(retrofitAdapter);
            rcl.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
