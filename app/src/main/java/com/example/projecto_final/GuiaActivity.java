package com.example.projecto_final;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class GuiaActivity extends AppCompatActivity {
    private void showDialog(String titulo, String mensaje) {
        new AlertDialog.Builder(this)
                .setTitle(titulo)
                .setMessage(mensaje)
                .setPositiveButton("Cerrar", null)
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guia);

        LinearLayout btnPreparar = findViewById(R.id.btnPreparar);
        LinearLayout btnAplicar = findViewById(R.id.btnAplicar);
        LinearLayout btnEsperar = findViewById(R.id.btnEsperar);
        LinearLayout btnEnjuagar = findViewById(R.id.btnEnjuagar);

        btnPreparar.setOnClickListener(v -> showDialog("Preparar",
                "Paso 1: Mezcla bien el contenido del tinte y el revelador en un recipiente no metálico.\n\n" +
                        "Paso 2: Usa una brocha o pincel para remover hasta lograr una consistencia uniforme."));

        btnAplicar.setOnClickListener(v -> showDialog("Aplicar",
                "Paso 1: Divide tu cabello en secciones para facilitar la aplicación.\n\n" +
                        "Paso 2: Con una brocha, aplica el tinte de manera uniforme desde la raíz hasta las puntas."));

        btnEsperar.setOnClickListener(v -> showDialog("Esperar",
                "Paso 1: Deja actuar el tinte durante el tiempo recomendado en el empaque (normalmente de 20 a 40 minutos).\n\n" +
                        "Paso 2: No cubras el cabello con plástico ni uses calor a menos que las instrucciones lo indiquen."));

        btnEnjuagar.setOnClickListener(v -> showDialog("Enjuagar",
                "Paso 1: Enjuaga con abundante agua tibia hasta que esta salga clara.\n\n" +
                        "Paso 2: Luego, aplica el tratamiento post-color si viene incluido y déjalo actuar según las instrucciones."));
    }
}
