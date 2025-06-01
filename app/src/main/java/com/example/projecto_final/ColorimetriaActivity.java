package com.example.projecto_final;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class ColorimetriaActivity extends AppCompatActivity {

    private Button btnAnalizar, btnVolver;
    private TextView tvResultadoPerfil;
    private LinearLayout contenedorTonos;
    private RadioGroup grupoVenas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colorimetria);


        btnAnalizar = findViewById(R.id.btnAnalizar);
        btnVolver = findViewById(R.id.btnVolverMenu);
        tvResultadoPerfil = findViewById(R.id.tvResultadoPerfil);
        contenedorTonos = findViewById(R.id.contenedorTonos);
        grupoVenas = findViewById(R.id.grupoVenas);


        btnAnalizar.setOnClickListener(v -> analizarPerfil());

        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(ColorimetriaActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private boolean findCheck(String texto) {
        ViewGroup layout = findViewById(android.R.id.content);
        return buscarCheckBox(layout, texto);
    }

    private boolean buscarCheckBox(ViewGroup parent, String texto) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            if (child instanceof CheckBox) {
                CheckBox cb = (CheckBox) child;
                if (cb.getText().toString().equalsIgnoreCase(texto) && cb.isChecked()) {
                    return true;
                }
            } else if (child instanceof ViewGroup) {
                if (buscarCheckBox((ViewGroup) child, texto)) return true;
            }
        }
        return false;
    }

    private void analizarPerfil() {
        String perfil = "neutro";
        String[] tonos = {};
        int[] colores = {};

        boolean ojosFrio = findCheck("Azul") || findCheck("Gris") || findCheck("Negro");
        boolean ojosCalido = findCheck("Marrón") || findCheck("Ámbar") || findCheck("Verde") || findCheck("Hazel");

        boolean peloFrio = findCheck("Negro") || findCheck("Rubio Claro") || findCheck("Gris/Blanco");
        boolean peloCalido = findCheck("Castaño Claro") || findCheck("Rubio Oscuro") || findCheck("Rojo");

        int idVena = grupoVenas.getCheckedRadioButtonId();
        boolean venaFria = idVena == R.id.venasAzules;
        boolean venaCalida = idVena == R.id.venasVerdes;

        int scoreFrio = (ojosFrio ? 1 : 0) + (peloFrio ? 1 : 0) + (venaFria ? 1 : 0);
        int scoreCalido = (ojosCalido ? 1 : 0) + (peloCalido ? 1 : 0) + (venaCalida ? 1 : 0);

        if (scoreCalido > scoreFrio) {
            perfil = "cálido";
            tonos = new String[]{"Cobre", "Castaño Avellana", "Miel", "Dorado"};
            colores = new int[]{Color.parseColor("#B87333"), Color.parseColor("#7B3F00"),
                    Color.parseColor("#FFD700"), Color.parseColor("#FFD700")};
        } else if (scoreFrio > scoreCalido) {
            perfil = "frío";
            tonos = new String[]{"Castaño Ceniza", "Rubio Platinado", "Chocolate Frío", "Negro Azulado"};
            colores = new int[]{Color.parseColor("#A9A9A9"), Color.parseColor("#E5E4E2"),
                    Color.parseColor("#3B2F2F"), Color.parseColor("#00008B")};
        } else {
            tonos = new String[]{"Chocolate Medio", "Castaño Neutro", "Rubio Beige"};
            colores = new int[]{Color.parseColor("#7B3F00"), Color.parseColor("#8B5A2B"), Color.parseColor("#F5F5DC")};
        }

        tvResultadoPerfil.setText("Colorimetría\nTu perfil es " + perfil);
        tvResultadoPerfil.setVisibility(View.VISIBLE);

        contenedorTonos.removeAllViews();
        contenedorTonos.setVisibility(View.VISIBLE);

        for (int i = 0; i < tonos.length; i++) {
            TextView tv = new TextView(this);
            tv.setText(tonos[i]);
            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tv.setTextColor(Color.BLACK);
            tv.setBackgroundColor(colores[i]);
            tv.setPadding(32, 24, 32, 24);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(12, 12, 12, 12);
            tv.setLayoutParams(params);
            contenedorTonos.addView(tv);
        }
    }
}

