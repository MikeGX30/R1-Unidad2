package Listas;

import java.util.Random;
import java.util.Scanner;

/**
 * @author GTID141 
 * @author 1224100689.mla@gmail.com
 * @author miguel Lozano
 */
////////////////////////////////////////////////////////////////////////
/**
 * Clase Nodo:
 * Aqui guardamos un numero y la referencia al siguiente nodo, basicamente es la pieza basica de la lista
 */
class Nodo {
    private int dato;       // Valor que guarda el nodo
    private Nodo siguiente; // Apunta al que sigue

    public Nodo(int valor) {
        this.dato = valor;
        this.siguiente = null;
    }

    public int getDato() {
        return dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}

/**
 * Clase ListaEnlazada:
 * Administra toda la lista, y aqui se agregan, muestran y eliminan nodos, la idea es que el inicio
 * es el primer nodo y fin es el ultimo
 */
class ListaEnlazada {
    private Nodo inicio;
    private Nodo fin;

    public ListaEnlazada() {
        inicio = fin = null;
    }

/**
* Agrega un nodo al final de la lista:
* Si estaa vacia, este nuevo nodo es inicio y fin si no, solo lo conectamos al final.
*/
    public void agregar(int valor) {
        Nodo nuevo = new Nodo(valor);

        if (inicio == null) {
            inicio = fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
    }

/**
* Muestra toda la lista en consola:
* Si no hay nada, pues avisamos, si hay nodos los recorremos uno por uno.
*/
    public void mostrar() {
        if (inicio == null) {
            System.out.println("La lista esta vacia");
            return;
        }

        Nodo actual = inicio;
        System.out.print("Lista: ");
        while (actual != null) {
            System.out.print(actual.getDato() + " -> ");
            actual = actual.getSiguiente();
        }
        System.out.println("null");
    }

/**
* Elimina los nodos cuyo valor sea mayor al limite dado osea filtramos la lista.
*/
    public void eliminarMayores(int limite) {
        if (inicio == null) {
            System.out.println("La lista esta vacia");
            return;
        }

        // Mientras el primer nodo sea mayor al limite, lo brincamos
        while (inicio != null && inicio.getDato() > limite) {
            inicio = inicio.getSiguiente();
        }

        // Si ya no quedo nada, pues fin tambien se va
        if (inicio == null) {
            fin = null;
            System.out.println("Todos los nodos fueron eliminados.");
            return;
        }

        // Recorremos la lista eliminando nodos intermedios
        Nodo actual = inicio;
        while (actual.getSiguiente() != null) {
            // Si el siguiente nodo supera el limite, lo quitamos
            if (actual.getSiguiente().getDato() > limite) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());

                // Si quitamos el ultimo, actual pasa a ser el nuevo fin
                if (actual.getSiguiente() == null) {
                    fin = actual;
                }
            } else {
                actual = actual.getSiguiente();
            }
        }
    }

/**
* Genera varios numeros aleatorios y los mete a la lista y es para no meterlos a mano
*/
    public void generarAleatorios(int cantidad, int maximo) {
        Random r = new Random();
        for (int i = 0; i < cantidad; i++) {
            agregar(r.nextInt(maximo) + 1);
        }
    }
}

/**
 * Clase principal:
 * Aqui ya nadamas pedimos datos, generamos la lista y aplicamos las funciones.
 */
public class Ejercicio1L {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        ListaEnlazada lista = new ListaEnlazada();

        System.out.println("=== MANIPULACION DE LISTA ENLAZADA ===");

        System.out.print("¿Cuantos numeros deseas generar? ");
        int cantidad = entrada.nextInt();

        System.out.print("¿Valor maximo para los numeros aleatorios? ");
        int maximo = entrada.nextInt();

        lista.generarAleatorios(cantidad, maximo);

        System.out.println("\n--- Lista Original ---");
        lista.mostrar();

        System.out.print("\nIngresa el valor limite para eliminar nodos: ");
        int limite = entrada.nextInt();

        lista.eliminarMayores(limite);

        System.out.println("\n--- Lista despues de eliminar nodos > " + limite + " ---");
        lista.mostrar();

        entrada.close();
        System.out.println("\n=== PROGRAMA TERMINADO ===");
    }
}