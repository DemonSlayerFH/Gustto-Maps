package com.example.gustto;

import java.util.ArrayList;
import java.util.HashSet;

/** Fuente de datos en memoria: los valores son FICTICIOS (también las posiciones). */
public final class DatosRestaurantes {
    // ¡Aquí está la ArrayList que pide el ejercicio!
    public static final ArrayList<Restaurante> restaurantes = new ArrayList<>();
    public static final HashSet<Integer> favoritos = new HashSet<>();

    static {
        restaurantes.add(new Restaurante(1, "Costa del Sabor", "Chilena",
            "Pescados, mariscos y platos tradicionales de la costa.",
            "Zona Avenida del Mar, La Serena", "Lunes a domingo · 12:00 a 22:00", -29.9131, -71.2781));
        restaurantes.add(new Restaurante(2, "Trattoria del Valle", "Italiana",
            "Pastas artesanales, pizzas al horno y postres.",
            "Sector Centro, La Serena", "Lunes a sábado · 13:00 a 23:00", -29.9042, -71.2532));
        restaurantes.add(new Restaurante(3, "Nori Sushi", "Japonesa",
            "Rolls, ramen y platos inspirados en la cocina japonesa.",
            "Sector Balmaceda, La Serena", "Martes a domingo · 12:30 a 22:30", -29.9226, -71.2571));
        restaurantes.add(new Restaurante(4, "Café Jardín", "Cafetería",
            "Café de especialidad, desayunos y repostería casera.",
            "Sector El Faro, La Serena", "Lunes a domingo · 09:00 a 20:00", -29.9027, -71.2750));
        restaurantes.add(new Restaurante(5, "La Brasa del Puerto", "Parrilla",
            "Carnes a las brasas y acompañamientos para compartir.",
            "Sector Barrio Inglés, Coquimbo", "Miércoles a domingo · 12:00 a 23:00", -29.9571, -71.3417));
        restaurantes.add(new Restaurante(6, "Dulce Amanecer", "Pastelería",
            "Tortas, bollería y opciones dulces para la tarde.",
            "Sector Peñuelas, Coquimbo", "Todos los días · 08:30 a 19:00", -29.9441, -71.2955));
        restaurantes.add(new Restaurante(7, "Taco Viajero", "Mexicana",
            "Tacos, burritos y salsas de distintos sabores.",
            "Sector Centro, Coquimbo", "Martes a domingo · 13:00 a 22:00", -29.9533, -71.3388));
        restaurantes.add(new Restaurante(8, "Rincón Marino", "Peruana",
            "Ceviches y preparaciones de inspiración marina.",
            "Sector Puerto, Coquimbo", "Lunes a sábado · 12:00 a 21:00", -29.9602, -71.3430));
    }

    private DatosRestaurantes() { }

    public static Restaurante buscar(int id) {
        for (Restaurante r : restaurantes) if (r.getId() == id) return r;
        return null;
    }
    public static boolean esFavorito(int id) { return favoritos.contains(id); }
    public static void alternarFavorito(int id) {
        if (!favoritos.add(id)) favoritos.remove(id);
    }
}
