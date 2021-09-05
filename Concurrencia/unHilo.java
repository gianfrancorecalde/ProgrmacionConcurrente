package ProgrmacionConcurrente.Concurrencia;

public class unHilo extends Thread {
    
    private String nombreHilo;

    public unHilo(String nombre){
        nombreHilo = nombre;
    }

    public void run(){
        System.out.println("Comenzando "+nombreHilo);
        try {
            for(int contar=0; contar<10; contar++){
                Thread.sleep(400);
                System.out.println("En "+nombreHilo+", el recuento "+contar);
            }
        } catch (Exception e) {
            System.out.println(nombreHilo+"interrumpido.");
        }
        System.out.println("Terminado "+nombreHilo);
    }
}
