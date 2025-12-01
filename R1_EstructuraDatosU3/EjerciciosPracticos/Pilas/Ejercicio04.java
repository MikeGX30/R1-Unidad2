/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pilas;

import java.util.Scanner;

/**
 * @author GTID141 
 * @author 1224100689.mla@gmail.com
 * @author miguel Lozano
 */

// Pila para guardar caracteres (letras)
class PilaCaracteres {
    private char[] letras;    // arreglo de letras
    private int tope;         // posicion del ultimo
    private int capacidad;    // cuantas letras caben

    // Hago la pila del tamaño que me digan
    public PilaCaracteres(int tamaño) {
        capacidad = tamaño;
        letras = new char[capacidad];
        tope = -1;
    }

    // Meto una letra
    public void push(char letra) {
        if (tope < capacidad - 1) {
            tope++;
            letras[tope] = letra;
        }
    }

    // Saco una letra
    public char pop() {
        if (tope >= 0) {
            char letra = letras[tope];
            tope--;
            return letra;
        }
        return ' ';  // si no hay nada regreso un espacio
    }

    // Checo si ya no tiene nada
    public boolean estaVacia() {
        return tope == -1;
    }
}

public class Ejercicio04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Pido una palabra
        System.out.print("Ingrese una palabra: ");
        String palabra = sc.nextLine();

        // Creo la pila para las letras
        PilaCaracteres pila = new PilaCaracteres(palabra.length());

        // Meto cada letra de la palabra en la pila
        for (int i = 0; i < palabra.length(); i++) {
            pila.push(palabra.charAt(i));
        }

        // Saco todas las letras y las muestro (salen al reves)
        System.out.print("Invertida: ");
        while (!pila.estaVacia()) {
            System.out.print(pila.pop());
        }
        System.out.println();
    }
}