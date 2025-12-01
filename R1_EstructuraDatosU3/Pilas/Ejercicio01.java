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
class PilaEnteros {
    private int[] elementos;  // aqui guardo los numeros
    private int tope;         // me dice donde esta el ultimo elemento
    private int capacidad;    // tamaño maximo de la pila

    // Constructor - crea la pila con un tamaño
    public PilaEnteros(int tamaño) {
        capacidad = tamaño;
        elementos = new int[capacidad];
        tope = -1;  // -1 significa que esta vacia
    }

    // Mete un numero a la pila
    public void push(int valor) {
        if (tope < capacidad - 1) {
            tope++;
            elementos[tope] = valor;
        }
    }

    // Saca el ultimo numero que meti
    public int pop() {
        if (tope >= 0) {
            int valor = elementos[tope];
            tope--;
            return valor;
        }
        return -1;  // si esta vacia regreso -1
    }

    // Para mostrar lo que tiene la pila
    public void mostrar() {
        System.out.print("Contenido actual: [");
        for (int i = 0; i <= tope; i++) {
            System.out.print(elementos[i]);
            if (i < tope) System.out.print(", ");
        }
        System.out.println("]");
    }
}

public class Ejercicio01 {
    public static void main(String[] args) {
        // Creo mi pila con capacidad para 10 numeros
        PilaEnteros pila = new PilaEnteros(10);

        // Le meto varios numeros
        pila.push(5);
        pila.push(10);
        pila.push(15);
        pila.push(20);

        // Saco 2 numeros
        pila.pop();
        pila.pop();

        // Muestro lo que queda
        pila.mostrar();
    }
}