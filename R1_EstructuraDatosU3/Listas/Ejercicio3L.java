package Listas;

import java.util.Scanner;

/**
 * Clase Termino:
 * Aqui guardo el coeficiente, el exponente y la referencia al siguiente, basicamente cada objeto de esta clase es una parte del polinomio.
 */
class Termino {
    private double coef; 
    private int exp;     
    private Termino sig; 

    Termino(double c, int e) {
        this.coef = c;
        this.exp = e;
        this.sig = null;
    }

    // getters
    public double getCoef() { return coef; }
    public int getExp() { return exp; }
    public Termino getSig() { return sig; }

    // setter para la liga
    public void setSig(Termino sig) {
        this.sig = sig;
    }
}

/**
 * Clase Polinomio:
 * Aqui se maneja la lista de terminos, esta clase controla todo:
 * agregar terminos, mostrarlos, evaluarlos, etc.
 */
class Polinomio {
    private Termino inicio;

    Polinomio() {
        inicio = null;
    }

    /**
     * Agregar un termino:
     * Mete el nuevo termino al final, no los ordeno, solo los encadeno.
     */
    void agregar(double c, int e) {
        Termino nuevo = new Termino(c, e);

        if (inicio == null) {
            inicio = nuevo;
        } else {
            Termino actual = inicio;
            while (actual.getSig() != null) {
                actual = actual.getSig();
            }
            actual.setSig(nuevo);
        }
    }

    /**
     * Mostrar el polinomio:
     * Solo se recorre y se imprime de forma decente.
     */
    void mostrar() {
        if (inicio == null) {
            System.out.println("Polinomio vacio");
            return;
        }

        Termino actual = inicio;
        boolean primero = true;

        System.out.print("P(x) = ");

        while (actual != null) {

            double coef = actual.getCoef();
            int exp = actual.getExp();

            if (coef >= 0 && !primero) System.out.print(" + ");
            else if (coef < 0) System.out.print(" - ");

            double valor = Math.abs(coef);

            if (exp == 0) System.out.print(valor);
            else if (exp == 1) System.out.print((valor == 1 ? "" : valor) + "x");
            else System.out.print((valor == 1 ? "" : valor) + "x^" + exp);

            actual = actual.getSig();
            primero = false;
        }

        System.out.println();
    }

    /**
     * Evaluar:
     * Se sustituye x en el polinomio y se va sumando cada termino.
     */
    double evaluar(double x) {
        double resultado = 0;

        Termino actual = inicio;

        while (actual != null) {
            resultado += actual.getCoef() * Math.pow(x, actual.getExp());
            actual = actual.getSig();
        }

        return resultado;
    }

    /**
     * Tabla de valores:
     * Solo se imprime como va el polinomio desde x=0 hasta x=5.
     */
    void tabla() {
        System.out.println("\n=== TABLA DE EVALUACION ===");
        System.out.println("  x   |   P(x)");
        System.out.println("---------------");

        for (double x = 0; x <= 5; x += 0.5) {
            System.out.printf("%4.1f  | %8.3f%n", x, evaluar(x));
        }
    }

    /**
     * ExisteExp:
     * se revisa si ya hay un termino con ese exponente para no repetir.
     */
    boolean existeExp(int e) {
        Termino actual = inicio;

        while (actual != null) {
            if (actual.getExp() == e) return true;
            actual = actual.getSig();
        }

        return false;
    }
}

/**
 * Clase principal:
 * Aqui ya nadamas pido los datos, construyo el polinomio y lo muestro.
 */
public class Ejercicio3L {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        Polinomio p = new Polinomio();

        System.out.println("=== POLINOMIO ===");
        System.out.println("Ingresa coeficiente y exponente (ej. 3 4), escribe 'fin' para terminar:");

        while (true) {
            System.out.print("Termino: ");

            if (entrada.hasNext("fin")) {
                entrada.next();
                break;
            }

            if (entrada.hasNextDouble()) {
                double c = entrada.nextDouble();

                if (entrada.hasNextInt()) {
                    int e = entrada.nextInt();

                    if (e < 0) {
                        System.out.println("Exponente no puede ser negativo.");
                        continue;
                    }

                    if (p.existeExp(e)) {
                        System.out.println("Ya existe un termino con exponente " + e);
                        continue;
                    }

                    p.agregar(c, e);
                    System.out.println("Anadido: " + c + "x^" + e);

                } else {
                    System.out.println("Exponente invalido.");
                    entrada.next();
                }

            } else {
                System.out.println("Formato incorrecto.");
                entrada.next();
            }
        }

        System.out.println("\n--- POLINOMIO ---");
        p.mostrar();

        p.tabla();

        System.out.println("\nEvaluar en otro valor? (s/n)");
        entrada.nextLine();
        String r = entrada.nextLine();

        if (r.equalsIgnoreCase("s")) {
            System.out.print("Valor de x: ");
            double x = entrada.nextDouble();
            System.out.printf("P(%.2f) = %.3f%n", x, p.evaluar(x));
        }

        entrada.close();
        System.out.println("\n=== FIN DEL PROGRAMA ===");
    }
}