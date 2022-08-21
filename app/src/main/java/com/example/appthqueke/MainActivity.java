package com.example.appthqueke;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appthqueke.databinding.ActivityMainBinding;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Instaciamos la clase
        MainViewModel viewModel= new ViewModelProvider(this).get(MainViewModel.class);

        binding.eqRecycler.setLayoutManager(new LinearLayoutManager(this));


        EqAdapter adapter= new EqAdapter();
        adapter.setOnItemClickListener(earthquake ->

               abrir_detalle(earthquake.getId(),earthquake.getPlace(),earthquake.getMagnitud(),
                       earthquake.getTime(),earthquake.getLatitud(),earthquake.getLongitud()));

        binding.eqRecycler.setAdapter(adapter);


        viewModel.getEqList().observe(this,listeq->{
            adapter.submitList(listeq);

            if (listeq.isEmpty()){
                binding.empty.setVisibility(View.VISIBLE);
            }else{
                binding.empty.setVisibility(View.GONE);
            }
        });
        viewModel.getEarthquakes();



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