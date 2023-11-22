package tarea5;

/**
 * Clase Cuenta: Representa una cuenta bancaria con un número de cuenta (IBAN) y un saldo.
 */
public class Cuenta {
    private String iban;
    private double saldo;


    public Cuenta(String iban, double saldo) {
        this.iban = iban;
        this.saldo = saldo;
    }

    public synchronized double getSaldo() {
        return saldo;
    }

    public synchronized void ingresaCantidad(double cantidad) {
        saldo += cantidad;
    }

    public synchronized void sacarCantidad(double cantidad) {
        saldo -= cantidad;
    }

    public synchronized String getNumeroCuenta() {
        return iban;
    }

    /**
     * Clase Transferencia: Representa una transferencia entre dos cuentas bancarias.
     */
    @Override
    public String toString() {
        return String.format("Cuenta con IBAN: %S con saldo total: %.2f",iban,saldo);
    }
}

