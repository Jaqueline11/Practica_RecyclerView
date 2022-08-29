package com.example.appthqueke.Api;

import com.google.firebase.database.core.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

//Singleton - Se mantenga activa una unica instance

public class EqApiClient {

    private static final EqApiClient ourInstance= new EqApiClient();
    private EqService service;
    public static EqApiClient getInstance(){
        return ourInstance;
    }

    private EqApiClient(){

    }
    //interfaz para generar el servicio
    public interface EqService {
        @GET("all_hour.geojson")
        Call<String> getEarthquakes();
    }

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/")
            .addConverterFactory(ScalarsConverterFactory.create()) //Conversion Scalars
            //.addConverterFactory(MoshiConverterFactory.create()) //Conversion Moshi
            .build();


    //Consumir datos -Api
    public EqService getService(){
        if (service==null){
            service = retrofit.create(EqService.class);
        }
        return service;
    }
}
