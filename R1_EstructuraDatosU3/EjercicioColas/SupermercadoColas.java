/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjercicioColas;

import java.util.LinkedList;
import java.util.Queue;
/**
 * @author GTID141 
 * @author 1224100689.mla@gmail.com
 * @author miguel Lozano
 */

public class SupermercadoColas {

    public static void main(String[] args) {
        
        // cola de carritos disponibles, empezamos con 25
        Queue<Integer> carritos = new LinkedList<>();
        for (int i = 1; i <= 25; i++) {
            carritos.add(i);
        }

        // colas de las tres cajas
        Queue<Integer> caja1 = new LinkedList<>();
        Queue<Integer> caja2 = new LinkedList<>();
        Queue<Integer> caja3 = new LinkedList<>();

        int totalClientes = 40;

        // simular la llegada de clientes
        for (int cliente = 1; cliente <= totalClientes; cliente++) {
            System.out.println("\nLlega el cliente " + cliente + " al supermercado:");

            // si no hay carritos el cliente tiene que esperar
            if (carritos.isEmpty()) {
                System.out.println("No hay carritos disponibles. El cliente " + cliente + " tiene que esperar");
                continue;
            }

            // el cliente toma un carrito
            int carritoTomado = carritos.remove();
            System.out.println("Cliente " + cliente + " toma el carrito " + carritoTomado);

            // ver cual caja tiene menos gente
            int tam1 = caja1.size();
            int tam2 = caja2.size();
            int tam3 = caja3.size();

            // el cliente se forma en la caja con menos gente
            if (tam1 <= tam2 && tam1 <= tam3) {
                caja1.add(cliente);
                System.out.println("Cliente " + cliente + " se forma en la Caja 1");
            } else if (tam2 <= tam1 && tam2 <= tam3) {
                caja2.add(cliente);
                System.out.println("Cliente " + cliente + " se forma en la Caja 2");
            } else {
                caja3.add(cliente);
                System.out.println("Cliente " + cliente + " se forma en la Caja 3");
            }

            // cada 3 clientes que llegan uno termina y devuelve carrito
            if (cliente % 3 == 0) {
                liberarCarrito(carritos, caja1, caja2, caja3);
            }

            // mostrar como esta el supermercado
            mostrarEstado(carritos, caja1, caja2, caja3);
        }
    }
    
    // metodo para cuando un cliente termina y devuelve el carrito
    public static void liberarCarrito(Queue<Integer> carritos, Queue<Integer> c1, Queue<Integer> c2, Queue<Integer> c3) {
        
        // se libera de la primera caja que tenga gente
        if (!c1.isEmpty()) {
            int cliente = c1.remove();
            carritos.add(1); 
            System.out.println(">> Cliente " + cliente + " termino en Caja 1 y devolvio el carrito.");
        } else if (!c2.isEmpty()) {
            int cliente = c2.remove();
            carritos.add(1);
            System.out.println(">> Cliente " + cliente + " termino en Caja 2 y devolvio el carrito.");
        } else if (!c3.isEmpty()) {
            int cliente = c3.remove();
            carritos.add(1);
            System.out.println(">> Cliente " + cliente + " termino en Caja 3 y devolvio el carrito.");
        }
    }

    // metodo para ver como esta todo en el supermercado
    public static void mostrarEstado(Queue<Integer> carritos, Queue<Integer> c1, Queue<Integer> c2, Queue<Integer> c3) {
        System.out.println("------------------------------------------------");
        System.out.println("Carritos disponibles: " + carritos.size());
        System.out.println("Cola Caja 1: " + c1.size() + " clientes");
        System.out.println("Cola Caja 2: " + c2.size() + " clientes");
        System.out.println("Cola Caja 3: " + c3.size() + " clientes");
        System.out.println("------------------------------------------------");
    }
}