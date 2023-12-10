package tarea7;

import java.util.Random;

public class Hilo extends Thread{
    private String nombre;
    private Colecta colecta;

    public Hilo(String nombre, Colecta colecta) {
        this.nombre = nombre;
        this.colecta = colecta;
    }


    @Override
    public void run() {
        try {
            sleep(new Random().nextInt(191)+ 10);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        while (true) {
            try {
                if (colecta.recolectar(this.nombre)) {
                    System.out.println("se ha ingresado el dinero de la colecta");
                } else {
                    System.out.println("no se puede ingresar dinero");
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
