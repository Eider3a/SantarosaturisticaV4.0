package com.eider.santarosaturistica;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DraweListaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //definicion de los atributos.
    private String categoria;
    private String username,correo;

    //Para el perfil de usuario.
    TextView tnombre,tcorreo;

    private Lista_Entrada[] datos=new Lista_Entrada[]{new Lista_Entrada(R.drawable.tipico1,"Pandequeso","Para disfrutar en familia","5500$"),
            new Lista_Entrada(R.drawable.tipico2,"Butifarra Santa Isabel","Ven y antojate de las mejores butifarras","4500$"),
            new Lista_Entrada(R.drawable.tipico3,"Volovanes con langostino","Siempre vas a querer uno mas","10000$")};
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawe_lista);

        Bundle extras=getIntent().getExtras();
        username=extras.getString("username");
        correo=extras.getString("correo");
//        username="Eider";
//        correo="eider@hotmail.com";

        //Para colocar la informacion del usuario en el NavigationDrawer.
//        tnombre=(TextView)findViewById(R.id.tperfilnombreusuario);
//        tcorreo=(TextView)findViewById(R.id.tperfilcorreo);
//        tnombre.setText(username);
//        tcorreo.setText(correo);

        list=(ListView)findViewById(R.id.list);
        Adapter adapter=new Adapter(this,datos);
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,parques);
        list.setAdapter(adapter);

        //Maneja los eventos para los items del listView.
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data=((Lista_Entrada)parent.getItemAtPosition(position)).getNombre();//Sabemos sobre quien se dio click.
                //Con esto abrimos la actividad que queramos.
//                Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();

                String titulo="ok",receta="ok";
                int imagen=0;
                switch (data){
                    case "Pandequeso":
                        titulo="Pandequeso";
                        imagen=R.drawable.tipico1;
                        receta="Ingredientes:\n" +
                                "700 gramos de queso duro (costeño) rallado \n" +
                                "700 gramos de almidon agrio \n" +
                                "175 gramos de mezcla para buñuelos \n" +
                                "2 huevos \n" +
                                "60 gramos de polvo para hornear \n"+

                                "\n Se arman los pandequesos, redondos o alargados y se llevan al horno precalentado a 350°C por 20 o 30 minutos.";
                        break;
                    case "Butifarra Santa Isabel":
                        titulo="Butifarra Santa Isabel";
                        imagen=R.drawable.tipico2;
                        receta="500 gr. de lomo de cerdo, " +
                                "Sal, ajos molidos, comino, pimienta, achiote o páprica fuerte.\n" +
                                "\n Poner a hervir todos estos ingredientes con 3 tazas de agua o poner todo al horno con una o dos tazas de agua. Una vez cocido retirar y cortar en lonjas.";
                        break;
                    case "Volovanes con langostino":
                        titulo="Volovanes con langostino";
                        imagen=R.drawable.tipico3;
                        receta="8 volovanes\n" +
                                "4 partes de mayonesa\n" +
                                "Media parte de zumo de naranja natural\n" +
                                "Langostinos al gusto\n" +
                                "Sal \n" +
                                "Hojas de laurel\n" +
                                "\nColocamos abundante agua en una cacerola con los langostinos, a fuego medio. Cuando rompa a hervir añadimos sal y alguna hoja de laurel. dejamos cocer dos minutos y después retiramos del fuego. Escurrimos los langostinos y los pasamos de inmediato a un recipiente con agua fría, hielo y sal. Servimos con salsa rosa.";
                        break;
                    default:
                        break;
                }

                Intent intent =new Intent(DraweListaActivity.this,DraweRecetasActivity.class);
                //Agregar datos para la vista DraweRecetasActivity.
                intent.putExtra("username",username);
                intent.putExtra("correo",correo);
                intent.putExtra("titulo",titulo);
                intent.putExtra("imagen",imagen);
                intent.putExtra("receta",receta);

                startActivity(intent);
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        tnombre=(TextView)header.findViewById(R.id.tperfilnombreusuariolista);
        tcorreo=(TextView)header.findViewById(R.id.tperfilcorreolista);
        tnombre.setText(username);
        tcorreo.setText(correo);
    }

    //No le pasamos un string sino la lista de entrada.
    class Adapter extends ArrayAdapter<Lista_Entrada> {

        public Adapter(@NonNull Context context, Lista_Entrada[] datos) {
            super(context, R.layout.list_item,datos);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater=LayoutInflater.from(getContext());
            View item=inflater.inflate(R.layout.list_item,null);//ME traigo los widgets de otro layout.

            TextView nombre=(TextView)item.findViewById(R.id.tnombre);
            nombre.setText(datos[position].getNombre());

            TextView descrip=(TextView)item.findViewById(R.id.tdescrip);
            descrip.setText(datos[position].getDescripcion());

            TextView direc=(TextView)item.findViewById(R.id.tdireccion);
            direc.setText(datos[position].getPrecio());

            ImageView imagen=(ImageView)item.findViewById(R.id.ifoto);
            imagen.setImageResource(datos[position].getIdimagen());



//            return super.getView(position, convertView, parent);
            return item;
        }
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
        getMenuInflater().inflate(R.menu.drawe_lista, menu);
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
            intent=new Intent(DraweListaActivity.this,DraweContenidoActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("correo",correo);
            intent.putExtra("categoria","restaurantes");
//                intent.putExtra("correo",correo);
            startActivity(intent);
            //finish();//Para que no se devulva.
        } else if (id == R.id.drawer_principal) {
            intent=new Intent(DraweListaActivity.this,DraweActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("correo",correo);
            intent.putExtra("categoria","hoteles");
//                intent.putExtra("correo",correo);
            startActivity(intent);
            //finish();

        }
        else if (id == R.id.drawer_hoteles) {
            intent=new Intent(DraweListaActivity.this,DraweContenidoActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("correo",correo);
            intent.putExtra("categoria","hoteles");
//                intent.putExtra("correo",correo);
            startActivity(intent);
            //finish();

        } else if (id == R.id.drawer_turisticos) {
            intent=new Intent(DraweListaActivity.this,DraweContenidoActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("correo",correo);
            intent.putExtra("categoria","turisticos");
//                intent.putExtra("correo",correo);
            startActivity(intent);
            //finish();

        } else if (id == R.id.drawer_comidas) {
            intent=new Intent(DraweListaActivity.this,DraweListaActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("correo",correo);
//                intent.putExtra("categoria","turisticos");
//                intent.putExtra("correo",correo);
            startActivity(intent);
            //finish();

        } else if (id == R.id.drawer_cerrar_sesion) {
            intent=new Intent(DraweListaActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
