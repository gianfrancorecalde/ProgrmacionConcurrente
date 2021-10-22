package ProgrmacionConcurrente.Recalde_FAI2757;

public class Entrega implements Runnable{
    
    private PuestoBuceo pB;

    public Entrega(PuestoBuceo puesto){
        pB = puesto;
    }


    @Override
    public void run() {
        pB.entregarSnorkel();
        espera();
        pB.entregarAntiparra();
    }

    private void espera(){
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
