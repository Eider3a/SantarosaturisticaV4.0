package com.eider.santarosaturistica;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

public class ContenidoActivity extends AppCompatActivity {

    //definicion de los atributos.
    private String categoria;
    private String tab1,tab2,tab3;//Tiene el nombre de cada tab.
    private String titulo1,titulo2,titulo3;//Titulos para los fragment.
    private String rutaimg1,rutaimg2,rutaimg3;//rutas para las imagenes.
    private String descr1,descr2,descr3;//Texto para el manejo de las descripciones.



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

        Bundle extras=getIntent().getExtras();
        categoria=extras.getString("categoria");

        if(categoria.equals("restaurantes")){
            tab1="Estadero santa rosa";
            tab2="Juan Gourmet";
            tab3="Puesta del sol";

            titulo1="Estadero santa rosa";
            titulo2="Juan Gourmet";
            titulo3="Puesta del sol";
        }
        else if(categoria.equals("hoteles")){
            tab1="Cortejo Imperial";
            tab2="La Circasia";
            tab3="Santa rosa de osos";

            titulo1="Cortejo Imperial";
            titulo2="La Circasia";
            titulo3="Santa rosa de osos";
        }
        else if(categoria.equals("turisticos")){
            tab1="C. Señora de chiquinquira";
            tab2="C. Señor de la humildad";
            tab3="B. Señora de la misericordia";

            titulo1="C. Señora de chiquinquira";
            titulo2="C. Señor de la humildad";
            titulo3="B. Señora de la misericordia";
        }

        setContentView(R.layout.activity_contenido);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        //Manejo de los datos que llegan desde MainActivity.


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contenido, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
//    public static class PlaceholderFragment extends Fragment {
//        /**
//         * The fragment argument representing the section number for this
//         * fragment.
//         */
//        private static final String ARG_SECTION_NUMBER = "section_number";
//
//        public PlaceholderFragment() {
//        }
//
//        /**
//         * Returns a new instance of this fragment for the given section
//         * number.
//         */
//        public static PlaceholderFragment newInstance(int sectionNumber) {
//            PlaceholderFragment fragment = new PlaceholderFragment();
//            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            fragment.setArguments(args);
//            return fragment;
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_contenido, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
//            return rootView;
//        }
//    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            Bundle data;

            if(categoria.equals("restaurantes")){
                switch (position){
                    case 0:
                        RestaurantesFragment tab1=new RestaurantesFragment();
                        data = new Bundle();
                        data.putString("titulo", titulo1);
                        tab1.setArguments(data);
                        return tab1;

                    case 1:
                        RestaurantesFragment tab2=new RestaurantesFragment();
                        data = new Bundle();
                        data.putString("titulo", titulo2);
                        tab2.setArguments(data);
                        return tab2;
                    case 2:
                        RestaurantesFragment tab3=new RestaurantesFragment();
                        data = new Bundle();
                        data.putString("titulo", titulo3);
                        tab3.setArguments(data);
                        return tab3;
                    default:return null;
                }
            }
            else if(categoria.equals("hoteles")){
                switch (position){
                    case 0:
                        HotelesFragment tab1=new HotelesFragment();
                        data = new Bundle();
                        data.putString("titulo", titulo1);
                        tab1.setArguments(data);
                        return tab1;

                    case 1:
                        HotelesFragment tab2=new HotelesFragment();
                        data = new Bundle();
                        data.putString("titulo", titulo2);
                        tab2.setArguments(data);
                        return tab2;
                    case 2:
                        HotelesFragment tab3=new HotelesFragment();
                        data = new Bundle();
                        data.putString("titulo", titulo3);
                        tab3.setArguments(data);
                        return tab3;
                    default:return null;
                }
            }
            else if(categoria.equals("turisticos")){
                switch (position){
                    case 0:
                        SitiosTuristicosFragment tab1=new SitiosTuristicosFragment();
                        data = new Bundle();
                        data.putString("titulo", titulo1);
                        tab1.setArguments(data);
                        return tab1;

                    case 1:
                        SitiosTuristicosFragment tab2=new SitiosTuristicosFragment();
                        data = new Bundle();
                        data.putString("titulo", titulo2);
                        tab2.setArguments(data);
                        return tab2;
                    case 2:
                        SitiosTuristicosFragment tab3=new SitiosTuristicosFragment();
                        data = new Bundle();
                        data.putString("titulo", titulo3);
                        tab3.setArguments(data);
                        return tab3;
                    default:return null;
                }
            }
            else{
                return null;
            }

            //Esto es lo que carga los fragment.

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
