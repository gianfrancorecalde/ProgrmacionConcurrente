package ProgrmacionConcurrente.PrimerParcial;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GestorCruce {
    
    private boolean semaforoO;
    private boolean semaforoN;
    private Lock lock; 

    public GestorCruce(){
        
        semaforoO = true;
        
        semaforoN = false;

        lock = new ReentrantLock();

    }

    public void cambiarEstado(){

        if(semaforoO){
            semaforoN = true;
            semaforoO = false;
        }else{
            semaforoN = false;
            semaforoO = true;
        }
    }


   /*  public synchronized void llegaOeste() {
        try {
            if(semaforoO){

            }else{

            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void llegaNorte() {
        try {
            //entradaN.acquire();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    
    public void intentarCruzar() {
        try {
            //mutex.acquire();
            
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public void saleEste() {
        entradaOE.release();
        mutex.release();
    }

    public void saleSur() {
        
        entradaN.release();
        mutex.release();
    }

    public void noPuedeCruzar() {
        mutex.release();
    } */

    
}
