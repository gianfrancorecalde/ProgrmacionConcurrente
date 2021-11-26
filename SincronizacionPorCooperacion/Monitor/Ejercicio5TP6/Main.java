package ProgrmacionConcurrente.SincronizacionPorCooperacion.Monitor.Ejercicio5TP6;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        
        Puente p = new Puente(3);
        new Thread(new GestorTrafico(p),"Gestor").start();

        int cant = 10;
        for (int i = 0; i < cant; i++) {
                new Thread(new Auto(p,false,'N'),"CocheNorte "+i).start();
                new Thread(new Auto(p,false,'S'),"CocheSur "+i).start();
        }
    }
}

class Puente{
    
    Semaphore norte;
    Semaphore sur;
    Semaphore avisarGestor;
    Semaphore mutexCant;
    int acumulador;
    int cantSur;
    int cantNorte;
    int limiteDeSeguidos;

    public Puente(int cant){
        limiteDeSeguidos = cant;
        acumulador = 0;
        cantSur = 0;
        cantNorte = 0;

        norte = new Semaphore(0,true);
        sur = new Semaphore(0,true);
        avisarGestor = new Semaphore(1);
        mutexCant = new Semaphore(1);
    }

    public void entrarCochesDelNorte(){
        try {
            norte.acquire();
            mutexCant.acquire();
        } catch (Exception e) {
            //TODO: handle exception
        }
        acumulador++;
        cantNorte++;
        mutexCant.release();
    }
    public void entrarCochesDelSur(){
        try {
            sur.acquire();
            mutexCant.acquire();
        } catch (Exception e) {
            //TODO: handle exception
        }
        acumulador++;
        cantSur++;
        mutexCant.release();
    }
    public void salirCochesDelNorte(boolean soyUltimo){
        try {
            mutexCant.acquire();
        } catch (Exception e) {
            //TODO: handle exception
        }
        acumulador--;
        if(acumulador == 0 || soyUltimo == true){
            avisarGestor.release();
        }
        mutexCant.release();
    }
    public void salirCochesDelSur(boolean soyUltimo){
        try {
            mutexCant.acquire();
        } catch (Exception e) {
            //TODO: handle exception
        }
        acumulador--;
        if(acumulador == 0 || soyUltimo == true){
            avisarGestor.release();
        }
        mutexCant.release();
    }

    public void habilitarCarril(){
        try {
            avisarGestor.acquire();
        } catch (Exception e) {
            //TODO: handle exception
        }
        if(cantNorte == 0&&cantSur == 0){
            norte.release(limiteDeSeguidos);
        }
        if(cantNorte > 0){
            cantNorte = 0;
            sur.release(limiteDeSeguidos);
        }else{
            if(cantSur > 0){
                cantSur = 0;
                norte.release(limiteDeSeguidos);
            }
        }
    }
}

class Auto implements Runnable{

    boolean soyUltimo;
    char tipo;
    Puente puente;

    public Auto(Puente p, boolean y, char x){
        soyUltimo = y;
        tipo = x;
        puente = p;
    }

    private void pasando(){
        try {
            System.out.println(Thread.currentThread().getName() +" Esta pasando por el puente");
            Thread.sleep(1000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    
    @Override
    public void run() {
        
        if(tipo == 'N'){
            puente.entrarCochesDelNorte();
            pasando();
            puente.salirCochesDelNorte(soyUltimo);
        }else{
            if (tipo == 'S') {
                puente.entrarCochesDelSur();
                pasando();
                puente.salirCochesDelSur(soyUltimo);
            }
        }
    }
}

class GestorTrafico implements Runnable{
    
    Puente puente;
    
    public GestorTrafico(Puente p){
        puente = p;
    }

    @Override
    public void run() {
        
        while (true) {
            puente.habilitarCarril();
        }
    }
}

