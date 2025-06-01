package com.example.projecto_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class FormularioClienteActivity extends AppCompatActivity {

    private EditText editNombre, editTelefono, editFechaAplicacion, editTonoTinte,
            editSugerencias, editComplemento, editDecolorante, editFechaRetoque;
    private Button btnGuardar;
    private DBHelper dbHelper;
    private int clienteId = -1; // -1 indica nuevo cliente

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cliente);

        // Referencias
        editNombre = findViewById(R.id.editNombre);
        editTelefono = findViewById(R.id.editTelefono);
        editFechaAplicacion = findViewById(R.id.editFechaAplicacion);
        editTonoTinte = findViewById(R.id.editTonoTinte);
        editSugerencias = findViewById(R.id.editSugerencias);
        editComplemento = findViewById(R.id.editComplemento);
        editDecolorante = findViewById(R.id.editDecolorante);
        editFechaRetoque = findViewById(R.id.editFechaRetoque);
        btnGuardar = findViewById(R.id.btnGuardarCliente);

        dbHelper = new DBHelper(this);

        // Ver si estamos editando un cliente existente
        clienteId = getIntent().getIntExtra("idCliente", -1);

        if (clienteId != -1) {
            cargarCliente(clienteId);
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCliente();
            }
        });
    }

    private void cargarCliente(int id) {
        Cliente cliente = dbHelper.obtenerClientePorId(id);
        if (cliente != null) {
            editNombre.setText(cliente.getNombre());
            editTelefono.setText(cliente.getTelefono());
            editFechaAplicacion.setText(cliente.getFechaAplicacion());
            editTonoTinte.setText(cliente.getTonoTinte());
            editSugerencias.setText(cliente.getSugerencias());
            editComplemento.setText(cliente.getComplemento());
            editDecolorante.setText(cliente.getDecolorante());
            editFechaRetoque.setText(cliente.getFechaRetoque());
        }
    }

    private void guardarCliente() {
        String nombre = editNombre.getText().toString().trim();
        String telefono = editTelefono.getText().toString().trim();
        String fechaAplicacion = editFechaAplicacion.getText().toString().trim();
        String tonoTinte = editTonoTinte.getText().toString().trim();
        String sugerencias = editSugerencias.getText().toString().trim();
        String complemento = editComplemento.getText().toString().trim();
        String decolorante = editDecolorante.getText().toString().trim();
        String fechaRetoque = editFechaRetoque.getText().toString().trim();

        if (nombre.isEmpty()) {
            Toast.makeText(this, "El nombre es obligatorio", Toast.LENGTH_SHORT).show();
            return;
        }

        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setTelefono(telefono);
        cliente.setFechaAplicacion(fechaAplicacion);
        cliente.setTonoTinte(tonoTinte);
        cliente.setSugerencias(sugerencias);
        cliente.setComplemento(complemento);
        cliente.setDecolorante(decolorante);
        cliente.setFechaRetoque(fechaRetoque);

        if (clienteId == -1) {
            dbHelper.insertarCliente(cliente);
            Toast.makeText(this, "Cliente registrado", Toast.LENGTH_SHORT).show();
        } else {
            cliente.setId(clienteId);
            dbHelper.actualizarCliente(cliente);
            Toast.makeText(this, "Cliente actualizado", Toast.LENGTH_SHORT).show();
        }

        //  Lógica automática para inventario
        if (!tonoTinte.isEmpty()) {
            if (!dbHelper.existeTinteEnInventario(tonoTinte)) {
                dbHelper.insertarInventarioItem(tonoTinte, "", 0);
            }

            InventarioItem item = dbHelper.obtenerInventarioItemPorNombre(tonoTinte);
            if (item != null) {
                if (item.getCantidad() > 0) {
                    dbHelper.actualizarInventarioItem(item.getId(), item.getNombre(), item.getCodigoColor(), item.getCantidad() - 1);
                } else {
                    Toast.makeText(this, "Advertencia: no hay stock de " + tonoTinte, Toast.LENGTH_SHORT).show();
                }
            }
        }

        setResult(RESULT_OK);
        finish(); // Volver a la lista
    }

}
