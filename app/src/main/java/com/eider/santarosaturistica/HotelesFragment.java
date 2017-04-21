package com.eider.santarosaturistica;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class HotelesFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    //Declaracion de atributos.

    private TextView titulo;
    private ImageView rutaimagen;
    private TextView descripcion;
    private Button enlace;

    private String text1,text2,text3;//Para manejar los textos que llegan desde el ContenidoActivity.
    private int id_imagenes;//Contiene el id de las imagenes.

    //PAra lo de los mapas.
    private MapView mapview;
    private GoogleMap mMap;
    private Double lat;
    private Double longit;

    public HotelesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View RootView = inflater.inflate(R.layout.fragment_hoteles2, container, false);

        //View view=new View();//Preguntar al profesor porque no sirvio.



        titulo=(TextView)(RootView.findViewById(R.id.tfragment1titulo));
        rutaimagen=(ImageView)(RootView.findViewById(R.id.ifragment1imagen));
        descripcion=(TextView)(RootView.findViewById(R.id.tfragment1descripcion));
        enlace=(Button)(RootView.findViewById(R.id.bfragment1enlace));

        //PAra el manejo de los mapas.
        mapview=(MapView)(RootView.findViewById(R.id.maphoteles));
        mapview.onCreate(savedInstanceState);
        mapview.getMapAsync((OnMapReadyCallback) this);

        //Capturo los valores que llegan desde ContenidoActivity.
        text1=getArguments().getString("titulo");
        text2=getArguments().getString("informacion");
        text3=getArguments().getString("enlace");
        id_imagenes=getArguments().getInt("imagen");
        lat=getArguments().getDouble("lat");
        longit=getArguments().getDouble("long");

        titulo.setText(text1);
        rutaimagen.setImageResource(id_imagenes);
        descripcion.setText(text2);

        enlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(text3);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return RootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Todo esto se trajo desde MapsActivity.
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);//tipo de mapa.
        mMap.setMyLocationEnabled(true);

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        // Add a marker in UDEA and move the camera
        LatLng hotel = new LatLng(lat,longit);
//        La proxima linea le agregaba un icono personalizado a la posicion.
//        mMap.addMarker(new MarkerOptions().position(santarosa).title("Santa rosa de osos").snippet("Mí pueblo").icon(BitmapDescriptorFactory.fromResource(R.drawable.icono)));
        mMap.addMarker(new MarkerOptions().position(hotel).title("Santa rosa de osos").snippet(text1));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hotel,16));
    }

    @Override
    public void onResume() {
        mapview.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        mapview.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        mapview.onLowMemory();
        super.onLowMemory();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
