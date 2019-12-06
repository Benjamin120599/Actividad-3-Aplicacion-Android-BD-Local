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

public class ActivityAltas extends Activity {

    Spinner spinnerEdad, spinnerSemestre, spinnerCarrera;
    EditText txtNumControl, txtNombre, txtPrimerAp, txtSegundoAp;
    Button botonAñadir, botonCancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altas);

        spinnerEdad = findViewById(R.id.spinnerEdad);
        ArrayAdapter<CharSequence> adapterEdad = ArrayAdapter.createFromResource(this, R.array.Edad, android.R.layout.simple_spinner_item);
        adapterEdad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEdad.setAdapter(adapterEdad);

        spinnerSemestre = findViewById(R.id.spinnerSemestre);
        ArrayAdapter<CharSequence> adapterSemestre = ArrayAdapter.createFromResource(this, R.array.Semestre, android.R.layout.simple_spinner_item);
        adapterSemestre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSemestre.setAdapter(adapterSemestre);

        spinnerCarrera = findViewById(R.id.spinnerCarrera);
        ArrayAdapter<CharSequence> adapterCarrera = ArrayAdapter.createFromResource(this, R.array.Carrera, android.R.layout.simple_spinner_item);
        adapterCarrera.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCarrera.setAdapter(adapterCarrera);

        txtNumControl = findViewById(R.id.txtNumControl);
        txtNombre = findViewById(R.id.txtNombre);
        txtPrimerAp = findViewById(R.id.txtPrimerApellido);
        txtSegundoAp = findViewById(R.id.txtSegundoApellido);

        botonAñadir = findViewById(R.id.botonAñadir);
        botonCancelar = findViewById(R.id.botonCancelar);

    }

    public void OnClick(View v) {
        final AlumnoDAO adao = new AlumnoDAO(this);
        Toast toast;
        
        if( txtNumControl.getText().equals("") || txtNombre.getText().equals("") || txtPrimerAp.getText().equals("") || txtSegundoAp.getText().equals("") || spinnerEdad.getSelectedItemPosition() == 0 || spinnerCarrera.getSelectedItemPosition() == 0 || spinnerSemestre.getSelectedItemPosition() == 0){
            Toast.makeText(this, "Aún hay campos vacíos", Toast.LENGTH_SHORT).show();
        } else {
            Alumno a = new Alumno();
            a.setNumControl(txtNumControl.getText().toString());
            a.setNombre(txtNombre.getText().toString());
            a.setPrimerAp(txtPrimerAp.getText().toString());
            a.setSegundoAp(txtSegundoAp.getText().toString());
            a.setEdad(Byte.parseByte(spinnerEdad.getSelectedItem().toString()));
            a.setSemestre(Byte.parseByte(spinnerSemestre.getSelectedItem().toString()));
            a.setCarrera(spinnerCarrera.getSelectedItem().toString());
            
            if(adao.buscarAlumno(a.getNumControl()) == null ) {
                if( adao.agregarAlumno(a) == true) {

                    txtNumControl.setText("");
                    txtNombre.setText("");
                    txtPrimerAp.setText("");
                    txtSegundoAp.setText("");
                    spinnerEdad.setSelection(0);
                    spinnerSemestre.setSelection(0);
                    spinnerCarrera.setSelection(0);
                    toast = Toast.makeText(getApplicationContext(), "Alumno añadido", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    toast = Toast.makeText(getApplicationContext(), "No se pudo añadir el registro", Toast.LENGTH_LONG);
                    toast.show();
                }
            } else {
                Toast.makeText(this, "Ya existe este usuario", Toast.LENGTH_SHORT).show();
            }
            

            
        }
        

    }

}

