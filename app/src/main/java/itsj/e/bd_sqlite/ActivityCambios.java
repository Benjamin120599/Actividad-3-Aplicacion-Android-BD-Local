package itsj.e.bd_sqlite;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.os.Bundle;
import android.support.v4.view.KeyEventDispatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import Controlador.AlumnoDAO;
import Modelo.Alumno;

public class ActivityCambios extends Activity {

    Spinner spinnerEdadC, spinnerCarreraC, spinnerSemestreC;
    EditText txtBuscarC, txtNumControlC, txtNombreC, txtPrimerApC, txtSegundoApC;
    Button btnSearchC, btnModificarC, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambios);

        txtBuscarC = findViewById(R.id.txt_buscarC);
        txtNumControlC = findViewById(R.id.txt_numControlC);
        txtNombreC = findViewById(R.id.txt_nombreC);
        txtPrimerApC = findViewById(R.id.txt_primerApC);
        txtSegundoApC = findViewById(R.id.txt_segundoApC);
        btnSearchC = findViewById(R.id.btn_searchC);
        btnModificarC = findViewById(R.id.btn_modificar);
        btnCancelar = findViewById(R.id.btn_cancelarC);

        spinnerEdadC = findViewById(R.id.spinner_edadC);
        ArrayAdapter<CharSequence> adapterEdad = ArrayAdapter.createFromResource(this, R.array.Edad, android.R.layout.simple_spinner_item);
        adapterEdad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEdadC.setAdapter(adapterEdad);

        spinnerSemestreC = findViewById(R.id.spinner_semestreC);
        ArrayAdapter<CharSequence> adapterSemestre = ArrayAdapter.createFromResource(this, R.array.Semestre, android.R.layout.simple_spinner_item);
        adapterSemestre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSemestreC.setAdapter(adapterSemestre);

        spinnerCarreraC = findViewById(R.id.spinner_carreraC);
        ArrayAdapter<CharSequence> adapterCarrera = ArrayAdapter.createFromResource(this, R.array.Carrera, android.R.layout.simple_spinner_item);
        adapterCarrera.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCarreraC.setAdapter(adapterCarrera);
    }

    public void buscar(View v) {

        if(txtBuscarC.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese un numero de control", Toast.LENGTH_SHORT).show();
        } else {

            AlumnoDAO adao = new AlumnoDAO(this);
            Alumno newAl  = adao.buscarAlumno(txtBuscarC.getText().toString());
            txtNumControlC.setText(newAl.getNumControl());
            txtNombreC.setText(newAl.getNombre());
            txtPrimerApC.setText(newAl.getPrimerAp());
            txtSegundoApC.setText(newAl.getSegundoAp());

            int posicion = 0;
            for (int i = 0; i < spinnerEdadC.getCount(); i++) {
                if (spinnerEdadC.getItemAtPosition(i).toString().equalsIgnoreCase(String.valueOf(newAl.getEdad())) ) {
                    posicion = i;
                }
            }
            spinnerEdadC.setSelection(posicion);
            posicion = 0;
            for (int i = 0; i < spinnerSemestreC.getCount(); i++) {
                if (spinnerSemestreC.getItemAtPosition(i).toString().equalsIgnoreCase(String.valueOf(newAl.getSemestre())) ) {
                    posicion = i;
                }
            }
            spinnerSemestreC.setSelection(posicion);
            posicion = 0;
            for (int i = 0; i < spinnerCarreraC.getCount(); i++) {
                if (spinnerCarreraC.getItemAtPosition(i).toString().equalsIgnoreCase(newAl.getCarrera()) ) {
                    posicion = i;
                }
            }
            spinnerCarreraC.setSelection(posicion);

        }
    }


    public void OnClick(View v) {
        final AlumnoDAO adao = new AlumnoDAO(this);
        Toast toast;

        if(txtBuscarC.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese un numero de control", Toast.LENGTH_SHORT).show();
        } else {

            if(!txtNumControlC.getText().toString().equals("") || !txtNombreC.getText().toString().equals("") || !txtPrimerApC.getText().toString().equals("") || !txtSegundoApC.getText().toString().equals("") || spinnerEdadC.getSelectedItemPosition() != 0 || spinnerSemestreC.getSelectedItemPosition() != 0 || spinnerCarreraC.getSelectedItemPosition() != 0) {

                Alumno a = new Alumno();
                a.setNumControl(txtNumControlC.getText().toString());
                a.setNombre(txtNombreC.getText().toString());
                a.setPrimerAp(txtPrimerApC.getText().toString());
                a.setSegundoAp(txtSegundoApC.getText().toString());
                a.setEdad(Byte.parseByte(spinnerEdadC.getSelectedItem().toString()));
                a.setSemestre(Byte.parseByte(spinnerSemestreC.getSelectedItem().toString()));
                a.setCarrera(spinnerCarreraC.getSelectedItem().toString());

                if( adao.modificarAlumno(a) == true) {
                    toast = Toast.makeText(getApplicationContext(), "Alumno modificado", Toast.LENGTH_SHORT);
                    toast.show();
                    txtNumControlC.setText("");
                    txtBuscarC.setText("");
                    txtNombreC.setText("");
                    txtPrimerApC.setText("");
                    txtSegundoApC.setText("");
                    spinnerEdadC.setSelection(0);
                    spinnerSemestreC.setSelection(0);
                    spinnerCarreraC.setSelection(0);
                } else {
                    toast = Toast.makeText(getApplicationContext(), "No se pudo modificar el registro", Toast.LENGTH_LONG);
                    toast.show();
                }

            } else {
                toast = Toast.makeText(getApplicationContext(), "Aún hay campos vacíos", Toast.LENGTH_SHORT);
            }
        }

    }

}
