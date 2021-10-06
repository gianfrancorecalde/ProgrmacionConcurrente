package ProgrmacionConcurrente.PrimerParcial;

public class MedicoClinico implements Runnable {
    
    private CentroHemoterapia centroHem;
    
    public MedicoClinico(CentroHemoterapia cH){
        centroHem = cH;
    }

    @Override
    public void run() {
        centroHem.atiendeDonanteParaControl();
        realizarControl();
        centroHem.finalizaControl();
        
    }

    private void realizarControl(){
        try {
            System.out.println("Relizando Control medico");
            Thread.sleep(500);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
