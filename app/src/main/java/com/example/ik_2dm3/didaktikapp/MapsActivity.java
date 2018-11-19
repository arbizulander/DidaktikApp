package com.example.ik_2dm3.didaktikapp;

import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineListener;
import com.mapbox.android.core.location.LocationEnginePriority;
import com.mapbox.android.core.location.LocationEngineProvider;

import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;

import com.mapbox.api.directions.v5.DirectionsCriteria;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.PolygonOptions;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;


import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import com.mapbox.mapboxsdk.plugins.locationlayer.LocationLayerPlugin;
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.CameraMode;
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.RenderMode;

import java.util.ArrayList;
import java.util.List;

//Clases para calcular rutas


import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute;
import com.mapbox.services.android.navigation.v5.navigation.MapboxNavigation;
import com.mapbox.services.android.navigation.v5.navigation.MapboxNavigationOptions;
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,LocationEngineListener,
        PermissionsListener {

    // variables para calcular la ruta
    private Point posicionOrigen;
    private Point posicionDestino;
    private DirectionsRoute rutaActual;
    private static final String TAG = "DirectionsActivity";
    private NavigationMapRoute navigationMapRoute;
    private MapboxNavigationOptions navigationOptions;

    private MapView mapView;
    private MapboxMap mapboxMap;
    private PermissionsManager permissionsManager;
    private LocationEngine locationEngine;
    private LocationLayerPlugin locationLayerPlugin;
    private Location originLocation;


    private Button butsig;
    private Button butprev;
    private Button butcent;

    //BD
        private MyOpenHelper db;

    //Aqui guardamos los datos de la BD
        private ArrayList<Paradas> lista_paradas;

    private int cont = 0;

    //VALORES DEL SQL
        private String[] titulo;

    // JSON encoding/decoding
    //public static final String JSON_CHARSET = "UTF-8";
    //public static final String JSON_FIELD_REGION_NAME = "Getxo";

   /* private static final LatLngBounds GETXO_BOUNDS = new LatLngBounds.Builder()
            .include(new LatLng(43.334930444724705, -3.010872036887065)) // Northeast
            .include(new LatLng(43.3228968359835, -3.01600064681665)) // Southwest
            .build();*/

   //prueba mapa en bilbao
    private static final LatLngBounds GETXO_BOUNDS = new LatLngBounds.Builder()
            .include(new LatLng(43.43586136841853, -3.11061477919111)) // Northeast
            .include(new LatLng(43.14809108135796, -2.701889190424396)) // Southwest
            .build();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.access_token));
        setContentView(R.layout.activity_maps);

        mapView = (MapView) findViewById(R.id.mapView);
        butsig = (Button) findViewById(R.id.butsig);
        butprev = (Button) findViewById(R.id.butprev);
        butcent = (Button) findViewById(R.id.butCent);

        butsig.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                subirCont();
            }});
        butprev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bajarCont();
            }});
        butcent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Centrar();
            }});

        //Cogemos todos los nombres de las paradas desde el SQL
        db=new MyOpenHelper(this);
        lista_paradas = db.getDatos_Paradas();
        db.close();

        //Los pasamos a un array
        titulo = new String [lista_paradas.size()];

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

        //GENERACION DE MARCADORES
        for (int i = 0; i<titulo.length;i++){
            mapboxMap.addMarker(new MarkerOptions()
            .position(new LatLng(lista_paradas.get(i).getLatitud(),lista_paradas.get(i).getLongitud()))
            .title(lista_paradas.get(i).getNombre()));
        }

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
            Log.d("mytag","posicion cambiada");
            //Funcion para sacar la ruta
            posicionDestino = Point.fromLngLat(lista_paradas.get(cont).getLongitud(),lista_paradas.get(cont).getLatitud());
            posicionOrigen = Point.fromLngLat(originLocation.getLongitude(), originLocation.getLatitude());
            getRoute(posicionOrigen, posicionDestino);

            getDistancia();
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

    private void getRoute(Point origin, Point destination) {

        NavigationRoute.builder(this)
                .accessToken(Mapbox.getAccessToken())
                .origin(origin)
                .destination(destination)
                .profile(DirectionsCriteria.PROFILE_WALKING)
                .build()
                .getRoute(new Callback<DirectionsResponse>() {
                    @Override
                    public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
                        // You can get the generic HTTP info about the response
                        Log.d(TAG, "Response code: " + response.code());
                        if (response.body() == null) {
                            Log.e(TAG, "No routes found, make sure you set the right user and access token.");
                            return;
                        } else if (response.body().routes().size() < 1) {
                            Log.e(TAG, "No routes found");
                            return;
                        }

                       DirectionsRoute rutaActual = response.body().routes().get(0);
                        /*double distancia = rutaActual.distance();
                        Log.d("mytag","" + distancia);*/


                        // Draw the route on the map
                        if (navigationMapRoute != null) {
                            navigationMapRoute.removeRoute();
                        } else {
                            navigationMapRoute = new NavigationMapRoute(null, mapView, mapboxMap, R.style.NavigationMapRoute);
                        }
                        navigationMapRoute.addRoute(rutaActual);
                    }

                    @Override
                    public void onFailure(Call<DirectionsResponse> call, Throwable throwable) {
                        Log.e(TAG, "Error: " + throwable.getMessage());
                    }
                });
    }

    private void subirCont(){
        if(cont >= 0 && cont < (lista_paradas.size()-1) ){
            cont++;
            posicionDestino = Point.fromLngLat(lista_paradas.get(cont).getLongitud(),lista_paradas.get(cont).getLatitud());
            posicionOrigen = Point.fromLngLat(originLocation.getLongitude(), originLocation.getLatitude());
            getRoute(posicionOrigen, posicionDestino);
            Log.d("mytag", "objetivo cambiado, ahora es " + cont);

            getDistancia();

        }else{}
    }

    private void bajarCont(){
        if(cont != 0 && cont <= (lista_paradas.size()-1)){
            cont--;
            posicionDestino = Point.fromLngLat(lista_paradas.get(cont).getLongitud(),lista_paradas.get(cont).getLatitud());
            posicionOrigen = Point.fromLngLat(originLocation.getLongitude(), originLocation.getLatitude());
            getRoute(posicionOrigen, posicionDestino);
            Log.d("mytag", "objetivo cambiado, ahora es " + cont);

            getDistancia();
        }else{}
    }

    private void Centrar(){
        if (originLocation != null) {
            setCameraPosition(originLocation);
        }else{
            Toast toast = Toast.makeText(this,"No se ha encontrado señal GPS, compruebe que esta buscando.",Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private void getDistancia(){

        Location destino = new Location(LocationManager.GPS_PROVIDER);
        destino.setLatitude(lista_paradas.get(cont).getLatitud());
        destino.setLongitude(lista_paradas.get(cont).getLongitud());
        float distancia = originLocation.distanceTo(destino);
        Log.d("mytag","La distancia a " + lista_paradas.get(cont).getNombre()+ " es de " + distancia +" metros.");

        if(distancia <= 15){
            Log.d("mytag","Estas al lado de " + lista_paradas.get(cont).getNombre() + "!!!");
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