package itsj.e.bd_sqlite;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abrirActivities(View v) {
        switch (v.getId() ){

            case R.id.btn_agregar:
                Intent a = new Intent(this, ActivityAltas.class);
                startActivity(a); break;
            case R.id.btn_eliminar:
                Intent b = new Intent(this, ActivityBajas.class);
                startActivity(b); break;
            case R.id.btn_modificar:
                Intent c = new Intent(this, ActivityCambios.class);
                startActivity(c); break;
            case R.id.btn_buscar:
                Intent d = new Intent(this, ActivityConsultas.class);
                startActivity(d); break;
        }
    }


}
