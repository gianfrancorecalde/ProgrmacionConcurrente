package ProgrmacionConcurrente.TpObligatorio3;

import java.util.concurrent.Semaphore;

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

    private final int CANTIDAD; // Cantidad de muebles a fabricar
    private int producidos = 0;
    private Semaphore mutex_ensamblado; // Semaforo para que el ensamblador que lo tome trabaje con exclusividad
    private Semaphore sem_parte_1; // Semaforo para carpintero que fabricar la parte 1
    private Semaphore sem_parte_2; // Semaforo para carpintero que fabricar la parte 2
    private Semaphore sem_parte_3; // Semaforo para carpintero que fabricar la parte 3
    private Semaphore sem_ensamblado;

    public Carpinteria(int CANTIDAD) {
        this.CANTIDAD = CANTIDAD;
        this.mutex_ensamblado = new Semaphore(1);
        this.sem_parte_1 = new Semaphore(1);
        this.sem_parte_2 = new Semaphore(1);
        this.sem_parte_3 = new Semaphore(1);
        this.sem_ensamblado = new Semaphore(0);
    }

    public void haciendo_parte_1() throws InterruptedException {
        sem_parte_1.acquire(1);
        System.out.println(
                Thread.currentThread().getName() + "------- 🛠 Le entrega una parte del tipo 1 al ensamblador");
        sem_ensamblado.release();
    }

    public void haciendo_parte_2() throws InterruptedException {
        sem_parte_2.acquire(1);
        System.out.println(
                Thread.currentThread().getName() + "------- 🛠 Le entrega una parte del tipo 1 al ensamblador");
        sem_ensamblado.release();
    }

    public void haciendo_parte_3() throws InterruptedException {
        sem_parte_3.acquire(1);
        System.out.println(Thread.currentThread().getName() + "------- 🛠 Estoy haciendo parte 3");
        sem_ensamblado.release();
    }

    public void ensamblando_partes() throws InterruptedException {
        if (this.producidos != this.CANTIDAD) {
            // COMIENZO DE ZONA CRITICA
            mutex_ensamblado.acquire();
            sem_ensamblado.acquire(3);

            System.out.println(Thread.currentThread().getName() + "-------Estoy ensamblando");
            this.producidos++;
            System.out.println("📦 se producieron: " + this.producidos + " de " + this.CANTIDAD);

            sem_parte_1.release();
            sem_parte_2.release();
            sem_parte_3.release();
            mutex_ensamblado.release();
            // FIN DE ZONA CRITICA
        }
    }
}

class Carpintero implements Runnable {

    Carpinteria carp;
    int tipoParte;

    public Carpintero(Carpinteria c, int tipo) {
        tipoParte = tipo;
        carp = c;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " esta fabricando parte de tipo " + tipoParte);

        }
    }
}

class Ensamblador implements Runnable {

    Carpinteria carp;

    public Ensamblador(Carpinteria c) {
        carp = c;
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