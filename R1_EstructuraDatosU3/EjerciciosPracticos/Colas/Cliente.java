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
public class Cliente {
    // cuando llego el cliente
    private int tiempoLlegada;

    // constructor
    public Cliente(int tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }

    // obtener el tiempo de llegada
    public int getTiempoLlegada() {
        return tiempoLlegada;
    }
}