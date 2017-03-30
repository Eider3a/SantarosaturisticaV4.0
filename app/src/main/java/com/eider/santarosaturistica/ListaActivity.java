package com.eider.santarosaturistica;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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

    private String username,correo;
    //Se creo la clase Lista_Entrada.
    //Se esta instanciando un arreglo de objetos.

    //Esta era la primer lista.
//    private Lista_Entrada[] datos=new Lista_Entrada[]{new Lista_Entrada(R.drawable.parque1,"parque1","Para visitar en familia","calle 56a"),
//        new Lista_Entrada(R.drawable.parque2,"parque2","Para visitar con la familia","calle 23"),
//        new Lista_Entrada(R.drawable.parque3,"parque3","Para visitar con amigos","calle 58")};

    private Lista_Entrada[] datos=new Lista_Entrada[]{new Lista_Entrada(R.drawable.tipico1,"Pandequeso","Para disfrutar en familia","5500$"),
            new Lista_Entrada(R.drawable.tipico2,"Butifarra Santa Isabel","Ven y antojate de las mejores butifarras","4500$"),
            new Lista_Entrada(R.drawable.tipico3,"Volovanes con langostino","Siempre vas a querer uno mas","10000$")};
    ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        Bundle extras=getIntent().getExtras();
//        username=extras.getString("username");
//        correo=extras.getString("correo");
        username="Eider";
        correo="eider@hotmail.com";

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
            direc.setText(datos[position].getPrecio());

            ImageView imagen=(ImageView)item.findViewById(R.id.ifoto);
            imagen.setImageResource(datos[position].getIdimagen());



//            return super.getView(position, convertView, parent);
            return item;
        }
    }


//    Esto se le agrego para la practica4.
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
                intent=new Intent(ListaActivity.this,PerfilActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("correo",correo);
                startActivity(intent);
                //finish();//Para que no se devulva.
                break;
            case R.id.mcerrar:
                intent=new Intent(ListaActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;

            //Para ir a ContenidoActivity para el manejo de Fragments.
            case R.id.mrestaurantes:
                intent=new Intent(ListaActivity.this,ContenidoActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("correo",correo);
                intent.putExtra("categoria","restaurantes");
//                intent.putExtra("correo",correo);
                startActivity(intent);
                //finish();//Para que no se devulva.
                break;
            case R.id.mhoteles:
                intent=new Intent(ListaActivity.this,ContenidoActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("correo",correo);
                intent.putExtra("categoria","hoteles");
//                intent.putExtra("correo",correo);
                startActivity(intent);
                //finish();
                break;
            case R.id.mturisticos:
                intent=new Intent(ListaActivity.this,ContenidoActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("correo",correo);
                intent.putExtra("categoria","turisticos");
//                intent.putExtra("correo",correo);
                startActivity(intent);
                //finish();
                break;
            case R.id.mpprincipal:
                intent=new Intent(ListaActivity.this,MainActivity.class);
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

}
