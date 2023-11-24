package tarea6;

import tarea2.Hilo;

public class SimulacionPuente {
    public static void main(String[] args) {
        int numeroCoches = 5;
        Puente puente = new Puente();
        Coche[] coches = new Coche[numeroCoches];
        for (int i = 1; i <= numeroCoches-1; i++) {
            coches[i] = new Coche(i,puente);
            coches[i].start();
        }

        for (int i = 1; i <= numeroCoches-1; i++) {
            try {
                coches[i].join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
