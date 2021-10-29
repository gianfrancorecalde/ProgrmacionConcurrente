package ProgrmacionConcurrente.SincronizacionPorCooperacion.SemaforoGenerico.Ejercicio5;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {

}

class Mirador {

    Semaphore escalones;
    Semaphore tobogan1;
    Semaphore tobogan2;
    Semaphore avisoEncargado;
    Semaphore liberarPersonaParaTobogan1;
    Semaphore liberarPersonaParaTobogan2;
    Semaphore mutex;
    Semaphore avisarPersona;

    public Mirador(int escalonesDeEscalera) {

        escalones = new Semaphore(escalonesDeEscalera);
        tobogan1 = new Semaphore(1);
        liberarPersonaParaTobogan1 = new Semaphore(0);
        liberarPersonaParaTobogan2 = new Semaphore(0);
        avisarPersona = new Semaphore(0);
        tobogan2 = new Semaphore(1);
        avisoEncargado = new Semaphore(0);
        mutex = new Semaphore(2,true);

    }

    public void subirEscalera(){
        try {
            escalones.acquire();
            mutex.acquire();
            System.out.println( " Esta en un escalon de la escalera");
        } catch (Exception e) {
            //TODO: handle exception
        }
        avisoEncargado.release();
    }

    public void permitirDescender() {

        try {
            avisoEncargado.acquire();
            if(new Random().nextInt(2) == 1){
                tobogan1.acquire();
                liberarPersonaParaTobogan1.release();
                System.out.println(" Desciende por el tobogan 1");
            }else{
                tobogan2.acquire();
                liberarPersonaParaTobogan2.release();
                System.out.println(" Desciende por el tobogan 2");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    

    public void seOcupaTobogan1(){
        try {
            liberarPersonaParaTobogan1.acquire(); 
        } catch (Exception e) {
            //TODO: handle exception
        }
        System.out.println("una persona esta descendiendo por ");
    }

    public void seDesocupaTobogan1(){
        System.out.println(" la persona termino su descenso");
        tobogan1.release();
        avisarPersona.release();
    }

    public void salidaTobogan2(){
        System.out.println(" termino de descender por el tobogan 2");
        tobogan2.release();
        mutex.release();
    }

    public void saleDelTobogan(){
        try {
            avisarPersona.acquire();
        } catch (Exception e) {
            //TODO: handle exception
        }
        System.out.println(" se retira del tobogan");
        mutex.release();
    }
}

class Persona implements Runnable{
    static Mirador mir;

    public Persona(Mirador m){
        mir = m;
    }

    @Override
    public void run() {
        mir.subirEscalera();
        mir.saleDelTobogan();
    }
}

class Encargado extends Persona implements Runnable {


    public Encargado(Mirador m){
        super(m);
    }

    @Override
    public void run() {
        mir.permitirDescender();
    }
}

class Tobogan implements Runnable{
    
    Mirador mir;

    public Tobogan(Mirador m){
        mir = m;
    }

    public void run() {
        mir.toboganDisponible();
        mir.toboganOcupado();

    }
}