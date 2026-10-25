package com.example.gustto;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private RestauranteAdapter adapter;
    private EditText buscador;
    private Spinner categorias;
    private TextView contador, titulo;
    private boolean mostrarSoloFavoritos = false;
    private Button botonInicio, botonFavoritos;

    @Override protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        buscador = findViewById(R.id.buscador);
        categorias = findViewById(R.id.categorias);
        contador = findViewById(R.id.contador);
        titulo = findViewById(R.id.titulo_pagina);
        botonInicio = findViewById(R.id.btn_inicio);
        botonFavoritos = findViewById(R.id.btn_favoritos);

        RecyclerView lista = findViewById(R.id.lista_restaurantes);
        lista.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RestauranteAdapter(new RestauranteAdapter.Eventos() {
            @Override public void alAbrir(Restaurante r) {
                Intent i = new Intent(MainActivity.this, DetalleActivity.class);
                i.putExtra("id", r.getId());
                startActivity(i);
            }
            @Override public void alFavorito(Restaurante r) {
                DatosRestaurantes.alternarFavorito(r.getId());
                Toast.makeText(MainActivity.this, "Favoritos actualizados", Toast.LENGTH_SHORT).show();
                filtrar();
            }
        });
        lista.setAdapter(adapter);

        String[] opciones = {"Todas las categorías", "Chilena", "Italiana", "Japonesa",
            "Cafetería", "Parrilla", "Pastelería", "Mexicana", "Peruana"};
        ArrayAdapter<String> sp = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, opciones);
        sp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorias.setAdapter(sp);
        categorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> p, View v, int position, long id) { filtrar(); }
            @Override public void onNothingSelected(AdapterView<?> p) { filtrar(); }
        });
        buscador.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int st, int c, int a) { }
            @Override public void onTextChanged(CharSequence s, int st, int before, int count) { filtrar(); }
            @Override public void afterTextChanged(Editable e) { }
        });
        botonInicio.setOnClickListener(v -> { mostrarSoloFavoritos = false; filtrar(); });
        botonFavoritos.setOnClickListener(v -> { mostrarSoloFavoritos = true; filtrar(); });
        findViewById(R.id.btn_mapa).setOnClickListener(v ->
            startActivity(new Intent(this, MapaActivity.class)));
        filtrar();
    }

    @Override protected void onResume() {
        super.onResume();
        if (adapter != null) filtrar();
    }

    private void filtrar() {
        if (adapter == null || buscador == null || categorias.getSelectedItem() == null) return;
        String texto = buscador.getText().toString().trim().toLowerCase(Locale.ROOT);
        String categoria = categorias.getSelectedItem().toString();
        ArrayList<Restaurante> encontrados = new ArrayList<>();
        for (Restaurante r : DatosRestaurantes.restaurantes) {
            boolean coincideTexto = r.getNombre().toLowerCase(Locale.ROOT).contains(texto)
                || r.getDescripcion().toLowerCase(Locale.ROOT).contains(texto)
                || r.getCategoria().toLowerCase(Locale.ROOT).contains(texto);
            boolean coincideCategoria = categoria.equals("Todas las categorías")
                || r.getCategoria().equals(categoria);
            boolean coincideFavorito = !mostrarSoloFavoritos || DatosRestaurantes.esFavorito(r.getId());
            if (coincideTexto && coincideCategoria && coincideFavorito) encontrados.add(r);
        }
        adapter.actualizar(encontrados);
        titulo.setText(mostrarSoloFavoritos ? "Mis favoritos" : "Explora restaurantes");
        contador.setText(encontrados.size() + " restaurantes · datos de demostración");
        botonInicio.setAlpha(mostrarSoloFavoritos ? 0.6f : 1f);
        botonFavoritos.setAlpha(mostrarSoloFavoritos ? 1f : 0.6f);
        findViewById(R.id.texto_vacio).setVisibility(encontrados.isEmpty() ? View.VISIBLE : View.GONE);
    }
}
