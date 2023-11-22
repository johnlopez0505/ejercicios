package tarea4;

import java.util.Random;

class NumeroOculto {
    private int numeroOculto;
    private boolean juegoTerminado;

    public NumeroOculto() {
        Random rand = new Random();
        this.numeroOculto = rand.nextInt(101);
        this.juegoTerminado = false;
    }

    public int getNumeroOculto() {
        return numeroOculto;
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }

    public synchronized int propuestaNumero(int num) {
        if (juegoTerminado) {
            return -1; // Juego terminado porque un hilo ya ha adivinado el número
        } else if (num == numeroOculto) {
            juegoTerminado = true;
            return 1; // Número adivinado correctamente
        } else {
            return 0; // Número incorrecto, el juego continúa
        }
    }
}

class HiloAdivinaNumero extends Thread {
    private int numeroHilo;
    private NumeroOculto numeroOculto;

    HiloAdivinaNumero(int numeroHilo, NumeroOculto numeroOculto) {
        this.numeroHilo = numeroHilo;
        this.numeroOculto = numeroOculto;
    }

    public void run() {
        int intentos = 10;
        for (int i = 0; i < intentos; i++) { // Utiliza un bucle for con 10 iteraciones
            int numeroPropuesto = new Random().nextInt(101); // Número aleatorio entre 0 y 100
            System.out.printf("Hilo %d propone el número %d  y el numero a adivinar es %d\n", numeroHilo,
                    numeroPropuesto,numeroOculto.getNumeroOculto());

            int resultado = numeroOculto.propuestaNumero(numeroPropuesto);

            if (resultado == 1) {
                System.out.printf("¡Hilo %d ha adivinado el número! Juego terminado.\n", numeroHilo);
                break;
            } else if (resultado == -1) {
                System.out.printf("Juego terminado. Hilo %d se detiene.\n", numeroHilo);
                break;
            }

            // Espera un momento antes de realizar la siguiente propuesta
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        if(!numeroOculto.isJuegoTerminado()) {
            // Ningun Hilo  adivinó el número después de 10 iteraciones
            System.out.printf("Hilo %d no ha adivinado el número después de %d intentos. Juego terminado.\n",
                    numeroHilo,intentos);
        }
    }
}

public class JuegoAdivinaNumero {
    private static final int NUM_HILOS = 10;

    public static void main(String[] args) {
        NumeroOculto numeroOculto = new NumeroOculto();
        HiloAdivinaNumero hilos[] = new HiloAdivinaNumero[NUM_HILOS];

        for (int i = 0; i < NUM_HILOS; i++) {
            hilos[i] = new HiloAdivinaNumero(i, numeroOculto);
            hilos[i].start();
        }

        for (int i=0; i<NUM_HILOS; i++){
            try{
                hilos[i].join();
            }catch(InterruptedException e){
                System.out.println ("Se ha producido una interrupción no deseado\n");
            }
        }

    }
}

