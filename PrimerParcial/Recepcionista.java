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
            recepcionando();
            centroHem.finalizaLlamada();
        }
    }

    private void organizaExtracciones(){
        System.out.println("Almacenando extracciones de sangre en heladeras");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    private void recepcionando(){
        System.out.println("El donante esta siendo recepcionado");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
