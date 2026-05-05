package com.example;


// --- SERVICIO DE VUELOS ---
class ServicioVuelos {
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

// --- SERVICIO DE HOTEL ---
class ServicioHotel {
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

// --- SERVICIO DE PAGO ---
class ServicioPago {
    private int saldo;

    public ServicioPago(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    public boolean tieneSaldo(int importe) {
        return saldo >= importe;
    }

    public boolean cobrar(int importe) {
        // El cobro puede fallar aleatoriamente o por falta de saldo
        if (tieneSaldo(importe)) {
            saldo -= importe;
            return true;
        }
        return false;
    }

    public void devolver(int importe) {
        saldo += importe;
    }

    public int getSaldo() {
        return saldo;
    }
    
    // Método auxiliar para forzar un fallo en las pruebas de rollback
    public boolean cobrarConFalloSimulado(int importe) {
        return false; 
    }
}



// --- FACHADA ---
class AgenciaViajes {
    private ServicioVuelos servicioVuelos;
    private ServicioHotel servicioHotel;
    private ServicioPago servicioPago;

    public AgenciaViajes(ServicioVuelos vuelos, ServicioHotel hotel, ServicioPago pago) {
        this.servicioVuelos = vuelos;
        this.servicioHotel = hotel;
        this.servicioPago = pago;
    }

    public String reservar(String origen, String destino, int noches) {
        // 1. Comprobar vuelo y plazas
        if (!servicioVuelos.existeVuelo(origen, destino)) {
            return "Error: No existe vuelo para el trayecto " + origen + "-" + destino + ".";
        }
        if (!servicioVuelos.hayPlazas(origen, destino)) {
            return "Error: No quedan plazas para el vuelo " + origen + "-" + destino + ".";
        }

        // 2. Comprobar habitación
        if (!servicioHotel.hayHabitacion(destino)) {
            return "Error: No hay habitaciones disponibles en " + destino + ".";
        }

        // 3. Calcular coste total
        int costeTotal = servicioVuelos.getPrecio(origen, destino) + (servicioHotel.getPrecioNoche(destino) * noches);

        // 4. Comprobar saldo
        if (!servicioPago.tieneSaldo(costeTotal)) {
            return "Error: Saldo insuficiente. El coste de la reserva es de " + costeTotal + "€.";
        }

        // 5. Reservar plaza y habitación
        servicioVuelos.reservarPlaza(origen, destino);
        servicioHotel.reservarHabitacion(destino);

        // Intentar cobrar
        if (servicioPago.cobrar(costeTotal)) {
            return "Éxito: Reserva completada. Coste total: " + costeTotal + "€. Saldo restante: " + servicioPago.getSaldo() + "€.";
        } else {
            // 6. Rollback si falla el pago
            servicioVuelos.liberarPlaza(origen, destino);
            servicioHotel.liberarHabitacion(destino);
            return "Error: El pago ha fallado. Se ha deshecho la reserva de vuelo y hotel (Rollback ejecutado).";
        }
    }
}

// --- SERVICIO DE VUELOS ---
class ServicioVuelos {
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

// --- SERVICIO DE HOTEL ---
class ServicioHotel {
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

// --- SERVICIO DE PAGO ---
class ServicioPago {
    private int saldo;

    public ServicioPago(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    public boolean tieneSaldo(int importe) {
        return saldo >= importe;
    }

    public boolean cobrar(int importe) {
        // El cobro puede fallar aleatoriamente o por falta de saldo
        if (tieneSaldo(importe)) {
            saldo -= importe;
            return true;
        }
        return false;
    }

    public void devolver(int importe) {
        saldo += importe;
    }

    public int getSaldo() {
        return saldo;
    }
    
    // Método auxiliar para forzar un fallo en las pruebas de rollback
    public boolean cobrarConFalloSimulado(int importe) {
        return false; 
    }
}



// --- FACHADA ---
class AgenciaViajes {
    private ServicioVuelos servicioVuelos;
    private ServicioHotel servicioHotel;
    private ServicioPago servicioPago;

    public AgenciaViajes(ServicioVuelos vuelos, ServicioHotel hotel, ServicioPago pago) {
        this.servicioVuelos = vuelos;
        this.servicioHotel = hotel;
        this.servicioPago = pago;
    }

    public String reservar(String origen, String destino, int noches) {
        // 1. Comprobar vuelo y plazas
        if (!servicioVuelos.existeVuelo(origen, destino)) {
            return "Error: No existe vuelo para el trayecto " + origen + "-" + destino + ".";
        }
        if (!servicioVuelos.hayPlazas(origen, destino)) {
            return "Error: No quedan plazas para el vuelo " + origen + "-" + destino + ".";
        }

        // 2. Comprobar habitación
        if (!servicioHotel.hayHabitacion(destino)) {
            return "Error: No hay habitaciones disponibles en " + destino + ".";
        }

        // 3. Calcular coste total
        int costeTotal = servicioVuelos.getPrecio(origen, destino) + (servicioHotel.getPrecioNoche(destino) * noches);

        // 4. Comprobar saldo
        if (!servicioPago.tieneSaldo(costeTotal)) {
            return "Error: Saldo insuficiente. El coste de la reserva es de " + costeTotal + "€.";
        }

        // 5. Reservar plaza y habitación
        servicioVuelos.reservarPlaza(origen, destino);
        servicioHotel.reservarHabitacion(destino);

        // Intentar cobrar
        if (servicioPago.cobrar(costeTotal)) {
            return "Éxito: Reserva completada. Coste total: " + costeTotal + "€. Saldo restante: " + servicioPago.getSaldo() + "€.";
        } else {
            // 6. Rollback si falla el pago
            servicioVuelos.liberarPlaza(origen, destino);
            servicioHotel.liberarHabitacion(destino);
            return "Error: El pago ha fallado. Se ha deshecho la reserva de vuelo y hotel (Rollback ejecutado).";
        }
    }
}