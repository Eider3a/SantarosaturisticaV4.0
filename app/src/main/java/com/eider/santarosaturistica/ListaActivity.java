package com.eider.santarosaturistica;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListaActivity extends AppCompatActivity {

//    String [] parques=new String[]{"Bolivar","lleras","Prado","Robledo"};
    //Creo arreglo de objetos.

    //Se creo la clase Lista_Entrada.
    //Se esta instanciando un arreglo de objetos.

    private Lista_Entrada[] datos=new Lista_Entrada[]{new Lista_Entrada(R.drawable.parque1,"parque1","Para visitar en familia","calle 56a"),
        new Lista_Entrada(R.drawable.parque2,"parque2","Para visitar con la familia","calle 23"),
        new Lista_Entrada(R.drawable.parque3,"parque3","Para visitar con amigos","calle 58")};
    ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        list=(ListView)findViewById(R.id.list);
        Adapter adapter=new Adapter(this,datos);
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,parques);
        list.setAdapter(adapter);

        //MAneja los eventos para los items del listView.
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data=((Lista_Entrada)parent.getItemAtPosition(position)).getNombre();//Sabemos sobre quien se dio click.
                //Con esto abrimos la actividad que queramos.
                Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();

                Intent intent =new Intent(ListaActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //Vamos a crear el adaptador.
    //No le pasamos un string sino la lista de entrada.
    class Adapter extends ArrayAdapter<Lista_Entrada>{

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
            direc.setText(datos[position].getDireccion());

            ImageView imagen=(ImageView)item.findViewById(R.id.ifoto);
            imagen.setImageResource(datos[position].getIdimagen());



//            return super.getView(position, convertView, parent);
            return item;
        }
    }


}
