package ProgrmacionConcurrente.SeccionCritica.Ejercicio5;

import java.util.concurrent.Semaphore;

public class CentroDeImpresion {

    private Semaphore impA[];
    private Semaphore impB[];
    private Semaphore mutex;

    public CentroDeImpresion(Semaphore arr1[], Semaphore arr2[]) {
        impA = arr1;
        impB = arr2;
        mutex= new Semaphore(1);   
    }

    public  void  intenta(){
        try {
            mutex.acquire();
            System.out.println(Thread.currentThread().getName() + " Tiene la exclusividad");
        } catch (Exception e) {
        }
    }

    public boolean intentaTomarA(int posicion){
        boolean exito = false;
        try {
            exito = impA[posicion].tryAcquire();
            if(exito){
                mutex.release();

            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        return exito;
    }

    public boolean intentaTomarB(int posicion){
        boolean exito = false;
        try {
            exito = impB[posicion].tryAcquire();
            if(exito){
                mutex.release();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        return exito;
    }
    
    public void liberarA(int posicion){
        try {
            impA[posicion].release();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void liberarB(int posicion){
        try {
            impB[posicion].release();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
