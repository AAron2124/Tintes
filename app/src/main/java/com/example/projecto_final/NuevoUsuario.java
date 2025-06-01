package com.example.projecto_final;

import android.content.ContentValues;
import android.content.Intent; // Necesario para Intent
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NuevoUsuario extends AppCompatActivity {

    EditText etNUsuario, etNPassword;
    Button btnRegistrar, btnRegresarLogin; // Cambiado btnVerUsuarios a btnRegresarLogin
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_usuario);

        etNUsuario = findViewById(R.id.etNUsuario2);
        etNPassword = findViewById(R.id.etNPassword2);
        btnRegistrar = findViewById(R.id.btnNuevoU2);
        btnRegresarLogin = findViewById(R.id.btnRegresarLogin); // Enlazamos el nuevo botón

        dbHelper = new DBHelper(this);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuevoUsuario = etNUsuario.getText().toString().trim();
                String nuevaPassword = etNPassword.getText().toString().trim();

                if (nuevoUsuario.isEmpty() || nuevaPassword.isEmpty()) {
                    Toast.makeText(NuevoUsuario.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                long resultado = dbHelper.insertarUsuario(nuevoUsuario, nuevaPassword);

                if (resultado != -1) {
                    Toast.makeText(NuevoUsuario.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                    etNUsuario.setText("");
                    etNPassword.setText("");
                } else {
                    Toast.makeText(NuevoUsuario.this, "Error al registrar usuario. El nombre de usuario podría ya existir.", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Listener para el nuevo botón "Regresar al Login"
        btnRegresarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia LoginActivity y finaliza la actividad actual
                startActivity(new Intent(NuevoUsuario.this, LoginActivity.class));
                finish(); // Cierra NuevoUsuario para que no quede en la pila de actividades
            }
        });
    }
}