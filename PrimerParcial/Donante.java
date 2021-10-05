package ProgrmacionConcurrente.PrimerParcial;

public class Donante implements Runnable{
    
    private CentroHemoterapia centroHem;
    
    public Donante(CentroHemoterapia cH){
        centroHem = cH;
    }

    @Override
    public void run() {
        centroHem.llamarAlCentro();
        espera();
        centroHem.esperandoControl();
        espera();
        centroHem.esperandoExtraccion();
        centroHem.entregaDeCertificadoDeDonacion();
        centroHem.desayunando();

        
    }

    private void espera(){
        System.out.println("Esperando ser atendido");
        try {
            Thread.sleep(2000); // simula viaje
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
