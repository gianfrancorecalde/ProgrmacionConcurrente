package ProgrmacionConcurrente.SincronizacionPorCooperacion.SemaforoGenerico.Ejercicio3;

public class Main {
    
    public static void main(String[] args) {
        TrenTuristico tren = new TrenTuristico(3);
        Thread vendedor = new Thread(new VendedorTicket(tren), "Vendedor");
        Thread encargado = new Thread(new ControlTren(tren), "EncargadoTren");
        vendedor.start();
        encargado.start();
        /* Thread [] arr = new Thread[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Thread(new Pasajeros(tren), "Pasajero " +i);
            arr[i].start();
        } */
        new Thread(new Pasajeros(tren), "Pasajero").start();

    }
}
