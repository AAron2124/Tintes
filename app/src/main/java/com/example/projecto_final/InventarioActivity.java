package com.example.projecto_final;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class InventarioActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private ListView listViewInventario;
    private Button btnAgregar;

    private ArrayList<InventarioItem> listaInventario;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> listaNombres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

        dbHelper = new DBHelper(this);
        listViewInventario = findViewById(R.id.listViewInventario);
        btnAgregar = findViewById(R.id.btnAgregarInventario);

        cargarLista();

        btnAgregar.setOnClickListener(v -> mostrarDialogoAgregar());

        listViewInventario.setOnItemClickListener((parent, view, position, id) -> {
            InventarioItem itemSeleccionado = listaInventario.get(position);
            mostrarDialogoOpciones(itemSeleccionado);
        });
    }

    private void cargarLista() {
        listaInventario = dbHelper.obtenerTodosLosInventarioItems();
        listaNombres = new ArrayList<>();
        for (InventarioItem item : listaInventario) {
            listaNombres.add(item.getNombre() + " - Cantidad: " + item.getCantidad());
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaNombres);
        listViewInventario.setAdapter(adapter);
    }

    private void mostrarDialogoAgregar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Agregar nuevo ítem");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        int margen = (int) (16 * getResources().getDisplayMetrics().density);
        layout.setPadding(margen, margen, margen, margen);

        final EditText inputNombre = new EditText(this);
        inputNombre.setHint("Nombre");
        layout.addView(inputNombre);

        final EditText inputCodigoColor = new EditText(this);
        inputCodigoColor.setHint("Código Color (hex, ej. #FF0000)");
        inputCodigoColor.setInputType(InputType.TYPE_CLASS_TEXT);
        layout.addView(inputCodigoColor);

        final EditText inputCantidad = new EditText(this);
        inputCantidad.setHint("Cantidad");
        inputCantidad.setInputType(InputType.TYPE_CLASS_NUMBER);
        layout.addView(inputCantidad);

        builder.setView(layout);

        builder.setPositiveButton("Agregar", (dialog, which) -> {
            String nombre = inputNombre.getText().toString().trim();
            String codigoColor = inputCodigoColor.getText().toString().trim();
            String cantidadStr = inputCantidad.getText().toString().trim();

            if (nombre.isEmpty() || codigoColor.isEmpty() || cantidadStr.isEmpty()) {
                Toast.makeText(this, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            int cantidad;
            try {
                cantidad = Integer.parseInt(cantidadStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Cantidad inválida", Toast.LENGTH_SHORT).show();
                return;
            }

            dbHelper.insertarInventarioItem(nombre, codigoColor, cantidad);
            cargarLista();
            Toast.makeText(this, "Ítem agregado", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }

    private void mostrarDialogoOpciones(InventarioItem item) {
        String[] opciones = {"Editar", "Eliminar"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(item.getNombre());
        builder.setItems(opciones, (dialog, which) -> {
            if (which == 0) {
                mostrarDialogoEditar(item);
            } else if (which == 1) {
                mostrarDialogoEliminar(item);
            }
        });
        builder.show();
    }

    private void mostrarDialogoEditar(InventarioItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Editar ítem");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        int margen = (int) (16 * getResources().getDisplayMetrics().density);
        layout.setPadding(margen, margen, margen, margen);

        final EditText inputNombre = new EditText(this);
        inputNombre.setHint("Nombre");
        inputNombre.setText(item.getNombre());
        layout.addView(inputNombre);

        final EditText inputCodigoColor = new EditText(this);
        inputCodigoColor.setHint("Código Color (hex, ej. #FF0000)");
        inputCodigoColor.setInputType(InputType.TYPE_CLASS_TEXT);
        inputCodigoColor.setText(item.getCodigoColor());
        layout.addView(inputCodigoColor);

        final EditText inputCantidad = new EditText(this);
        inputCantidad.setHint("Cantidad");
        inputCantidad.setInputType(InputType.TYPE_CLASS_NUMBER);
        inputCantidad.setText(String.valueOf(item.getCantidad()));
        layout.addView(inputCantidad);

        builder.setView(layout);

        builder.setPositiveButton("Guardar", (dialog, which) -> {
            String nombre = inputNombre.getText().toString().trim();
            String codigoColor = inputCodigoColor.getText().toString().trim();
            String cantidadStr = inputCantidad.getText().toString().trim();

            if (nombre.isEmpty() || codigoColor.isEmpty() || cantidadStr.isEmpty()) {
                Toast.makeText(this, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            int cantidad;
            try {
                cantidad = Integer.parseInt(cantidadStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Cantidad inválida", Toast.LENGTH_SHORT).show();
                return;
            }

            dbHelper.actualizarInventarioItem(item.getId(), nombre, codigoColor, cantidad);
            cargarLista();
            Toast.makeText(this, "Ítem actualizado", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }

    private void mostrarDialogoEliminar(InventarioItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar ítem");
        builder.setMessage("¿Seguro que quieres eliminar " + item.getNombre() + "?");
        builder.setPositiveButton("Sí", (dialog, which) -> {
            boolean eliminado = dbHelper.eliminarInventarioItem(item.getId());
            if (eliminado) {
                cargarLista();
                Toast.makeText(this, "Ítem eliminado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No se pudo eliminar el ítem", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }
}
