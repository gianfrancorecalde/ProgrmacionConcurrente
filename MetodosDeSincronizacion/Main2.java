package ProgrmacionConcurrente.MetodosDeSincronizacion;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main2 {
    
    public static void main(String[] args) {
        BlockingQueue q = new ArrayBlockingQueue(1);

        new Thread(new Empaquetador1(q), "Empaquetador").start();
        for (int i = 0; i < 3; i++) {
            new Thread(new Embotellador1(q), "Embotellador "+i).start();            
        }
    }
}

class Embotellador1 implements Runnable {

    BlockingQueue cola;
    int botellas;

    public Embotellador1(BlockingQueue q){
        cola = q;
        botellas = 0;
    }

    void ponerBotellas(){
        botellas++;
        System.out.println(Thread.currentThread().getName()+ " coloco botella "+botellas);
        try {
            if(botellas == 10){
                botellas = 0;
                System.out.println(Thread.currentThread().getName()+ " lleno la caja de botellas");
                cola.put("Pone las 10 botellas");
                System.out.println(Thread.currentThread().getName());
            }
            Thread.sleep(2000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Override
    public void run() {
        while (true) {
            ponerBotellas();
        }
    }
}

class Empaquetador1 implements Runnable {

    BlockingQueue cola;
    
    public Empaquetador1(BlockingQueue q){
        cola = q;
    }
    void sacarBotellas(){
        try {
            cola.take();
            System.out.println(Thread.currentThread().getName() + " Empaquetó las botellas");
            Thread.sleep(200);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Override
    public void run() {
        while (true) {
            sacarBotellas();
        }
    }
}
