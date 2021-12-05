package ProgrmacionConcurrente.PracticaRecuperatorio;

import java.util.Random;

public class Dificil2 {
    
    public static void main(String[] args) {
        
        Fila f = new Fila();
        new Thread(new Control(f)).start();
        for (int i = 0; i < 10; i++) {
            new Thread(new Soltero(f,-1), " Soltero "+i).start();
            new Thread(new Pareja(f),"Pareja "+i).start();
        }
    }
}

class Fila {

    private int turnoBailarFila1;
    private int turnoBailarFila2;

    public Fila(){
        turnoBailarFila1 = 0;
        turnoBailarFila2 = 0;
    }

    public synchronized void ingresarSoltero(int fila){

        if(new Random().nextInt(2) == 0 || fila == 1){
            // fila 1
            try {
                while (turnoBailarFila1 == 0) {
                    this.wait();
                }
            } catch (Exception e) {
                //TODO: handle exception
            }
            turnoBailarFila1--;
        }else{
            // fila 2
            try {
                while (turnoBailarFila2 == 0) {
                    this.wait();
                }
            } catch (Exception e) {
                //TODO: handle exception
            }
            turnoBailarFila2--;
        }
    }

    public synchronized void ingresarPareja(){
        // divido la pareja en dos solteros
        new Thread(new Soltero(this,1), Thread.currentThread().getName()+" (en f1)").start();
        new Thread(new Soltero(this,2), Thread.currentThread().getName()+" (en f2)").start();
    }

    public synchronized void largarTurnos(){
        turnoBailarFila1 = 1;
        turnoBailarFila2 = 1;
        this.notifyAll();
    }
}



class Soltero implements Runnable {

    private Fila fila;
    private int filaAsignada; 

    public Soltero(Fila f, int num){
        fila = f;
        filaAsignada = num;
    }

    private void bailar(){
        try {
            System.out.println(Thread.currentThread().getName() + " sale a bailar");
            Thread.sleep(1000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        System.out.println(Thread.currentThread().getName() + " ingresa al hotel");
        fila.ingresarSoltero(filaAsignada);
        bailar();
    }
}

class Pareja implements Runnable {

    private Fila fila;

    public Pareja(Fila f){
        fila = f;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        fila.ingresarPareja();
    }
}

class Control implements Runnable {

    private Fila fila;

    public Control(Fila f){
        fila = f;
    }

    private void esperar(){
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            fila.largarTurnos();
            esperar();
        }
    }
}

class ParejaBaile implements Runnable {

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }
}
