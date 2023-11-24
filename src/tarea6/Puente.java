package tarea6;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Puente {
    private final int pesoMaximo = 5000;
    private final int capacidadMaxima = 3;
    private int pesoActual = 0;
    private int cochesEnPuente = 0;

    public synchronized boolean sePermitePaso(int pesoCoche,int id) throws InterruptedException {

        while (cochesEnPuente == capacidadMaxima || pesoActual + pesoCoche > pesoMaximo) {
            System.out.printf("Coche %d no cumple condicion y se bloquea%n",id);
            wait();  // Espera hasta que la condición sea verdadera
        }
        cochesEnPuente++;
        System.out.printf("Coche %d entra  al puente ahora hay %d coches en el puente%n",id,cochesEnPuente);
        pesoActual += pesoCoche;
        System.out.println("peso Actual en el puente " + pesoActual);
        return true;
    }

    public synchronized void finalizarPaso(int pesoCoche,int id) {
        cochesEnPuente--;
        System.out.printf("Coche %d sale del puente ahora hay %d coches en el puente%n",id,cochesEnPuente);
        pesoActual -= pesoCoche;
        System.out.println("peso Actual en el puente " + pesoActual);
        notifyAll();  // Notifica a todos los hilos bloqueados
    }
}