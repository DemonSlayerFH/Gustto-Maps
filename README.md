# Gustto — Entrega de interfaz con ArrayList (sin Firebase ni SQLite)

Es una versión **simplificada y autocontenida** de Gustto para explicar RecyclerView y CardView.
No sustituye todas las pantallas y el código de producción del proyecto original.

## Qué funciona
- Inicio: RecyclerView vertical con ocho restaurantes ficticios.
- Tarjetas: CardView definido en `app/src/main/res/layout/item_restaurante.xml`.
- Datos: `app/src/main/java/com/example/gustto/DatosRestaurantes.java` contiene
  `ArrayList<Restaurante> restaurantes` y ocho objetos declarados con `new Restaurante(...)`.
- Buscador dinámico, filtro por categoría, favoritos temporales.
- Detalles con nombre, categoría, descripción, horario y dirección de ejemplo.
- Botones para abrir y volver de la pantalla de mapa.
- Google Maps **real**, opcional, con marcadores si configuras la clave.
- Sin clave, la pantalla muestra la lista de ubicaciones de ejemplo (no presenta un mapa falso).

## Requisitos
- Android Studio con SDK Platform 35 y JDK 17 (JDK 21 también puede funcionar).
- Gradle Wrapper 8.9 incluido; Android Gradle Plugin 8.7.3.
- Conexión a Internet para descargar dependencias y para Google Maps real.

1. Descomprime el ZIP.
2. Android Studio → File → Open → selecciona esta carpeta raíz.
3. Espera Sync Project with Gradle Files. SDK Platform 35 según te solicite.
4. Ejecuta. **No requiere `google-services.json` ni clave de Maps para mostrar la lista.**
5. Si quieres un mapa real, sigue `CONFIGURACION_MAPS.txt`.

**Importante**: los restaurantes, direcciones y coordenadas son ejemplos ficticios,
no establecimientos verificados. Los favoritos se almacenan en RAM y se reinician
cuando se cierra el proceso de la aplicación. No hay inicio de sesión ni base de datos.

## Cómo explicar el código al profesor

- `Restaurante.java`: modelo que define los atributos del restaurante.
- `DatosRestaurantes.java`: inicializa una `ArrayList` con `restaurantes.add(...)`.
- `MainActivity.java`: conecta RecyclerView, configura `LinearLayoutManager` y Adapter.
- `RestauranteAdapter.java`: `onCreateViewHolder()` crea el diseño, `onBindViewHolder()`
  presenta los datos y `getItemCount()` devuelve cantidad de resultados.
- `item_restaurante.xml`: contiene la CardView que se repite en la lista.
- `MapaActivity.java`: crea marcadores desde la misma ArrayList usando Maps SDK.

## Límite de la validación
Se revisaron los archivos Java/XML y la coherencia de referencias, pero no hay
compilación en un emulador Android desde este entorno. En caso de error
de Gradle, copia el mensaje exacto de Build Output para diagnosticarlo.
