package ProgrmacionConcurrente.SincronizacionPorCooperacion.Monitor.Ejercicio2TP6;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        GestorSala gs = new GestorSala();
        new Thread(new Termometro(gs), "Termo").start();
        for (int i = 0; i < 5; i++) {
            new Thread(new Persona(false, gs), "Persona "+i).start();
            new Thread(new Persona(true, gs), "Jubilado "+i).start();
        }
    }
}

class GestorSala {

    int tUmbral;
    int limitePersTotal;
    int limitePersUmbral;
    int cantPersTotal;
    int tempActual;
    int cantJubiladoPendiente;

    Lock cerrojo = new ReentrantLock();
    Condition capacidadAlcanzada;
    Condition temperaturaAlcanzada;
    Condition jubiladoEnEspera;

    public GestorSala() {

        capacidadAlcanzada = cerrojo.newCondition();
        temperaturaAlcanzada = cerrojo.newCondition();
        jubiladoEnEspera = cerrojo.newCondition();

        tUmbral = 30;
        limitePersUmbral = 2;
        limitePersTotal = 3;

        cantPersTotal = 0;
        cantJubiladoPendiente = 0;
        tempActual = 25;
    }

    private void actualizarTemp() {
        tempActual = new Random().nextInt(8) + 25;
    }// actualizar temperatura 

    public void entrarSala() {
        cerrojo.lock();
        try {
            while (cantPersTotal > limitePersTotal) {
                System.out.println("sala llena");
                capacidadAlcanzada.wait();
            }
            while (tempActual > tUmbral && cantPersTotal >= limitePersUmbral) {
                System.out.println("temperatura en la sala alta");
                temperaturaAlcanzada.wait();
            }
            while (cantJubiladoPendiente > 0) {
                jubiladoEnEspera.wait();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        cantPersTotal++;
        System.out.println(Thread.currentThread().getName() + " entro a la sala");
        this.actualizarTemp();
        cerrojo.unlock();
    }

    public  void entrarSalaJubilado() {
        cerrojo.lock();
        try {
            cantJubiladoPendiente++;
            while (cantPersTotal >= limitePersTotal) {
                System.out.println("sala llena");
                capacidadAlcanzada.wait();
            }
            while (tempActual > tUmbral && cantPersTotal >= limitePersUmbral) {
                System.out.println("temperatura en la sala alta");
                temperaturaAlcanzada.wait();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        cantPersTotal++;
        cantJubiladoPendiente--;
        System.out.println(Thread.currentThread().getName() + " entro a la sala");
        jubiladoEnEspera.signal();
        this.actualizarTemp();
        cerrojo.unlock();
    }

    public  void salirSala() {
        cerrojo.lock();
        cantPersTotal--;
        System.out.println(" sale de la sala");
        capacidadAlcanzada.signal();
        cerrojo.unlock();
    }

    public  void notificarTemperatura() {
        cerrojo.lock();
        if (tempActual > tUmbral) {
            System.out.println("La temperatura supero el umbral"); 
        }else{
            temperaturaAlcanzada.signalAll();
        }
        cerrojo.unlock();
    }
}

class Persona implements Runnable {

    boolean jubilado;
    GestorSala gestor;

    public Persona(boolean tipoPers, GestorSala g) {
        jubilado = tipoPers;
        gestor = g;
    }

    private void recorriendoMuseo(){
        try {
            Thread.sleep(1000);
            System.out.println("Esta recorriendo el museo");
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    @Override
    public void run() {
        while (true) {
            if (jubilado) {
                gestor.entrarSalaJubilado();
            } else {
                gestor.entrarSala();
            }
            recorriendoMuseo();
            gestor.salirSala();
        }

    }
}

class Termometro implements Runnable {

    GestorSala gestor;

    public Termometro(GestorSala g) {
        gestor = g;
    }

    private void esperar(){
        try {
            Thread.sleep(1500);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Override
    public void run() {
       while (true) {
           gestor.notificarTemperatura();
           esperar();
       }       
    }
}
