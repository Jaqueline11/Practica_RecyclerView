package com.example.appthqueke;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.appthqueke.databinding.ActivityDetalleEarthquakeBinding;

public class detalle_earthquake extends AppCompatActivity {
    public static final String LOGIN_KEY= "Earthquake";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetalleEarthquakeBinding binding= ActivityDetalleEarthquakeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("    Earthquake Monitor");

        Bundle extras = getIntent().getExtras();


        String id= extras.getString("id");

        binding.txtId.setText(extras.getDouble("magnitud")+" °N");
        binding.txtLatitud.setText(extras.getDouble("latitud")+"");
        binding.txtLongitud.setText(extras.getDouble("longitud")+" °W");
        binding.txtKm.setText(extras.getString("place")+"");
        binding.txtFecha.setText(extras.getLong("time")+"");

    }
}