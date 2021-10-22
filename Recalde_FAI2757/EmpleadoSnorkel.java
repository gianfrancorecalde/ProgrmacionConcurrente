package ProgrmacionConcurrente.Recalde_FAI2757;

public class EmpleadoSnorkel extends Persona {
    
    public EmpleadoSnorkel(PuestoBuceo puesto){
        super(puesto);
    }

    @Override
    public void run() {
        while(true){
            while(pB.getSnorkel() >0){
                pB.entregarSnorkel();
            }
            while(pB.getSnorkel() < 1){
                pB.recepcionaSnorkel();
            }
        }
    }
}
