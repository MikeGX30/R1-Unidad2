package Listas;

import java.util.Scanner;

/**
 * @author GTID141
 * @author 1224100689.mla@gmail.com
 * @author miguel lozano
 */

////////////////////////////////////////////////////////////////////////
/**
 * Clase Nodo:
 * Representa un termino del polinomio.
 * Como no podemos acceder directo, usamos getters y setters.
 */
class Nodo {
    private double coef;
    private int exp;
    private Nodo sig;

    public Nodo(double c, int e) {
        coef = c;
        exp = e;
        sig = null;
    }

    // Getters y setters porque los atributos son privados
    public double getCoef() {
        return coef;
    }

    public int getExp() {
        return exp;
    }

    public Nodo getSig() {
        return sig;
    }

    public void setSig(Nodo siguiente) {
        this.sig = siguiente;
    }
}

////////////////////////////////////////////////////////////////////////
/**
 * Clase Polinomio:
 * Controla toda la lista circular.
 */
class Polinomio {

    private Nodo ultimo;

    public Polinomio() {
        ultimo = null;
    }

    /**
     * Agregar un nuevo termino al final del polinomio circular.
     */
    public void agregar(double c, int e) {
        Nodo nuevo = new Nodo(c, e);

        if (ultimo == null) {
            ultimo = nuevo;
            nuevo.setSig(nuevo);
        } else {
            nuevo.setSig(ultimo.getSig());
            ultimo.setSig(nuevo);
            ultimo = nuevo;
        }
    }

    /**
     * Mostrar todo el polinomio con formato.
     */
    public void mostrar() {
        if (ultimo == null) {
            System.out.println("Polinomio vacio");
            return;
        }

        Nodo actual = ultimo.getSig();
        Nodo inicio = actual;
        boolean primero = true;

        System.out.print("P(x) = ");
        do {
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

        } while (actual != inicio);

        System.out.println();
    }

    /**
     * Evaluar P(x) usando la formula coef * x^exp.
     */
    public double evaluar(double x) {
        if (ultimo == null) return 0;

        double res = 0;
        Nodo actual = ultimo.getSig();
        Nodo inicio = actual;

        do {
            res += actual.getCoef() * Math.pow(x, actual.getExp());
            actual = actual.getSig();
        } while (actual != inicio);

        return res;
    }

    /**
     * Mostrar una tabla de valores desde x = 0 hasta 5.
     */
    public void tabla() {
        System.out.println("\n=== TABLA DE EVALUACION ===");
        System.out.println("  x   |   P(x)");
        System.out.println("---------------");

        for (double x = 0; x <= 5; x += 0.5) {
            System.out.printf("%4.1f  | %8.3f%n", x, evaluar(x));
        }
    }

    /**
     * Ver si ya existe un termino con el mismo exponente.
     */
    public boolean existe(int e) {
        if (ultimo == null) return false;

        Nodo actual = ultimo.getSig();
        Nodo inicio = actual;

        do {
            if (actual.getExp() == e) return true;
            actual = actual.getSig();
        } while (actual != inicio);

        return false;
    }

    /**
     * Mostrar el recorrido circular viendo a quien apunta cada nodo.
     */
    public void recorrido() {
        if (ultimo == null) {
            System.out.println("Lista vacia");
            return;
        }

        System.out.println("\n=== RECORRIDO CIRCULAR ===");

        Nodo actual = ultimo.getSig();
        Nodo inicio = actual;
        int i = 1;

        do {
            System.out.println("Nodo " + i + ": " + actual.getCoef() + "x^" + actual.getExp());
            System.out.println("  Apunta a: " + actual.getSig().getCoef() + "x^" + actual.getSig().getExp());
            actual = actual.getSig();
            i++;
        } while (actual != inicio);

        System.out.println("Ultimo nodo apunta al primero: " +
                ultimo.getCoef() + "x^" + ultimo.getExp() +
                " -> " + ultimo.getSig().getCoef() + "x^" + ultimo.getSig().getExp());
    }
}

////////////////////////////////////////////////////////////////////////
/**
 * Clase principal:
 * Aqui solo se capturan terminos, se valida que no se repitan exponentes y se muestran todas las funciones del polinomio.
 */
public class Ejercicio4L {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Polinomio p = new Polinomio();

        System.out.println("=== POLINOMIO CIRCULAR ===");
        System.out.println("Ingresa coeficiente y exponente, o escribe fin para parar.");

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
                        System.out.println("Exponente no valido.");
                        continue;
                    }

                    if (p.existe(e)) {
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

        p.recorrido();
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