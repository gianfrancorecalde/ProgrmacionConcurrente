package ProgrmacionConcurrente.SincronizacionPorCooperacion.SemaforoGenerico.Ejercicio3;
import java.util.concurrent.Semaphore;
public class Main {
    
    public static void main(String[] args) {
        TrenTuristico tren = new TrenTuristico(3);
        Thread vendedor = new Thread(new VendedorTicket(tren), "Vendedor");
        Thread encargado = new Thread(new ControlTren(tren), "EncargadoTren");
        vendedor.start();
        encargado.start();
        Thread [] arr = new Thread[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Thread(new Pasajeros(tren), "Pasajero " +i);
            arr[i].start();
        } 
        new Thread(new Pasajeros(tren), "Pasajero").start();

    }
}

class TrenTuristico {
    
    private Semaphore asientos;
    private Semaphore mutexTicket;
    private Semaphore mutexAsientosOCupados;
    private Semaphore trenLleno;
    private Semaphore compraTicket;
    private Semaphore ticket;
    private Semaphore avisos;
    private int asientosOcupados;
    private int cantAsientos;

    public TrenTuristico(int cant){

        asientos = new Semaphore(cant,true); // establezco un orden de para que los pasajero que no pudieron viajear en un viaje, lo hagan en el siguiente
        avisos = new Semaphore(0);
        mutexTicket = new Semaphore(1);
        mutexAsientosOCupados = new Semaphore(1);
        trenLleno = new Semaphore(0);
        compraTicket = new Semaphore(0);
        ticket = new Semaphore(0);
        asientosOcupados = 0;
        cantAsientos = cant;

    }

    public void comprarTicket(){
        try {
            mutexTicket.acquire();
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
        mutexTicket.release();
    }

    public void subirAlTren(){
        try {
                asientos.acquire();                         // toma un asiento si es que hay disponibilidad
                mutexAsientosOCupados.acquire();
                asientosOcupados++;
                System.out.println(Thread.currentThread().getName() + " toma un asiento");
                if(asientosOcupados == cantAsientos){
                    trenLleno.release();
                }
        } catch (Exception e) {
            //TODO: handle exception
        }
        mutexAsientosOCupados.release();
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

    public void viajar(){
        try {
            //System.out.println("termina aca");
            avisos.acquire();
            mutexAsientosOCupados.acquire();
            System.out.println(Thread.currentThread().getName() + "baja del tren");
        } catch (Exception e) {
            //TODO: handle exception
        }
        asientos.release();
        asientosOcupados--;
        mutexAsientosOCupados.release();
    }

}

class ControlTren implements Runnable{  
    
    private TrenTuristico tren;

    public ControlTren(TrenTuristico trenT){
        tren =trenT;
    }

    private void recorrido(){
        try {
            System.out.println("Tren en recorrido");
            Thread.sleep(2000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Override
    public void run() {
        while(true){
            tren.arrancarTren();
            recorrido();
            tren.detenerTren();
        }
    }
}

class Pasajeros implements Runnable{
    private TrenTuristico tren;

    public Pasajeros(TrenTuristico trenT){
        tren = trenT;
    }

    

    @Override
    public void run() {
        while (true) {
            tren.comprarTicket();
            tren.recibirTicket();
            tren.subirAlTren();
            tren.viajar();
        }
    }
}

class VendedorTicket implements Runnable{
    
    private TrenTuristico tren;

    public VendedorTicket(TrenTuristico trenT){
        tren = trenT;
    }

    @Override
    public void run() {
        while(true){
            tren.atenderCliente();

        }
        
    }
}
