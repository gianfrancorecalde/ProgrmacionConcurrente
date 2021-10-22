package ProgrmacionConcurrente.Recalde_FAI2757;

import java.util.concurrent.Semaphore;

public class PuestoBuceo {
    
    private int cantidadAntiparras;                 // Cantidad de antiparras            
    private int cantidadSnorkel;                    // cantidad de snorkels

    private Semaphore mutexSnorkel;              
    private Semaphore mutexAntiparra;
    private Semaphore mutexCantAntiparras;
    private Semaphore mutexCantSnorkels;

    private Semaphore empleadoSnorkel;
    //private Semaphore turnoParaDevolucionSnorkel;
    private Semaphore empleadoAntiparra;
    //private Semaphore turnoParaDevolucionAntiparra;

    private Semaphore snorkelDisponible;
    private Semaphore antiparraDisponible;
    
    
   // private Semaphore terminaDevolucionSnorkel;
    //private Semaphore despedir;

    public PuestoBuceo(int cantAnt, int cantSnorkel){

        cantidadAntiparras = cantAnt;
        cantidadSnorkel = cantSnorkel;

        mutexSnorkel = new Semaphore(1,true);            // semaforo para exclusividad de cliente que piden
        mutexAntiparra = new Semaphore(1,true);      // semeforo para exclusividad de cliente que devuelven
        mutexCantAntiparras = new Semaphore(1);         // semaforo para esclusividad de incremente/decremento de las cantidades de antiparras
        mutexCantSnorkels = new Semaphore(1);           // semaforo para esclusividad de incremente/decremento de las cantidades de snorkels

        empleadoSnorkel = new Semaphore(0);        // semaforo para que empleado de snorkel antienda en orden en que fueron llamados
        empleadoAntiparra = new Semaphore(0);      // semaforo para que empleado de atiparra atienda en orden en que fueron llamados
        //turnoParaDevolucionAntiparra = new Semaphore(0,true);      // semaforo para que empleado de atiparra atienda en orden en que fueron llamados
        //turnoParaDevolucionSnorkel = new Semaphore(0,true);      // semaforo para que empleado de atiparra atienda en orden en que fueron llamados

        //terminaDevolucionSnorkel = new Semaphore(0);                // semaforo para habilitar la devolucion de antiparras

        snorkelDisponible = new Semaphore(0);           // semaforo que permite la entreaga de snorkel, si hay. 
        antiparraDisponible = new Semaphore(0);         // semaforo que permite la entrega de antiparra, si hay.

        //despedir = new Semaphore(0);                    // Semaforo para cerra hilo cliente
        
    }

    public int getSnorkel(){
        
        return cantidadSnorkel;

    }

    public int geta(){
        
        return cantidadAntiparras;

    }
    //--------------------------------------------------------------------------------------
    /* ENTREGA DE SNORKEL y ANTIPARRAS */
    //------------------------------------------------------------------------------

    public void pideSnorkel() {
        try {
            mutexSnorkel.acquire();
            System.out.println(Thread.currentThread().getName() + " pide snorkel");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        empleadoSnorkel.release();
    }

    public void entregarSnorkel() {
        try {
            empleadoSnorkel.acquire();
            System.out.println(Thread.currentThread().getName() + " le entrga el snorkel");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        decrementarCantSnorkel();
    }

    private void decrementarCantSnorkel(){
        // decremnta la cantidad de snorkel y libera permiso de snorkelDisponible si hay
        try {
            mutexCantSnorkels.acquire();
            if(cantidadSnorkel > 0){
                cantidadSnorkel--;
                snorkelDisponible.release();
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mutexCantSnorkels.release();
    }

    public void recibeSnorkel() {
        try {
            snorkelDisponible.acquire();
            System.out.println(Thread.currentThread().getName() + " recibe antiparra" );
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mutexSnorkel.release();
    }

    public void pideAntiparra() {
        try {
            mutexAntiparra.acquire();
            System.out.println(Thread.currentThread().getName() + " pide antiparra");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        empleadoAntiparra.release();
    }

    public void entregarAntiparra() {
        try {
            empleadoAntiparra.acquire();
            System.out.println(Thread.currentThread().getName() + " entrega Antiparra");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        decrementarCantAntiparras();
    }

    private void decrementarCantAntiparras(){
        // decremnta la cantidad de antiparra y libera permiso de antiparraDiponible si hay
        try {
            mutexCantAntiparras.acquire();
            if(cantidadAntiparras > 0){
                cantidadAntiparras--;
                antiparraDisponible.release();
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mutexCantAntiparras.release();
    }

    public void recibeAntiparra() {
        try {
            antiparraDisponible.acquire();
            System.out.println(Thread.currentThread().getName() + " recibe antiparra" );
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mutexAntiparra.release();
    }


    /* ---------------------------------------------------------------------------------------------------------------------------------- */
    /* DEVOLUCION DE SNORKELS y ANTIPARRAS */
    /* ---------------------------------------------------------------------------------------------------------------------------------- */

    public void devuelveSnorkel() {
        try {
            mutexSnorkel.acquire();
            System.out.println(Thread.currentThread().getName() + " devuelve el snorkel");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        empleadoSnorkel.release();
    }

    public void recepcionaSnorkel() {
        try {
            empleadoSnorkel.acquire();
            System.out.println(Thread.currentThread().getName() + " recibe snorkels ");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        incrementaCantSnorkel();
    }

    private void incrementaCantSnorkel(){
        // incremente la cantidad de snorkel y si no habia ninguno en stock libera permiso snorkelDisponble para q el que necesitaba lo tome
        try {
            mutexCantSnorkels.acquire();
            if(cantidadSnorkel < 1){
                cantidadSnorkel++; // no deberia modificar la variable ya que apenas se devuelva se le entrega otro cliente
                snorkelDisponible.release();
            }else{
                cantidadSnorkel++;
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mutexCantSnorkels.release();
        System.out.println(Thread.currentThread().getName() + " pasa a devolver antiparra");
        mutexSnorkel.release();
    }

    public void devuelveAntiparra() {
        try {
            mutexAntiparra.acquire();
            System.out.println(Thread.currentThread().getName() + " devuelve el antiparra");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        empleadoAntiparra.release();
    }

    public void recepcionaAntiparra() {
        try {
            empleadoAntiparra.acquire();
            System.out.println(Thread.currentThread().getName() + " recibe la antiparra ");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        incrementaCantAntiparras();
    }

    private void incrementaCantAntiparras(){
        // incremente la cantidad de antiparras y si no habia ninguno en stock libera permiso antiparraDisponible para q el que necesitaba lo tome
        try {
            mutexCantAntiparras.acquire();
            if(cantidadAntiparras < 1){
                cantidadAntiparras++;
                antiparraDisponible.release();
            }else{
                cantidadAntiparras++;
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mutexCantAntiparras.release();
        System.out.println(Thread.currentThread().getName() + " Se retira");
        mutexAntiparra.release();
    }

    /* public void seRetira() {
        try {
            despedir.acquire();
            System.out.println(Thread.currentThread().getName() + " se retira del puesto" );
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mutexAntiparra.release();
    } */
}
