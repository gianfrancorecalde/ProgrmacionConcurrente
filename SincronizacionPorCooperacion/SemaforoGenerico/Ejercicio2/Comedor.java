package ProgrmacionConcurrente.SincronizacionPorCooperacion.SemaforoGenerico.Ejercicio2;

import java.util.concurrent.Semaphore;

public class Comedor {
    
    private Semaphore razaCorrecta;
    private Semaphore razaIncorrecta;
    private Semaphore platos;
    private Semaphore esperar;
    private Semaphore otraRaza;
    private Semaphore mutex;
    private int cantPlatos;
    private int cantPerros;
    private int cantGatos;
    private int cantLugaresOcupados;

    public Comedor(int cantPerrosSeguidos, int cantGatosSeguidos, int cant){

        platos = new Semaphore(cant);
        mutex = new Semaphore(1);
        esperar = new Semaphore(0);
        otraRaza = new Semaphore(0);
        razaCorrecta = new Semaphore(0);
        razaIncorrecta = new Semaphore(cantGatosSeguidos);
        cantPlatos = cant;
        cantLugaresOcupados = 0;
    }

    /* public void verificarComedor(char raza){
        try {
            mutex.acquire();
            if(platos.availablePermits() == cantPlatos){
                animalesComiendo = raza;
                platos.acquire();
                cantLugaresOcupados++;
            }else{
                if(raza == animalesComiendo){
                    razaCorrecta.release();
                }else{
                    razaIncorrecta.release();
                }
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        mutex.release();
    }

    public void comedorConOtraRaza(){
        try {
            otraRaza.acquire();
            animalesComiendo|
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void enEspera(){
        try {
            razaIncorrecta.acquire();
            esperar.acquire();
            otraRaza.release();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void comer(){
        try {
            razaCorrecta.acquire();
            platos.acquire();
            cantLugaresOcupados++;

        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void abandonarComedor(){
        platos.release();
        cantLugaresOcupados--;
    } */

    public void comerGato(){
        try {
            mutex.acquire();
            if (cantPerros == 0) {
                platos.acquire();
                cantGatos++;
                //System.out.println(Thread.currentThread().getName() + " Entre");
                System.out.println(Thread.currentThread().getName() + " Entra al comedor");
            }else{
                esperar.acquire();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        mutex.release();
    }
    
    public void comerPerro() {
        try {
            mutex.acquire();
            if (cantGatos == 0) {
                platos.acquire();
                cantPerros++;
                //System.out.println(Thread.currentThread().getName() + " Entre");
                System.out.println(Thread.currentThread().getName() + " Entra al comedor");
            }else{
                // dead block
                esperar.release();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        mutex.release();
    }

    public void terminarGato(){      
        System.out.println(Thread.currentThread().getName() + " Se retira del comedor");
        cantGatos--;
        if(cantGatos == 0){
            esperar.release();
        }
        platos.release();
    }

    public void terminarPerro(){
        
        System.out.println(Thread.currentThread().getName() + " Se retira del comedor");
        cantPerros--;
        if(cantPerros == 0){
            esperar.release(cantPlatos);
        }
        platos.release();
    }


    

    
}
