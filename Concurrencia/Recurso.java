package ProgrmacionConcurrente.Concurrencia;

public class Recurso {
    static void uso(){
        Thread t = Thread.currentThread();
        System.out.println("En recurso: soy"+t.getName());
    }
}
