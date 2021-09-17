package ProgrmacionConcurrente.Synchronized;

public class DualSync {

    private Object syncObject = new Object();
    private int dato = 5;

    public synchronized void f() {
        for (int i = 0; i < 5; i++) {
            dato = dato * 4;
            System.out.println("f()" + dato);
            Thread.yield(); // cede la CPU el subprocesos o hilo q este ejecutando el metodo f()
        }
    }

    public void g() {
        synchronized (syncObject) {
            for (int i = 0; i < 5; i++) {
                dato = dato + 20;
                System.out.println("g()" + dato);
                Thread.yield(); // cede la CPU el subprocesos o hilo q este ejecutando el metodo g()
            }
        }
    }
}
