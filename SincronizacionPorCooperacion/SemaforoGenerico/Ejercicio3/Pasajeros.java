package ProgrmacionConcurrente.SincronizacionPorCooperacion.SemaforoGenerico.Ejercicio3;

public class Pasajeros implements Runnable{
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
            tren.bajarDelTren();
        }
    }
}
