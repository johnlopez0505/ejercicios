package tarea5;

import java.util.Random;

/**
 * Clase Hilo: Representa un hilo de ejecución que realiza transferencias
 * entre dos cuentas.
 */
public class Hilo extends Thread {
    private String nombre;
    private Cuenta c1;
    private Cuenta c2;

    public Hilo(String nombre,Cuenta c1,Cuenta c2) {
        this.nombre = nombre;
        this.c1     = c1;
        this.c2     = c2;
    }

    /**
     * Método que devuelve el nombre del hilo.
     * @return Nombre del hilo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método run que ejecuta las transferencias entre las cuentas.
     */
    @Override
    public void run() {

        for (int i = 0; i < 2; i++) {
            boolean resultado = Transferencia.transferencia(c1,c2, new Random().nextInt(100));
            System.out.println((resultado)?"El Hilo " + nombre + " realizo la transferencia con exito" :
                    "No se ha realizado la transferencia");

        }
    }
}