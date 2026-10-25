package com.example.gustto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RestauranteAdapter extends RecyclerView.Adapter<RestauranteAdapter.Tarjeta> {
    public interface Eventos {
        void alAbrir(Restaurante restaurante);
        void alFavorito(Restaurante restaurante);
    }
    private final ArrayList<Restaurante> datos = new ArrayList<>();
    private final Eventos eventos;

    public RestauranteAdapter(Eventos eventos) { this.eventos = eventos; }
    public void actualizar(ArrayList<Restaurante> nuevos) {
        datos.clear();
        datos.addAll(nuevos);
        notifyDataSetChanged();
    }
    @NonNull @Override
    public Tarjeta onCreateViewHolder(@NonNull ViewGroup padre, int tipo) {
        View vista = LayoutInflater.from(padre.getContext())
                .inflate(R.layout.item_restaurante, padre, false);
        return new Tarjeta(vista);
    }
    @Override public void onBindViewHolder(@NonNull Tarjeta tarjeta, int posicion) {
        Restaurante r = datos.get(posicion);
        tarjeta.nombre.setText(r.getNombre());
        tarjeta.tipo.setText(r.getCategoria());
        tarjeta.direccion.setText(r.getDireccion());
        tarjeta.favorito.setText(DatosRestaurantes.esFavorito(r.getId()) ? "♥ Guardado" : "♡ Guardar");
        tarjeta.itemView.setOnClickListener(v -> eventos.alAbrir(r));
        tarjeta.detalles.setOnClickListener(v -> eventos.alAbrir(r));
        tarjeta.favorito.setOnClickListener(v -> eventos.alFavorito(r));
    }
    @Override public int getItemCount() { return datos.size(); }

    static class Tarjeta extends RecyclerView.ViewHolder {
        final TextView nombre, tipo, direccion;
        final Button favorito, detalles;
        Tarjeta(View v) {
            super(v);
            nombre = v.findViewById(R.id.txt_nombre);
            tipo = v.findViewById(R.id.txt_tipo);
            direccion = v.findViewById(R.id.txt_direccion);
            favorito = v.findViewById(R.id.btn_favorito);
            detalles = v.findViewById(R.id.btn_detalles);
        }
    }
}
