package ProgrmacionConcurrente.SincronizacionPorCooperacion.SemaforoGenerico.Ejercicio3;

import java.util.concurrent.Semaphore;

public class TrenTuristico {
    
    private Semaphore asientos;
    private Semaphore mutex;
    private Semaphore mutex2;
    private Semaphore trenLleno;
    private Semaphore compraTicket;
    private Semaphore ticket;
    private Semaphore avisos;
    private int asientosOcupados;
    private int cantAsientos;

    public TrenTuristico(int cant){

        asientos = new Semaphore(cant,true); // establezco un orden de para que los pasajero que no pudieron viajear en un viaje, lo hagan en el siguiente
        avisos = new Semaphore(0);
        mutex = new Semaphore(1);
        mutex2 = new Semaphore(1);
        trenLleno = new Semaphore(0);
        compraTicket = new Semaphore(0);
        ticket = new Semaphore(0);
        asientosOcupados = 0;
        cantAsientos = cant;

    }

    public void comprarTicket(){
        try {
            mutex.acquire();
            System.out.println(Thread.currentThread().getName() + " Es atendido");
        } catch (Exception e) {
            //TODO: handle exception
        }
        compraTicket.release();
    }

    public void atenderCliente(){
        try {
            compraTicket.acquire();
            System.out.println(Thread.currentThread().getName() + " entrega el ticket al cliente");
        } catch (Exception e) {
            //TODO: handle exception
        }
        ticket.release();
    }

    public void recibirTicket(){
        try {
            ticket.acquire();
        } catch (Exception e) {
            //TODO: handle exception
        }
        System.out.println(Thread.currentThread().getName() + " recibe los tickets pedidos");
        mutex.release();
    }

    public void subirAlTren(){
        try {
                asientos.acquire();
                mutex2.acquire();
                asientosOcupados++;
                System.out.println(Thread.currentThread().getName() + " toma un asiento");
                if(asientosOcupados == cantAsientos){
                    trenLleno.release();
                }
        } catch (Exception e) {
            //TODO: handle exception
        }
        mutex2.release();
    }

    public void arrancarTren(){
        try {
            trenLleno.acquire();
            System.out.println("Tren comienza recorrido");
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void detenerTren(){
        System.out.println("Finaliza el recorrido");
        avisos.release(asientosOcupados);
    }

    public void bajarDelTren(){
        try {
            System.out.println("termina aca");
            avisos.acquire();
            System.out.println(Thread.currentThread().getName() + "baja del tren");
        } catch (Exception e) {
            //TODO: handle exception
        }
        asientos.release();
        asientosOcupados--;
    }

}
