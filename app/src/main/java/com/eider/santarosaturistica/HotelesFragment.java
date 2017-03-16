package com.eider.santarosaturistica;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HotelesFragment extends Fragment {

    //Declaracion de atributos.

    private TextView titulo;
    private ImageView rutaimagen;
    private TextView descripcion;
    private Button enlace;

    private String text1,text2,text3;//Para manejar los textos que llegan desde el ContenidoActivity.
    private int id_imagenes;//Contiene el id de las imagenes.

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
