package com.example.gustto;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetalleActivity extends AppCompatActivity {
    private Restaurante restaurante;
    private Button favorito;
    @Override protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_detalle);
        restaurante = DatosRestaurantes.buscar(getIntent().getIntExtra("id", -1));
        if (restaurante == null) { finish(); return; }
        ((TextView)findViewById(R.id.det_nombre)).setText(restaurante.getNombre());
        ((TextView)findViewById(R.id.det_categoria)).setText(restaurante.getCategoria());
        ((TextView)findViewById(R.id.det_descripcion)).setText(restaurante.getDescripcion());
        ((TextView)findViewById(R.id.det_direccion)).setText(restaurante.getDireccion());
        ((TextView)findViewById(R.id.det_horario)).setText(restaurante.getHorario());
        favorito = findViewById(R.id.det_favorito);
        actualizarFavorito();
        favorito.setOnClickListener(v -> {
            DatosRestaurantes.alternarFavorito(restaurante.getId());
            actualizarFavorito();
        });
        findViewById(R.id.det_mapa).setOnClickListener(v -> {
            Intent i = new Intent(this, MapaActivity.class);
            i.putExtra("id", restaurante.getId());
            startActivity(i);
        });
        findViewById(R.id.det_volver).setOnClickListener(v -> finish());
    }
    private void actualizarFavorito() {
        favorito.setText(DatosRestaurantes.esFavorito(restaurante.getId())
            ? "♥ Quitar de favoritos" : "♡ Agregar a favoritos");
    }
}
