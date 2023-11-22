package tarea5;


/**
 * Clase TransferenciaConBloqueo: Programa principal que simula
 * transferencias entre dos cuentas con bloqueo.
 */
public class TransferenciaConBloqueo {
    public static void main(String[] args) {

        Cuenta c1 = new Cuenta("ES88888888888888888888", 50000.0);
        Cuenta c2 = new Cuenta("ES55555555555555555555", 30000.0);

        Hilo h1 = new Hilo("john_lopez",c1,c2);
        Hilo h2 = new Hilo("lopez_john",c1,c2);
        h1.setName("Hilo1");
        h2.setName("Hilo2");

        System.out.println("Saldo inicial de las cuentas: ");
        System.out.printf("Hilo %s %s%n",h1.getNombre(),c1);
        System.out.printf("Hilo %s %s%n%n",h2.getNombre(),c2);

        h1.start();
        h2.start();

        try {
            h1.join();
            h2.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nSaldo final de las dos cuentas:");
        System.out.printf("Hilo %s %s%n",h1.getNombre(),c1);
        System.out.printf("Hilo %s %s%n",h2.getNombre(),c2);
    }
}
