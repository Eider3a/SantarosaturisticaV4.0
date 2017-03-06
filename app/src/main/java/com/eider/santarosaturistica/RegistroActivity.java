package com.eider.santarosaturistica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {

    EditText eRusername,eRcontrasenia,eRrepcontrasenia,eRemail;
    Button bRiniciar,bRcancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        eRusername=(EditText)findViewById(R.id.eRusername);
        eRcontrasenia=(EditText)findViewById(R.id.eRcontrasenia);
        eRrepcontrasenia=(EditText)findViewById(R.id.eRrepcontrasenia);
        eRemail=(EditText)findViewById(R.id.eRemail);
        bRiniciar=(Button)findViewById(R.id.bRiniciar);
        bRcancelar=(Button)findViewById(R.id.bRcancelar);

        bRiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Debe enviar los datos.
                //Puedo enviar cualquier tipo de datos.
                //Estoy enviando datos desde una activity a otra.

                //Validacion de los datos, que no esten vacios.
                String text1,text2,text3,text4;
                text1=eRusername.getText().toString();
                text2=eRcontrasenia.getText().toString();
                text3=eRrepcontrasenia.getText().toString();
                text4=eRemail.getText().toString();
                if(text1.isEmpty() || text2.isEmpty() || text3.isEmpty() || text4.isEmpty()){
                    Toast.makeText(RegistroActivity.this,getResources().getText(R.string.falta_campos),Toast.LENGTH_SHORT).show();
                    return;
                }


                //Verificar y validar los datos.
                //Intent intent=new Intent(RegistroActivity.this,LoginActivity.class);
                //por el codigo
                //validacion de ambas contraseñas.
                if((eRcontrasenia.getText().toString().equals(eRrepcontrasenia.getText().toString()))){//Si son iguales.
                    Intent intent=new Intent();//No tengo que decir para donde voy.
                    intent.putExtra("username",eRusername.getText().toString());
                    intent.putExtra("contrasenia",eRcontrasenia.getText().toString());
                    intent.putExtra("correo",eRemail.getText().toString());
//                startActivity(intent);//ya no inicio una actividad sino que repsondo
                    setResult(RESULT_OK,intent);
                    finish();//PAra que envie la respuesta.
                }

                else {
                    Toast.makeText(RegistroActivity.this,getResources().getText(R.string.password_malo),Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });

        bRcancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(RegistroActivity.this,LoginActivity.class);
                Intent intent=new Intent();
//                startActivity(intent); //Ya no llama una actividad sino que solo debe responder,
                //El sabe a quien responder de acuerdo al codigo.
                setResult(RESULT_CANCELED,intent);//Responde a la actividad.
                finish();
            }
        });
    }
}
