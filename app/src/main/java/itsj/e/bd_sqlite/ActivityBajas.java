package itsj.e.bd_sqlite;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import Controlador.AlumnoDAO;
import Modelo.Alumno;

public class ActivityBajas extends Activity {

    Spinner spinnerEdadB, spinnerCarreraB, spinnerSemestreB;
    EditText txtBuscarB, txtNumControlB, txtNombreB, txtPrimerApB, txtSegundoApB;
    Button btnSearchB, btnEliminarB, btnCancelarB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bajas);

        txtBuscarB = findViewById(R.id.txt_buscarC);
        txtNumControlB = findViewById(R.id.txt_numControlB);
        txtNombreB = findViewById(R.id.txt_nombreB);
        txtPrimerApB = findViewById(R.id.txt_primerApB);
        txtSegundoApB = findViewById(R.id.txt_segundoApB);
        btnSearchB = findViewById(R.id.btn_searchC);
        btnEliminarB = findViewById(R.id.btn_eliminarB);
        btnCancelarB = findViewById(R.id.btn_cancelarB);

        spinnerEdadB = findViewById(R.id.spinner_edadB);
        ArrayAdapter<CharSequence> adapterEdad = ArrayAdapter.createFromResource(this, R.array.Edad, android.R.layout.simple_spinner_item);
        adapterEdad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEdadB.setAdapter(adapterEdad);

        spinnerSemestreB = findViewById(R.id.spinner_semestreB);
        ArrayAdapter<CharSequence> adapterSemestre = ArrayAdapter.createFromResource(this, R.array.Semestre, android.R.layout.simple_spinner_item);
        adapterSemestre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSemestreB.setAdapter(adapterSemestre);

        spinnerCarreraB = findViewById(R.id.spinner_carreraB);
        ArrayAdapter<CharSequence> adapterCarrera = ArrayAdapter.createFromResource(this, R.array.Carrera, android.R.layout.simple_spinner_item);
        adapterCarrera.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCarreraB.setAdapter(adapterCarrera);
    }

    public void buscar(View v) {

        if(txtBuscarB.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese un numero de control", Toast.LENGTH_SHORT).show();
        } else {

            AlumnoDAO adao = new AlumnoDAO(this);
            Alumno newAl  = adao.buscarAlumno(txtBuscarB.getText().toString());
            txtNumControlB.setText(newAl.getNumControl());
            txtNombreB.setText(newAl.getNombre());
            txtPrimerApB.setText(newAl.getPrimerAp());
            txtSegundoApB.setText(newAl.getSegundoAp());

            int posicion = 0;
            for (int i = 0; i < spinnerEdadB.getCount(); i++) {
                if (spinnerEdadB.getItemAtPosition(i).toString().equalsIgnoreCase(String.valueOf(newAl.getEdad())) ) {
                    posicion = i;
                }
            }
            spinnerEdadB.setSelection(posicion);
            posicion = 0;
            for (int i = 0; i < spinnerSemestreB.getCount(); i++) {
                if (spinnerSemestreB.getItemAtPosition(i).toString().equalsIgnoreCase(String.valueOf(newAl.getSemestre())) ) {
                    posicion = i;
                }
            }
            spinnerSemestreB.setSelection(posicion);
            posicion = 0;
            for (int i = 0; i < spinnerCarreraB.getCount(); i++) {
                if (spinnerCarreraB.getItemAtPosition(i).toString().equalsIgnoreCase(newAl.getCarrera()) ) {
                    posicion = i;
                }
            }
            spinnerCarreraB.setSelection(posicion);

        }

    }

    public void OnClick(View v) {
        AlumnoDAO adao = new AlumnoDAO(this);
        if(txtBuscarB.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese un numero de control", Toast.LENGTH_SHORT).show();
        } else {
            if(adao.eliminarAlumno(txtBuscarB.getText().toString()) == true ) {
                Toast.makeText(this, "Se elimino un registro", Toast.LENGTH_SHORT).show();
                txtNumControlB.setText("");
                txtBuscarB.setText("");
                txtNombreB.setText("");
                txtPrimerApB.setText("");
                txtSegundoApB.setText("");
                spinnerEdadB.setSelection(0);
                spinnerSemestreB.setSelection(0);
                spinnerCarreraB.setSelection(0);

            } else {
                Toast.makeText(this, "No se pudo eliminar el registro", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
