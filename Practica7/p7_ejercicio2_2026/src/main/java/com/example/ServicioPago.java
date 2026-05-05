package com.example;

public class ServicioPago {
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
