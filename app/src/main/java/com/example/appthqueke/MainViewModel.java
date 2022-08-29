package com.example.appthqueke;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.appthqueke.Api.EqApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {


   //metodo para recoger datos de Earthquake
    private MutableLiveData<List<Earthquake>> listaeq = new MutableLiveData<>();


    public LiveData<List<Earthquake>> getEqList(){
        return listaeq;
    }

    public void getEarthquakes(){
       /* ArrayList<Earthquake> listaeq=new ArrayList<>();
        listaeq.add(new Earthquake("001","Carchi-Tulcan",5,5082022,1005,154.8));
        listaeq.add(new Earthquake("002","Guayas-Guayaquil",6,5082022,1005,154.8));
        listaeq.add(new Earthquake("003","Azuay-Cuenca",7,5082022,1005,154.8));
        listaeq.add(new Earthquake("004","Azogues-biblian",8,5082022,1005,154.8));
        listaeq.add(new Earthquake("005","Chimborazo-Riobamba",9,5082022,1005,154.8));
        this.listaeq.setValue(listaeq);*/
        EqApiClient.EqService service = EqApiClient.getInstance().getService();
        service.getEarthquakes().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //Log.d("MainViewModel", response.body());
                List<Earthquake> earthquakeslist = parseEarthquake(response.body());
                listaeq.setValue(earthquakeslist);

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("MainViewModel", t.getMessage());
            }
        });
    }

    //
    private List<Earthquake> parseEarthquake(String responseString){
        ArrayList<Earthquake> listaeq=new ArrayList<>();
        try {
            JSONObject  jsonResponse = new JSONObject(responseString);
            JSONArray featuresJSONArray= jsonResponse.getJSONArray("features");

            for (int i=0; i<featuresJSONArray.length();i++){
                JSONObject jsonFeatures = featuresJSONArray.getJSONObject(i);
                String id= jsonFeatures.getString("id");

                JSONObject jsonProperties = jsonFeatures.getJSONObject("properties");
                double magnitude = jsonProperties.getDouble("mag");
                double mag= Double.parseDouble(obtieneDosDecimales(magnitude));
                String place = jsonProperties.getString("place");
                long time = jsonProperties.getLong("time");

                JSONObject jsongeometry= jsonFeatures.getJSONObject("geometry");
                JSONArray coordinateJSONARRAY= jsongeometry.getJSONArray("coordinates");
                double longitude = coordinateJSONARRAY.getDouble(0);
                double latitude = coordinateJSONARRAY.getDouble(1);

                Earthquake earthquake= new Earthquake(id,place,mag,time,latitude,longitude);
                listaeq.add(earthquake);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listaeq;
    }
    private String obtieneDosDecimales(double valor){
        DecimalFormat format = new DecimalFormat();
        format.setMaximumFractionDigits(2); //Define 2 decimales.
        return format.format(valor);
    }


}
