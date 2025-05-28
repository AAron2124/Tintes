package com.example.projecto_final;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CatalogoActivity extends AppCompatActivity {

    private TableLayout tableColores;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);  // El XML que me diste

        tableColores = findViewById(R.id.tableColores);

        cargarColoresDesdeBD();
    }

    private void cargarColoresDesdeBD() {
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT " + DBHelper.COL_NOMBRE_TONO + ", " + DBHelper.COL_COLOR_CODE +
                        " FROM " + DBHelper.TABLE_TONOS,
                null);

        // Variables para organizar en filas de 3 columnas
        TableRow currentRow = null;
        int columnCount = 0;

        if (cursor.moveToFirst()) {
            do {
                String nombre = cursor.getString(0);
                String codigoColor = cursor.getString(1);

                // Crear nueva fila si es necesario
                if (columnCount == 0) {
                    currentRow = new TableRow(this);
                }

                // Crear el LinearLayout que contendrá el rectángulo y el texto
                LinearLayout cellLayout = new LinearLayout(this);
                cellLayout.setOrientation(LinearLayout.VERTICAL);
                cellLayout.setGravity(Gravity.CENTER);
                LinearLayout.LayoutParams cellParams = new LinearLayout.LayoutParams(0,
                        LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
                cellLayout.setLayoutParams(cellParams);
                cellLayout.setPadding(6,6,6,6);

                // Crear la vista para el color (rectángulo)
                View colorView = new View(this);
                LinearLayout.LayoutParams colorParams = new LinearLayout.LayoutParams(
                        dpToPx(80), dpToPx(50));
                colorView.setLayoutParams(colorParams);

                // Convertir codigoColor (que viene tipo 'negro_1_0') a color hexadecimal o color válido
                // Aquí debes adaptar tu sistema, por ahora pongo un switch sencillo:
                int colorHex = mapCodigoColorToHex(codigoColor);
                colorView.setBackgroundColor(colorHex);

                // Texto con nombre
                TextView tvNombre = new TextView(this);
                tvNombre.setText(nombre);
                tvNombre.setTextSize(12f);
                LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                tvParams.topMargin = dpToPx(4);
                tvNombre.setLayoutParams(tvParams);

                // Agregar a cellLayout
                cellLayout.addView(colorView);
                cellLayout.addView(tvNombre);

                // Agregar cellLayout a la fila
                currentRow.addView(cellLayout);

                columnCount++;

                if (columnCount == 3) {
                    // Añadir fila completa a la tabla
                    tableColores.addView(currentRow);
                    columnCount = 0;
                }

            } while (cursor.moveToNext());
        }

        // Si la última fila tiene menos de 3 columnas, agregarla igual
        if (columnCount > 0 && currentRow != null) {
            tableColores.addView(currentRow);
        }

        cursor.close();
        db.close();
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    // Mapea los códigos de color (de la BD) a colores hexadecimales válidos para Android
    private int mapCodigoColorToHex(String codigoColor) {
        switch (codigoColor) {
            case "negro_1_0": return Color.parseColor("#1B1B1B");
            case "castano_oscuro_7_2": return Color.parseColor("#4B3621");
            case "castano_claro_8": return Color.parseColor("#A9746E");
            case "rubio_9_0": return Color.parseColor("#DCD8A8");
            case "rubio_dorado_7_3": return Color.parseColor("#FFD700");
            case "rubio_nacarado_7_2": return Color.parseColor("#E6D8AD");
            case "rubio_oscuro": return Color.parseColor("#D1A054");
            case "castano_profundo_5_0": return Color.parseColor("#5B3A29");
            case "pelirrojo_cobrizo": return Color.parseColor("#B55239");
            default: return Color.GRAY;  // color por defecto si no coincide
        }
    }
}
