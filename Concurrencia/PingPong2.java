package ProgrmacionConcurrente.Concurrencia;

public class PingPong2 implements Runnable {

    private String cadena;
    private int delay;

    public PingPong2(String cartel, int cantMs) {
        cadena = cartel;
        delay = cantMs;
    }

    public void run() {
        for (int i = 1; i < delay /100; i++) {
            System.out.print(cadena + "");
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.err.println("falla");
            }
        }
    }
}
