package tarea8;

import java.util.Random;

class Deposito {
    private int capacidad;
    private int cantidadActual;
    private final Object lock = new Object();

    public Deposito(int capacidad) {
        this.capacidad = capacidad;
        this.cantidadActual = capacidad;
    }

    public int sacarAgua(int cantidad) {
        synchronized (lock) {
            int aguaObtenida = Math.min(cantidad, cantidadActual);
            cantidadActual -= aguaObtenida;
            return aguaObtenida;
        }
    }

    public int rellenarAgua(int cantidad) {
        synchronized (lock) {
            int espacioDisponible = capacidad - cantidadActual;
            int aguaRellenada = Math.min(cantidad, espacioDisponible);
            cantidadActual += aguaRellenada;
            return aguaRellenada;
        }
    }

    public int getCantidadActual() {
        synchronized (lock) {
            return cantidadActual;
        }
    }
}

class Cocinero extends Thread {
    private String nombre;
    private Deposito depositoCaliente;
    private Deposito depositoFrio;
    private int platosRealizados;
    private int litrosCaliente;
    private int litrosFrio;

    public Cocinero(String nombre, Deposito depositoCaliente, Deposito depositoFrio) {
        this.nombre = nombre;
        this.depositoCaliente = depositoCaliente;
        this.depositoFrio = depositoFrio;
        this.platosRealizados = 0;
        this.litrosCaliente = 0;
        this.litrosFrio = 0;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(new Random().nextInt(1000)); // Tiempo de espera aleatorio
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (new Random().nextBoolean()) {  // Decide si necesita agua caliente o fría
                int cantidadAgua = new Random().nextInt(1976) + 25;
                int aguaObtenida = depositoCaliente.sacarAgua(cantidadAgua);
                litrosCaliente += aguaObtenida;
            } else {
                int cantidadAgua = new Random().nextInt(1976) + 25;
                int aguaObtenida = depositoFrio.sacarAgua(cantidadAgua);
                litrosFrio += aguaObtenida;
            }

            platosRealizados++;

            System.out.println(nombre + ": He realizado un plato. Total platos: " + platosRealizados +
                    ", Caliente: " + litrosCaliente + " cl, Fría: " + litrosFrio + " cl");

            if (depositoCaliente.getCantidadActual() == 0 && depositoFrio.getCantidadActual() == 0) {
                System.out.println(nombre + ": He terminado mi trabajo. Platos: " + platosRealizados +
                        ", Caliente: " + litrosCaliente + " cl, Fría: " + litrosFrio + " cl");
                break;
            }
        }
    }
}

class Rellenador extends Thread {
    private Deposito depositoCaliente;
    private Deposito depositoFrio;

    public Rellenador(Deposito depositoCaliente, Deposito depositoFrio) {
        this.depositoCaliente = depositoCaliente;
        this.depositoFrio = depositoFrio;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(4000); // Rellenar cada 4 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int cantidadRellenar = 50;
            depositoCaliente.rellenarAgua(cantidadRellenar);
            depositoFrio.rellenarAgua(cantidadRellenar);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Deposito depositoCaliente = new Deposito(300000);  // 300 litros convertidos a centilitros
        Deposito depositoFrio = new Deposito(600000);  // 600 litros convertidos a centilitros

        Cocinero[] cocineros = new Cocinero[6];
        for (int i = 0; i < 6; i++) {
            cocineros[i] = new Cocinero("Cocinero " + i, depositoCaliente, depositoFrio);
            cocineros[i].start();
        }

        Rellenador rellenador = new Rellenador(depositoCaliente, depositoFrio);
        rellenador.start();
    }
}
