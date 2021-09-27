package ProgrmacionConcurrente.SeccionCritica.Ejercicio1;

public class SynchronizedObjectCounter {
    private int c = 0;

    public void increment() {
        synchronized ((Integer) c) {
            c++;
            System.out.println(Thread.currentThread().getName() + " acutal crece " + c);
        }
        // E ste e lemento d ebe s er c asteado a Integer
    }

    public void decrement() {
        synchronized (this) {
            c--;
            System.out.println(Thread.currentThread().getName() + " acutal decrece " + c);
        }
    }

    public int value() {
        return c;
    }
}
