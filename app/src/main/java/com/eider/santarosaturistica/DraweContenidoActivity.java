package com.eider.santarosaturistica;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DraweContenidoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //definicion de los atributos.
    private String categoria;
    private String tab1, tab2, tab3;//Tiene el nombre de cada tab.
    private String titulo1, titulo2, titulo3;//Titulos para los fragment.
    private int rutaimg1, rutaimg2, rutaimg3;//Envio el ID de la imagen.
    private String descr1, descr2, descr3;//Texto para el manejo de las descripciones.

    //Para pasar datos entre actividades.
    String username, correo;

    //Para el perfil de usuario.
    TextView tnombre,tcorreo;

    //Preferencias compartidas.
    SharedPreferences prefs;
    SharedPreferences.Editor editor;


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        username = extras.getString("username");
        correo = extras.getString("correo");
        categoria = extras.getString("categoria");

        //Para el manejo de las preferencias.
        prefs=getSharedPreferences("Mispreferencias",MODE_PRIVATE);
        editor=prefs.edit();

        //Para colocar la informacion del usuario en el NavigationDrawer.
//        tnombre=(TextView)findViewById(R.id.tperfilnombreusuario);
//        tcorreo=(TextView)findViewById(R.id.tperfilcorreo);
//        tnombre.setText(username);
//        tcorreo.setText(correo);
//        username="Eider";
//        correo="eider@hotmail.com";

        if (categoria.equals("restaurantes")) {
            tab1 = "Estadero santa rosa";
            tab2 = "Juan Gourmet";
            tab3 = "Puesta del sol";

            titulo1 = "Estadero Santa Rosa";
            titulo2 = "Juan Gourmet";
            titulo3 = "Puesta del sol";

            rutaimg1 = R.drawable.restaurante1;
            rutaimg2 = R.drawable.restaurante2;
            rutaimg3 = R.drawable.restaurante3;

            descr1 = "Estadero SANTA ROSA fundado en el año de 1954 por los señores" +
                    " Roberto Restrepo y Libardo roldan con la idea de construir un" +
                    " paradero donde la gente descansara \n" +
                    "Tel: 8605590    ";
            descr2 = "Dirección: Cl El Palo 28-20 \n" +
                    "Ciudad: Santa Rosa De Osos\n" +
                    "Región: Antioquia\n" +
                    "País: Colombia \n" +
                    "Teléfono (4) 860-5504";
            descr3 = " Dirección: Cl Del Medio 29-65 \n" +
                    "Ciudad o Municipio: Santa Rosa de Osos \n" +
                    "Departamento, Estado o provincia: Antioquia \n" +
                    "País: Colombia \n" +
                    "Teléfono: +(57) (4) 8607592";
        } else if (categoria.equals("hoteles")) {
            tab1 = "Cortejo Imperial";
            tab2 = "La Circasia";
            tab3 = "Santa rosa de osos";

            titulo1 = "Cortejo Imperial";
            titulo2 = "La Circasia";
            titulo3 = "Santa rosa de osos";


            rutaimg1 = R.drawable.hotel1;
            rutaimg2 = R.drawable.hotel2;
            rutaimg3 = R.drawable.hotel3;

            descr1 = "Teléfonos (s)\n" +
                    "(57) (4) 8609573\n" +
                    "Estacionamiento: Valet parking\n" +
                    "Horario de Atención: 24h\n" +
                    "Servicios:Internet,WIFI,Restaurante,domicilio";
            descr2 = "A Un Km Via A La Costa Santa Rosa de Osos Santa Rosa de Osos, Colombia" +
                    "Tel: (57) (4) 8605500";
            descr3 = "Situado a la entrada de la Ciudad de Santa Rosa de Osos (Antioquia), en la troncal del norte, via la costa atlantica,  en una de las mejores zonas de la ciudad";
        } else if (categoria.equals("turisticos")) {
            tab1 = "C. Señora de chiquinquira";
            tab2 = "C. Señor de la humildad";
            tab3 = "B. Señora de la misericordia";

            titulo1 = "Señora de chiquinquira";
            titulo2 = "Señor de la humildad";
            titulo3 = "Señora de la misericordia";

            rutaimg1 = R.drawable.turistico1;
            rutaimg2 = R.drawable.turistico2;
            rutaimg3 = R.drawable.turistico3;

            descr1 = "Esta catedral es bellamente rica en pintura,espaciosa,lugar adecuado para reflexionar,al menos para nosotros catolicos,se encuentra en una hermosa plaza muy tranquila.";
            descr2 = "Es un templo colombiano de culto católico dedicado a Cristo bajo la advocación del Señor de la Humildad, está ubicada en la Calle 30 con Carrera  28, a dos cuadras del parque principal del municipio de Santa Rosa.";
            descr3 = "El edificio es de planta de cruz griega, cuenta con un área de 800 metros cuadrados, capacidad para 3.400 personas; su estilo es moderno inspirado en el gótico, pues consta de arcos ojivales que le dan la forma al templo.";
        }

        setContentView(R.layout.activity_drawe_contenido);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

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
        tnombre=(TextView)header.findViewById(R.id.tperfilnombreusuariocontenido);
        tcorreo=(TextView)header.findViewById(R.id.tperfilcorreocontenido);
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
        getMenuInflater().inflate(R.menu.drawe_contenido, menu);
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
            intent = new Intent(DraweContenidoActivity.this, DraweContenidoActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("correo", correo);
            intent.putExtra("categoria", "restaurantes");
//                intent.putExtra("correo",correo);
            startActivity(intent);
            //finish();//Para que no se devulva.
        }else if (id == R.id.drawer_principal) {
            intent=new Intent(DraweContenidoActivity.this,DraweActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("correo",correo);
            intent.putExtra("categoria","hoteles");
//                intent.putExtra("correo",correo);
            startActivity(intent);
            //finish();

        }
        else if (id == R.id.drawer_hoteles) {
            intent = new Intent(DraweContenidoActivity.this, DraweContenidoActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("correo", correo);
            intent.putExtra("categoria", "hoteles");
//                intent.putExtra("correo",correo);
            startActivity(intent);
            //finish();

        } else if (id == R.id.drawer_turisticos) {
            intent = new Intent(DraweContenidoActivity.this, DraweContenidoActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("correo", correo);
            intent.putExtra("categoria", "turisticos");
//                intent.putExtra("correo",correo);
            startActivity(intent);
            //finish();

        } else if (id == R.id.drawer_comidas) {
            intent = new Intent(DraweContenidoActivity.this, DraweListaActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("correo", correo);
//                intent.putExtra("categoria","turisticos");
//                intent.putExtra("correo",correo);
            startActivity(intent);
            //finish();

        } else if (id == R.id.drawer_cerrar_sesion) {
            editor.putInt("login",-1);//para que coloque la preferencia login en -1 y no pase derecho al logearse.
            editor.commit();//Siempre hay que hacer un commit.
            intent = new Intent(DraweContenidoActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            Bundle data;

            if (categoria.equals("restaurantes")) {
                switch (position) {
                    case 0:
                        RestaurantesFragment tab1 = new RestaurantesFragment();
                        data = new Bundle();
                        data.putString("titulo", titulo1);
                        data.putInt("imagen", rutaimg1);
                        data.putString("informacion", descr1);
                        data.putString("enlace", "http://paradorsantarosadeosos.blogspot.com.co");
                        data.putDouble("lat",6.6482113);
                        data.putDouble("long",-75.461429);
                        tab1.setArguments(data);
                        return tab1;

                    case 1:
                        RestaurantesFragment tab2 = new RestaurantesFragment();
                        data = new Bundle();
                        data.putString("titulo", titulo2);
                        data.putInt("imagen", rutaimg2);
                        data.putString("informacion", descr2);
                        data.putString("enlace", "http://www.amarillascolombia.co/colombia/santa-rosa-de-osos/restaurantes/restaurante-juan-gourmet-340268#.WMnhG302vIU");
                        data.putDouble("lat",6.6472953);
                        data.putDouble("long",-75.4607177);
                        tab2.setArguments(data);
                        return tab2;
                    case 2:
                        RestaurantesFragment tab3 = new RestaurantesFragment();
                        data = new Bundle();
                        data.putString("titulo", titulo3);
                        data.putInt("imagen", rutaimg3);
                        data.putString("informacion", descr3);
                        data.putString("enlace", "https://www.nexdu.com/co/santa-rosa-de-osos-ant/restaurantes/restaurante-bar-puesta-del-sol-357050");
                        data.putDouble("lat",6.6488234);
                        data.putDouble("long",-75.4598942);
                        tab3.setArguments(data);
                        return tab3;
                    default:
                        return null;
                }
            } else if (categoria.equals("hoteles")) {
                switch (position) {
                    case 0:
                        HotelesFragment tab1 = new HotelesFragment();
                        data = new Bundle();
                        data.putString("titulo", titulo1);
                        data.putInt("imagen", rutaimg1);
                        data.putString("informacion", descr1);
                        data.putString("enlace", "http://www.paginasamarillas.com.co/empresas/hotel-cortejo-imperial-eu/santa-rosa-de-osos-15384876");
                        data.putDouble("lat",6.6483474);
                        data.putDouble("long",-75.4595998);
                        tab1.setArguments(data);
                        return tab1;

                    case 1:
                        HotelesFragment tab2 = new HotelesFragment();
                        data = new Bundle();
                        data.putString("titulo", titulo2);
                        data.putInt("imagen", rutaimg2);
                        data.putString("informacion", descr2);
                        data.putString("enlace", "http://www.paginasamarillas.com.co/empresas/hotel-la-circasia/santa-rosa-de-osos-30248969");
                        data.putDouble("lat",6.6483705);
                        data.putDouble("long",-75.4598471);
                        tab2.setArguments(data);
                        return tab2;
                    case 2:
                        HotelesFragment tab3 = new HotelesFragment();
                        data = new Bundle();
                        data.putString("titulo", titulo3);
                        data.putInt("imagen", rutaimg3);
                        data.putString("informacion", descr3);
                        data.putString("enlace", "http://www.hotelsantarosadeosos.com");
                        data.putDouble("lat",6.6522049);
                        data.putDouble("long",-75.456649);
                        tab3.setArguments(data);
                        return tab3;
                    default:
                        return null;
                }
            } else if (categoria.equals("turisticos")) {
                switch (position) {
                    case 0:
                        SitiosTuristicosFragment tab1 = new SitiosTuristicosFragment();
                        data = new Bundle();
                        data.putString("titulo", titulo1);
                        data.putInt("imagen", rutaimg1);
                        data.putString("informacion", descr1);
                        data.putString("enlace", "https://www.tripadvisor.co/Attraction_Review-g4454684-d7789496-Reviews-Catedral_Nuestra_Senora_del_Rosario_de_Chiquinquira-Santa_Rosa_de_Osos_Antioquia.html");
                        data.putDouble("lat",6.6482401);
                        data.putDouble("long",-75.4610103);
                        tab1.setArguments(data);
                        return tab1;

                    case 1:
                        SitiosTuristicosFragment tab2 = new SitiosTuristicosFragment();
                        data = new Bundle();
                        data.putString("titulo", titulo2);
                        data.putInt("imagen", rutaimg2);
                        data.putString("informacion", descr2);
                        data.putString("enlace", "https://es.wikipedia.org/wiki/Capilla_del_Señor_de_la_Humildad_(Santa_Rosa_de_Osos)");
                        data.putDouble("lat",6.6468117);
                        data.putDouble("long",-75.4595256);
                        tab2.setArguments(data);
                        return tab2;
                    case 2:
                        SitiosTuristicosFragment tab3 = new SitiosTuristicosFragment();
                        data = new Bundle();
                        data.putString("titulo", titulo3);
                        data.putInt("imagen", rutaimg3);//LLeva el id de la imagen en tipo int.
                        data.putString("informacion", descr3);
                        data.putString("enlace", "https://es.wikipedia.org/wiki/Basílica_menor_de_Nuestra_Señora_de_las_Misericordias_(Santa_Rosa_de_Osos)");
                        data.putDouble("lat",6.6421166);
                        data.putDouble("long",-75.4601538);
                        tab3.setArguments(data);
                        return tab3;
                    default:
                        return null;
                }
            } else {
                return null;
            }

            //Esto es lo que carga los fragment.
            //

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //Log.d("dato", tab1);
            switch (position) {

                case 0:
                    return tab1;
                case 1:
                    return tab2;
                case 2:
                    return tab3;
            }
            return null;
        }


    }
}