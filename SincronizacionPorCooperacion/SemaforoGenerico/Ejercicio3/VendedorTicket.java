package ProgrmacionConcurrente.SincronizacionPorCooperacion.SemaforoGenerico.Ejercicio3;

public class VendedorTicket implements Runnable{
    
    private TrenTuristico tren;

    public VendedorTicket(TrenTuristico trenT){
        tren = trenT;
    }

    @Override
    public void run() {
        tren.atenderCliente();
        
    }
}
