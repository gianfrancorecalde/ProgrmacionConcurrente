package ProgrmacionConcurrente.SincronizacionPorCooperacion.Monitor.Ejercicio6TP6;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Main {
    
    public static void main(String[] args) {
        
        Observatorio s = new Observatorio();
        for (int i = 0; i < 10; i++) {
            new Thread(new Visitante(s, false), "Visitante").start();
            new Thread(new Investigador(s)).start();
            new Thread(new PersonalDeMantenimiento(s)).start();
        }
        new Thread(new Visitante(s, true), "Visitante discapacitado").start();
        new Thread(new Visitante(s, true), "Visitante discapacitado").start();
    }
}

class Observatorio{

    Lock cerrojo = new ReentrantLock();
    Condition esperandoParaVisitar;
    Condition esperandoLibro;
    Condition esperandoParaInvestigacion;
    Condition esperandoParaLimpieza;

    int cantVisitantes;
    int cantPersonalMant;
    int cantInvestigadores;
    int cantVisitantesEnSillaDeRuedas;
    boolean libroOcupado;

    public Observatorio(){
        esperandoLibro = cerrojo.newCondition();
        esperandoParaInvestigacion = cerrojo.newCondition();
        esperandoParaLimpieza = cerrojo.newCondition();
        esperandoParaVisitar = cerrojo.newCondition();

        cantInvestigadores = 0;
        cantPersonalMant = 0;
        cantVisitantes = 0;
        cantVisitantesEnSillaDeRuedas = 0;
        libroOcupado = false;
    } 

    public void entrarObservatorio(boolean discapacitado){
        cerrojo.lock();
        try {
            while(cantVisitantes == 3 || (cantVisitantesEnSillaDeRuedas > 0 && cantVisitantes == 2)){
                esperandoParaVisitar.await();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        cantVisitantes++;
        if(discapacitado){
            cantVisitantesEnSillaDeRuedas++;
        }
        System.out.println(cantVisitantes + " "+ cantVisitantesEnSillaDeRuedas);
        cerrojo.unlock();
    }
    
    public void salirObservatorio(boolean discapacitado){
        cerrojo.lock();
        cantVisitantes--;
        esperandoParaVisitar.signal();
        if (discapacitado) {
            cantVisitantesEnSillaDeRuedas--;
        }
        if(cantVisitantesEnSillaDeRuedas > 0 && cantVisitantes <3)
        if(cantVisitantes == 0){
            esperandoParaInvestigacion.signalAll();
            esperandoParaLimpieza.signalAll();
        }
        System.out.println(cantVisitantes + " "+ cantVisitantesEnSillaDeRuedas);
        cerrojo.unlock();
    }
    
    public void entrarInvertigar(){
        cerrojo.lock();
        try {
            while (cantVisitantes > 0 || cantPersonalMant > 0) {
                esperandoParaInvestigacion.await();
            }
            System.out.println("Investigador observando");
            while (libroOcupado) {
                esperandoLibro.await();
            }
            System.out.println("Investigador anotando");
        } catch (Exception e) {
            //TODO: handle exception
        }
        cantInvestigadores++;
        cerrojo.unlock();
    }

    public void salirDeInvertigar(){
        cerrojo.lock();
        cantInvestigadores--;
        libroOcupado = false;
        esperandoLibro.signal();
        if(cantInvestigadores == 0){
            esperandoParaLimpieza.signalAll();
            esperandoParaVisitar.signalAll();
        }
        cerrojo.unlock();
    }

    public void entrarLimpiar(){
        cerrojo.lock();
        try {
            while (cantInvestigadores > 0 || cantVisitantes > 0) {
                esperandoParaLimpieza.await();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        cantPersonalMant++;
        cerrojo.unlock();
    }

    public void salirDeLimpiar(){
        cerrojo.lock();
        cantPersonalMant--;
        if(cantPersonalMant == 0){
            esperandoParaInvestigacion.signalAll();
            esperandoParaVisitar.signalAll();
        }
        cerrojo.unlock();
    }
}

class Investigador implements Runnable{

    Observatorio sala;

    public Investigador(Observatorio s){
        sala = s;
    }

    @Override
    public void run() {
        sala.entrarInvertigar();
        sala.salirDeInvertigar();
    }
}

class PersonalDeMantenimiento implements Runnable{

    Observatorio sala;

    public PersonalDeMantenimiento(Observatorio s){
        sala = s;
    }  

    private void limpiando(){
        try {
            System.out.println("Limpiando");
            Thread.sleep(1000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Override
    public void run() {
        while (true) {
            sala.entrarLimpiar();
            limpiando();
            sala.salirDeLimpiar();
        }
    }
}

class Visitante implements Runnable{

    Observatorio sala;
    boolean discapacitado; 

    public Visitante(Observatorio s, boolean r){
        sala = s;
        discapacitado = r;
    } 

    private void observando(){
        try {
            System.out.println(Thread.currentThread().getName() + " Observando");
            Thread.sleep(1000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Override
    public void run() {
        sala.entrarObservatorio(discapacitado);
        observando();
        sala.salirObservatorio(discapacitado);
    }
}