/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pilas;

/**
 * @author GTID141 
 * @author 1224100689.mla@gmail.com
 * @author miguel Lozano
 */

// Pila simple para numeros
class PilaNumeros {
    private int[] numeros;    // donde guardo los numeros
    private int tope;         // ultimo numero que meti
    private int capacidad;    // tamaño maximo

    // Creo la pila
    public PilaNumeros(int tamaño) {
        capacidad = tamaño;
        numeros = new int[capacidad];
        tope = -1;
    }

    // Agrego un numero
    public void push(int num) {
        if (tope < capacidad - 1) {
            tope++;
            numeros[tope] = num;
        }
    }

    // Saco un numero
    public int pop() {
        if (tope >= 0) {
            int num = numeros[tope];
            tope--;
            return num;
        }
        return -1;
    }

    // Reviso si esta vacia
    public boolean estaVacia() {
        return tope == -1;
    }
}

public class Ejercicio09 {
    public static void main(String[] args) {
        // Tengo una lista de numeros
        int[] lista = {1, 2, 3, 4};

        // Creo la pila para invertirlos
        PilaNumeros pila = new PilaNumeros(lista.length);

        // Meto todos los numeros en la pila
        for (int i = 0; i < lista.length; i++) {
            pila.push(lista[i]);
        }

        // Los saco y los muestro (salen al reves)
        System.out.print("Lista invertida: ");
        while (!pila.estaVacia()) {
            System.out.print(pila.pop() + " ");
        }
        System.out.println();
    }
}