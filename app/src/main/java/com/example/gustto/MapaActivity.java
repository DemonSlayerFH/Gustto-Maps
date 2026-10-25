package com.example.gustto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_mapa);
        findViewById(R.id.mapa_volver).setOnClickListener(v -> finish());
        if (!BuildConfig.MAPS_CONFIGURADO) {
            findViewById(R.id.mapa_host).setVisibility(View.GONE);
            findViewById(R.id.mapa_sin_clave).setVisibility(View.VISIBLE);
            mostrarEjemplos();
            return;
        }
        ((TextView)findViewById(R.id.mapa_subtitulo)).setText(
            "Marcadores de demostración en La Serena y Coquimbo");
        SupportMapFragment fragmento = SupportMapFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.mapa_host, fragmento).commitNow();
        fragmento.getMapAsync(this::mostrarMapa);
    }

    private void mostrarMapa(GoogleMap mapa) {
        mapa.getUiSettings().setZoomControlsEnabled(true);
        mapa.getUiSettings().setMapToolbarEnabled(false);
        LatLngBounds.Builder limites = new LatLngBounds.Builder();
        for (Restaurante r : DatosRestaurantes.restaurantes) {
            LatLng punto = new LatLng(r.getLatitud(), r.getLongitud());
            limites.include(punto);
            Marker marcador = mapa.addMarker(new MarkerOptions()
                .position(punto).title(r.getNombre()).snippet(r.getCategoria()));
            if (marcador != null) marcador.setTag(r.getId());
        }
        mapa.setOnInfoWindowClickListener(m -> {
            Object id = m.getTag();
            if (id instanceof Integer) abrirDetalle((Integer)id);
        });
        int id = getIntent().getIntExtra("id", -1);
        Restaurante elegido = DatosRestaurantes.buscar(id);
        if (elegido != null) {
            mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(elegido.getLatitud(), elegido.getLongitud()), 15f));
        } else {
            // Ajustar cámara cuando el mapa ya tenga dimensiones.
            findViewById(R.id.mapa_host).post(() -> {
                try { mapa.moveCamera(CameraUpdateFactory.newLatLngBounds(limites.build(), 70)); }
                catch (Exception e) { mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(-29.92, -71.28), 11f)); }
            });
        }
    }

    private void mostrarEjemplos() {
        LinearLayout contenedor = findViewById(R.id.lista_marcadores);
        for (Restaurante r : DatosRestaurantes.restaurantes) {
            Button boton = new Button(this);
            boton.setAllCaps(false);
            boton.setText("📍 " + r.getNombre() + " · " + r.getCategoria());
            boton.setOnClickListener(v -> abrirDetalle(r.getId()));
            contenedor.addView(boton);
        }
    }
    private void abrirDetalle(int id) {
        Intent i = new Intent(this, DetalleActivity.class);
        i.putExtra("id", id);
        startActivity(i);
    }
}
