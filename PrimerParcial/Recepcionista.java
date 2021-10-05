package ProgrmacionConcurrente.PrimerParcial;

public class Recepcionista implements Runnable{
    
    private CentroHemoterapia centroHem;
    
    public Recepcionista(CentroHemoterapia cH){
        centroHem = cH;
    }

    @Override
    public void run() {
        while(true){
            organizaExtracciones();
            centroHem.atiendeLLamado();
        }
    }

    private void organizaExtracciones(){
        System.out.println("Almacenando extracciones en heladeras");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
