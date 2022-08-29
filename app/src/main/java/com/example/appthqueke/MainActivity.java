package com.example.appthqueke;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.widget.Toast;

import com.example.appthqueke.databinding.ActivityMainBinding;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Instaciamos la clase
        MainViewModel viewModel= new ViewModelProvider(this).get(MainViewModel.class);
        binding.eqRecycler.setLayoutManager(new LinearLayoutManager(this));

        //Carga de datos
        EqAdapter adapter= new EqAdapter();
        adapter.setOnItemClickListener(earthquake ->

               abrir_detalle(earthquake.getId(),earthquake.getPlace(),earthquake.getMagnitud(),
                       earthquake.getTime(),earthquake.getLatitud(),earthquake.getLongitud()));

        binding.eqRecycler.setAdapter(adapter);


        viewModel.getEqList().observe(this,listeq->{
            adapter.submitList(listeq);

            //validar magnitud
            Magnitud_mayor(listeq);

            for (int i=0; i<listeq.size(); i++){
                if(listeq.get(i).getMagnitud()== Magnitud_mayor(listeq)){
                    int rotacion = getWindowManager().getDefaultDisplay().getRotation();
                    if (rotacion == Surface.ROTATION_0 ) {

                    }else{
                        binding.txtFecha.setText(listeq.get(i).getTime()+"");
                        binding.txtLatitud.setText(listeq.get(i).getLatitud()+"");
                        binding.txtLongitud.setText(listeq.get(i).getLongitud()+"");
                        binding.txtId.setText(listeq.get(i).getMagnitud()+"");
                        binding.txtKm.setText(listeq.get(i).getPlace());
                    }


                }
            }

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

    public Double Magnitud_mayor(List<Earthquake> list){
        double mag;
        mag=list.get(0).getMagnitud();
        for (int i=0; i<list.size();i++){
            if (list.get(i).getMagnitud()>mag){
                mag=list.get(i).getMagnitud();
            }
        }
        return mag;
    }
}