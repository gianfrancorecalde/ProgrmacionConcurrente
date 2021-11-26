package ProgrmacionConcurrente.MetodosDeSincronizacion;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    
    public static void main(String[] args) {
        
        BlockingQueue<String> q = new ArrayBlockingQueue<String>(20);

        Planta p = new Planta(q);
        new Thread(new Empaquetador(p), "Empaquetador").start();
        for (int i = 0; i < 3; i++) {
            new Thread(new Embotellador(p), "Embotellador").start();            
        }
    }
}

class Planta{

    BlockingQueue<String> cola;

    public Planta(BlockingQueue<String> q){
        cola = q;
    }

    public synchronized void ponerBotella(){

        try {
            cola.put("botella");
        } catch (Exception e) {
            //TODO: handle exception
        }

        if(cola.size() == 10){
            notify();
        }
    }

    public synchronized void sacarBotella(){
        try {
            while (cola.size() < 10) {
                wait();
            }

            for (int i = 0; i < 10; i++) {
                cola.take();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }

        
    }
}

class Embotellador implements Runnable {

    Planta planta;

    public Embotellador(Planta p){
        planta = p;
    }

    void ponerBotella(){
        
    }

    @Override
    public void run() {
        while (true) {
            planta.ponerBotella();
            System.out.println( Thread.currentThread().getName() + " Esta poniendo botellas");
        }
    }
}

class Empaquetador implements Runnable {

    Planta planta;
    
    public Empaquetador(Planta p){
        planta = p;
    }

    @Override
    public void run() {
        while (true) {
            planta.sacarBotella();
            System.out.println(Thread.currentThread().getName() + " Empaquetandote botella");
        }
    }
}
