package ProgrmacionConcurrente.TpObligatorio3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main2 {
    
    public static void main(String[] args) {
        Libro2 l = new Libro2();
        for (int i = 0; i < 100; i++) {
            new Thread(new Escritor2(l), "Escritor "+i).start();
            new Thread(new Lector2(l), "Lector "+i).start(); 
        }
    }
}

class Libro2{

    Lock cerrojo = new ReentrantLock();
    Condition esperandoLectoresTerminar = cerrojo.newCondition();
    Condition esperandoEscritorTerminar = cerrojo.newCondition();
    private boolean hayEscritor; 
    private int lectores; 

    public Libro2(){
        hayEscritor = false;
        lectores = 0;
    }

    public void comenzarLectura(){
        cerrojo.lock();
        try {
            while (hayEscritor) {
                esperandoEscritorTerminar.await();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        lectores++;
        cerrojo.unlock();
    }
    public  void terminarLectura(){
        cerrojo.lock();
        lectores--;
        if(lectores == 0){
            esperandoLectoresTerminar.signal();
        }
        cerrojo.unlock();
    }
    public  void comenzarEscritura(){
        cerrojo.lock();
        try {
            while (lectores > 0) {
                esperandoLectoresTerminar.await();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        hayEscritor = true;
        cerrojo.unlock();
    }
    public  void terminarEscritura(){
        cerrojo.lock();
        hayEscritor = false;
        esperandoEscritorTerminar.signalAll();
        cerrojo.unlock();
    }
}

class Lector2 implements Runnable {

    Libro2 l;

    public Lector2(Libro2 libro) {
        l = libro;
    }

    private void leyendo(){
        try {
            System.out.println(Thread.currentThread().getName() +"Esta leyendo");
            Thread.sleep(1000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Override
    public void run() {
            l.comenzarLectura();
            leyendo();
            l.terminarLectura();

    }
}

class Escritor2 implements Runnable {

    Libro2 l;

    public Escritor2(Libro2 libro) {
        l = libro;
    }

    private void escribir(){
        try {
            System.out.println(Thread.currentThread().getName() +"Esta escribiendo");
            Thread.sleep(1000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Override
    public void run() {
            l.comenzarEscritura();
            escribir();
            l.terminarEscritura();
    }
}
