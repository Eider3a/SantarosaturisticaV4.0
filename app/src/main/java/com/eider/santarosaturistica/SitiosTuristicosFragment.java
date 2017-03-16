package com.eider.santarosaturistica;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SitiosTuristicosFragment extends Fragment {

    //Declaracion de atributos.

    private TextView titulo;
    private ImageView rutaimagen;
    private TextView descripcion;

    private String text1,text2,text3;//Para manejar los textos que llegan desde el ContenidoActivity.
    private int id_imagenes;//Contiene el id de las imagenes.
    private Button enlace;


    public SitiosTuristicosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sitios_turisticos, container, false);

        View RootView = inflater.inflate(R.layout.fragment_sitios_turisticos, container, false);


        titulo=(TextView)(RootView.findViewById(R.id.tfragment3titulo));
        rutaimagen=(ImageView)(RootView.findViewById(R.id.ifragment3imagen));
        descripcion=(TextView)(RootView.findViewById(R.id.tfragment3descripcion));
        enlace=(Button)(RootView.findViewById(R.id.bfragment3enlace));

        //Capturo los valores que llegan desde ContenidoActivity.
        text1=getArguments().getString("titulo");
        text2=getArguments().getString("informacion");
        text3=getArguments().getString("enlace");
        id_imagenes=getArguments().getInt("imagen");

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

}