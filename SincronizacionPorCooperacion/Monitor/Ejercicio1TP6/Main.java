package ProgrmacionConcurrente.SincronizacionPorCooperacion.Monitor.Ejercicio1TP6;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Main {

    public static void main(String[] args) {
        SalaFumadores sala = new SalaFumadores();
        new Thread(new Agente(sala), "Agente").start();
        for (int i = 0; i < 3; i++) {
            new Thread(new Fumador(i + 1, sala), "Fumador "+i).start();
        }
    }// main

}// class Main

class SalaFumadores {

    Lock cerrojo = new ReentrantLock();
    Condition noCambiar = cerrojo.newCondition();
    Condition noFumar = cerrojo.newCondition();
    int primerIngrediente;      // Ingrendiente que coloca agente
    int segundoIngrediente;     // ingrediente que coloca agente

    public SalaFumadores() {
        primerIngrediente = 0;
        segundoIngrediente = 0;    
    }

    // 1 --> representa tabaco
    // 2 --> representa papel
    // 3 --> representa fosforo

    public void entraFumar(int id) {
        try {
            cerrojo.lock();
            while ((id + primerIngrediente + segundoIngrediente) != 6) {
                System.out.println(Thread.currentThread().getName() + " No tiene lo ingrediente");
                noFumar.await();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println(Thread.currentThread().getName() + " comienza a fumar");
        cerrojo.unlock();
        
    }// entrarFumar()

    public void terminaFumar() {
        try {
            cerrojo.lock();
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        primerIngrediente = 0;
        segundoIngrediente = 0;
        System.out.println(Thread.currentThread().getName() + " termina de fumar");
        noCambiar.signalAll();
        cerrojo.unlock();

    }// terminarFumar()

    public void colocar(int ingrediente) {
        try {
            cerrojo.lock();
            while (primerIngrediente != 0 && segundoIngrediente != 0) {
                noCambiar.await();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        if (primerIngrediente == 0) {
            System.out.println("Agrega ingrediente "+ingrediente+ " como primerIngrediente");
            primerIngrediente = ingrediente;
        } else {
            if(ingrediente != primerIngrediente){
                System.out.println("Agrega ingrediente "+ingrediente+ " como segundoIngrediente");
                segundoIngrediente = ingrediente;
            }
        }
        
        System.out.println(Thread.currentThread().getName() + " termino de colocar los ingrediente en la mesa");
        
        if (primerIngrediente != 0 && segundoIngrediente != 0) {
            noFumar.signalAll();
        }

        cerrojo.unlock();
        
    }// colocar()

}// class SalaFumadores

class Fumador implements Runnable {

    private int id;
    private SalaFumadores sala;

    public Fumador(int id, SalaFumadores sala) {
        this.id = id;
        this.sala = sala;
    }// constructor

    public void run() {
        while (true) {
            try {
                sala.entraFumar(id);
                System.out.println(Thread.currentThread().getName() + " está fumando");
                Thread.sleep(1000);
                sala.terminaFumar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } // catch
        } // while
    }// run
}// class Fumador

class Agente implements Runnable {

    private SalaFumadores sala;
    private Random r;

    public Agente(SalaFumadores sala) {
        this.sala = sala;
        r = new Random();
    } // constructor

    public void run() {
        while (true) {
            sala.colocar(r.nextInt(3) + 1);
        } // while
    }// run

}// class Agente
