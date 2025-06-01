package com.example.projecto_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnInventario, btnClientes, btnColorimetria, btnGuia, btnCatalogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInventario = findViewById(R.id.btnInventario);
        btnClientes = findViewById(R.id.btnClientes);
        btnColorimetria = findViewById(R.id.btnColorimetria);
        btnGuia = findViewById(R.id.btnGuia);
        btnCatalogo = findViewById(R.id.btnCatalogo);



        btnClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir Activity Clientes
                Intent intent = new Intent(MainActivity.this, ClientesActivity.class);
                startActivity(intent);
            }
        });


             btnColorimetria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
 //               // Abrir Activity Colorimetria (por definir)
                Intent intent = new Intent(MainActivity.this, ColorimetriaActivity.class);
               startActivity(intent);
           }
       });


        btnCatalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir Activity Catalogo
                Intent intent = new Intent(MainActivity.this, CatalogoActivity.class);
                startActivity(intent);
            }
        });

        btnInventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InventarioActivity.class);
                startActivity(intent);
            }
        });

    }
}
