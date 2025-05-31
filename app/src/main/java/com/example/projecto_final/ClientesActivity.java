package com.example.projecto_final;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ClientesActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_FORMULARIO = 1;

    ListView listViewClientes;
    Button btnAgregarCliente;

    DBHelper dbHelper;
    ArrayList<Cliente> listaClientes;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        listViewClientes = findViewById(R.id.listViewClientes);
        btnAgregarCliente = findViewById(R.id.btnAgregarCliente);

        dbHelper = new DBHelper(this);

        cargarClientes();

        btnAgregarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Abrir formulario para agregar cliente (sin id)
                Intent intent = new Intent(ClientesActivity.this, FormularioClienteActivity.class);
                startActivityForResult(intent, REQUEST_CODE_FORMULARIO);
            }
        });

        // Click corto: mostrar diálogo con datos y botón editar
        listViewClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Cliente cliente = listaClientes.get(position);
                mostrarDetalleCliente(cliente);
            }
        });

        // Click largo: mostrar diálogo para eliminar cliente
        listViewClientes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                Cliente cliente = listaClientes.get(position);
                mostrarDialogoEliminar(cliente.getId(), cliente.getNombre());
                return true; // consume el evento
            }
        });
    }

    private void cargarClientes() {
        listaClientes = dbHelper.obtenerTodosLosClientes();

        ArrayList<String> nombres = new ArrayList<>();
        for (Cliente c : listaClientes) {
            nombres.add(c.getNombre() + " - " + c.getTelefono());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nombres);
        listViewClientes.setAdapter(adapter);
    }

    private void mostrarDetalleCliente(final Cliente cliente) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(cliente.getNombre());

        String mensaje = "Teléfono: " + cliente.getTelefono() + "\n"
                + "Fecha Aplicación: " + cliente.getFechaAplicacion() + "\n"
                + "Tono Tinte: " + cliente.getTonoTinte() + "\n"
                + "Sugerencias: " + cliente.getSugerencias() + "\n"
                + "Complemento: " + cliente.getComplemento() + "\n"
                + "Decolorante: " + cliente.getDecolorante() + "\n"
                + "Fecha Retoque: " + cliente.getFechaRetoque();

        builder.setMessage(mensaje);

        builder.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(ClientesActivity.this, FormularioClienteActivity.class);
                intent.putExtra("idCliente", cliente.getId());
                startActivityForResult(intent, REQUEST_CODE_FORMULARIO);
            }
        });

        builder.setNegativeButton("Cerrar", null);

        builder.show();
    }

    private void mostrarDialogoEliminar(final int idCliente, String nombreCliente) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar cliente")
                .setMessage("¿Seguro que deseas eliminar a " + nombreCliente + "?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        boolean eliminado = dbHelper.eliminarCliente(idCliente);
                        if (eliminado) {
                            Toast.makeText(ClientesActivity.this, "Cliente eliminado", Toast.LENGTH_SHORT).show();
                            cargarClientes(); // refrescar lista
                        } else {
                            Toast.makeText(ClientesActivity.this, "Error al eliminar", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_FORMULARIO && resultCode == RESULT_OK) {
            // Al regresar del formulario, refrescar lista
            cargarClientes();
        }
    }
}
