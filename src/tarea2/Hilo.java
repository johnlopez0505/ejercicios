package tarea2;

public class Hilo implements Runnable {
    private String nombre;

    public Hilo(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        
        for (int i = 0; i < 5; i++) {
            System.out.println("Este es el " + this.nombre);
        }
    }
}

class PrioridadHilo{
    private final int maximaPrioridad;
    private final int minimaPrioridad;
    private final int defaultPrioridad;

    public PrioridadHilo(int maximaPrioridad, int minimaPrioridad, int defaultPrioridad) {
        this.maximaPrioridad = maximaPrioridad;
        this.minimaPrioridad = minimaPrioridad;
        this.defaultPrioridad = defaultPrioridad;
    }

    public static void main(String[] args) {
        PrioridadHilo p = new PrioridadHilo(Thread.MAX_PRIORITY,Thread.MIN_PRIORITY,Thread.NORM_PRIORITY);

        Thread h1 = new Thread(new Hilo("hilo1_john"));
        Thread h2 = new Thread(new Hilo("hilo2-john"));
        Thread h3 = new Thread(new Hilo("hilo3 john"));

        h1.start();
        h2.start();
        h3.start();

        h1.setPriority(p.minimaPrioridad);
        h2.setPriority(p.maximaPrioridad);
        h3.setPriority(p.defaultPrioridad);

        try {
            h1.join();
            System.out.println("h1 termino correctamente con prioridad: " + h1.getPriority());
            h2.join();
            System.out.println("h2 termino correctamente con prioridad: " + h2.getPriority());
            h3.join(1,569);
            System.out.println("h3 termino correctamente con prioridad: " + h3.getPriority());

        } catch (InterruptedException e) {
            System.out.println("El hilo ha sido interrumpido");
        }
    }
}

