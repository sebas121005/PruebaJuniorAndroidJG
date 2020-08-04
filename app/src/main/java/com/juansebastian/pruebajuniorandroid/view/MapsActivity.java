package com.juansebastian.pruebajuniorandroid.view;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DragAndDropPermissions;
import android.view.DragEvent;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.juansebastian.pruebajuniorandroid.MainActivity;
import com.juansebastian.pruebajuniorandroid.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.prefs.Preferences;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener {

    private GoogleMap mMap;
    private FloatingActionButton guardaUbicacion;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        guardaUbicacion = findViewById(R.id.guarda_ubicacion);
        preferences = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        final LatLng posicion = new LatLng(4.810505, -75.7112924);
        final MarkerOptions markerOptions = new MarkerOptions().position(posicion).draggable(true);
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(markerOptions.getPosition()));
        mMap.setOnMarkerDragListener(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] { Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        } else {
            mMap.setMyLocationEnabled(true);
        }

        final String ubicacion = markerOptions.getPosition().latitude + "," + markerOptions.getPosition().longitude;
        
        guardaUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = preferences.edit();

                if (preferences.getString("determina_ubicacion", "").equals("1")) {
                    JSONObject ubicacionJson = new JSONObject();
                    try {
                        ubicacionJson.put("usuario", preferences.getString("usuario", ""));
                        ubicacionJson.put("contrasena", preferences.getString("contrasena", ""));
                        ubicacionJson.put("ubicacion", ubicacion);
                        Toast.makeText(getApplicationContext(), "Ubicación almacenada correctamente", Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {

                    }
                    editor.putString("ubicacion_perfil", ubicacionJson.toString());

                } else {
                    editor.putString("ubicacion", ubicacion);

                }

                editor.commit();
                onBackPressed();

            }
        });


    }


    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        final String posicion = marker.getPosition().latitude + "," + marker.getPosition().longitude;

        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));

        guardaUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = preferences.edit();

                if (preferences.getString("determina_ubicacion", "").equals("1")) {
                    JSONObject ubicacionJson = new JSONObject();
                    try {
                        ubicacionJson.put("usuario", preferences.getString("usuario", ""));
                        ubicacionJson.put("contrasena", preferences.getString("contrasena", ""));
                        ubicacionJson.put("ubicacion", posicion);
                        Toast.makeText(getApplicationContext(), "Ubicación almacenada correctamente", Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {

                    }
                    editor.putString("ubicacion_perfil", ubicacionJson.toString());

                } else {
                    editor.putString("ubicacion", posicion);

                }


                editor.commit();
                onBackPressed();
            }
        });
    }
}