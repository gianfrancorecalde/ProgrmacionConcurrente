package ProgrmacionConcurrente.SeccionCritica.Ejercicio6;

import java.util.concurrent.Semaphore;

public class Testigo {
    
    private Semaphore permiso;

    public Testigo(){
        permiso = new Semaphore(1);
    }

    public void correr(){
        try {
            permiso.acquire();
            System.out.println(Thread.currentThread().getName()+ " Esta corriendo con el testigo");
            Thread.sleep((int)Math.random()*10000);
            System.out.println(Thread.currentThread().getName()+ " tardo "+ System.currentTimeMillis());
            permiso.release();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
