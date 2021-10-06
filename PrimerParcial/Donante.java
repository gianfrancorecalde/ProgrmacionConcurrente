package ProgrmacionConcurrente.PrimerParcial;

public class Donante implements Runnable{
    
    private CentroHemoterapia centroHem;
    
    public Donante(CentroHemoterapia cH){
        centroHem = cH;
    }

    @Override
    public void run() {
        centroHem.llamarAlCentro();
        centroHem.esperaFinalizacionDeLaRecepcion();
        System.out.println(Thread.currentThread().getName() + "El donante llega al centro");
        centroHem.perdirTurno();
        centroHem.esperandoControlClinico();
        centroHem.proseguirAExtraccion();
        centroHem.despideAlEnfermero();
        centroHem.desayunando();
        terminaDeDesayunar();

        
    }

    private void terminaDeDesayunar(){
        try {
            Thread.sleep(2000); // simula viaje
            System.out.println(Thread.currentThread().getName() + " se retira del centro de hemoterapia");
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
