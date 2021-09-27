package ProgrmacionConcurrente.SeccionCritica.Ejercicio1;

public class SynchronizedCounter {
    private int c = 0;

    public synchronized void increment() {
        c++;
        System.out.println(Thread.currentThread().getName() + " actual incremento "+c);
    }

    public  void decrement() {
        c--;
        System.out.println(Thread.currentThread().getName() + " actual decremento " + c);
    }

    public synchronized int value() {
        return c;
    }
}
