package com.example;

public class AgenciaViajes {
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
