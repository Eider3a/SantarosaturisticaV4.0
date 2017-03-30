package com.eider.santarosaturistica;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

//Se va a comportar como el main activity.
public class DraweActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    String username,correo;
    Button binformacion;

    //PAra lo de los mapas.
    private MapView mapview;
    private GoogleMap mMap;

    //Para el perfil de usuario.
    TextView tnombre,tcorreo;

    //PReferencias compartidas.
    SharedPreferences prefs;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Para el manejo de las preferencias.
        prefs=getSharedPreferences("Mispreferencias",MODE_PRIVATE);
        editor=prefs.edit();

        Bundle extras=getIntent().getExtras();
        username=extras.getString("username");
        correo=extras.getString("correo");
//        username="Eider Arango Amaya";
//        correo="eider@hotmail.com";

        //Para colocar la informacion del usuario en el NavigationDrawer.
//        tnombre=(TextView)findViewById(R.id.tperfilnombreusuariomain);
//        tcorreo=(TextView)findViewById(R.id.tperfilcorreomain);
//        tnombre.setText("eider");
//        tcorreo.setText(correo);


//        tnombre=(TextView)findViewById(R.id.tperfilnombreusuario);
//        tcorreo=(TextView)findViewById(R.id.tperfilcorreo);
//        tnombre.setText(username);
//        tcorreo.setText(correo);

        //Boton flotante.
        //El profesor borro este boton
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

//        Con este parte de codigo se maneja la parte donde sale el logo de android
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        tnombre=(TextView)header.findViewById(R.id.tperfilnombreusuariomain);
        tcorreo=(TextView)header.findViewById(R.id.tperfilcorreomain);
        tnombre.setText(username);
        tcorreo.setText(correo);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Intent intent;
        int id = item.getItemId();

        if (id == R.id.drawer_restaurantes) {
            intent=new Intent(DraweActivity.this,DraweContenidoActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("correo",correo);
            intent.putExtra("categoria","restaurantes");
//                intent.putExtra("correo",correo);
            startActivity(intent);
            //finish();//Para que no se devulva.
        }else if (id == R.id.drawer_principal) {
            intent=new Intent(DraweActivity.this,DraweActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("correo",correo);
            intent.putExtra("categoria","hoteles");
//                intent.putExtra("correo",correo);
            startActivity(intent);
            //finish();

        }else if (id == R.id.drawer_hoteles) {
            intent=new Intent(DraweActivity.this,DraweContenidoActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("correo",correo);
            intent.putExtra("categoria","hoteles");
//                intent.putExtra("correo",correo);
            startActivity(intent);
            //finish();

        } else if (id == R.id.drawer_turisticos) {
            intent=new Intent(DraweActivity.this,DraweContenidoActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("correo",correo);
            intent.putExtra("categoria","turisticos");
//                intent.putExtra("correo",correo);
            startActivity(intent);
            //finish();

        } else if (id == R.id.drawer_comidas) {
            intent=new Intent(DraweActivity.this,DraweListaActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("correo",correo);
//                intent.putExtra("categoria","turisticos");
//                intent.putExtra("correo",correo);
            startActivity(intent);
            //finish();

        } else if (id == R.id.drawer_cerrar_sesion) {
            //Hay que hacerlo en todas opciones de cerrar sesion.
            editor.putInt("login",-1);//para que coloque la preferencia login en -1 y no pase derecho al logearse.
            editor.commit();//Siempre hay que hacer un commit.
            intent=new Intent(DraweActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

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
        LatLng santarosa = new LatLng(6.647828,-75.460764);
//        La proxima linea le agregaba un icono personalizado a la posicion.
//        mMap.addMarker(new MarkerOptions().position(santarosa).title("Santa rosa de osos").snippet("Mí pueblo").icon(BitmapDescriptorFactory.fromResource(R.drawable.icono)));
        mMap.addMarker(new MarkerOptions().position(santarosa).title("Santa rosa de osos").snippet("Mí pueblo"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(santarosa,8));
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
}
