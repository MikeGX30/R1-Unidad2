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
public class Caja {
    // tiempo que falta para terminar de atender
    private int tiempoRestante;

    // constructor empieza en 0 porque esta libre
    public Caja() {
        this.tiempoRestante = 0;
    }

    // ver si la caja esta libre
    public boolean estaLibre() {
        return tiempoRestante == 0;
    }

    // empezar a atender un cliente
    public void atenderCliente(int tiempoAtencion) {
        this.tiempoRestante = tiempoAtencion;
    }

    // cada minuto que pasa se reduce el tiempo
    public void pasarUnMinuto() {
        if (tiempoRestante > 0) {
            tiempoRestante--;
        }
    }
}
