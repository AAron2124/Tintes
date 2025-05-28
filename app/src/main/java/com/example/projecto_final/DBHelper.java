package com.example.projecto_final;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tintes.db";
    private static final int DATABASE_VERSION = 1;

    // Tabla Tonos
    public static final String TABLE_TONOS = "Tonos";
    public static final String COL_ID_TONO = "id";
    public static final String COL_NOMBRE_TONO = "nombre";
    public static final String COL_COLOR_CODE = "codigoColor";

    // Tabla Inventario
    public static final String TABLE_INVENTARIO = "Inventario";
    public static final String COL_ID_INV = "id";
    public static final String COL_ID_TONO_INV = "id_tono";
    public static final String COL_CANTIDAD = "cantidad";

    // Tabla Clientes (NO MODIFICABLE)
    public static final String TABLE_CLIENTES = "Clientes";
    public static final String COL_ID_CLIENTE = "id";
    public static final String COL_NOMBRE = "nombre";
    public static final String COL_TELEFONO = "telefono";
    public static final String COL_FECHA_APLICACION = "fechaAplicacion";
    public static final String COL_TONO_TINTE = "tonoTinte";
    public static final String COL_SUGERENCIAS = "sugerencias";
    public static final String COL_COMPLEMENTO = "complemento";
    public static final String COL_DECOLORANTE = "decolorante";
    public static final String COL_FECHA_RETOQUE = "fechaRetoque";

    // Tabla Usuarios (para login)
    public static final String TABLE_USUARIOS = "Usuarios";
    public static final String COL_ID_USUARIO = "id";
    public static final String COL_NOMBRE_USUARIO = "nombre_usuario";
    public static final String COL_PASSWORD = "password";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tabla Tonos
        String createTableTonos = "CREATE TABLE " + TABLE_TONOS + " (" +
                COL_ID_TONO + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NOMBRE_TONO + " TEXT NOT NULL," +
                COL_COLOR_CODE + " TEXT NOT NULL);";

        // Tabla Inventario
        String createTableInventario = "CREATE TABLE " + TABLE_INVENTARIO + " (" +
                COL_ID_INV + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_ID_TONO_INV + " INTEGER NOT NULL," +
                COL_CANTIDAD + " INTEGER NOT NULL," +
                "FOREIGN KEY(" + COL_ID_TONO_INV + ") REFERENCES " + TABLE_TONOS + "(" + COL_ID_TONO + "));";

        // Tabla Clientes (no modificable)
        String createTableClientes = "CREATE TABLE " + TABLE_CLIENTES + " (" +
                COL_ID_CLIENTE + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NOMBRE + " TEXT NOT NULL," +
                COL_TELEFONO + " TEXT," +
                COL_FECHA_APLICACION + " TEXT," +
                COL_TONO_TINTE + " TEXT," +
                COL_SUGERENCIAS + " TEXT," +
                COL_COMPLEMENTO + " TEXT," +
                COL_DECOLORANTE + " TEXT," +
                COL_FECHA_RETOQUE + " TEXT);";

        // Tabla Usuarios
        String createTableUsuarios = "CREATE TABLE " + TABLE_USUARIOS + " (" +
                COL_ID_USUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NOMBRE_USUARIO + " TEXT NOT NULL UNIQUE," +
                COL_PASSWORD + " TEXT NOT NULL);";

        // Crear todas las tablas
        db.execSQL(createTableTonos);
        db.execSQL(createTableInventario);
        db.execSQL(createTableClientes);
        db.execSQL(createTableUsuarios);

        // Insertar tonos iniciales
        db.execSQL("INSERT INTO " + TABLE_TONOS + " (nombre, codigoColor) VALUES " +
                "('Negro 1.0', 'negro_1_0')," +
                "('Castaño oscuro 7.2', 'castano_oscuro_7_2')," +
                "('Castaño claro 8', 'castano_claro_8')," +
                "('Rubio 9.0', 'rubio_9_0')," +
                "('Rubio dorado 7.3', 'rubio_dorado_7_3')," +
                "('Rubio nacarado 7.2', 'rubio_nacarado_7_2')," +
                "('Rubio oscuro', 'rubio_oscuro')," +
                "('Castaño profundo 5.0', 'castano_profundo_5_0')," +
                "('Pelirrojo cobrizo', 'pelirrojo_cobrizo');");

        // Inventario inicial (vinculado a los tonos anteriores)
        db.execSQL("INSERT INTO " + TABLE_INVENTARIO + " (id_tono, cantidad) VALUES " +
                "(1, 10), (2, 5), (3, 15);");

        // Usuario por defecto
        db.execSQL("INSERT INTO " + TABLE_USUARIOS + " (nombre_usuario, password) VALUES ('admin', '1234');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TONOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        onCreate(db);
    }
}
