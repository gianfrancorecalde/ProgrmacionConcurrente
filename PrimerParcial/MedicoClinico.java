package ProgrmacionConcurrente.PrimerParcial;

public class MedicoClinico implements Runnable {

    private CentroHemoterapia centroHem;

    public MedicoClinico(CentroHemoterapia cH) {
        centroHem = cH;
    }

    @Override
    public void run() {
        while (true) {
            centroHem.atiendeDonanteParaControl();
            realizarControl();
            centroHem.finalizaControl();
            centroHem.comunicarEnfermero();
        }

    }

    private void realizarControl() {
        try {
            System.out.println(Thread.currentThread().getName() + "Relizando Control medico");
            Thread.sleep(500);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
