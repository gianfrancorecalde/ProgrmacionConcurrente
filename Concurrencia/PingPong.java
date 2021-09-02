package ProgrmacionConcurrente.Concurrencia;

public class PingPong extends Thread {
    private String cadena;
    private int delay;

    public PingPong(String cartel, int cantMs){
        cadena = cartel;
        delay = cantMs;
    }

    @Override
    public void run() {
        for(int i=1; i<delay/100;i++){
            System.out.print(cadena+i+" ");
            try{
                Thread.sleep(delay);
            }catch(InterruptedException e){
                System.out.println("falla");
            }
        }
    }
}
