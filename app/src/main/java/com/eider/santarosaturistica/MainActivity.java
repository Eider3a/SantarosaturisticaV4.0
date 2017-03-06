package com.eider.santarosaturistica;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String username,correo;
    Button binformacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toast.makeText(this,"onCreate",Toast.LENGTH_SHORT).show();
        Bundle extras=getIntent().getExtras();
        username=extras.getString("username");
        correo=extras.getString("correo");

        binformacion=(Button)findViewById(R.id.binformacion);
        binformacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.santarosadeosos-antioquia.gov.co/Paginas/default.aspx");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


    }

    @Override
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
                intent=new Intent(MainActivity.this,PerfilActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("correo",correo);
                startActivity(intent);
                //finish();//Para que no se devulva.
                break;
            case R.id.mcerrar:
                intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
