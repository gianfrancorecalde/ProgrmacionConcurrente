package ProgrmacionConcurrente.SeccionCritica.Ejercicio7;

import java.util.concurrent.Semaphore;

public class Taxi {
    
    private Semaphore exclusividad;
    private Semaphore pasajero;
    private Semaphore taxista;
    
    public Taxi(){
        exclusividad = new Semaphore(1);
        pasajero = new Semaphore(0);
        taxista = new Semaphore(0);
    }
    
    public void subir(){
        try {
            exclusividad.acquire(); // dentro del taxi
            System.out.println(Thread.currentThread().getName() + " Taxi Encontrado");
            taxista.release();  // Despierta al taxista
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void atenderCliente(){
        try {
            taxista.acquire(); // se despierta
            System.out.println(Thread.currentThread().getName() + " Se despierta");
            pasajero.release(); // le pregunta el destino
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void indicarDestino(String destino){
        try {
            pasajero.acquire();
            System.out.println(Thread.currentThread().getName() + " se dirigie a "+destino);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void bajar(){
        try {
            System.out.println("Viaje finalizado");
            taxista.release();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void paradaAlcanzada(){
        try {
            taxista.acquire(); // toma su siesta
            System.out.println("Retoma su siesta");
            exclusividad.release();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
