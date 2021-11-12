package ProgrmacionConcurrente.SincronizacionPorCooperacion.SemaforoGenerico.Ejercicio4;

import java.util.concurrent.Semaphore;

public class Main {
    
    public static void main(String[] args) {
        
        new Thread(new Torre(), "Torre de control").start();
        for (int i = 0; i < 6; i++) {
            new Thread(new AvionAterrizar(), "Avion Aterrizar "+i).start();
            new Thread(new AvionDespegar(), "Avion Despegar "+i).start();
        }
    }

} 

class Pista{

    // Semaphore
    Semaphore aterrizar; 
    Semaphore mutexPista;
    Semaphore control;
    Semaphore despegue;
    Semaphore mutexCantDespeges;
    Semaphore mutexCantAterrizaje;
    //Semaphore esperar;

    // Otras varibles
    int aterrizajes;
    boolean prioridadDespegue;
    int aterrizajesPermitidos;
    int esperandoDespegar;
    int esperandoAterrizaje;
    
    public Pista(int cantPermisos){
        // Semaphore
        aterrizar = new Semaphore(aterrizajesPermitidos);
        mutexPista = new Semaphore(1);
        control = new Semaphore(1);
        despegue = new Semaphore(0);
        mutexCantDespeges = new Semaphore(1);
        mutexCantAterrizaje = new Semaphore(1);
        //esperar = new Semaphore(0);

        // Otras variables
        aterrizajes = 0;
        aterrizajesPermitidos = cantPermisos;
        prioridadDespegue = false;
        esperandoDespegar = 0;
        esperandoAterrizaje = 0;
        
    }

    public void supervisar(){
        try {
            control.acquire();
        } catch (Exception e) {
            //TODO: handle exception
        }
        if (prioridadDespegue && esperandoDespegar > 0 && esperandoAterrizaje <1) {
            System.out.println(" No hay mas avioens que quieran aterrizar");
            despegue.release();
        } else {
            if(aterrizajes == aterrizajesPermitidos){
                System.out.println(Thread.currentThread().getName() + " libera permiso de despegue");
                aterrizajes = 0;
                despegue.release();
            }else{
                if(aterrizajes == 0){
                    System.out.println(Thread.currentThread().getName() +" libera los permisos de aterrizaje");
                    aterrizar.release(aterrizajesPermitidos);
                }
            }
        }
        
    }

    // si prioridadDepgue = true y esperandoDespegar > 0 ---> no puedo aterrizar
    // quieroDespegar -> si prioridadDespegue = false y se esta usando la pista -> esperandoDespegar ++

    public void intentaAterrizar(){
        try {
            System.out.println(Thread.currentThread().getName() +" Quiere aterrizar");
            mutexCantAterrizaje.acquire();
            esperandoAterrizaje++;
            mutexCantAterrizaje.release();
            aterrizar.acquire();
            mutexPista.acquire();
            aterrizajes++; 
            
        } catch (Exception e) {
            //TODO: handle exception
        }
        System.out.println(Thread.currentThread().getName() +" tiene permitido aterrizar");
    }
    
    public void finalizaAterrizaje(){
        try {
            mutexCantAterrizaje.acquire();
            esperandoAterrizaje--;
            mutexCantAterrizaje.release();
        } catch (Exception e) {
            //TODO: handle exception
        }
        System.out.println(esperandoAterrizaje);
        System.out.println( Thread.currentThread().getName() +" avisa a control que es el ultimo en aterrizar");
        prioridadDespegue = true;
        control.release();
        mutexPista.release();
    }
    
    public void intentaDespegar(){
        try {
            System.out.println(Thread.currentThread().getName() +" Quiere despegar");
            mutexCantDespeges.acquire();
            esperandoDespegar++;
            mutexCantDespeges.release();
            despegue.acquire();
        } catch (Exception e) {
            //TODO: handle exception
        }
        System.out.println(Thread.currentThread().getName() +" Tiene permitido despegar");
    }

    public void finalizaDespegue(){
        try {
            mutexCantDespeges.acquire();
            esperandoDespegar--;
            mutexCantDespeges.release();
        } catch (Exception e) {
            //TODO: handle exception
        }
        System.out.println(Thread.currentThread().getName() + " Avisa a control que ya despego");
        control.release();
    }
}

class Avion {
    
    static Pista mutexPista = new Pista(2);
    public Avion (){} 
}

class AvionDespegar extends Avion implements Runnable{

    public AvionDespegar(){
        super();
    }

    void despegando(){
        try {
            System.out.println(" Esta despegando");
            Thread.sleep(2000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Override
    public void run() {
        mutexPista.intentaDespegar();
        despegando();
        mutexPista.finalizaDespegue();
    }
}

class AvionAterrizar extends Avion implements Runnable{

    public AvionAterrizar(){
        super();
    }

    void aterrizando(){
        try {
            System.out.println(" Esta aterrizando");
            Thread.sleep(2000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Override
    public void run() {
        mutexPista.intentaAterrizar();
        aterrizando();
        mutexPista.finalizaAterrizaje();
    }
}

class Torre extends Avion implements Runnable{
    
    public Torre(){
        super();
    }

    @Override
    public void run() {
        while(true){
            mutexPista.supervisar();
        }
    }
}
