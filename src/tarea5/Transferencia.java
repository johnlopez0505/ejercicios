package tarea5;


/**
 * Clase Transferencia: Representa una transferencia entre dos cuentas bancarias.
 */
public class Transferencia {
    private static  Cuenta cuenta1;
    private static  Cuenta cuenta2;



    /**
     * Método estático sincronizado que realiza una transferencia entre dos cuentas.
     * @param c1 Cuenta de origen.
     * @param c2 Cuenta de destino.
     * @param cantidad Cantidad a transferir.
     * @return true si la transferencia se realiza con éxito, false si no es posible.
     */
    public static boolean transferencia(Cuenta c1, Cuenta c2, double cantidad) {

        System.out.printf("\nSoy el Hilo con nombre %s%n",
                Thread.currentThread().getName());
        // Establecer un orden fijo para adquirir los bloqueos
        if (c1.getSaldo() >= c2.getSaldo()) {
            cuenta1 = c1;
            cuenta2 = c2;
            System.out.println(retirarDinero(cantidad) ?
                    "Retiro hecho correctamente" : "Fallo retiro");
            return depositarDinero(cantidad);
        } else {
            cuenta1 = c2;
            cuenta2 = c1;
            System.out.println(retirarDinero(cantidad) ?
                    "Retiro hecho correctamente" : "Fallo retiro");
            return depositarDinero(cantidad);
        }

    }

    /**
     * Método estático sincronizado que retira dinero de la cuenta de origen.
     * @param cantidad Cantidad a retirar.
     * @return true si el retiro se realiza con éxito, false si no es posible.
     */
    public static synchronized boolean retirarDinero(double cantidad){
        System.out.printf("%s Accediendo al recurso y bloqueado%n",
                Thread.currentThread().getName());
        if( cuenta1.getSaldo() >= cantidad){
            System.out.printf("El saldo de la cuenta %s es de: %.2f%n",
                    cuenta1.getNumeroCuenta(),cuenta1.getSaldo());
            cuenta1.sacarCantidad(cantidad);
            System.out.printf("Cantidad a retirar %.2f%n",cantidad);
            System.out.printf("El nuevo saldo de la cuenta %s es de: %.2f%n",
                    cuenta1.getNumeroCuenta(),cuenta1.getSaldo());
            return true;
        }else{
            System.out.println("saldo insuficiente al intentar retirar " + cantidad);
            return false;
        }
    }

    /**
     * Método estático sincronizado que deposita dinero en la cuenta de destino.
     * @param cantidad Cantidad a depositar.
     * @return true si el depósito se realiza con éxito, false si no es posible.
     */
    public static synchronized  boolean depositarDinero(double cantidad){
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.printf("El saldo de la cuenta %s es de: %.2f%n",
                cuenta2.getNumeroCuenta(),cuenta2.getSaldo());
        cuenta2.ingresaCantidad(cantidad);
        System.out.printf("Cantidad a ingresar %.2f%n",cantidad);
        System.out.printf("El nuevo saldo de la cuenta %s es de: %.2f%n",
                cuenta2.getNumeroCuenta(),cuenta2.getSaldo());
        return true;
    }

}