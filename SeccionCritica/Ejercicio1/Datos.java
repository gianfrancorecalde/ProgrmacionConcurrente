package ProgrmacionConcurrente.SeccionCritica.Ejercicio1;

import java.util.concurrent.Semaphore;

public class Datos {
    private int dato;
    private Semaphore sem1;
    private Semaphore sem2;
    private Semaphore sem3;

    public Datos(int nro) {
        dato = nro;
        sem1 = new Semaphore(1);
        sem2 = new Semaphore(0);
        sem3 = new Semaphore(0);
    }

    public int getDato() {
        return dato;
    }

    public void incrementar() {
        try {
            sem1.acquire();
            //System.out.print(dato++ + " "+Thread.currentThread().getName()+"\n");
            sem1.release();
        } catch (InterruptedException e) {
            System.err.println("No funciono el acquire()");
        }
    }
    
    public void proceso1() {
        try {
            sem1.acquire();
            dato+=1;
            System.out.println( dato /* + " "+Thread.currentThread().getName()+"\n" */);
            sem2.release();
        } catch (InterruptedException e) {
            System.err.println("No funciono el acquire()");
        }
    }
    
    public void proceso2() {
        try {
            sem2.acquire();
            dato+=2;
            System.out.println( dato /* + " "+Thread.currentThread().getName()+"\n" */);
            sem3.release();
        } catch (InterruptedException e) {
            System.err.println("No funciono el acquire()");
        }
    
    }

    public void proceso3() {
        try {
            sem3.acquire();
            dato+=3;
            System.out.println( dato /* + " "+Thread.currentThread().getName()+"\n" */);
            sem1.release();
        } catch (InterruptedException e) {
            System.err.println("No funciono el acquire()");
        }
    }


}
