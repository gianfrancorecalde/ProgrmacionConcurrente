package ProgrmacionConcurrente.TpObligatorio3;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {

        /* Carpinteria c = new Carpinteria(4);

        for (int i = 0; i < 5; i++) {
            new Thread(new Carpintero(c, 1), "Carpitenro tipo 1 " + i).start();
            new Thread(new Carpintero(c, 2), "Carpitenro tipo 2 " + i).start();
            new Thread(new Carpintero(c, 3), "Carpitenro tipo 3 " + i).start();
            new Thread(new Ensamblador(c), "Ensamblador " + i).start();
        } */

        Libro l = new Libro();
        for (int i = 0; i < 3; i++) {
            new Thread(new Escritor(l), "Escritor "+i).start();
            new Thread(new Lector(l), "Lector "+i).start(); 
        }
    }
}

class Carpinteria {

    Lock cerrojo = new ReentrantLock();
    Condition esperandoPartes;
    Condition esperandoEnsamblado;
    Condition esperandoDisponibilidad;
    Condition esperandoPart1Hab;
    Condition esperandoPart2Hab;
    Condition esperandoPart3Hab;
    Condition esperandoHabEnsambladores;
    Condition esperandoVolverAlTrabajoParte1;
    Condition esperandoVolverAlTrabajoParte2;
    Condition esperandoVolverAlTrabajoParte3;
    Condition esperandoTrabajar;

    int cant;
    int ensambladorDisponible;
    boolean part1;
    boolean part2;
    boolean part3;

    public Carpinteria(int CANTIDAD) {
        cant = CANTIDAD;
        esperandoEnsamblado = cerrojo.newCondition();
        esperandoEnsamblado = cerrojo.newCondition();
        
        ensambladorDisponible = 0;
    }

    public int entregarParte1() throws InterruptedException {
        cerrojo.lock();
        try {
            while (ensambladorDisponible == 0) {
                esperandoHabEnsambladores.await();
            }
            while (part1) {
                esperandoPart1Hab.await();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        part1 = true;
        esperandoPartes.signal();
        System.out.println(Thread.currentThread().getName() + "------- 🛠 Le entrega una parte del tipo 1 al ensamblador");
        cerrojo.unlock();
        return ensambladorDisponible;
    }

    public void volverAlTrabajoParte1(int ensambladorAsignado) throws InterruptedException{
        esperandoTrabajar.await();
        while (ensambladorDisponible != ensambladorAsignado) {
            esperandoVolverAlTrabajoParte1.await();
        }
    }

    public int entregarParte2() throws InterruptedException {
        cerrojo.lock();
        try {
            while (ensambladorDisponible == 0) {
                esperandoHabEnsambladores.await();
            }
            while (part2) {
                esperandoPart2Hab.await();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        part2 = true;
        esperandoPartes.signal();
        System.out.println(Thread.currentThread().getName() + "------- 🛠 Le entrega una parte del tipo 2 al ensamblador");
        cerrojo.unlock();
        return ensambladorDisponible;
        
    }

    public int entregarParte3() throws InterruptedException {
        
        cerrojo.lock();
        try {
            while (ensambladorDisponible == 0) {
                esperandoHabEnsambladores.await();
            }
            while (part3) {
                esperandoPart3Hab.await();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        part3 = true;
        esperandoPartes.signal();
        System.out.println(Thread.currentThread().getName() + "------- 🛠 Le entrega una parte del tipo 3 al ensamblador");
        cerrojo.unlock();
        return ensambladorDisponible;
       
    }

    public void ensamblando_partes(int id) throws InterruptedException {
        cerrojo.lock();
            while(ensambladorDisponible != 0){
                esperandoDisponibilidad.await();
            }
            ensambladorDisponible = id;
            esperandoHabEnsambladores.signalAll();
            
            while(!part1 && !part2 && !part3){
                esperandoPartes.await();
            }
            part1 = false;
            esperandoPart1Hab.signalAll();
            part2 = false;
            esperandoPart2Hab.signalAll();
            part3 = false;
            esperandoPart3Hab.signalAll();
            
            ensambladorDisponible = 0;
            esperandoDisponibilidad.signalAll();
        System.out.println(" esta ensamblando");
        cerrojo.unlock();
    }

    public void avisarACarpinteros(int id) throws InterruptedException{
        cerrojo.lock();
        while(ensambladorDisponible != 0){
            esperandoDisponibilidad.await();
        }
        ensambladorDisponible = id;
        esperandoTrabajar.signalAll();
        esperandoPart1Hab.signalAll();
        esperandoPart2Hab.signalAll();
        esperandoPart3Hab.signalAll();
        cerrojo.unlock();
    }
}

class Carpintero implements Runnable {

    Carpinteria carp;
    int tipoParte;
    int ensambladorAsignado; 

    public Carpintero(Carpinteria c, int tipo) {
        tipoParte = tipo;
        carp = c;
        ensambladorAsignado = 0;
    }

    @Override
    public void run() {
        while (true) {
            ensambladorAsignado = carp.entregarParte();
            System.out.println(Thread.currentThread().getName() + " esta fabricando parte de tipo " + tipoParte);

        }
    }
}

class Ensamblador implements Runnable {

    Carpinteria carp;
    int id;

    public Ensamblador(Carpinteria c, int id) {
        carp = c;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            try {
                carp.ensamblando_partes();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
/* ---------------------------------------------------------------------------------------------------------------- */
/* ---------------------------------------------------------------------------------------------------------------- */
/* ---------------------------------------------------------------------------------------------------------------- */
/* ---------------------------------------------------------------------------------------------------------------- */
/* ---------------------------------------------------------------------------------------------------------------- */


class Libro {

    Semaphore lectores;
    Semaphore escritores;
    Semaphore mutex1;

    int n_lectores;

    public Libro() {
        lectores = new Semaphore(1);
        escritores = new Semaphore(1);
        mutex1 = new Semaphore(1);
        n_lectores = 0;
    }

    public void empezarLectura() {
        try {
            lectores.acquire();
            mutex1.acquire();
            n_lectores++;
            if (n_lectores == 1){
                escritores.acquire();
            }
            //System.out.println(Thread.currentThread().getName() + " empieza a leer");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mutex1.release();
        lectores.release();
    }

    public void terminarLectura() {
        try {
            mutex1.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        n_lectores--;
        //System.out.println(Thread.currentThread().getName() + " termina de leer");
        if (n_lectores == 0){
            escritores.release();
        }
        mutex1.release();

    }

    public void comenzarEscritura() {
        try {
            escritores.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //System.out.println(Thread.currentThread().getName() + "Empieza a escribir");
    }

    public void terminarEscritura() {
        //System.out.println(Thread.currentThread().getName() + "Termina de escribir");
        escritores.release();
    }
}

class Lector implements Runnable {

    Libro l;

    public Lector(Libro libro) {
        l = libro;
    }

    private void leyendo(){
        try {
            System.out.println(Thread.currentThread().getName() +"Esta leyendo");
            Thread.sleep(100);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Override
    public void run() {
        while (true) {
            l.empezarLectura();
            leyendo();
            l.terminarLectura();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                //TODO: handle exception
            }
        }
    }
}

class Escritor implements Runnable {

    Libro l;

    public Escritor(Libro libro) {
        l = libro;
    }

    private void escribir(){
        try {
            System.out.println(Thread.currentThread().getName() +"Esta escribiendo");
            Thread.sleep(100);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Override
    public void run() {
        while (true) {
            l.comenzarEscritura();
            escribir();
            l.terminarEscritura();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                //TODO: handle exception
            }
        }
    }
}