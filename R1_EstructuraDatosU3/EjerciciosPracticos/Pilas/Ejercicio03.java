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

// Mi propia clase de pila
class MiPila {
    private int[] datos;      // aqui guardo todo
    private int tope;         // donde esta el ultimo elemento
    private int capacidad;    // tamaño total

    // Constructor para crear la pila
    public MiPila(int tamaño) {
        capacidad = tamaño;
        datos = new int[capacidad];
        tope = -1;  // empieza en -1 porque no hay nada
    }

    // Meto un numero
    public void push(int numero) {
        if (tope < capacidad - 1) {
            tope++;
            datos[tope] = numero;
        }
    }

    // Checo si esta vacia
    public boolean estaVacia() {
        return tope == -1;  // si tope es -1 es que no hay nada
    }
}

public class Ejercicio03 {
    public static void main(String[] args) {
        
        // Creo una pila nueva vacia
        MiPila pila = new MiPila(10);

        // Verifico si esta vacia (deberia decir true)
        System.out.println("Esta vacia la pila? " + pila.estaVacia());

        // Le meto un elemento
        pila.push(1);

        // Vuelvo a checar si esta vacia (ahora deberia decir false)
        System.out.println("Esta vacia la pila? " + pila.estaVacia());
    }
}