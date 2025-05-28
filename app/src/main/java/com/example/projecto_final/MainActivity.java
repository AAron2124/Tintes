package com.example.projecto_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnInventario, btnClientes, btnColorimetria, btnGuia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Asegúrate que el nombre coincide con tu XML

        btnInventario = findViewById(R.id.btnInventario);
        btnClientes = findViewById(R.id.btnClientes);
        btnColorimetria = findViewById(R.id.btnColorimetria);
        btnGuia = findViewById(R.id.btnGuia);

        btnInventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre la actividad de Inventario de tintes
                Intent intent = new Intent(MainActivity.this, InventarioActivity.class);
                startActivity(intent);
            }
        });

        btnClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre la actividad de lista de clientes
                Intent intent = new Intent(MainActivity.this, ListaClientesActivity.class);
                startActivity(intent);
            }
        });

        btnColorimetria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre la actividad de Colorimetría
                Intent intent = new Intent(MainActivity.this, ColorimetriaActivity.class);
                startActivity(intent);
            }
        });

        btnGuia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre la actividad de Guía de Aplicación
                Intent intent = new Intent(MainActivity.this, GuiaAplicacionActivity.class);
                startActivity(intent);
            }
        });
    }
}
