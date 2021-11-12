package ProgrmacionConcurrente.SincronizacionPorCooperacion.Monitor;

public class Perro implements Runnable{
    
    private Comedor cm;

    public Perro(Comedor comedor){
        cm = comedor;
    }

    @Override
    public void run() {
        cm.comerPerro();
        if(cm.getCantPerros()!= 0){
            comer();
            cm.terminarPerro();
        }else{
            System.out.println("comedor ocupados por gatos");
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
