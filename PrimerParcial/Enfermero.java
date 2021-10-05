package ProgrmacionConcurrente.PrimerParcial;

public class Enfermero implements Runnable {

    private CentroHemoterapia centroHem;
    
    public Enfermero(CentroHemoterapia cH){
        centroHem = cH;
    }

    @Override
    public void run() {
        centroHem.enEsperaDeDonante();
        generandoCertificado();
        centroHem.entregaDeCertificado();
    }

    private void generandoCertificado(){
        try {
            System.out.println("Generando Certificado");
            Thread.sleep(1000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
