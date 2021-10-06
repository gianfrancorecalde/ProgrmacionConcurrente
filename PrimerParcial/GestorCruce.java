package ProgrmacionConcurrente.PrimerParcial;

import java.util.concurrent.Semaphore;

public class GestorCruce {
    
    private boolean semaforoOE;
    private boolean semaforoN;
    private Semaphore entradaOE;
    private Semaphore entradaN;
    private Semaphore mutex;

    public GestorCruce(){
        
        semaforoOE = true;
        mutex = new Semaphore(1);
        semaforoN = false;
        entradaOE = new Semaphore(1,true);
        entradaN = new Semaphore(1,true);

    }

    public void llegaOeste() {
        try {
            entradaOE.acquire();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void llegaNorte() {
        try {
            entradaN.acquire();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void saleEste() {
        entradaOE.release();
    }

    public void saleSur() {
        entradaN.release();
    }

    public void cambiarSemaforo() {
        try {
            mutex.acquire();
            if(semaforoOE){
                semaforoOE = false;
                semaforoN = true;
            }else{
                semaforoOE = true;
                semaforoN= false;
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        mutex.release();
    }

    public void cruzarDeOesteAEste() {
        try {
            OE.acquire();
        } catch (Exception e) {
            //TODO: handle exception
        }
        entradaOE.release();
    }

    
}
