package ProgrmacionConcurrente.SincronizacionPorCooperacion.SemaforoGenerico.Ejercicio6;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Cuerda cuerda = new Cuerda(2);
        for (int i = 0; i <5 ; i++) {
            new Thread(new BabuinoIzq(cuerda), "Babuino Izq "+i).start();
            new Thread(new BabuinoDer(cuerda), "Babuino Der "+i).start();
        }
    }
}

class Cuerda {

    Semaphore cruzarIzq;
    Semaphore cruzarDer;
    Semaphore mutexCant;
    Semaphore mutexCruzar;
    Semaphore enEspera;
    
    int capacidadMax;
    int cantBabuinosAlaDerecha;
    int cantBabuinosAlaIzquiersa;
    int babuinoHabilitado; // 0 -> babuinos de la derecha y 1 -> babuinos de la izquierda
    int cantBabuinosCruzando;

    public Cuerda(int capacidad) {
        
        cruzarDer = new Semaphore(capacidad);
        cruzarIzq = new Semaphore(capacidad);
        mutexCant = new Semaphore(1);
        //mutexCruzar = new Semaphore(1);
        //enEspera = new Semaphore(1);
        
        capacidadMax = capacidad;
        //cantBabuinosAlaDerecha = 0;
        //cantBabuinosAlaIzquiersa = 0;
        babuinoHabilitado = -1;
        cantBabuinosCruzando = 0;
    }

    /* public void tomarCuerda(int lado) {
        try {
            
                mutexCruzar.acquire();
                if (babuinoHabilitado == -1) {
                    babuinoHabilitado = lado;
                } else {
                    if (babuinoHabilitado != lado || cantBabuinosCruzando == capacidadMax) {
                        mutexCruzar.release();
                        System.out.println(Thread.currentThread().getName() + " No puede cruzar");
                        enEspera.acquire();
                    }
                }
            
            mutexCruzar.release();

        } catch (Exception e) {
            // TODO: handle exception
        }
    } */

    public void puedeCruzarIzq(int lado){
        try {
            cruzarIzq.acquire();
            mutexCant.acquire();
            cantBabuinosCruzando++;
            if(babuinoHabilitado == -1){
                cruzarDer.acquire(capacidadMax);
                babuinoHabilitado = lado;
            }
            System.out.println(Thread.currentThread().getName() + " comieza a cruzar");
        } catch (Exception e) {
            //TODO: handle exception
        }
        mutexCant.release();
    }

    public void puedeCruzarDer(int lado){
        try {
            cruzarDer.acquire();
            mutexCant.acquire();
            cantBabuinosCruzando++;
            if(babuinoHabilitado == -1){
                cruzarIzq.acquire(capacidadMax);
                babuinoHabilitado = lado;
            }
            System.out.println(Thread.currentThread().getName() + " comieza a cruzar");
        } catch (Exception e) {
            //TODO: handle exception
        }
        mutexCant.release();
    }

    public void dejarCuerda(){
        try {
            mutexCant.acquire();
            cantBabuinosCruzando--;
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mutexCant.release();
        System.out.println(Thread.currentThread().getName() + " Deja la cuerda");
        if(cantBabuinosCruzando == 0){
            babuinoHabilitado = -1;
            cruzarDer.release(capacidadMax);
            cruzarIzq.release(capacidadMax);
        }
    }
}

class Babuino {

    Cuerda c;

    public Babuino(Cuerda cuerda){
        c = cuerda;
    } 

    protected void cruzando(){
        try {
            System.out.println("Cruzando cuerda");
            Thread.sleep(1000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

}

class BabuinoIzq extends Babuino implements Runnable{
    
    static int tipo = 1;

    public BabuinoIzq(Cuerda cuerda){
        super(cuerda);
    }

    @Override
    public void run() {
        c.puedeCruzarIzq(tipo);
        cruzando();
        c.dejarCuerda();
    }
}

class BabuinoDer extends Babuino implements Runnable{
    
    static int tipo = 0;

    public BabuinoDer(Cuerda cuerda){
        super(cuerda);
    }

    @Override
    public void run() {
        c.puedeCruzarDer(tipo);
        cruzando();
        c.dejarCuerda();
    }
}

