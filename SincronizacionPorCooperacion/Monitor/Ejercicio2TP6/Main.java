package ProgrmacionConcurrente.SincronizacionPorCooperacion.Monitor.Ejercicio2TP6;

import java.util.Random;

public class Main {
    
}

class GestorSala{
    
    private int tUmbral; 
    private int cantPersSinUmbral;
    private int cantPersConUmbral;
    private int cantPersTotal;
    private int tempActual;


    public GestorSala(){
        tUmbral = 30;
        cantPersConUmbral = 35;
        cantPersSinUmbral = 50;
        cantPersTotal = 0;
        tempActual = 0;
    }

    public synchronized void entrarSala(){
        try {
            while ((cantPersTotal >= cantPersSinUmbral) || (tempActual > tUmbral && cantPersTotal >= cantPersConUmbral)) {
                this.wait();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        cantPersTotal++;
        System.out.println(Thread.currentThread().getName() + " Entro a la sala");
    }

    public synchronized void entrarSalaJubilado(){
        try {
            while ((cantPersTotal >= cantPersSinUmbral) || (tempActual > tUmbral && cantPersTotal >= cantPersConUmbral)) {
                this.wait();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        cantPersTotal++;
        System.out.println(Thread.currentThread().getName() + " jubilado entro a la sala");
    }

    public synchronized notificarTemperatura(int temp){

    }
}

class Persona implements Runnable{

    boolean jubilado;
    GestorSala gestor;

    public Persona(boolean tipoPers, GestorSala g){
        jubilado = tipoPers;
        gestor = g;
    }

    @Override
    public void run() {
        while (true) {
            if (jubilado) {
                gestor.entrarSalaJubilado();
            } else {
                gestor.entrarSala();
            }
            gestor.salirSala();
        }
        
    }
}

class Termometro implements Runnable{

    GestorSala gestor;
    Random temperatura;
    public Termometro(GestorSala g){
        temperatura = new Random();
        gestor = g;
    }
    @Override
    public void run() {
       while (true) {
           gestor.notificarTemperatura(temperatura.nextInt(16)+25);
       }       
    }
}
