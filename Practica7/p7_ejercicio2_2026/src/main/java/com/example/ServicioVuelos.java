package com.example;

public class ServicioVuelos {
  // Base de datos de vuelos: Origen-Destino -> [Precio, Plazas]
    private java.util.Map<String, int[]> vuelos = new java.util.HashMap<>();

    public ServicioVuelos() {
        // Datos de ejemplo: [Precio, Plazas]
        vuelos.put("Madrid-Paris", new int[]{150, 5});
        vuelos.put("Madrid-Roma", new int[]{120, 0}); // Sin plazas
    }

    public boolean existeVuelo(String origen, String destino) {
        return vuelos.containsKey(origen + "-" + destino);
    }

    public boolean hayPlazas(String origen, String destino) {
        String ruta = origen + "-" + destino;
        return vuelos.containsKey(ruta) && vuelos.get(ruta)[1] > 0;
    }

    public boolean reservarPlaza(String origen, String destino) {
        if (hayPlazas(origen, destino)) {
            vuelos.get(origen + "-" + destino)[1]--;
            return true;
        }
        return false;
    }
    
    // Método extra necesario para el rollback
    public void liberarPlaza(String origen, String destino) {
        if (existeVuelo(origen, destino)) {
            vuelos.get(origen + "-" + destino)[1]++;
        }
    }

    public int getPrecio(String origen, String destino) {
        if (existeVuelo(origen, destino)) {
            return vuelos.get(origen + "-" + destino)[0];
        }
        return 0;
    }
}
