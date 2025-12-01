/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjercicioColas;

/**
 * @author GTID141 
 * @author 1224100689.mla@gmail.com
 * @author miguel Lozano
 */
import java.util.LinkedList;
import java.util.Queue;

public class EjercicioComparacion{

    // metodo que compara si dos colas son iguales
    public static boolean compararColas(Queue<Integer> cola1, Queue<Integer> cola2) {
        
        // si tienen diferente tamano ya no son iguales
        if (cola1.size() != cola2.size()) {
            return false;
        }

        // colas temporales para no perder los datos originales
        Queue<Integer> aux1 = new LinkedList<>();
        Queue<Integer> aux2 = new LinkedList<>();

        boolean sonIguales = true;

        // revisar elemento por elemento
        while (!cola1.isEmpty()) {
            int dato1 = cola1.remove();
            int dato2 = cola2.remove();

            // guardar en las colas temporales
            aux1.add(dato1);
            aux2.add(dato2);

            // si alguno es diferente ya no son iguales
            if (dato1 != dato2) {
                sonIguales = false;
            }
        }

        // regresar los datos a las colas originales
        while (!aux1.isEmpty()) {
            cola1.add(aux1.remove());
            cola2.add(aux2.remove());
        }

        return sonIguales;
    }

    public static void main(String[] args) {
        // crear dos colas para probar
        Queue<Integer> colaA = new LinkedList<>();
        Queue<Integer> colaB = new LinkedList<>();

        // llenar las colas con los mismos datos
        colaA.add(10);
        colaA.add(20);
        colaA.add(30);

        colaB.add(10);
        colaB.add(20);
        colaB.add(30);

        // comparar y mostrar resultado
        boolean resultado = compararColas(colaA, colaB);

        System.out.println("Las colas son iguales?: " + resultado);
    }
}
