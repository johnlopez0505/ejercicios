package tarea7;

import java.util.Random;

public class Principal {
    public static void main(String[] args) {
        Colecta c = new Colecta(0);

        Hilo[] h = new Hilo[4];
        for (int i = 0; i < 4; i++) {
            h[i]= new Hilo("persona" + i,c);
            System.out.println("persona" + i + " creado");
            h[i].start();
        }
        for (int i = 0; i < 4; i++) {
            try {
                h[i].join();
                System.out.printf("HILO %S termino correctamente%n",h[i]);

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
