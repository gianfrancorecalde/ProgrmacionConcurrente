package ProgrmacionConcurrente.Recalde_FAI2757;

import java.util.concurrent.Semaphore;

public class PuestoBuceo {
    
    private int cantidadAntiparras;                 // Cantidad de antiparras            
    private int cantidadSnorkel;                    // cantidad de snorkels

    private Semaphore mutexClientePide;              
    private Semaphore mutexClienteDevolucion;
    private Semaphore mutexCantAntiparras;
    private Semaphore mutexCantSnorkels;

    private Semaphore turnoParaEntregarSnorkel;
    private Semaphore turnoParaEntregarAntiparra;

    private Semaphore snorkelDisponible;
    private Semaphore antiparraDisponible;
    
    
    private Semaphore habilitacion;
    private Semaphore despedir;

    public PuestoBuceo(int cantAnt, int cantSnorkel){

        cantidadAntiparras = cantAnt;
        cantidadSnorkel = cantSnorkel;

        mutexClientePide = new Semaphore(1,true);            // semaforo para exclusividad de cliente que piden
        mutexClienteDevolucion = new Semaphore(1,true);      // semeforo para exclusividad de cliente que devuelven
        mutexCantAntiparras = new Semaphore(1);         // semaforo para esclusividad de incremente/decremento de las cantidades de antiparras
        mutexCantSnorkels = new Semaphore(1);           // semaforo para esclusividad de incremente/decremento de las cantidades de snorkels

        turnoParaEntregarSnorkel = new Semaphore(0,true);        // semaforo para que empleado de snorkel antienda en orden en que fueron llamados
        turnoParaEntregarAntiparra = new Semaphore(0,true);      // semaforo para que empleado de atiparra atienda en orden en que fueron llamados

        habilitacion = new Semaphore(0);                // semaforo para habilitar la devolucion de antiparras

        snorkelDisponible = new Semaphore(0);           // semaforo que permite la entreaga de snorkel, si hay. 
        antiparraDisponible = new Semaphore(0);         // semaforo que permite la entrega de antiparra, si hay.

        despedir = new Semaphore(0);                    // Semaforo para cerra hilo cliente
        
    }

    //--------------------------------------------------------------------------------------
    /* ENTREGA DE SNORKEL y ANTIPARRAS */
    //------------------------------------------------------------------------------

    public void pideSnorkel() {
        try {
            mutexClientePide.acquire();
            System.out.println(Thread.currentThread().getName() + " pide snorkel");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        turnoParaEntregarSnorkel.release();
    }

    public void entregarSnorkel() {
        try {
            turnoParaEntregarSnorkel.acquire();
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

    public void pideAntiparra() {
        try {
            snorkelDisponible.acquire();
            System.out.println(Thread.currentThread().getName() + " recibe snorkel y pide antiparra");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        turnoParaEntregarAntiparra.release();
    }

    public void entregarAntiparra() {
        try {
            turnoParaEntregarAntiparra.acquire();
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
        mutexClientePide.release();
    }


    /* ---------------------------------------------------------------------------------------------------------------------------------- */
    /* DEVOLUCION DE SNORKELS y ANTIPARRAS */
    /* ---------------------------------------------------------------------------------------------------------------------------------- */

    public void devuelveSnorkel() {
        try {
            mutexClienteDevolucion.acquire();
            System.out.println(Thread.currentThread().getName() + " devuelve el snorkel");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        turnoParaEntregarSnorkel.release();
    }

    public void recepcionaSnorkel() {
        try {
            turnoParaEntregarSnorkel.acquire();
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
                cantidadSnorkel++;
                snorkelDisponible.release();
            }else{
                cantidadSnorkel++;
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        habilitacion.release();
        mutexCantSnorkels.release();
    }

    public void devuelveAntiparra() {
        try {
            habilitacion.acquire();
            System.out.println(Thread.currentThread().getName() + " devuelve el antiparra");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        turnoParaEntregarAntiparra.release();
    }

    public void recepcionaAntiparra() {
        try {
            turnoParaEntregarAntiparra.acquire();
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
        despedir.release();
        mutexCantAntiparras.release();
    }

    public void seRetira() {
        try {
            despedir.acquire();
            System.out.println(Thread.currentThread().getName() + " se retira del puesto" );
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mutexClienteDevolucion.release();
    }
}
