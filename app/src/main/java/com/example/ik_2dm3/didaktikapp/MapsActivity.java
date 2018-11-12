package com.example.ik_2dm3.didaktikapp;

import android.location.Location;
import android.support.annotation.NonNull;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineListener;
import com.mapbox.android.core.location.LocationEnginePriority;

import com.mapbox.android.core.location.LocationEngineProvider;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;


import com.mapbox.mapboxsdk.annotations.PolygonOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;


import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import com.mapbox.mapboxsdk.plugins.locationlayer.LocationLayerPlugin;
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.CameraMode;
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.RenderMode;

import java.util.List;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,LocationEngineListener,
        PermissionsListener {

    private MapView mapView;
    private MapboxMap mapboxMap;
    private PermissionsManager permissionsManager;
    private LocationEngine locationEngine;
    private LocationLayerPlugin locationLayerPlugin;
    private Location originLocation;

    // JSON encoding/decoding
    //public static final String JSON_CHARSET = "UTF-8";
    //public static final String JSON_FIELD_REGION_NAME = "Getxo";

    private static final LatLngBounds GETXO_BOUNDS = new LatLngBounds.Builder()
            .include(new LatLng(43.334930444724705, -3.010872036887065)) // Northeast
            .include(new LatLng(43.3228968359835, -3.01600064681665)) // Southwest
            .build();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.access_token));
        setContentView(R.layout.activity_maps);

        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        try{
            Log.d("mytag", "cargando mapa offline");

            // Limites de Getxo
            mapboxMap.setLatLngBoundsForCameraTarget(GETXO_BOUNDS);
            mapboxMap.setMinZoomPreference(15);
            mapboxMap.setMaxZoomPreference(18);
            // Visualise bounds area
            showBoundsArea(mapboxMap);
        }catch(Exception e){
            Log.d("mytag", "ERROR CARGAR MAPA");
        }


    }

    private void showBoundsArea(MapboxMap mapboxMap) {

        PolygonOptions boundsArea = new PolygonOptions()
                .add(GETXO_BOUNDS.getNorthWest())
                .add(GETXO_BOUNDS.getNorthEast())
                .add(GETXO_BOUNDS.getSouthEast())
                .add(GETXO_BOUNDS.getSouthWest());
        // Ajusta la transparencia del area seleccionada | 0 = transparente y 1 = opaco
        boundsArea.alpha(0f);
        mapboxMap.addPolygon(boundsArea);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);


    }

    @Override
    public void onMapReady(MapboxMap mapboxMap) {

        this.mapboxMap = mapboxMap;

        enableLocation();

    }

    private void enableLocation(){

        if(PermissionsManager.areLocationPermissionsGranted(this)){
            initializeLocationEngine();
            initializeLocationLayer();


        }else{
            permissionsManager = new PermissionsManager((this));
            permissionsManager.requestLocationPermissions(this);
        }

    }
    @SuppressWarnings("MissingPermission")
    private void initializeLocationEngine(){

        locationEngine = new LocationEngineProvider(this).obtainBestLocationEngineAvailable();
        locationEngine.setPriority(LocationEnginePriority.HIGH_ACCURACY);
        locationEngine.activate();

        Location lastLocation = locationEngine.getLastLocation();
        if(lastLocation != null){

            originLocation = lastLocation;
            setCameraPosition(lastLocation);


        }else{
            locationEngine.addLocationEngineListener(this);

        }

    }


    @SuppressWarnings("MissingPermission")
    private void initializeLocationLayer(){


        locationLayerPlugin = new LocationLayerPlugin(mapView, mapboxMap, locationEngine);
        locationLayerPlugin.setLocationLayerEnabled(true);
        locationLayerPlugin.setCameraMode(CameraMode.TRACKING_COMPASS);
        locationLayerPlugin.setRenderMode(RenderMode.COMPASS);



    }

    private void setCameraPosition(Location location){
        mapboxMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(),
                location.getLongitude()), 13.0));

    }


    @Override
    @SuppressWarnings("MissingPermission")
    public void onConnected() {
        locationEngine.requestLocationUpdates();

    }

    @Override
    public void onLocationChanged(Location location) {

        if(location != null){
            originLocation = location;
            setCameraPosition(location);
        }
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {

    }

    @Override
    public void onPermissionResult(boolean granted) {
        if(granted){
            enableLocation();
        }

    }



    /**********************************************************/

    @SuppressWarnings("MissingPermission")
    @Override
    public void onStart() {
        super.onStart();
        if(locationEngine != null){
            locationEngine.requestLocationUpdates();
        }
        if(locationLayerPlugin != null){
            locationLayerPlugin.onStart();
        }
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(locationEngine != null){
            locationEngine.removeLocationUpdates();
        }
        if(locationLayerPlugin != null){
            locationLayerPlugin.onStop();
        }
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(locationEngine != null){
            locationEngine.deactivate();
        }
        mapView.onDestroy();
    }




}