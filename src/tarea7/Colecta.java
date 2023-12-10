package tarea7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Colecta {
    private int cantidad = 0;
    private int[] cantidadesPorHilo = {0,0,0,0};


    public Colecta(int cantidad) {
        this.cantidad = cantidad;
    }

    public synchronized int getCantidad() {
        return cantidad;
    }

    public synchronized boolean recolectar(String nombre) throws InterruptedException {
        Random random = new Random();

        System.out.println(nombre + " llega a recolectar");

        while (!(this.getCantidad() < 2000)) {

            System.out.printf("Hilo %s Entra y se bloquea%n",nombre);
            wait();
        }
        int cantidadAIngresar = random.nextInt(22) + 4; // entre 4 y 25
        double tiempoEspera =  // entre 0.01 y 0.2 segundos

        cantidad += cantidadAIngresar;


        switch (nombre){
            case "persona0" :
                cantidadesPorHilo[0] += cantidadAIngresar;
                System.out.println(nombre + ": Recolectó " + cantidadAIngresar + ". Cantidad total recolectada: " + cantidad);
                System.out.println(nombre + ": Total recolectado: " + cantidadesPorHilo[0]);
                break;
            case "persona1" :
                cantidadesPorHilo[1] += cantidadAIngresar;
                System.out.println(nombre + ": Recolectó " + cantidadAIngresar + ". Cantidad total recolectada: " + cantidad);
                System.out.println(nombre + ": Total recolectado: " + cantidadesPorHilo[1]);
                break;
            case "persona2" :
                cantidadesPorHilo[2] += cantidadAIngresar;
                System.out.println(nombre + ": Recolectó " + cantidadAIngresar + ". Cantidad total recolectada: " + cantidad);
                System.out.println(nombre + ": Total recolectado: " + cantidadesPorHilo[2]);
                break;
            case "persona3" :
                cantidadesPorHilo[3] += cantidadAIngresar;
                System.out.println(nombre + ": Recolectó " + cantidadAIngresar + ". Cantidad total recolectada: " + cantidad);
                System.out.println(nombre + ": Total recolectado: " + cantidadesPorHilo[3]);
                break;
            default:
                System.exit(1);
        }


        notifyAll();
        return true;
    }




    /*
Varios hilos (por ejemplo, cuatro) realizan una colecta. En un tiempo aleatorio entre 10 y 200 ms, consiguen una 
cantidad de entre 4 y 25. La colecta termina cuando se llega a una cantidad de 2000. Utilizar una clase Colecta 
que almacene y permita consultar la cantidad recogida hasta el momento. Recordar que en todos nuestro ejemplos, 
el hilo principal crea un objeto que en este caso es de tipo Colecta, crea los hilos y les pasa ese objeto que es
 compartido entre todos.

Mostrar cuánto a colectado cada hilo diferenciándose por un nombre de persona.
*/

}
