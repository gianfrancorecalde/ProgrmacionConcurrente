package ProgrmacionConcurrente.PrimerParcial;

public class Donante implements Runnable{
    
    private CentroHemoterapia centroHem;
    
    public Donante(CentroHemoterapia cH){
        centroHem = cH;
    }

    @Override
    public void run() {
        centroHem.llamarAlCentro();
        System.out.println("El donante llega al centro");
        espera();
        centroHem.recibeTurnoParaControlClinico();
        espera();
        centroHem.recibeTurnoParaExtraccion();
        centroHem.desayunando();
        terminaDeDesayunar();

        
    }

    private void espera(){
        System.out.println("Esperando ser atendido");
        try {
            Thread.sleep(2000); // simula viaje
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    private void terminaDeDesayunar(){
        try {
            Thread.sleep(2000); // simula viaje
            System.out.println("Se retira del centro de hemoterapia");
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
