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
import java.util.Random;


public class AtencionClienteCola{
    public static void main(String[] args) {
        // variables para la simulacion
        int minutosTotales = 7 * 60; // 7 horas son 420 minutos
        int clientesAtendidos = 0;
        int maxFila = 0;
        int sumaTamanioFila = 0;
        int tiempoMaxEspera = 0;
        int tiempoAperturaCuartaCaja = -1;
        boolean cuartaCajaAbierta = false;

        // cola de clientes esperando
        Queue<Cliente> fila = new LinkedList<>();
        Random rand = new Random();

        // crear las 4 cajas, la 4 solo si es necesario
        Caja[] cajas = {new Caja(), new Caja(), new Caja(), new Caja()};

        // simulacion minuto a minuto
        for (int minuto = 1; minuto <= minutosTotales; minuto++) {
            // llega un cliente nuevo cada minuto
            fila.add(new Cliente(minuto));

            // si hay mas de 20 en la fila abrir la cuarta caja
            if (fila.size() > 20 && !cuartaCajaAbierta) {
                cuartaCajaAbierta = true;
                tiempoAperturaCuartaCaja = minuto;
                System.out.println("Cuarta caja abierta en el minuto: " + minuto);
            }

            // revisar cada caja activa
            for (int i = 0; i < (cuartaCajaAbierta ? 4 : 3); i++) {
                Caja caja = cajas[i];

                // si la caja esta libre y hay gente esperando
                if (caja.estaLibre() && !fila.isEmpty()) {
                    Cliente cliente = fila.poll();
                    
                    // calcular cuanto tiempo espero el cliente
                    int tiempoEspera = minuto - cliente.getTiempoLlegada();
                    tiempoMaxEspera = Math.max(tiempoMaxEspera, tiempoEspera);

                    // tiempo de atencion random entre 2 y 5 minutos
                    int tiempoAtencion = 2 + rand.nextInt(4); 
                    caja.atenderCliente(tiempoAtencion);
                    clientesAtendidos++;
                }

                // pasar un minuto en la caja
                caja.pasarUnMinuto();
            }

            // ir guardando estadisticas
            sumaTamanioFila += fila.size();
            maxFila = Math.max(maxFila, fila.size());
        }

        // calcular el promedio de la fila
        double promedioFila = (double) sumaTamanioFila / minutosTotales;

        // mostrar resultados
        System.out.println("\n RESULTADOS DE LA SIMULACION:");
        System.out.println("Total de clientes atendidos: " + clientesAtendidos);
        System.out.println("Tamano medio de la fila: " + String.format("%.2f", promedioFila));
        System.out.println("Tamano maximo de la fila: " + maxFila);
        System.out.println("Tiempo maximo de espera: " + tiempoMaxEspera + " min");
        if (cuartaCajaAbierta)
            System.out.println("Cuarta caja abierta en el minuto: " + tiempoAperturaCuartaCaja);
        else
            System.out.println("La cuarta caja nunca se abrio.");
    }
}