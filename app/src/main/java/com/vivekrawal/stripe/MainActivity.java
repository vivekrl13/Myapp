package com.vivekrawal.stripe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.smarteist.autoimageslider.SliderView;
import com.vivekrawal.stripe.adapter.RetroAdapter;
import com.vivekrawal.stripe.adapter.SliderAdapter;
import com.vivekrawal.stripe.model.ModelListView;
import com.vivekrawal.stripe.model.SliderData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

String Baseimage="https://image.tmdb.org/t/p/w500";
    String url1,d1,n1;
    String url2,d2,n2;
    String url3,d3,n3;
    JSONObject obj;
    JSONArray dataArray;
    JSONObject dataobj, dataobjs;
    ModelListView modelListView;
    String url4,d4,n4;
    private ListView listView;
    private RetroAdapter retroAdapter;
    SliderView sliderView;
    SliderAdapter adapter;
    ArrayList<SliderData> sliderDataArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sliderDataArrayList = new ArrayList<>();
        sliderView = findViewById(R.id.slider);


        listView = findViewById(R.id.lv);

        getJSONResponse();

      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

              try {
                  dataobjs = dataArray.getJSONObject(pos);
                  String imagepath= (Baseimage+(dataobjs.getString("backdrop_path")));
                  String movname= dataobjs.getString("original_title");
                  String mdate= dataobjs.getString("release_date");
                  String mrating= dataobjs.getString("vote_average");
                  String mlang= dataobjs.getString("original_language");
                  String details= dataobjs.getString("overview");
                  String ids= dataobjs.getString("id");

                  Bundle bundle = new Bundle();
                  bundle.putString("imageurl",imagepath);
                  bundle.putString("namemovie",movname);
                  bundle.putString("datemov",mdate);
                  bundle.putString("ratingm",mrating);
                  bundle.putString("langm",mlang);
                  bundle.putString("mdetails",details);
                  bundle.putString("id",ids);
                  Intent intent = new Intent(MainActivity.this, MovieDetails.class);
                  intent.putExtras(bundle);
                  startActivity(intent);



                 // Log.i("onSuccess", s1);
              }
              catch (JSONException e) {
                  e.printStackTrace();
              }




          }
      });

    }

    private void getJSONResponse(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        MyInterface api = retrofit.create(MyInterface.class);

        Call<String> call = api.getString();

      //  Log.i("User Linksssssssssssssssssss", u);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccesssssssss", response.body().toString());

                        String jsonresponse = response.body().toString();


                        writeListView(jsonresponse);

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

    private void writeListView(String response){

        try {
            //getting the whole json object from the response
             obj = new JSONObject(response);

                  Log.d("In my logggggggggggggggggggggggggggg","after object");
                ArrayList<ModelListView> modelListViewArrayList = new ArrayList<>();
               dataArray  = obj.getJSONArray("results");
                int r=dataArray.length();
                Log.d("In my logggggggggggggggggggggggggggg","getting r");



                for (int i = 0; i < dataArray.length(); i++) {

                    modelListView = new ModelListView();
                   dataobj = dataArray.getJSONObject(i);



                    modelListView.setbackdrop_path(Baseimage+(dataobj.getString("backdrop_path")));
                    modelListView.setoriginal_title(dataobj.getString("original_title"));
                    modelListView.setoriginal_language(dataobj.getString("original_language"));
                    modelListView.setrelease_date(dataobj.getString("release_date"));
                    modelListView.setvote_average(dataobj.getString("vote_average"));

                    modelListViewArrayList.add(modelListView);


                }

            //    SliderData smodel=new SliderData();

            JSONObject rrlobj1 = dataArray.getJSONObject(4);
            JSONObject rrlobj2 = dataArray.getJSONObject(5);
            JSONObject rrlobj3 = dataArray.getJSONObject(6);
            JSONObject rrlobj4 = dataArray.getJSONObject(7);



            url1=Baseimage+(rrlobj1.getString("backdrop_path"));
            url2=Baseimage+(rrlobj2.getString("backdrop_path"));
            url3=Baseimage+(rrlobj3.getString("backdrop_path"));
            url4=Baseimage+(rrlobj4.getString("backdrop_path"));

            d1=rrlobj1.getString("release_date");
            d2=rrlobj2.getString("release_date");
            d3=rrlobj3.getString("release_date");
            d4=rrlobj4.getString("release_date");

            n1=rrlobj1.getString("title");
            n2=rrlobj2.getString("title");
            n3=rrlobj3.getString("title");
            n4=rrlobj4.getString("title");



            sliderDataArrayList.add(new SliderData(url1,d1,n1));
            sliderDataArrayList.add(new SliderData(url2,d2,n2));
            sliderDataArrayList.add(new SliderData(url3,d3,n3));
            sliderDataArrayList.add(new SliderData(url4,d4,n4));
            adapter = new SliderAdapter(this, sliderDataArrayList);

            sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
            sliderView.setSliderAdapter(adapter);

            sliderView.setScrollTimeInSec(4);
            sliderView.setAutoCycle(true);
            sliderView.startAutoCycle();

                retroAdapter = new RetroAdapter(this, modelListViewArrayList);
                listView.setAdapter(retroAdapter);



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}