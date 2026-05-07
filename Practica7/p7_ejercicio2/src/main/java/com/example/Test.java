package com.example;
public class Test {
   public static void main(String[] args) {
       // Inicializamos los subsistemas
       ServicioVuelos vuelos = new ServicioVuelos();
       ServicioHotel hoteles = new ServicioHotel();
       ServicioPago pagos = new ServicioPago(1000); // Saldo inicial fijo de 1000€


       // Inicializamos la Fachada
       AgenciaViajes agencia = new AgenciaViajes(vuelos, hoteles, pagos);


       System.out.println("--- INICIANDO PRUEBAS DEL SISTEMA DE RESERVAS ---\n");


       // Escenario 1: Reserva completada con éxito
       System.out.println("1. Intento de reserva exitosa (Madrid -> Paris, 3 noches):");
       System.out.println(agencia.reservar("Madrid", "Paris", 3));
       System.out.println();


       // Escenario 2: Vuelo inexistente o sin plazas
       System.out.println("2. Intento de reserva de vuelo sin plazas (Madrid -> Roma, 2 noches):");
       System.out.println(agencia.reservar("Madrid", "Roma", 2));
       System.out.println();


       // Escenario 3: Hotel sin habitaciones
       // Primero forzamos la creación de un vuelo a Londres para probar el fallo del hotel
       vuelos.liberarPlaza("Madrid", "Londres"); // Añadimos una plaza ficticia
       System.out.println("3. Intento de reserva en hotel sin habitaciones (Madrid -> Londres, 2 noches):");
       System.out.println(agencia.reservar("Madrid", "Londres", 2));
       System.out.println();


       // Escenario 4: Saldo insuficiente
       System.out.println("4. Intento de reserva con saldo insuficiente (Madrid -> Paris, 15 noches):");
       System.out.println(agencia.reservar("Madrid", "Paris", 15)); // 150 + (80*15) = 1350€ (mayor a los ~610€ restantes)
       System.out.println();
   }
}

