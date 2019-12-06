package Controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import Modelo.Usuarios;

public class UsuarioDAO extends SQLiteOpenHelper {

    private static final int VERSION_BD = 1;
    private static final String NOMBRE_BD = "Escuela";
    private static final String TABLA_USUARIOS = "Users";
    private static final String CAMPO_USUARIO = "Usuario";
    private static final String CAMPO_CONTRASEÑA = "Contraseña";

    private static final String CREAR_TABLA_USUARIO = "CREATE TABLE "+TABLA_USUARIOS+"("+CAMPO_USUARIO+" TEXT PRIMARY KEY, "+CAMPO_CONTRASEÑA+" TEXT);";

    public UsuarioDAO(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_USUARIO);
        db.execSQL(AlumnoDAO.CREAR_TABLA_ALUMNOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //para actualizaar ESQUEMA de la BD
    }


    public boolean agregarUsuario(Usuarios a) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cV = new ContentValues();
        cV.put(CAMPO_USUARIO, a.getUsuario());
        cV.put(CAMPO_CONTRASEÑA, a.getContraseña());

        long res = db.insert(TABLA_USUARIOS, null, cV);

        return (res == -1) ? false : true;
    }

    public Usuarios buscarUsuario(String filtro) {
        Usuarios user = null;
        SQLiteDatabase db = this.getWritableDatabase();
        //"SELECT * FROM Users WHERE Usuario = '1';"
        String consulta = "SELECT * FROM "+TABLA_USUARIOS+" WHERE "+CAMPO_USUARIO+" = '"+filtro+"';";

        Cursor cursor = db.rawQuery(consulta, null);

        if(cursor.moveToFirst()) {

            do {
                user = new Usuarios(cursor.getString(0), cursor.getString(1) );

            } while(cursor.moveToNext());

        }
        return user;
    }



}
