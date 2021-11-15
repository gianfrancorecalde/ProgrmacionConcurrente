package ProgrmacionConcurrente.SincronizacionPorCooperacion.SemaforoGenerico.Ejercicio6;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {

    }
}

class Cuerda {

    Semaphore cruzar, mutexCruzar, enEspera;
    int capacidadMax;
    int cantBabuinosAlaDerecha;
    int cantBabuinosAlaIzquiersa;
    int babuinoHabilitado; // 0 -> babuinos de la derecha y 1 -> babuinos de la izquierda
    int cantBabuinosCruzando;

    public Cuerda(int capacidad) {
        capacidadMax = capacidad;
        cruzar = new Semaphore(capacidad);
        enEspera = new Semaphore(0);
        mutexCruzar = new Semaphore(1);
        cantBabuinosAlaDerecha = 0;
        cantBabuinosAlaIzquiersa = 0;
        babuinoHabilitado = -1;
        cantBabuinosCruzando = 0;
    }

    public void tomarCuerda(int lado) {
        try {
                
            mutexCruzar.acquire();
            if (babuinoHabilitado == -1) {
                babuinoHabilitado = lado;
                mutexCruzar.release();
            } else {
                if (babuinoHabilitado != lado) {
                    mutexCruzar.release();
                    System.out.println(" No puede cruzar");
                    enEspera.acquire();
                } else {
                    mutexCruzar.release();
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void puedeCruzar(){
        try {
            cruzar.acquire();
            cantBabuinosCruzando++;
            System.out.println(" comieza a cruzar");
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    public void dejarCuerda(){

    }
}

