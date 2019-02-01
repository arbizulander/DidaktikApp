package com.example.ik_2dm3.didaktikapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.media.Image;
import android.os.Parcel;
import android.support.annotation.NonNull;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineListener;
import com.mapbox.android.core.location.LocationEnginePriority;
import com.mapbox.android.core.location.LocationEngineProvider;

import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;

import com.mapbox.api.directions.v5.DirectionsCriteria;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.PolygonOptions;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;


import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import com.mapbox.mapboxsdk.plugins.locationlayer.LocationLayerPlugin;
import com.mapbox.mapboxsdk.plugins.locationlayer.LocationLayerOptions;
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.CameraMode;
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.RenderMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

//Clases para calcular rutas


import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapbox.mapboxsdk.style.sources.Source;
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
    private static final int REQ_MAPA = 7;
    private NavigationMapRoute navigationMapRoute;
    private MapboxNavigationOptions navigationOptions;

    private MapView mapView;
    private MapboxMap mapboxMap;
    private PermissionsManager permissionsManager;
    private LocationEngine locationEngine;
    private LocationLayerPlugin locationLayerPlugin;
    private LocationLayerOptions locationLayerOptions;
    private Location originLocation;

    private boolean dentrozona;
    private ImageButton butsig;
    private ImageButton butprev;
    private ImageButton butcent;
    private Button butact;
    private TextView textodist;
    private TextView textodest;
    private ImageView marcador1;

    private Context contex = this;


    //BD
        private details_list listaDetalles;
        private MyOpenHelper db;

    //Aqui guardamos los datos de la BD
        private ArrayList<Paradas> lista_paradas;
        private ArrayList<Juegos> lista_juegos;
        private int cont = 0;
        int contJuegos = 0;

    //VALORES DEL SQL
        private String[] titulo;
    //Guardamos los marcadores en una lista para poder configurarlos una vez creados
        private List<Marker> lista;

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
        butsig = (ImageButton) findViewById(R.id.butsig);
        butprev = (ImageButton) findViewById(R.id.butprev);
        butcent = (ImageButton) findViewById(R.id.butCent);
        butact = (Button) findViewById(R.id.butAct);
        textodist = (TextView) findViewById(R.id.idTextdistancia);
        textodest = (TextView) findViewById(R.id.idTextdestino);
        marcador1 = (ImageView) findViewById(R.id.marcador1);

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
        butact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent  i = new Intent(MapsActivity.this, details_list.class);
                i.putExtra("id_parada",cont+1);
                startActivityForResult(i, REQ_MAPA);
                Log.d("mytag","Click a actividades");
            }});


        //Cogemos todos los nombres de las paradas desde el SQL
        db=new MyOpenHelper(this);

        listaDetalles = new details_list();
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
           // mapboxMap.setMinZoomPreference(10);
           // mapboxMap.setMaxZoomPreference(14);
            // Visualise bounds area
            showBoundsArea(mapboxMap);
        }catch(Exception e){
            Log.d("mytag", "ERROR CARGAR MAPA");
        }
    }

    private void showBoundsArea(MapboxMap mapboxMap) {

        //Delimitamos los limites del mapa en la pantalla -> Getxo
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

        mapboxMap.setCameraPosition(new CameraPosition.Builder()
                .zoom(16)
                .build());

            //PRUEBA GENERACION LINEAS
            ArrayList<LatLng> puntos = new ArrayList<>();
        for (int i = 0; i < titulo.length; i++) {
            LatLng laln = new LatLng(lista_paradas.get(i).getLatitud(), lista_paradas.get(i).getLongitud());
            puntos.add(laln);
        }
        //Desactivacion de interaccion con el mapa
        //mapboxMap.getUiSettings().setZoomGesturesEnabled(false);


/*  GENERACION DE RUTAS ENTRE PUNTOS (HABRIA QUE OPTIMIZAR CON GEOJSONS DESCARGADOS), dejar desactivado por el momento
        mapboxMap.addPolyline(new PolylineOptions()
            .addAll(puntos)
            .color(Color.parseColor("#3bb2d0"))
            .width(8));*/
//Convertimos los pngs en iconos para usarlos mas tarde
        IconFactory iconFactory = IconFactory.getInstance(contex);
        Icon iconorojo = iconFactory.fromResource(R.drawable.red_marker);
        Icon iconoamarillo = iconFactory.fromResource(R.drawable.yellow_marker);
        Icon iconoverde = iconFactory.fromResource(R.drawable.green_marker);
        Icon iconogris = iconFactory.fromResource(R.drawable.grey_marker);


            //GENERACION DE MARCADORES
            for (int i = 0; i < titulo.length; i++) {

                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(lista_paradas.get(i).getLatitud(), lista_paradas.get(i).getLongitud()))
                        .title(lista_paradas.get(i).getNombre())
                        .setIcon(iconogris));
            }


        /*******************************************
         * Añadimos un marcador en Txurdinaga PARA DESARROLLO*/

            //Onclick de marcador
            mapboxMap.setOnMarkerClickListener(new MapboxMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(@NonNull Marker marker) {
                    //Comprobacion a traves del nombre
                    if(marker.getTitle().equals(lista_paradas.get(cont).getNombre())){
                    for(int i = 0 ; i < lista.size();i++){
                        if(lista.get(i).getId() == marker.getId()){
                            Intent in = new Intent(MapsActivity.this, details_list.class);
                            Log.d("mytag", "ID_PARADA: "+cont+1);
                            in.putExtra("id_parada",cont+1);
                            in.putExtra("pag_anterior", 0);
                            startActivityForResult(in, REQ_MAPA);
                        }
                    }
                }
                    return true;
                }
            });

            //Llenamos la lista de marcadores
            lista = mapboxMap.getMarkers();



        /********************************************/

            mapboxMap.addMarker(new MarkerOptions()
                    .position(new LatLng(43.257895,-2.902738))
                    .title("Marcador Prueba"))
                    .setIcon(iconogris);
        /********************************************/

        enableLocation();
    }


    private void enableLocation(){
        //condicion para comprobar los permisos que necesita la aplicacion
        if(PermissionsManager.areLocationPermissionsGranted(this)){
            initializeLocationEngine();
            initializeLocationLayer();
        }else{
            //La aplicacion no tiene permisos, se piden
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


        //OPCIONES DEL ZOOM
       /* LocationLayerOptions options = LocationLayerOptions.builder(this)

                .maxZoom(16)
                .minZoom(14)
                .build();*/



        //locationPlayerPlugin, el punto de localizacion. En estas líneas le damos formato.
        //locationLayerPlugin = new LocationLayerPlugin(mapView, mapboxMap, locationEngine, options);
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
           // getRoute(posicionOrigen, posicionDestino);

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
            //getRoute(posicionOrigen, posicionDestino);
            Log.d("mytag", "objetivo cambiado, ahora es " + cont);

            //getDistancia();

        }else{}
    }

    private void bajarCont(){
        if(cont != 0 && cont <= (lista_paradas.size()-1)){
            cont--;
            posicionDestino = Point.fromLngLat(lista_paradas.get(cont).getLongitud(),lista_paradas.get(cont).getLatitud());
            posicionOrigen = Point.fromLngLat(originLocation.getLongitude(), originLocation.getLatitude());
            //getRoute(posicionOrigen, posicionDestino);
            Log.d("mytag", "objetivo cambiado, ahora es " + cont);

            //getDistancia();
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

        //MARCAR COMPLETADOS LOS QUE ESTEN
        Log.d("mytag","LLEGA AQUI AAAAA");
        for (int i = 0; i < lista.size(); i++) {
            if(lista_paradas.get(i).isRealizado()){
                marcarCompletado();
                Log.d("mytag","GetDistancia realizado");
            }
        }

        //Convertimos los pngs en iconos para usarlos mas tarde
        IconFactory iconFactory = IconFactory.getInstance(contex);
        Icon iconorojo = iconFactory.fromResource(R.drawable.red_marker);
        Icon iconoverde = iconFactory.fromResource(R.drawable.green_marker);

        Location destino = new Location(LocationManager.GPS_PROVIDER);

        destino.setLatitude(lista_paradas.get(cont).getLatitud());
        destino.setLongitude(lista_paradas.get(cont).getLongitud());

/*
        if (lista_paradas.get(cont).isRealizado()) {
            Log.d("mytag","" +lista_paradas.get(cont).getNombre() + "esta completado");
            lista.get(cont).setIcon(iconoverde);
            }*/

        /*destino.setLatitude(43.257895);
        destino.setLongitude(-2.902738);*/

        marcarCompletado();

        float distancia = originLocation.distanceTo(destino);
        String destinotexto = String.format("%.0f", distancia); //Quitamos los decimales
        textodest.setText( "Ondare " + (cont+1) + ": " + lista_paradas.get(cont).getNombre());
        textodist.setText("Distantzia: " + destinotexto + " metro");
        lista.get(cont).setIcon(iconorojo);
        Log.d("mytag","latitud de " + lista_paradas.get(cont).getNombre() + ": " + lista_paradas.get(cont).getLatitud());
        Log.d("mytag","longitud de " + lista_paradas.get(cont).getNombre() + ": " + lista_paradas.get(cont).getLongitud());

        Log.d("mytag" , "la distancia es " + destinotexto + " metros");

        lista_juegos = (ArrayList<Juegos>) db.getDatos_juegos_ID(cont+1);
        Log.d("mytag",""+ lista_juegos);
        Log.d("mytag","La distancia a " + lista_paradas.get(cont).getNombre()+ " es de " + distancia +" metros.");
        Log.d("mytag","ZOOM MAXIMO ES"+ mapboxMap.getMaxZoomLevel());

        //Comprobacion de que no este ya completado

    if(!lista_paradas.get(cont).isRealizado()){
        if(distancia <= 15 && !dentrozona){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage(lista_paradas.get(cont).getNombre() + " -(a)ren alboan zaude, jolasak egin nahi dituzu?" );
            alert.setNegativeButton("EZ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.d("mytag","has pulsado NO OK");
                }
            });
            alert.setPositiveButton("BAI", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    ////listaDetalles.CargarJuegos(lista_juegos,0);

                    Intent  i = new Intent(MapsActivity.this, details_list.class);
                    Log.d("mytag", "ID_PARADA: "+cont+1);
                    i.putExtra("id_parada",cont+1);
                    i.putExtra("pag_anterior", 0);
                    startActivityForResult(i, REQ_MAPA);
                }
            });
            alert.show();
            butact.setVisibility(View.VISIBLE);
            dentrozona = true;
            Log.d("mytag","Estas al lado de " + lista_paradas.get(cont).getNombre() + "!!!");
        }
        else{dentrozona = false;}
    }
    /*
    //CODIGO MARCADOR DE PRUEBA
        Log.d("mytag","ESTADO DE PARADA 1: " + lista_paradas.get(cont).isRealizado());
        if((distancia <= 60 && !dentrozona) && !lista_paradas.get(cont).isRealizado()){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Estas cerca del marcador de prueba quieres hacer las actividades?" );
            alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.d("mytag","has pulsado NO OK");
                }
            });
            alert.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    ////listaDetalles.CargarJuegos(lista_juegos,0);
                    Log.d("mytag","ID_PARADA: " +(cont+1));
                    Intent  i = new Intent(MapsActivity.this, details_list.class);
                    i.putExtra("id_parada",cont+1);
                    i.putExtra("pag_anterior", 0);
                    startActivityForResult(i, REQ_MAPA);
                }
            });
            alert.show();
            butact.setVisibility(View.VISIBLE);
            dentrozona = true;
        }
        else{dentrozona = false;
            butact.setVisibility(View.INVISIBLE);
        }
        */
        ultimaParada();
}

    private void marcarCompletado(){
//Convertimos los pngs en iconos para usarlos mas tarde
        IconFactory iconFactory = IconFactory.getInstance(contex);
        Icon iconoverde = iconFactory.fromResource(R.drawable.green_marker);

        if (lista_paradas.get(cont).isRealizado()) {
            Log.d("mytag","" +lista_paradas.get(cont).getNombre() + "esta completado");
            lista.get(cont).setIcon(iconoverde);
            subirCont();
            Log.d("mytag","" + cont);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQ_MAPA){
            Log.d("mytag","HE VUELTO DEL JUEGO POR EL MAPA");
            lista_paradas = db.getDatos_Paradas();
            db.close();
            /*contJuegos +=1;
            Log.d("mytag","contJuegos es " + contJuegos);
            Log.d("mytag","listajuegos size es " + lista_juegos.size());

            if (contJuegos < lista_juegos.size()){
                listaDetalles.CargarJuegos(lista_juegos, contJuegos);
            }*/
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // Esto es lo que hace mi botón al pulsar ir a atrás
            /*Toast.makeText(getApplicationContext(), "Voy hacia atrás!!",
                    Toast.LENGTH_SHORT).show();*/

        }
        return super.onKeyDown(keyCode, event);
    }

    public void ultimaParada(){
        IconFactory iconFactory = IconFactory.getInstance(contex);
        Icon iconoverde = iconFactory.fromResource(R.drawable.green_marker);
        if (lista_paradas.get(lista_paradas.size()-1).isRealizado()){
            lista.get(cont).setIcon(iconoverde);
        }
    }
}