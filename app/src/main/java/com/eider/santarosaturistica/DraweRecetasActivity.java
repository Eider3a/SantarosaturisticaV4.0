package com.eider.santarosaturistica;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DraweRecetasActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String username,correo;
    String titulo,receta;
    int imagen;

    TextView ttitulo,treceta;
    ImageView iimagen;

    //Preferencias compartidas.
    SharedPreferences prefs;
    SharedPreferences.Editor editor;



    //Para el perfil de usuario.
    TextView tnombre,tcorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawe_recetas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Para el manejo de las preferencias.
        prefs=getSharedPreferences("Mispreferencias",MODE_PRIVATE);
        editor=prefs.edit();

        Bundle extras=getIntent().getExtras();
        username=extras.getString("username");
        correo=extras.getString("correo");
        titulo=extras.getString("titulo");
        imagen=extras.getInt("imagen");
        receta=extras.getString("receta");
//        username="Eider";
//        correo="eider@hotmail.com";

        //Para colocar la informacion del usuario en el NavigationDrawer.
//        tnombre=(TextView)findViewById(R.id.tperfilnombreusuario);
//        tcorreo=(TextView)findViewById(R.id.tperfilcorreo);
//        tnombre.setText(username);
//        tcorreo.setText(correo);

        ttitulo=(TextView)findViewById(R.id.trecetastitulo);
        ttitulo.setText(titulo);
        iimagen=(ImageView)findViewById(R.id.irecetasimagen);
        iimagen.setImageResource(imagen);
        treceta=(TextView)findViewById(R.id.trecetasreceta);
        treceta.setText(receta);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        tnombre=(TextView)header.findViewById(R.id.tperfilnombreusuariorecetas);
        tcorreo=(TextView)header.findViewById(R.id.tperfilcorreorecetas);
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
        getMenuInflater().inflate(R.menu.drawe_recetas, menu);
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
            intent=new Intent(DraweRecetasActivity.this,DraweContenidoActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("correo",correo);
            intent.putExtra("categoria","restaurantes");
//                intent.putExtra("correo",correo);
            startActivity(intent);
            //finish();//Para que no se devulva.
        } else if (id == R.id.drawer_principal) {
            intent=new Intent(DraweRecetasActivity.this,DraweActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("correo",correo);
            intent.putExtra("categoria","hoteles");
//                intent.putExtra("correo",correo);
            startActivity(intent);
            //finish();

        }
        else if (id == R.id.drawer_hoteles) {
            intent=new Intent(DraweRecetasActivity.this,DraweContenidoActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("correo",correo);
            intent.putExtra("categoria","hoteles");
//                intent.putExtra("correo",correo);
            startActivity(intent);
            //finish();

        } else if (id == R.id.drawer_turisticos) {
            intent=new Intent(DraweRecetasActivity.this,DraweContenidoActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("correo",correo);
            intent.putExtra("categoria","turisticos");
//                intent.putExtra("correo",correo);
            startActivity(intent);
            //finish();

        } else if (id == R.id.drawer_comidas) {
            intent=new Intent(DraweRecetasActivity.this,DraweListaActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("correo",correo);
//                intent.putExtra("categoria","turisticos");
//                intent.putExtra("correo",correo);
            startActivity(intent);
            //finish();

        } else if (id == R.id.drawer_cerrar_sesion) {
            editor.putInt("login",-1);//para que coloque la preferencia login en -1 y no pase derecho al logearse.
            editor.commit();//Siempre hay que hacer un commit.
            intent=new Intent(DraweRecetasActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
