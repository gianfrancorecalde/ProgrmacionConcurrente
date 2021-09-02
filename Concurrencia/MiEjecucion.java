package ProgrmacionConcurrente.Concurrencia;

public class MiEjecucion extends Thread{
    public void run(){
        System.out.println("en RUN()");
        ir();
    }
    public void ir(){
        System.out.println("en IR()");
        hacerMas();
    }
    public void hacerMas(){
        System.out.println("En la pila");
    }
}
