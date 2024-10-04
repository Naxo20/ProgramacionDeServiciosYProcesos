package com.mymodule.serviceprocessprogramming.ut1_concurrent_programming.exercises;

import java.util.ArrayList;

// Clase base para la cuenta bancaria
class CuentaBancaria {
    private int saldo = 1000;
    private int limiteSaldo = 1500;
    private ArrayList<String> historial = new ArrayList<>();

    public synchronized void retirar(int cantidad, String nombre) {
        System.out.println(nombre + " está intentando retirar " + cantidad + " euros.");
        try {
            if (saldo >= cantidad) {
                Thread.sleep(500);
                saldo -= cantidad;
                System.out.println("Retirado: " + cantidad + ". Saldo actual: " + saldo + ".");
                historial.add(nombre + " retira " + cantidad + " euros.");
            } else {
                System.out.println("Saldo insuficiente.");
                historial.add(nombre + " no puede retirar  " + cantidad + " euros por insuficiencia de saldo.");
            }
        } catch (InterruptedException e) {
            e.getMessage();
        }
    }

    public synchronized void depositar(int cantidad, String nombre) {
        System.out.println(nombre + " está intentando depositar " + cantidad + " euros.");
        try {
            if (comprobarLimiteMaximo(cantidad)) {
                Thread.sleep(1000);
                saldo += cantidad;
                System.out.println("Depositado: " + cantidad + ". Saldo actual: " + saldo + ".");
                historial.add(nombre + " deposita " + cantidad + " euros.");
            } else {
                System.out.println("No se puede depositar más de " + limiteSaldo + " en un cajero.");
                historial.add(nombre + " no puede depositar " + cantidad + " euros por superar el límite de saldo.");
            }
        } catch (InterruptedException e) {
            e.getMessage();
        }
    }

    public synchronized boolean comprobarLimiteMaximo(int cantidad) {
        return cantidad <= limiteSaldo;
    }

    public int getSaldo() {
        return saldo;
    }

    public String verHistorial() {
        String historial = "Historial: \n";
        for (String historia : this.historial) {
            historial += historia + "\n";
        }
        return historial;
    }
}

// Clase que representa a un cajero automático
class Cajero extends Thread {
    private CuentaBancaria cuenta;
    private int cantidad;
    private boolean esDeposito;
    private String nombreCajero;

    public Cajero(CuentaBancaria cuenta, int cantidad, boolean esDeposito, String nombreCajero) {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
        this.esDeposito = esDeposito;
        this.nombreCajero = nombreCajero;
    }

    public void run() {
        try {
            // Simulamos un pequeño retardo en la operación
            Thread.sleep(1000);
            if (esDeposito) {
                cuenta.depositar(cantidad, nombreCajero);
            } else {
                cuenta.retirar(cantidad, nombreCajero);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Clase principal con la ejecución de hilos
public class BankOperationSimulation {
    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria();
        Cajero cajero1 = new Cajero(cuenta, 200, true, "Cajero 1");  // Depósito de 200
        Cajero cajero2 = new Cajero(cuenta, 150, false, "Cajero 2"); // Retiro de 150
        Cajero cajero3 = new Cajero(cuenta, 50, false, "Cajero 3");  // Retiro de 50
        Cajero cajero4 = new Cajero(cuenta, 500, true, "Cajero 4");  // Depósito de 500
        Cajero cajero5 = new Cajero(cuenta, 1500, false, "Cajero 5");  // Retiro de 1500
        Cajero cajero6 = new Cajero(cuenta, 1500, true, "Cajero 6");  // Depósito de 1500
        Cajero cajero7 = new Cajero(cuenta, 1501, true, "Cajero 7");  // Depósito de 1501

        // Iniciamos los hilos de cada cajero
        cajero1.start();
        cajero2.start();
        cajero3.start();
        cajero4.start();
        cajero5.start();
        cajero6.start();
        cajero7.start();

        // Esperamos a que los hilos terminen su ejecución
        try {
            cajero1.join();
            cajero2.join();
            cajero3.join();
            cajero4.join();
            cajero5.join();
            cajero6.join();
            cajero7.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Mostramos el saldo final
        System.out.println("Saldo final de la cuenta: " + cuenta.getSaldo() + " euros.");

        // Mostramos el historial
        System.out.println(cuenta.verHistorial());
    }
}
