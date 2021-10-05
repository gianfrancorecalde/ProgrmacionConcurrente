package ProgrmacionConcurrente.PrimerParcial;

import java.util.concurrent.Semaphore;

public class CentroHemoterapia {

    private Semaphore exclusividad;
    private Semaphore sillaTurno;
    private Semaphore medicoClinico;
    private Semaphore enfermero;
    private Semaphore recepcionista;

    public CentroHemoterapia(){
        exclusividad = new Semaphore(1);
        sillaTurno = new Semaphore(0, true);
        medicoClinico = new Semaphore(0);
        enfermero = new Semaphore(0);
        recepcionista = new Semaphore(0);
    }

    
}
