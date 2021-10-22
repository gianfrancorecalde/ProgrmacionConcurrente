package ProgrmacionConcurrente.SincronizacionPorCooperacion.SemaforoGenerico.Ejercicio2;

public class Gato extends Animal implements Runnable{
    
    public Gato(){
        super();    
    }

    private void comer(){
        try {
            System.err.println(Thread.currentThread().getName() + " Esta comiendo");
            Thread.sleep(1000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Override
    public void run() {
        com.comerGato();
        if(com.gatos() > 0){
            comer();
            com.terminarGato();
        }
        
    }
}
