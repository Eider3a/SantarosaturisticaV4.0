package com.eider.santarosaturistica;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText eusername,econtrasenia;
    Button biniciar,bregistrese;
    String username,password,correo;

    //PReferencias compartidas.
    SharedPreferences prefs;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Para el manejo de las preferencias.
        prefs=getSharedPreferences("Mispreferencias",MODE_PRIVATE);
        editor=prefs.edit();

        username=prefs.getString("username","noname");
        password=prefs.getString("password","nopass");
        correo=prefs.getString("correo","nocorreo");

        Log.d("login",String.valueOf(prefs.getInt("login",-1)==1));
        Log.d("username",username);
        Log.d("password",password);
        Log.d("correo",correo);


        if(prefs.getInt("login",-1)==1){//si login vale 1 hay alguien logeado, si vale -1 no hay nadie logeado.
            Intent intent = new Intent(LoginActivity.this, DraweActivity.class);//Se va para el mainactivity.
            intent.putExtra("username",username);
            intent.putExtra("correo",correo);
            startActivity(intent);
            finish();
        }

//        Bundle extras=getIntent().getExtras();
//
//        username=extras.getString("username");//getString debido a que envia datos tipos string.
//        password=extras.getString("contrasenia");
//        correo=extras.getString("correo");

        eusername=(EditText)findViewById(R.id.eusername);
        econtrasenia=(EditText)findViewById(R.id.econtrasenia);
        biniciar=(Button)findViewById(R.id.biniciar);
        bregistrese=(Button)findViewById(R.id.bregistrar);

        biniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eusername.getText().toString().isEmpty() || econtrasenia.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this,getResources().getText(R.string.llenar_todos_campos),Toast.LENGTH_SHORT).show();
                    return;//Sale del metodo.
                }

                //Verificar que los datos que se colocaron en el login sean los mismos que hay en el registro.
                if (eusername.getText().toString().equals(username) && econtrasenia.getText().toString().equals(password)){

                    editor.putInt("login",1);//Si se logea coloca el login en 1, la proxima vez pasa derecho no hay que logearse.
                    editor.commit();//Siempre hay que hacer un commit.

                    //Si se cierra la aplicacion se mantienen los datos de las preferencias.
                    Intent intent = new Intent(LoginActivity.this, DraweActivity.class);
                    intent.putExtra("username",username);
                    intent.putExtra("correo",correo);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(LoginActivity.this,getResources().getText(R.string.usuario_contraseña_incorrectos),Toast.LENGTH_SHORT).show();
                }

            }
        });

        bregistrese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegistroActivity.class);
                startActivityForResult(intent,1234);//MAndo el intent y un codigo
                //El codigo sirve para identificar quien lanza la actividad, y para saber a donde debe volver.
            }
        });
    }

    @Override
    //Metodo para procesar la respuesta de la otra actividad(registro).
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1234 && resultCode==RESULT_OK){
//            Bundle extras=getIntent().getExtras();

            username=data.getExtras().getString("username");//getString debido a que envia datos tipos string.
            password=data.getExtras().getString("contrasenia");
            correo=data.getExtras().getString("correo");

            //Guardo lo que llega del registro en preferencias compartidas.
            //Hay que hacerlo en todas opciones de cerrar sesion.
            editor.putString("username",username);//para que coloque la preferencia login en -1 y no pase derecho al logearse.
            editor.putString("password",password);//para que coloque la preferencia login en -1 y no pase derecho al logearse.
            editor.putString("correo",correo);//para que coloque la preferencia login en -1 y no pase derecho al logearse.
            editor.commit();//Siempre hay que hacer un commit.

            //Lo siguiente es para debugear.
            Log.d("username",username);//Hacer monitoreo de variables.
            Log.d("password",password);//Hacer monitoreo de variables.
            Log.d("email",correo);//Hacer monitoreo de variables.
        }
        else{
            if(requestCode==1234 && resultCode==RESULT_CANCELED){
                Toast.makeText(LoginActivity.this,getResources().getText(R.string.error_registro),Toast.LENGTH_SHORT).show();


            }
        }
    }
}
