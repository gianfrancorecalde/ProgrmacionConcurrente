package ProgrmacionConcurrente.SeccionCritica.Ejercicio6;

import java.util.concurrent.Semaphore;

public class Carrera {
    
    private Semaphore testigo;
    private Semaphore [] turnos;

    public Carrera(Semaphore [] t){
        testigo = new Semaphore(1);
        turnos = t;
    }

    public void intentaTomarElTestigo(int t){
        try {
            System.out.println(Thread.currentThread().getName() + " paso por aca");
                turnos[t].acquire();
                System.out.println(Thread.currentThread().getName()+ " tiene el testigo");
                testigo.acquire();
                System.out.println(Thread.currentThread().getName()+ " comienza a correr");
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    public void terminaDeCorrer(int t){
        try {
            turnos[t].release();
            System.out.println(Thread.currentThread().getName()+ " finalizo carrera");
            testigo.release();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
