package ProgrmacionConcurrente.SincronizacionPorCooperacion.SemaforoGenerico.Ejercicio5;

import java.util.Random;
import java.util.concurrent.Semaphore;

import ProgrmacionConcurrente.Synchronized.Ejercicio1.Turno;

public class Main {

    public static void main(String[] args) {
        Mirador mir = new Mirador(3);
        new Thread(new Encargado(mir), "Encargado").start();
        for (int i = 0; i < 5; i++) {
            new Thread(new Persona(mir), "Persona "+i).start();
        }
    }
}

class Mirador {

    Semaphore escalones; // Simula los escalones disponibles donde esperan las personas 
    Semaphore tobogan1;  // Semaforo binario para el primer tobogan
    Semaphore tobogan2;  // Semforo binario para el segundo tobogan
    Semaphore avisoEncargado;   // Avisar al encargado que hay al menos una persona esperando en la escalera  
    Semaphore mutexEncargado;   // Simula la exclusion mutua con el encargado 
    Semaphore avisarPersona;    // Avisa a la persona que puede descender por el tobogan

    int toboganDisponible;  // Varibale mediadora para que el encargado comunique a la persona el tobogan asignado
    boolean tobogan1Hab;
    boolean tobogan2Hab;

    public Mirador(int escalonesDeEscalera) {

        escalones = new Semaphore(escalonesDeEscalera);
        tobogan1 = new Semaphore(1);
        tobogan2 = new Semaphore(1);
        avisarPersona = new Semaphore(0);
        avisoEncargado = new Semaphore(0);
        mutexEncargado = new Semaphore(1,true);
        toboganDisponible = 0;
        tobogan1Hab = true;
        tobogan2Hab = true;


    }// Constructor 

    public void subirEscalera(){
        try {
            escalones.acquire();
            System.out.println( Thread.currentThread().getName() +  " Esta en un escalon de la escalera");
            mutexEncargado.acquire();
            System.out.println( Thread.currentThread().getName() + " Esperando al encargado ");
        } catch (Exception e) {
            //TODO: handle exception
        }
        avisoEncargado.release();
    } // en run de persona

    public void permitirDescender() {
        try {
            avisoEncargado.acquire();
                if(tobogan1Hab){
                    toboganDisponible = 1;
                }else{
                    if (tobogan2Hab) {
                        toboganDisponible = 2;
                    } else {
                        if (new Random().nextInt(1)+1 == 1) {
                            toboganDisponible = 1;
                        } else {
                            toboganDisponible = 2;
                        }
                    } 
                }
                avisarPersona.release();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }// en run de encargado
    
    public int metodoX(){
        try {
            avisarPersona.acquire();
        } catch (Exception e) {
            //TODO: handle exception
        }
        escalones.release();
        System.out.println(Thread.currentThread().getName() + " le toca el tobogan "+toboganDisponible);
        return toboganDisponible;
    }

    public void descendiendoPorTobogan1(){
        try {
            tobogan1.acquire();
        } catch (Exception e) {
            //TODO: handle exception
        }
        tobogan1Hab = false;
        toboganDisponible = 0;
        System.out.println(Thread.currentThread().getName() + " descendiendo por tobogan 1");
        mutexEncargado.release();
    }// en run de persona

    public void descendiendoPorTobogan2(){
        try {
            tobogan2.acquire();
            tobogan2Hab = false;
        } catch (Exception e) {
            //TODO: handle exception
        }
        toboganDisponible = 0;
        System.out.println(Thread.currentThread().getName() +" descendiendo por tobogan 2");
        mutexEncargado.release();
    }// en run de persona

    public void salidaTobogan(int toboganAsignado){
        if(toboganAsignado == 1){
            System.out.println(Thread.currentThread().getName() + " termino su descenso por el tobogan 1");
            tobogan1Hab = true;
            tobogan1.release();
        }else{
            if(toboganAsignado == 2){
                System.out.println(Thread.currentThread().getName() + " termino su descenso por el tobogan 2");
                tobogan2Hab = true;
                tobogan2.release();
            }
        }
    }// en run de persona

} // clase Mirador (RECURSO COMPARTIDO)

class Persona implements Runnable{
    static Mirador mir;
    int toboganAsignado;

    public Persona(Mirador m){
        mir = m;
        toboganAsignado = 0;
    }

    private void descendiendo(){
        try {
            System.out.println("Descendiendo");
            Thread.sleep(1000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Override
    public void run() {
        mir.subirEscalera();
        toboganAsignado =  mir.metodoX();
        if(toboganAsignado == 1){
            mir.descendiendoPorTobogan1();
        }else{
            if (toboganAsignado == 2) {
                mir.descendiendoPorTobogan2();
            }
        }
        descendiendo();
        mir.salidaTobogan(toboganAsignado);
    }
}// clase Persona

class Encargado extends Persona implements Runnable {


    public Encargado(Mirador m){
        super(m);
    }

    @Override
    public void run() {
        while(true){
            mir.permitirDescender();
        }
    }
}// Clase encargado