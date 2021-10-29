package ProgrmacionConcurrente.SincronizacionPorCooperacion.SemaforoGenerico.Ejercicio2;

public class Perro  implements Runnable {
    
    public Perro(){
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
        /* com.comerPerro();
        if(com.perros() > 0){
            comer();
            com.terminarPerro();
        } */
        
    }
}
