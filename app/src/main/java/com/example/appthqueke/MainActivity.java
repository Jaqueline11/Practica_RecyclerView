package com.example.appthqueke;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appthqueke.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.eqRecycler.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Earthquake> listaeq=new ArrayList<>();
        listaeq.add(new Earthquake("001","Carchi-Tulcan",5,5082022,1005,154.8));
        listaeq.add(new Earthquake("002","Guayas-Guayaquil",6,5082022,1005,154.8));
        listaeq.add(new Earthquake("003","Azuay-Cuenca",7,5082022,1005,154.8));
        listaeq.add(new Earthquake("004","Azogues-biblian",8,5082022,1005,154.8));
        listaeq.add(new Earthquake("005","Chimborazo-Riobamba",9,5082022,1005,154.8));

        EqAdapter adapter= new EqAdapter();
        adapter.setOnItemClickListener(earthquake ->
               abrir_detalle(earthquake.getId(),earthquake.getPlace(),earthquake.getMagnitud(),
                       earthquake.getTime(),earthquake.getLatitud(),earthquake.getLongitud()));

        binding.eqRecycler.setAdapter(adapter);
        adapter.submitList(listaeq);


        if (listaeq.isEmpty()){
            binding.empty.setVisibility(View.VISIBLE);
        }else{
            binding.empty.setVisibility(View.GONE);
        }
    }

    public  void abrir_detalle(String id,String place,double magnitud, long time, double latitud, double longitud){
        Intent intent= new Intent(this,detalle_earthquake.class);
        intent.putExtra("id",id);
        intent.putExtra("place",place);
        intent.putExtra("magnitud",magnitud);
        intent.putExtra("time",time);
        intent.putExtra("latitud",latitud);
        intent.putExtra("longitud",longitud);
        startActivity(intent);
    }
}