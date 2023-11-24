package tarea6;

import java.util.Random;

public class Coche extends Thread {
    private final int id;
    private final Puente puente;

    public Coche(int id, Puente puente) {
        this.id = id;
        this.puente = puente;
    }

    @Override
    public void run() {
        Random random = new Random();
        int peso = random.nextInt(1201) + 800;
        int tiempoLlegada = random.nextInt(2);
        int tiempoPaso = random.nextInt(5) ;

        try {
            sleep(tiempoLlegada * 1000);  // Convierte segundos a milisegundos
            System.out.println("Coche " + id + " llega al puente.");
            if (puente.sePermitePaso(peso,id)) {
                sleep(tiempoPaso * 1000);  // Convierte segundos a milisegundos
                puente.finalizarPaso(peso,id);
            } else {
                System.out.println("Coche " + id + " no puede cruzar en este momento.");
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
