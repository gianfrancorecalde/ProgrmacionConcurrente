package ProgrmacionConcurrente.SincronizacionPorCooperacion.Monitor.Ejercicio1TP5;

public class Comedor {

    private int cantGatos;
    private int cantPerros;
    private int comedores;

    public Comedor(int cantComedores) {
        cantGatos = 0;
        cantPerros = 0;
        comedores = cantComedores;
    }

    public synchronized void comerGato() {

        try {
            while (cantGatos >= comedores || cantPerros != 0) {

                System.out.println(Thread.currentThread().getName() + " esperando");
                wait();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        cantGatos++;
        System.out.println(Thread.currentThread().getName() + " entró al comedor");

    }

    public synchronized void terminarGato() {
        System.out.println(Thread.currentThread().getName() + " Termino de comer");
        cantGatos--;
        notifyAll();

    }

    public synchronized void comerPerro() {

        try {
            while (cantGatos != 0 || cantPerros >= comedores && comedoresPar()) {
                System.out.println(Thread.currentThread().getName() + " esperando");
                wait();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        cantPerros ++;
        System.out.println(Thread.currentThread().getName() + " entró al comedor");
    }

    public synchronized void terminarPerro() {
        System.out.println(Thread.currentThread().getName() + " Termino de comer");
        cantPerros--;
        notifyAll();
    }

    public synchronized int getCantGatos() {
        return cantGatos;
    }

    public synchronized int getCantPerros() {
        return cantPerros;
    }

    public synchronized boolean comedoresPar(){
        return (comedores - cantPerros*2)%2 == 0;
    }
}
