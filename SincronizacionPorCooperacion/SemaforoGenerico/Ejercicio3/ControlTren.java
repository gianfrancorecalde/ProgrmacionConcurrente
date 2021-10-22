package ProgrmacionConcurrente.SincronizacionPorCooperacion.SemaforoGenerico.Ejercicio3;

public class ControlTren implements Runnable{  
    
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
        tren.arrancarTren();
        recorrido();
        tren.detenerTren();
    }
}

