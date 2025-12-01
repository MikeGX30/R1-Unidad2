package Listas;

import java.util.Scanner;

/**
 * @author GTID141
 * @author 1224100689.mla@gmail.com
 * @author Miguel Lozano
 */
////////////////////////////////////////////////////////////////////////
/**
 * Clase Nodo:
 * Aqui guardamos un caracter y las ligas al nodo anterior y al siguiente, es basicamente la pieza que arma toda la lista doble.
 */
class Nodo {
    private char valor;   // Caracter que guarda el nodo
    private Nodo antes;   // Nodo de la izquierda
    private Nodo despues; // Nodo de la derecha

    public Nodo(char v) {
        this.valor = v;
        this.antes = null;
        this.despues = null;
    }

    // Getters y setters para aplicar encapsulacion

    public char getValor() {
        return valor;
    }

    public void setValor(char valor) {
        this.valor = valor;
    }

    public Nodo getAntes() {
        return antes;
    }

    public void setAntes(Nodo antes) {
        this.antes = antes;
    }

    public Nodo getDespues() {
        return despues;
    }

    public void setDespues(Nodo despues) {
        this.despues = despues;
    }
}

/**
 * Clase Lista:
 * Administra toda la lista doble. Aqui se agregan nodos, se recorre para los dos lados, se ordena y se revisan referencias,
 * el inicio es el primer nodo y el fin es el ultimo.
 */
class Lista {
    private Nodo inicio;
    private Nodo fin;

    public Lista() {
        inicio = fin = null;
    }

    /**
     * agregar:
     * Mete un nuevo nodo al final de la lista. Si esta vacia, ese nodo es inicio y fin, si no, solo se conecta al final.
     */
    public void agregar(char v) {
        Nodo nuevo = new Nodo(v);

        if (inicio == null) {
            inicio = fin = nuevo;
        } else {
            fin.setDespues(nuevo);
            nuevo.setAntes(fin);
            fin = nuevo;
        }
    }

    /**
     * desdeTexto:
     * Convierte un texto en nodos, ignorando espacios, cada caracter se mete como un nodo nuevo.
     */
    public void desdeTexto(String texto) {
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c != ' ') agregar(c);
        }
    }

    /**
     * mostrarAdelante:
     * Recorre desde el inicio hacia adelante mostrando cada valor.
     */
    public void mostrarAdelante() {
        if (inicio == null) {
            System.out.println("Lista vacia");
            return;
        }

        Nodo actual = inicio;
        System.out.print("Adelante: ");
        while (actual != null) {
            System.out.print(actual.getValor() + " ");
            actual = actual.getDespues();
        }
        System.out.println();
    }

    /**
     * mostrarAtras:
     * Recorre desde el final hacia atras.
     */
    public void mostrarAtras() {
        if (fin == null) {
            System.out.println("Lista vacia");
            return;
        }

        Nodo actual = fin;
        System.out.print("Atras: ");
        while (actual != null) {
            System.out.print(actual.getValor() + " ");
            actual = actual.getAntes();
        }
        System.out.println();
    }

    /**
     * ordenarBurbuja:
     * Hace una burbuja en la lista doble intercambiando valores.
     */
    public void ordenarBurbuja() {
        if (inicio == null || inicio.getDespues() == null) return;

        boolean cambio;
        do {
            cambio = false;
            Nodo actual = inicio;

            while (actual.getDespues() != null) {
                if (actual.getValor() > actual.getDespues().getValor()) {
                    char temp = actual.getValor();
                    actual.setValor(actual.getDespues().getValor());
                    actual.getDespues().setValor(temp);
                    cambio = true;
                }
                actual = actual.getDespues();
            }
        } while (cambio);
    }

    /**
     * ordenarInsercion:
     * Insercion directa pero aplicada sobre nodos ligados hacia atras.
     */
    public void ordenarInsercion() {
        if (inicio == null || inicio.getDespues() == null) return;

        Nodo actual = inicio.getDespues();

        while (actual != null) {
            char valor = actual.getValor();
            Nodo anterior = actual.getAntes();

            while (anterior != null && anterior.getValor() > valor) {
                anterior.getDespues().setValor(anterior.getValor());
                anterior = anterior.getAntes();
            }

            if (anterior == null) {
                inicio.setValor(valor);
            } else {
                anterior.getDespues().setValor(valor);
            }

            actual = actual.getDespues();
        }
    }

    /**
     * contar:
     * Recorre toda la lista y cuenta nodos, no tiene ciencia.
     */
    public int contar() {
        int total = 0;
        Nodo actual = inicio;

        while (actual != null) {
            total++;
            actual = actual.getDespues();
        }
        return total;
    }

    /**
     * mostrarReferencias:
     * Muestra que nodo apunta a cual, para revisar que todo este bien armado.
     */
    public void mostrarReferencias() {
        if (inicio == null) {
            System.out.println("Lista vacia");
            return;
        }

        Nodo actual = inicio;
        System.out.println("\n=== REFERENCIAS ===");

        while (actual != null) {
            String antes = (actual.getAntes() == null) ? "null" : String.valueOf(actual.getAntes().getValor());
            String despues = (actual.getDespues() == null) ? "null" : String.valueOf(actual.getDespues().getValor());

            System.out.println("Nodo: " + actual.getValor() +
                    " | Antes: " + antes +
                    " | Despues: " + despues);

            actual = actual.getDespues();
        }
    }
}

/**
 * Clase principal:
 * Aqui solo pedimos el texto, armamos la lista, mostramos todo el recorrido, revisamos referencias y luego aplicamos el ordenamiento.
 */
public class Ejercicio5L {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Lista lista = new Lista();

        System.out.println("=== LISTA DOBLE DE CARACTERES ===");
        System.out.print("Ingresa una cadena: ");
        String texto = entrada.nextLine();

        lista.desdeTexto(texto);

        System.out.println("\n--- ORIGINAL ---");
        lista.mostrarAdelante();
        lista.mostrarAtras();
        System.out.println("Total caracteres: " + lista.contar());

        lista.mostrarReferencias();

        System.out.println("\n--- ORDENANDO ---");
        lista.ordenarBurbuja();

        System.out.println("\n--- ORDENADA ---");
        lista.mostrarAdelante();
        lista.mostrarAtras();
        lista.mostrarReferencias();

        entrada.close();
        System.out.println("\n=== FIN DEL PROGRAMA ===");
    }
}