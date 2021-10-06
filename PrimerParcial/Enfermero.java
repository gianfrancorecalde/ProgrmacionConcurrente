package ProgrmacionConcurrente.PrimerParcial;

public class Enfermero implements Runnable {

    private CentroHemoterapia centroHem;
    
    public Enfermero(CentroHemoterapia cH){
        centroHem = cH;
    }
    
    private void realizandoExtracion(){
        try {
            System.out.println("Realizando la extraccion de sangre");
            Thread.sleep(1000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Override
    public void run() {
        centroHem.atiendeDonanteParaExtraccion();
        realizandoExtracion();
        centroHem.finalizaExtraccion();
    }

}
