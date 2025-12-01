package Listas;

import java.io.*;
import java.util.Scanner;

/**
 * @author GTID141
 * @author 1224100689.mla@gmail.com
 * @author miguel Lozano
 */
////////////////////////////////////////////////////////////////////////
/**
 * Clase Nodo:
 * Aqui guardamos una palabra y la referencia al siguiente nodo.
 * Basicamente es el bloque base de la lista, como una cajita que guarda texto.
 */
class Nodo {
    private String palabra;   // Lo que vamos guardando
    private Nodo siguiente;   // Enlace al siguiente nodo

    public Nodo(String texto) {
        this.palabra = texto;
        this.siguiente = null;
    }

    public String getPalabra() {
        return palabra;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}

/**
 * Clase Lista:
 * Aqui manejamos toda la lista de palabras: agregar, mostrar, eliminar,
 * buscar, leer desde archivo y guardar. Inicio es el primer nodo y fin el ultimo.
 */
class Lista {
    private Nodo inicio;
    private Nodo fin;

    public Lista() {
        inicio = fin = null;
    }

/**
* Agrega una palabra al final de la lista:
* Si esta vacia, inicio y fin son el mismo. Si no, la conectamos al final.
*/
    public void agregar(String texto) {
        Nodo nuevo = new Nodo(texto);
        if (inicio == null) {
            inicio = fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
    }
/**
* Muestra la lista completa:
* Si esta vacia avisamos, si no, la recorremos toda.
*/
    public void mostrar() {
        if (inicio == null) {
            System.out.println("La lista esta vacia");
            return;
        }

        Nodo actual = inicio;
        int i = 1;
        System.out.println("=== LISTA DE PALABRAS ===");
        while (actual != null) {
            System.out.println(i + ". " + actual.getPalabra());
            actual = actual.getSiguiente();
            i++;
        }
        System.out.println("=========================");
    }
/**
* Elimina una palabra si existe:
* Si esta al inicio, solo movemos la referencia, si no, buscamos el nodo previo.
*/
    public boolean eliminar(String texto) {
        if (inicio == null) return false;

        // Caso 1: esta al inicio
        if (inicio.getPalabra().equals(texto)) {
            inicio = inicio.getSiguiente();
            if (inicio == null) fin = null;
            return true;
        }

        // Caso 2: esta en medio o final
        Nodo actual = inicio;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getPalabra().equals(texto)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());

                // si eliminamos el ultimo, actual es el nuevo fin
                if (actual.getSiguiente() == null) {
                    fin = actual;
                }
                return true;
            }
            actual = actual.getSiguiente();
        }

        return false;
    }
/**
* Busca una palabra en la lista:
* Recorremos hasta encontrarla o llegar al final.
*/
    public boolean buscar(String texto) {
        Nodo actual = inicio;
        while (actual != null) {
            if (actual.getPalabra().equals(texto)) return true;
            actual = actual.getSiguiente();
        }
        return false;
    }
/**
* Lee palabras desde un archivo:
* Cada palabra se limpia, se hace minuscula y se agrega a la lista.
*/
    public void leerArchivo(String archivo) {
        try (Scanner lector = new Scanner(new File(archivo))) {
            System.out.println("Leyendo desde: " + archivo);

            while (lector.hasNext()) {
                String palabra = lector.next()
                    .replaceAll("[^a-zA-ZaeiounAEIOUN]", "") // quitamos simbolos
                    .toLowerCase();

                if (!palabra.isEmpty()) agregar(palabra);
            }

            System.out.println("Lectura completada.");
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Se creara uno nuevo al guardar.");
        }
    }
/**
* Guarda todas las palabras en el archivo:
* Las escribe separadas por espacio.
*/
    public void guardarArchivo(String archivo) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) {
            Nodo actual = inicio;
            while (actual != null) {
                escritor.write(actual.getPalabra());
                if (actual.getSiguiente() != null) escritor.write(" ");
                actual = actual.getSiguiente();
            }
            System.out.println("Palabras guardadas en: " + archivo);
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }
/**
* Cuenta cuantas palabras hay en la lista:
* Se recorre toda la lista sumando.
*/
    public int contar() {
        int total = 0;
        Nodo actual = inicio;

        while (actual != null) {
            total++;
            actual = actual.getSiguiente();
        }

        return total;
    }
}

/**
 * Clase principal:
 * Aqui hacemos todo lo del menu y la interaccion con el usuario.
 */
public class Ejercicio2L {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Lista lista = new Lista();
        final String ARCHIVO = "palabras.txt";

        System.out.println("=== LISTA DE PALABRAS DESDE ARCHIVO ===");
        lista.leerArchivo(ARCHIVO);

        int opcion;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Ver palabras");
            System.out.println("2. Agregar palabra");
            System.out.println("3. Eliminar palabra");
            System.out.println("4. Buscar palabra");
            System.out.println("5. Guardar y salir");
            System.out.print("Opcion: ");
            opcion = entrada.nextInt();
            entrada.nextLine(); // limpiar enter

            switch (opcion) {
                case 1 -> {
                    lista.mostrar();
                    System.out.println("Total: " + lista.contar());
                }
                case 2 -> {
                    System.out.print("Nueva palabra: ");
                    String nueva = entrada.nextLine().trim().toLowerCase();
                    if (!nueva.isEmpty()) {
                        lista.agregar(nueva);
                        System.out.println("Agregada: '" + nueva + "'");
                    } else {
                        System.out.println("No puede estar vacia.");
                    }
                }
                case 3 -> {
                    System.out.print("Palabra a eliminar: ");
                    String eliminar = entrada.nextLine().trim().toLowerCase();
                    if (lista.eliminar(eliminar)) {
                        System.out.println("Eliminada: '" + eliminar + "'");
                    } else {
                        System.out.println("No encontrada.");
                    }
                }
                case 4 -> {
                    System.out.print("Palabra a buscar: ");
                    String buscar = entrada.nextLine().trim().toLowerCase();
                    if (lista.buscar(buscar)) {
                        System.out.println("Si esta en la lista.");
                    } else {
                        System.out.println("No esta en la lista.");
                    }
                }
                case 5 -> {
                    lista.guardarArchivo(ARCHIVO);
                    System.out.println("Hasta luego!");
                }
                default -> System.out.println("Opcion invalida.");
            }

        } while (opcion != 5);

        entrada.close();
    }
}