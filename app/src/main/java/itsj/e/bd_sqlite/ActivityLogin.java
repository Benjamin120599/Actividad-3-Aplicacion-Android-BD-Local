package itsj.e.bd_sqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Controlador.AlumnoDAO;
import Controlador.UsuarioDAO;
import Modelo.Usuarios;

public class ActivityLogin extends Activity {

    Button btnCrear, btnAcceder;
    EditText usuario, contraseña;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnAcceder = findViewById(R.id.btn_acceder);
        btnCrear = findViewById(R.id.btn_registrar);
        usuario = findViewById(R.id.txt_user);
        contraseña = findViewById(R.id.txt_pass);


        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioDAO udao = new UsuarioDAO(getApplicationContext());
                String campoUsuario = usuario.getText().toString();
                String campoContraseña = contraseña.getText().toString();

                if(campoUsuario.equals("") || campoContraseña.equals("")){
                    Toast.makeText(getApplicationContext(), "Aún hay campos vacíos", Toast.LENGTH_SHORT).show();
                } else {
                    Usuarios us = new Usuarios(campoUsuario, campoContraseña);

                    if ( udao.buscarUsuario(us.getUsuario()) == null ) {
                        Toast.makeText(getApplicationContext(), "El usuario no existe", Toast.LENGTH_SHORT).show();
                    } else {
                        if (us.getContraseña().equals(udao.buscarUsuario(usuario.getText().toString()).getContraseña())) {
                            //Abre la ventana principal
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        });


        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioDAO udao = new UsuarioDAO(getApplicationContext());
                String campoUsuario = usuario.getText().toString();
                String campoContraseña = contraseña.getText().toString();

                if(campoUsuario.equals("") || campoContraseña.equals("")){
                    Toast.makeText(getApplicationContext(), "Aún hay campos vacíos", Toast.LENGTH_SHORT).show();
                } else {
                    Usuarios us = new Usuarios(campoUsuario, campoContraseña);

                    if(    udao.buscarUsuario(us.getUsuario()) != null ) {
                        Toast.makeText(getApplicationContext(), "El usuario ya existe", Toast.LENGTH_SHORT).show();
                    } else {

                        if(udao.agregarUsuario(us) == true ) {
                            Toast.makeText(getApplicationContext(), "Se añadió un nuevo usaurio", Toast.LENGTH_SHORT).show();
                            //usuario.setText("");
                            //contraseña.setText("");
                        } else {
                            Toast.makeText(getApplicationContext(), "El usuario no se pudo crear", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        });

    }

}
