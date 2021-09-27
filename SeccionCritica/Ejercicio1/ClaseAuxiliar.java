package ProgrmacionConcurrente.SeccionCritica.Ejercicio1;

public class ClaseAuxiliar implements Runnable {

    SynchronizedCounter algo = new SynchronizedCounter();
    //SynchronizedObjectCounter algo = new SynchronizedObjectCounter();

    public void run() {
        try {
            algo.increment();
            algo.increment();
            
            algo.decrement();
            System.out.println(algo.value());
        } catch (Exception e) {
            System.err.println("Algo salio mal");
        }
    }
}
