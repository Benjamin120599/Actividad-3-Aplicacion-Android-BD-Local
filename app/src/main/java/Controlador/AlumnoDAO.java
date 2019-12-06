package Controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

import Modelo.Alumno;

public class AlumnoDAO extends SQLiteOpenHelper {

    // Constantes que definiran la estructura de la BD
    private static final int VERSION_BD = 1;
    private static final String NOMBRE_BD = "Escuela";
    private static final String TABLA_ALUMNOS = "Alumnos";
    private static final String CAMPO_NUM_CONTROL = "NumControl";
    private static final String CAMPO_NOMBRE = "Nombre";
    private static final String CAMPO_PRIMER_AP = "PrimerAp";
    private static final String CAMPO_SEGUNDO_AP = "SegundoAp";
    private static final String CAMPO_EDAD = "Edad";
    private static final String CAMPO_SEMESTRE = "Semestre";
    private static final String CAMPO_CARRERA = "Carrera";

    public static final String CREAR_TABLA_ALUMNOS = "CREATE TABLE "+TABLA_ALUMNOS+"("+CAMPO_NUM_CONTROL+" TEXT, "+CAMPO_NOMBRE+" TEXT, " +
            ""+CAMPO_PRIMER_AP+" TEXT, "+CAMPO_SEGUNDO_AP+" TEXT, "+CAMPO_EDAD+" INTEGER, "+CAMPO_SEMESTRE+" INTEGER, "+CAMPO_CARRERA+" TEXT);";



    public AlumnoDAO(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_ALUMNOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Para actualizar ESQUEMA de la BD
    }

    // ================================== Métodos para ABCC ==================================

    public boolean agregarAlumno(Alumno a) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cV = new ContentValues();
        cV.put(CAMPO_NUM_CONTROL, a.getNumControl());
        cV.put(CAMPO_NOMBRE, a.getNombre());
        cV.put(CAMPO_PRIMER_AP, a.getPrimerAp());
        cV.put(CAMPO_SEGUNDO_AP, a.getSegundoAp());
        cV.put(CAMPO_EDAD, a.getEdad());
        cV.put(CAMPO_SEMESTRE, a.getSemestre());
        cV.put(CAMPO_CARRERA, a.getCarrera());

        long res = db.insert(TABLA_ALUMNOS, null, cV);

        return (res == -1) ? false : true;

    }

    public boolean eliminarAlumno(String nc) {
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(TABLA_ALUMNOS, "numControl = '"+nc+"'", null);
        return (res == -1) ? false : true;
    }

    public boolean modificarAlumno(Alumno a) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cV = new ContentValues();
        cV.put(CAMPO_NUM_CONTROL, a.getNumControl());
        cV.put(CAMPO_NOMBRE, a.getNombre());
        cV.put(CAMPO_PRIMER_AP, a.getPrimerAp());
        cV.put(CAMPO_SEGUNDO_AP, a.getSegundoAp());
        cV.put(CAMPO_EDAD, a.getEdad());
        cV.put(CAMPO_SEMESTRE, a.getSemestre());
        cV.put(CAMPO_CARRERA, a.getCarrera());

        long res = db.update(TABLA_ALUMNOS, cV, "numControl = '"+a.getNumControl()+"'", null);;

        return (res == -1) ? false : true;
    }

    public ArrayList<Alumno> obtenerTodosLosAlumnos() {
        ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
        SQLiteDatabase db = this.getWritableDatabase();
        String consulta = "SELECT * FROM "+TABLA_ALUMNOS+";";

        Cursor cursor = db.rawQuery(consulta, null);

        if(cursor.moveToFirst()) {

            do {
                listaAlumnos.add( new Alumno(cursor.getString(0),
                                             cursor.getString(1),
                                             cursor.getString(2),
                                             cursor.getString(3),
                                             (byte) cursor.getInt(4),
                                             (byte) cursor.getInt(5),
                                             cursor.getString(6)));
            } while(cursor.moveToNext());

        } else {

        }

        return listaAlumnos;
    }

    public ArrayList<Alumno> obtenerTodosLosAlumnos(String filtro, String campo) {
        ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
        SQLiteDatabase db = this.getWritableDatabase();
        String consulta = "SELECT * FROM "+TABLA_ALUMNOS+" WHERE "+campo+" = '"+filtro+"';";

        Cursor cursor = db.rawQuery(consulta, null);

        if(cursor.moveToFirst()) {

            do {
                listaAlumnos.add( new Alumno(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        (byte) cursor.getInt(4),
                        (byte) cursor.getInt(5),
                        cursor.getString(6)));
            } while(cursor.moveToNext());

        }

        return listaAlumnos;
    }

    public Alumno buscarAlumno(String filtro) {
        Alumno alumno = null;
        SQLiteDatabase db = this.getWritableDatabase();
        String consulta = "SELECT * FROM "+TABLA_ALUMNOS+" WHERE numControl = '"+filtro+"';";

        Cursor cursor = db.rawQuery(consulta, null);

        if(cursor.moveToFirst()) {

            do {
                alumno = new Alumno( cursor.getString(0), cursor.getString(1), cursor.getString(2),
                                     cursor.getString(3), (byte) cursor.getInt(4), (byte) cursor.getInt(5),
                                     cursor.getString(6));
            } while(cursor.moveToNext());

        }
        return alumno;
    }

}
