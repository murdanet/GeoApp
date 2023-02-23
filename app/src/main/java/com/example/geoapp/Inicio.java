package com.example.geoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class Inicio extends Activity {
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    Button boton;
    Button botoncarro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        boton = findViewById(R.id.botonubi);
        botoncarro=findViewById(R.id.botoncarro);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(Inicio.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Inicio.this, new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION
                    }, REQUEST_LOCATION_PERMISSION);
                } else {
                    // El permiso de ubicación ya fue concedido, llama a la función para obtener las coordenadas
                    obtenerUbi();
                }
            }
        });


        botoncarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Inicio.this, MapsActivity.class);
                startActivity(i);
            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso de ubicación concedido, llama a la función para obtener las coordenadas
                obtenerUbi();
            } else {
                // Permiso de ubicación denegado, muestra un mensaje al usuario
                Toast.makeText(this, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /* Función para obtener las coordenadas del usuario y mostrarlas en un Toast.
    Probablemente no la muestre al abrir la aplicación a la primera vez, por lo que habría que
    abrir el google maps y presionar la ubicación, y volver a la app y esta vez mostrará la latitud y longitud
    en un Toast.
     */

    private void obtenerUbi() {

        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Obtiene la última ubicación conocida del usuario (En este caso la predeterminada que pone Android Studio)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    // Muestra de las coordenadas de mi ultima ubicación en un Toast
                    String coordinates = "Latitud: " + location.getLatitude() + ", Longitud: " + location.getLongitude();
                    Toast.makeText(Inicio.this, coordinates, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Inicio.this, "No se pudo obtener la ubicación del usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

