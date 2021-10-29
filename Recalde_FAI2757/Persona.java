package ProgrmacionConcurrente.Recalde_FAI2757;

public class Persona implements Runnable{
    
    protected PuestoBuceo pB;

    public Persona(PuestoBuceo puesto){
        pB = puesto;
    }

    public void bucea(){
        try {
            System.out.println(Thread.currentThread().getName() + " esta buceando");
            Thread.sleep(10000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }


    @Override
    public void run() {
        pB.pideSnorkel();
        pB.recibeSnorkel();
        pB.pideAntiparra();
        pB.recibeAntiparra();
        bucea();
        pB.devuelveSnorkel();
        pB.devuelveAntiparra();
    }
}
