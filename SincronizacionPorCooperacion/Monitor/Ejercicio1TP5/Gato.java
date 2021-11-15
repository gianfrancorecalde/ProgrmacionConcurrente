package ProgrmacionConcurrente.SincronizacionPorCooperacion.Monitor.Ejercicio1TP5;

public class Gato implements Runnable{
    
    private Comedor cm;

    public Gato(Comedor comedor){
        cm = comedor;
    }

    @Override
    public void run() {
        cm.comerGato();
        if(cm.getCantGatos()!= 0){
            comer();
            cm.terminarGato();
        }else{
            System.out.println(" Comedor ocupado por Perros");
        }
    }

    private void comer(){
        try {
            System.out.println(Thread.currentThread().getName() + " esta comiendo");
            Thread.sleep(1000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
