package com.eider.santarosaturistica;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    String username,correo;
    Button binformacion;

    //PAra lo de los mapas.
    private MapView mapview;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toast.makeText(this,"onCreate",Toast.LENGTH_SHORT).show();
        Bundle extras=getIntent().getExtras();
//        username=extras.getString("username");
//        correo=extras.getString("correo");
        username="Eider";
        correo="eider@hotmail.com";

        binformacion=(Button)findViewById(R.id.binformacion);
        binformacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.santarosadeosos-antioquia.gov.co/Paginas/default.aspx");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        //PAra el manejo de los mapas.
        mapview=(MapView)findViewById(R.id.map);
        mapview.onCreate(savedInstanceState);
        mapview.getMapAsync(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//asignar el menu xml.
//        return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu,menu);//Colocal el menu, lo infla.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//verificar cual fue el menu que el usuario presiono.
//        return super.onOptionsItemSelected(item);
        int id=item.getItemId();
        Intent intent;
        switch (id){
            case R.id.mperfil:
                intent=new Intent(MainActivity.this,PerfilActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("correo",correo);
                startActivity(intent);
                //finish();//Para que no se devulva.
                break;
            case R.id.mcerrar:
                intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;

            //Para ir a ContenidoActivity para el manejo de Fragments.
            case R.id.mrestaurantes:
                intent=new Intent(MainActivity.this,ContenidoActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("correo",correo);
                intent.putExtra("categoria","restaurantes");
//                intent.putExtra("correo",correo);
                startActivity(intent);
                //finish();//Para que no se devulva.
                break;
            case R.id.mhoteles:
                intent=new Intent(MainActivity.this,ContenidoActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("correo",correo);
                intent.putExtra("categoria","hoteles");
//                intent.putExtra("correo",correo);
                startActivity(intent);
                //finish();
                break;
            case R.id.mturisticos:
                intent=new Intent(MainActivity.this,ContenidoActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("correo",correo);
                intent.putExtra("categoria","turisticos");
//                intent.putExtra("correo",correo);
                startActivity(intent);
                //finish();
                break;
            case R.id.mcomidas:
                intent=new Intent(MainActivity.this,ListaActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("correo",correo);
//                intent.putExtra("categoria","turisticos");
//                intent.putExtra("correo",correo);
                startActivity(intent);
                //finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Generado para trabajar con los mapas, lo genero android studio.
    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Todo esto se trajo desde MapsActivity.
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);//tipo de mapa.
        mMap.setMyLocationEnabled(true);

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        // Add a marker in UDEA and move the camera
        LatLng udea = new LatLng(6.266953,-75.569111);
        mMap.addMarker(new MarkerOptions().position(udea).title("Universidad de Antioquia").snippet("Nuestra alma mater").icon(BitmapDescriptorFactory.fromResource(R.drawable.icono)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(udea,8));
    }

    @Override
    protected void onResume() {
        mapview.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mapview.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        mapview.onLowMemory();
        super.onLowMemory();
    }

    //PAra los mapas.
}
