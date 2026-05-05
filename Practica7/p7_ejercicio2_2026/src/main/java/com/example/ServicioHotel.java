package com.example;

public class ServicioHotel {
  // Base de datos de hoteles: Ciudad -> [PrecioNoche, Habitaciones]
    private java.util.Map<String, int[]> hoteles = new java.util.HashMap<>();

    public ServicioHotel() {
        hoteles.put("Paris", new int[]{80, 10});
        hoteles.put("Londres", new int[]{100, 0}); // Sin habitaciones
    }

    public boolean hayHabitacion(String ciudad) {
        return hoteles.containsKey(ciudad) && hoteles.get(ciudad)[1] > 0;
    }

    public boolean reservarHabitacion(String ciudad) {
        if (hayHabitacion(ciudad)) {
            hoteles.get(ciudad)[1]--;
            return true;
        }
        return false;
    }

    // Método extra necesario para el rollback
    public void liberarHabitacion(String ciudad) {
        if (hoteles.containsKey(ciudad)) {
            hoteles.get(ciudad)[1]++;
        }
    }

    public int getPrecioNoche(String ciudad) {
        if (hoteles.containsKey(ciudad)) {
            return hoteles.get(ciudad)[0];
        }
        return 0;
    }
}
