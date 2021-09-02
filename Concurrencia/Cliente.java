package ProgrmacionConcurrente.Concurrencia;

public class Cliente extends Thread {
    
    public void run() {
        Recurso.uso();
        System.out.println("soy "+Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
    }
}

