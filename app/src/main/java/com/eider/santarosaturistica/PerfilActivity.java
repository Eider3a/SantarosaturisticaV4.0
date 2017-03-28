package com.eider.santarosaturistica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class PerfilActivity extends AppCompatActivity {

    String username,correo;
    TextView tusername,tcorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        tusername=(TextView)findViewById(R.id.tusername);
        tcorreo=(TextView)findViewById(R.id.tcorreo);

        Bundle extras=getIntent().getExtras();
        username=extras.getString("username");
        correo=extras.getString("correo");

        tusername.setText(username);
        tcorreo.setText(correo);



    }

    //Metodos para agregar el menu y colocar sus elementos a la escucha.


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuperfil,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        Intent intent;
        switch (id){
            case R.id.mpprincipal:
                intent=new Intent(PerfilActivity.this,MainActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("correo",correo);
                startActivity(intent);
                finish();
                break;

            case R.id.mpcerrar:
                intent=new Intent(PerfilActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.mrestaurantes:
                intent=new Intent(PerfilActivity.this,ContenidoActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("correo",correo);
                intent.putExtra("categoria","restaurantes");
//                intent.putExtra("correo",correo);
                startActivity(intent);
                //finish();//Para que no se devulva.
                break;
            case R.id.mhoteles:
                intent=new Intent(PerfilActivity.this,ContenidoActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("correo",correo);
                intent.putExtra("categoria","hoteles");
//                intent.putExtra("correo",correo);
                startActivity(intent);
                //finish();
                break;
            case R.id.mturisticos:
                intent=new Intent(PerfilActivity.this,ContenidoActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("correo",correo);
                intent.putExtra("categoria","turisticos");
//                intent.putExtra("correo",correo);
                startActivity(intent);
                //finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
