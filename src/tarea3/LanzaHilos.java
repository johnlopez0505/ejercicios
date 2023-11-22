package tarea3;


    import java.util.Random;

class Hilo1 implements Runnable {
    private String nombre;

    public Hilo1(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        System.out.println("Soy un hilo de la clase Hilo1 y me llamo " + this.nombre);
        for (int i = 0; i < 4; i++) {
            int pausa = 10 + new Random().nextInt(500 - 10);
            try {
                Thread.sleep(pausa);
                System.out.println("Hilo1 dormido " + this.nombre);
            } catch (InterruptedException e) {
                System.out.println("El hilo ha sido interrumpido");
            }
        }
    }
}

class Hilo2 implements Runnable {
    private String nombre;

    public Hilo2(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        System.out.println("Soy un hilo de la clase Hilo2 y me llamo " + this.nombre);
        for (int i = 0; i < 5; i++) {
            int pausa = 10 + new Random().nextInt(500 - 10);
            try {
                Thread.sleep(pausa);
                System.out.println("Hilo2 dormido " + this.nombre);
            } catch (InterruptedException e) {
                System.out.println("El hilo ha sido interrumpido");
            }
        }
    }
}

public class LanzaHilos {
    public static void main(String[] args) {
        Thread hilo;
        for (int i = 0; i < 4; i++) {
            boolean aleatorio = new Random().nextBoolean();
            if (aleatorio) {
                hilo = new Thread(new Hilo1("HiloJohn1_" + i));
                hilo.start();
            } else {
                hilo = new Thread(new Hilo2("hiloJohn2_" + i));
                hilo.start();
            }

            try {
                hilo.join();
                System.out.println("Hilo terminó correctamente: " + hilo.getName());
            } catch (InterruptedException e) {
                System.out.println("El hilo ha sido interrumpido");
            }
        }
    }
}

