// DBHelper.java
package com.example.projecto_final;

import android.content.ContentValues; // Importar ContentValues
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List; // Importar List

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tintes.db";
    private static final int DATABASE_VERSION = 1;


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

    // Constantes de la tabla Inventario
    public static final String TABLE_INVENTARIO = "Inventario";
    public static final String COL_ID_INV = "id";
    public static final String COL_NOMBRE_INV = "nombre";
    public static final String COL_CODIGO_COLOR_INV = "codigoColor";
    public static final String COL_CANTIDAD = "cantidad";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

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
                COL_NOMBRE_USUARIO + " TEXT NOT NULL UNIQUE," + // UNIQUE para evitar usuarios duplicados
                COL_PASSWORD + " TEXT NOT NULL);";

        //Tabla Inventario
        String createTableInventario = "CREATE TABLE " + TABLE_INVENTARIO + " (" +
                COL_ID_INV + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NOMBRE_INV + " TEXT NOT NULL," +
                COL_CODIGO_COLOR_INV + " TEXT NOT NULL," +
                COL_CANTIDAD + " INTEGER NOT NULL);";

        // Crear todas las tablas
        db.execSQL(createTableInventario);
        db.execSQL(createTableClientes);
        db.execSQL(createTableUsuarios);


        // Inventario inicial
        db.execSQL("INSERT INTO " + TABLE_INVENTARIO + " (" + COL_NOMBRE_INV + ", " + COL_CODIGO_COLOR_INV + ", " + COL_CANTIDAD + ") VALUES ('Cobre', '#B87333', 10);");
        db.execSQL("INSERT INTO " + TABLE_INVENTARIO + " (" + COL_NOMBRE_INV + ", " + COL_CODIGO_COLOR_INV + ", " + COL_CANTIDAD + ") VALUES ('Castaño Avellana', '#8E735B', 15);");
        db.execSQL("INSERT INTO " + TABLE_INVENTARIO + " (" + COL_NOMBRE_INV + ", " + COL_CODIGO_COLOR_INV + ", " + COL_CANTIDAD + ") VALUES ('Miel', '#DDB67D', 20);");
        db.execSQL("INSERT INTO " + TABLE_INVENTARIO + " (" + COL_NOMBRE_INV + ", " + COL_CODIGO_COLOR_INV + ", " + COL_CANTIDAD + ") VALUES ('Dorado', '#FFD700', 12);");
        db.execSQL("INSERT INTO " + TABLE_INVENTARIO + " (" + COL_NOMBRE_INV + ", " + COL_CODIGO_COLOR_INV + ", " + COL_CANTIDAD + ") VALUES ('Castaño Ceniza', '#6D5843', 8);");
        db.execSQL("INSERT INTO " + TABLE_INVENTARIO + " (" + COL_NOMBRE_INV + ", " + COL_CODIGO_COLOR_INV + ", " + COL_CANTIDAD + ") VALUES ('Rubio Platinado', '#E5E4E2', 25);");
        db.execSQL("INSERT INTO " + TABLE_INVENTARIO + " (" + COL_NOMBRE_INV + ", " + COL_CODIGO_COLOR_INV + ", " + COL_CANTIDAD + ") VALUES ('Chocolate Frío', '#4B3621', 18);");
        db.execSQL("INSERT INTO " + TABLE_INVENTARIO + " (" + COL_NOMBRE_INV + ", " + COL_CODIGO_COLOR_INV + ", " + COL_CANTIDAD + ") VALUES ('Negro Azulado', '#0D1B2A', 5);");
        db.execSQL("INSERT INTO " + TABLE_INVENTARIO + " (" + COL_NOMBRE_INV + ", " + COL_CODIGO_COLOR_INV + ", " + COL_CANTIDAD + ") VALUES ('Chocolate Medio', '#7B3F00', 22);");
        db.execSQL("INSERT INTO " + TABLE_INVENTARIO + " (" + COL_NOMBRE_INV + ", " + COL_CODIGO_COLOR_INV + ", " + COL_CANTIDAD + ") VALUES ('Castaño Neutro', '#7E5C3C', 16);");
        db.execSQL("INSERT INTO " + TABLE_INVENTARIO + " (" + COL_NOMBRE_INV + ", " + COL_CODIGO_COLOR_INV + ", " + COL_CANTIDAD + ") VALUES ('Rubio Beige', '#F5F5DC', 30);");

        //Insertar Usuarios Base
        db.execSQL("INSERT INTO " + TABLE_USUARIOS + " (nombre_usuario, password) VALUES ('admin', '1234');");
        db.execSQL("INSERT INTO " + TABLE_USUARIOS + " (nombre_usuario, password) VALUES ('usuario1', 'abcd');");
        db.execSQL("INSERT INTO " + TABLE_USUARIOS + " (nombre_usuario, password) VALUES ('cliente_demo', 'demo123');");

        //Insertar Clientes
        db.execSQL("INSERT INTO " + TABLE_CLIENTES + " (nombre, telefono, fechaAplicacion, tonoTinte, sugerencias, complemento, decolorante, fechaRetoque) " +
                "VALUES ('Ana López', '5551234567', '2025-05-01', '9.0', 'Evitar tonos cálidos', 'Matizante Plata', 'Sí', '2025-06-01');");

        db.execSQL("INSERT INTO " + TABLE_CLIENTES + " (nombre, telefono, fechaAplicacion, tonoTinte, sugerencias, complemento, decolorante, fechaRetoque) " +
                "VALUES ('Carlos Rivera', '5559876543', '2025-04-15', '5.0', 'Agregar brillo', 'Ampolleta de brillo', 'No', '2025-06-15');");

        db.execSQL("INSERT INTO " + TABLE_CLIENTES + " (nombre, telefono, fechaAplicacion, tonoTinte, sugerencias, complemento, decolorante, fechaRetoque) " +
                "VALUES ('Lucía Torres', '5551112233', '2025-03-20', '7.0', 'Mantener tonos fríos', 'Mascarilla Hidratante', 'Sí', '2025-06-20');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        onCreate(db);
    }

    // Método para insertar un nuevo usuario
    public long insertarUsuario(String nombreUsuario, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NOMBRE_USUARIO, nombreUsuario);
        values.put(COL_PASSWORD, password);

        // insert() devuelve el ID de la nueva fila, o -1 si hubo un error
        long newRowId = db.insert(TABLE_USUARIOS, null, values);
        db.close();
        return newRowId;
    }


    // Métodos existentes para Cliente (sin cambios)
    public void insertarCliente(Cliente cliente) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO " + TABLE_CLIENTES + " (" +
                COL_NOMBRE + ", " + COL_TELEFONO + ", " + COL_FECHA_APLICACION + ", " +
                COL_TONO_TINTE + ", " + COL_SUGERENCIAS + ", " + COL_COMPLEMENTO + ", " +
                COL_DECOLORANTE + ", " + COL_FECHA_RETOQUE + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        db.execSQL(sql, new Object[]{
                cliente.getNombre(),
                cliente.getTelefono(),
                cliente.getFechaAplicacion(),
                cliente.getTonoTinte(),
                cliente.getSugerencias(),
                cliente.getComplemento(),
                cliente.getDecolorante(),
                cliente.getFechaRetoque()
        });
        db.close();
    }

    public void actualizarCliente(Cliente cliente) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "UPDATE " + TABLE_CLIENTES + " SET " +
                COL_NOMBRE + "=?, " + COL_TELEFONO + "=?, " + COL_FECHA_APLICACION + "=?, " +
                COL_TONO_TINTE + "=?, " + COL_SUGERENCIAS + "=?, " + COL_COMPLEMENTO + "=?, " +
                COL_DECOLORANTE + "=?, " + COL_FECHA_RETOQUE + "=? WHERE " + COL_ID_CLIENTE + "=?";
        db.execSQL(sql, new Object[]{
                cliente.getNombre(),
                cliente.getTelefono(),
                cliente.getFechaAplicacion(),
                cliente.getTonoTinte(),
                cliente.getSugerencias(),
                cliente.getComplemento(),
                cliente.getDecolorante(),
                cliente.getFechaRetoque(),
                cliente.getId()
        });
        db.close();
    }

    public boolean eliminarCliente(int id) {
        SQLiteDatabase db = getWritableDatabase();
        int filasEliminadas = db.delete(TABLE_CLIENTES, COL_ID_CLIENTE + "=?", new String[]{String.valueOf(id)});
        db.close();
        return filasEliminadas > 0;
    }

    public Cliente obtenerClientePorId(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CLIENTES + " WHERE " + COL_ID_CLIENTE + "=?",
                new String[]{String.valueOf(id)});
        Cliente cliente = null;

        if (cursor.moveToFirst()) {
            cliente = new Cliente();
            cliente.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID_CLIENTE)));
            cliente.setNombre(cursor.getString(cursor.getColumnIndexOrThrow(COL_NOMBRE)));
            cliente.setTelefono(cursor.getString(cursor.getColumnIndexOrThrow(COL_TELEFONO)));
            cliente.setFechaAplicacion(cursor.getString(cursor.getColumnIndexOrThrow(COL_FECHA_APLICACION)));
            cliente.setTonoTinte(cursor.getString(cursor.getColumnIndexOrThrow(COL_TONO_TINTE)));
            cliente.setSugerencias(cursor.getString(cursor.getColumnIndexOrThrow(COL_SUGERENCIAS)));
            cliente.setComplemento(cursor.getString(cursor.getColumnIndexOrThrow(COL_COMPLEMENTO)));
            cliente.setDecolorante(cursor.getString(cursor.getColumnIndexOrThrow(COL_DECOLORANTE)));
            cliente.setFechaRetoque(cursor.getString(cursor.getColumnIndexOrThrow(COL_FECHA_RETOQUE)));
        }

        cursor.close();
        db.close();
        return cliente;
    }

    public ArrayList<Cliente> obtenerTodosLosClientes() {
        ArrayList<Cliente> lista = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CLIENTES + " ORDER BY " + COL_NOMBRE, null);

        while (cursor.moveToNext()) {
            Cliente cliente = new Cliente();
            cliente.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID_CLIENTE)));
            cliente.setNombre(cursor.getString(cursor.getColumnIndexOrThrow(COL_NOMBRE)));
            cliente.setTelefono(cursor.getString(cursor.getColumnIndexOrThrow(COL_TELEFONO)));
            cliente.setFechaAplicacion(cursor.getString(cursor.getColumnIndexOrThrow(COL_FECHA_APLICACION)));
            cliente.setTonoTinte(cursor.getString(cursor.getColumnIndexOrThrow(COL_TONO_TINTE)));
            cliente.setSugerencias(cursor.getString(cursor.getColumnIndexOrThrow(COL_SUGERENCIAS)));
            cliente.setComplemento(cursor.getString(cursor.getColumnIndexOrThrow(COL_COMPLEMENTO)));
            cliente.setDecolorante(cursor.getString(cursor.getColumnIndexOrThrow(COL_DECOLORANTE)));
            cliente.setFechaRetoque(cursor.getString(cursor.getColumnIndexOrThrow(COL_FECHA_RETOQUE)));
            lista.add(cliente);
        }

        cursor.close();
        db.close();
        return lista;
    }
    // Métodos para Inventario

    // Insertar un item en Inventario
    public void insertarInventarioItem(String nombre, String codigoColor, int cantidad) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO " + TABLE_INVENTARIO + " (" +
                COL_NOMBRE_INV + ", " + COL_CODIGO_COLOR_INV + ", " + COL_CANTIDAD + ") VALUES (?, ?, ?)";
        db.execSQL(sql, new Object[]{nombre, codigoColor, cantidad});
        db.close();
    }

    // Actualizar un item del Inventario por id
    public void actualizarInventarioItem(int id, String nombre, String codigoColor, int cantidad) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "UPDATE " + TABLE_INVENTARIO + " SET " +
                COL_NOMBRE_INV + "=?, " + COL_CODIGO_COLOR_INV + "=?, " + COL_CANTIDAD + "=? WHERE " + COL_ID_INV + "=?";
        db.execSQL(sql, new Object[]{nombre, codigoColor, cantidad, id});
        db.close();
    }

    // Eliminar un item del Inventario por id
    public boolean eliminarInventarioItem(int id) {
        SQLiteDatabase db = getWritableDatabase();
        int filasEliminadas = db.delete(TABLE_INVENTARIO, COL_ID_INV + "=?", new String[]{String.valueOf(id)});
        db.close();
        return filasEliminadas > 0;
    }

    // Obtener un item del Inventario por id
    public InventarioItem obtenerInventarioItemPorId(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_INVENTARIO + " WHERE " + COL_ID_INV + "=?", new String[]{String.valueOf(id)});
        InventarioItem item = null;

        if (cursor.moveToFirst()) {
            item = new InventarioItem();
            item.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID_INV)));
            item.setNombre(cursor.getString(cursor.getColumnIndexOrThrow(COL_NOMBRE_INV)));
            item.setCodigoColor(cursor.getString(cursor.getColumnIndexOrThrow(COL_CODIGO_COLOR_INV)));
            item.setCantidad(cursor.getInt(cursor.getColumnIndexOrThrow(COL_CANTIDAD)));
        }

        cursor.close();
        db.close();
        return item;
    }

    // Obtener todos los items del Inventario
    public ArrayList<InventarioItem> obtenerTodosLosInventarioItems() {
        ArrayList<InventarioItem> lista = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_INVENTARIO + " ORDER BY " + COL_NOMBRE_INV, null);

        while (cursor.moveToNext()) {
            InventarioItem item = new InventarioItem();
            item.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID_INV)));
            item.setNombre(cursor.getString(cursor.getColumnIndexOrThrow(COL_NOMBRE_INV)));
            item.setCodigoColor(cursor.getString(cursor.getColumnIndexOrThrow(COL_CODIGO_COLOR_INV)));
            item.setCantidad(cursor.getInt(cursor.getColumnIndexOrThrow(COL_CANTIDAD)));
            lista.add(item);
        }

        cursor.close();
        db.close();
        return lista;
    }
}
