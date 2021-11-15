package ProgrmacionConcurrente.SincronizacionPorCooperacion.SemaforoGenerico.Ejercicio4;

import java.util.concurrent.Semaphore;

public class Main {
    
    public static void main(String[] args) {
        Pista p = new Pista(2, 6);
        new Thread(new Torre(p), "Torre de control").start();
        for (int i = 0; i < 6; i++) {
            new Thread(new AvionAterrizar(p), "Avion Aterrizar "+i).start();
            new Thread(new AvionDespegar(p), "Avion Despegar "+i).start();
        }
    }

} 

class Pista{

    // Semaphore
    Semaphore aterrizar; 
    Semaphore mutexPista;
    Semaphore control;
    Semaphore despegue;
  

    // Otras varibles
    int aterrizajes;
    int aterrizajesPermitidos;
    int aterrizajesEsperado;
    
    public Pista(int cantPermisos, int cantAterrizajes){
        // Semaphore
        aterrizar = new Semaphore(aterrizajesPermitidos);
        mutexPista = new Semaphore(1);
        control = new Semaphore(1);
        despegue = new Semaphore(0);
        
        // Otras variables
        aterrizajes = 0;
        aterrizajesPermitidos = cantPermisos;
        aterrizajesEsperado = cantAterrizajes;
    }

    public void supervisar(){
        try {
            control.acquire();
        } catch (Exception e) {
            //TODO: handle exception
        }
        if(aterrizajes == aterrizajesPermitidos){
            System.out.println(Thread.currentThread().getName() + " libera permiso de despegue");
            aterrizajes = 0;
            despegue.release();
        }else{
            if(aterrizajesEsperado==0){
                System.out.println("No hay mas aviones que quieran aterrizar");
                despegue.release();
            }else{
                System.out.println(Thread.currentThread().getName() +" libera los permisos de aterrizaje");
                aterrizar.release(aterrizajesPermitidos);
            }
        }
        
    }

    // si prioridadDepgue = true y esperandoDespegar > 0 ---> no puedo aterrizar
    // quieroDespegar -> si prioridadDespegue = false y se esta usando la pista -> esperandoDespegar ++

    public void intentaAterrizar(){
        try {
            System.out.println(Thread.currentThread().getName() +" Quiere aterrizar");
            aterrizar.acquire();
            mutexPista.acquire();
            aterrizajes++;
            aterrizajesEsperado--;
            
        } catch (Exception e) {
            //TODO: handle exception
        }
        System.out.println(Thread.currentThread().getName() +" tiene permitido aterrizar");
    }
    
    public void finalizaAterrizaje(){
        
        if(aterrizajes == aterrizajesPermitidos){
            System.out.println( Thread.currentThread().getName() +" avisa a control que es el ultimo en aterrizar");
            control.release();
        }else{
            if(aterrizajesEsperado == 0){
                System.out.println("No hay mas aviones que quieran aterrizar");
                control.release();
            }
        }
        mutexPista.release();
    }
    
    public void intentaDespegar(){
        try {
            despegue.acquire();
        } catch (Exception e) {
            //TODO: handle exception
        }
        System.out.println(Thread.currentThread().getName() +" Tiene permitido despegar");
    }

    public void finalizaDespegue(){
        System.out.println(Thread.currentThread().getName() + " Avisa a control que ya despego");
        control.release();
    }
}

class Avion {
    
    Pista pista;
    public Avion (Pista p){
        pista = p;
    } 
}

class AvionDespegar extends Avion implements Runnable{

    public AvionDespegar(Pista p){
        super(p);
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
        pista.intentaDespegar();
        despegando();
        pista.finalizaDespegue();
    }
}

class AvionAterrizar extends Avion implements Runnable{

    public AvionAterrizar(Pista p){
        super(p);
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
        pista.intentaAterrizar();
        aterrizando();
        pista.finalizaAterrizaje();
    }
}

class Torre extends Avion implements Runnable{
    
    public Torre(Pista p){
        super(p);
    }

    @Override
    public void run() {
        while(true){
            pista.supervisar();
        }
    }
}
