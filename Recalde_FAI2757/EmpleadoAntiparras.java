package ProgrmacionConcurrente.Recalde_FAI2757;

public class EmpleadoAntiparras extends Persona {
    
    public EmpleadoAntiparras(PuestoBuceo puesto){
        super(puesto);
    }

    @Override
    public void run() {
        while(true){
            while(pB.geta() >0){
                pB.entregarAntiparra();
                
            }
            while(pB.geta() < 1){
                pB.recepcionaAntiparra();
            }
        }
    }
}
