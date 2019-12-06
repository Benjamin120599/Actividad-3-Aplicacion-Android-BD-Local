package itsj.e.bd_sqlite;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Controlador.AlumnoDAO;
import Modelo.Alumno;

public class ActivityConsultas extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    AlumnoDAO adao = new AlumnoDAO(this);
    Spinner spinnerFiltro;
    EditText txtBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        spinnerFiltro = findViewById(R.id.spinnerFiltro);
        ArrayAdapter<CharSequence> adapterCarrera = ArrayAdapter.createFromResource(this, R.array.Filtros, android.R.layout.simple_spinner_item);
        adapterCarrera.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFiltro.setAdapter(adapterCarrera);
        txtBuscar = findViewById(R.id.txt_buscarC);

        ArrayList<Alumno> listaAlumnos = new ArrayList();
        listaAlumnos = adao.obtenerTodosLosAlumnos();

        setAdapter(listaAlumnos);
    }

    public void restablecer(View v) {
        ArrayList<Alumno> listaAlumnos = new ArrayList();
        listaAlumnos = adao.obtenerTodosLosAlumnos();

        setAdapter(listaAlumnos);
    }

    public void filtrarBusqueda(View v) {

        AlumnoDAO adao = new AlumnoDAO(this);

        if(spinnerFiltro.getSelectedItemPosition() == 0 ){
            Toast.makeText(this, "Selecciona un campo de búsqueda", Toast.LENGTH_SHORT).show();
        } else {

            if(txtBuscar.getText().toString().equals("")) {
                Toast.makeText(this, "Aún hay campos vacíos", Toast.LENGTH_SHORT).show();
            } else {
                setAdapter(adao.obtenerTodosLosAlumnos(txtBuscar.getText().toString(), spinnerFiltro.getSelectedItem().toString() ));
            }

        }

    }


    public void setAdapter(ArrayList<Alumno> listaAlumnos){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //ArrayList<Alumno> listaAlumnos = new ArrayList();
        //for(int i=0; i < adao.obtenerTodosLosAlumnos().size(); i++) {
        //    listaAlumnos = adao.obtenerTodosLosAlumnos();
        //}
        //listaAlumnos = adao.obtenerTodosLosAlumnos();

        mAdapter = new MyAdapter(listaAlumnos);
        recyclerView.setAdapter(mAdapter);
    }

}


 class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Alumno> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public MyViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<Alumno> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.textview_registros, parent, false);
        TextView tv = v.findViewById(R.id.textview_datos);
        MyViewHolder vh = new MyViewHolder(tv);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.textView.setText(mDataset[position]);
        holder.textView.setText(mDataset.get(position).toString());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}



