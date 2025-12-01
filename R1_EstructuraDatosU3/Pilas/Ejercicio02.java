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
// Clase para manejar una pila de nombres
class PilaNombres {
    private String[] nombres;  // donde guardo los nombres
    private int tope;          // posicion del ultimo nombre
    private int capacidad;     // cuantos nombres caben

    // Creo la pila con el tamaño que le pase
    public PilaNombres(int tamaño) {
        capacidad = tamaño;
        nombres = new String[capacidad];
        tope = -1;
    }

    // Agrego un nombre a la pila
    public void push(String nombre) {
        if (tope < capacidad - 1) {
            tope++;
            nombres[tope] = nombre;
        }
    }

    // Saco el ultimo nombre que agregue
    public String pop() {
        if (tope >= 0) {
            String nombre = nombres[tope];
            tope--;
            return nombre;
        }
        return null;  // si no hay nada regreso null
    }

    // Reviso si la pila tiene algo o no
    public boolean estaVacia() {
        return tope == -1;
    }
}

public class Ejercicio02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Creo la pila para guardar hasta 50 nombres
        PilaNombres pila = new PilaNombres(50);
        String nombre;

        // Pido nombres hasta que escriban FIN
        while (true) {
            System.out.print("Ingrese un nombre (FIN para salir): ");
            nombre = sc.nextLine();

            // Si escriben FIN me salgo del ciclo
            if (nombre.equalsIgnoreCase("FIN")) break;

            // Guardo el nombre en la pila
            pila.push(nombre);
        }

        // Ahora los saco y los muestro al reves
        System.out.println("Nombres en orden inverso:");
        while (!pila.estaVacia()) {
            System.out.println(pila.pop());
        }
    }
}