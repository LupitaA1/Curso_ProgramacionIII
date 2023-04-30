
package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class ClinicaMedica {

    private static final int NUMERO_MEDICOS = 3;
    private static final int NUMERO_PACIENTES = 10;

    public static void main(String[] args) throws InterruptedException {
        Queue<Paciente> pacientes = new LinkedList<>();
        Thread[] medicos = new Thread[NUMERO_MEDICOS];
        // Generamos los pacientes y los agregamos a la cola de espera
        for (int i = 0; i < NUMERO_PACIENTES; i++) {
            pacientes.add(new Paciente("Paciente " + (i + 1), new Random().nextInt(5) + 1));
        }

        // Generamos los hilos de los médicos y los iniciamos
        for (int i = 0; i < NUMERO_MEDICOS; i++) {
            medicos[i] = new Thread(new Medico(pacientes));
            medicos[i].start();
        }

        // Esperamos a que terminen todos los médicos
        for (int i = 0; i < NUMERO_MEDICOS; i++) {
            medicos[i].join();
        }
    }
}

class Paciente {
    private String nombre;
    private int tiempoAtencion;

    public Paciente(String nombre, int tiempoAtencion) {
        this.nombre = nombre;
        this.tiempoAtencion = tiempoAtencion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTiempoAtencion() {
        return tiempoAtencion;
    }
}

class Medico implements Runnable {

    private Queue<Paciente> pacientes;

    public Medico(Queue<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    @Override
    public void run() {
        while (!pacientes.isEmpty()) {
            Paciente paciente = pacientes.poll();
            System.out.println("Atendiendo a " + paciente.getNombre());
            try {
                Thread.sleep(paciente.getTiempoAtencion() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}




