package com.eider.santarosaturistica;

//import android.app.FragmentManager;
//import android.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

//        Manejo de fragments dinamicos.
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        hotel1fragment fragment=new hotel1fragment();
        ft.add(android.R.id.content,fragment).commit();//Quitar support.v4 en el demo_activity

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();//Siempre hay que volver a colocar la transacion si se cambia de metodo.

        switch (id){
            case R.id.mfrag1:
                hotel1fragment frag=new hotel1fragment();
                ft.replace(android.R.id.content,frag).commit();
                break;

            case R.id.mfrag2:
                hotel2fragment frag2=new hotel2fragment();
                ft.replace(android.R.id.content,frag2).commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
