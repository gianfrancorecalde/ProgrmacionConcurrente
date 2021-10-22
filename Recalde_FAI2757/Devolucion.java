package ProgrmacionConcurrente.Recalde_FAI2757;

public class Devolucion implements Runnable{

    private PuestoBuceo pB;

    public Devolucion(PuestoBuceo puesto){
        pB = puesto;
    }


    @Override
    public void run() {
        pB.devuelveSnorkel();
        espera();
        pB.devuelveAntiparra();
        
    }
    private void espera(){
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
