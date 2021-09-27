package ProgrmacionConcurrente.SeccionCritica.Ejercicio4;

import java.util.concurrent.Semaphore;

public class GestorLetras {

    private Semaphore semA;
    private String str;

    public GestorLetras() {
        semA = new Semaphore(1);
        str = "";        
    }

    public void generarCadena(int cantidad, char letra ) {
        try {
            semA.acquire();
            for (int i = 0; i < cantidad; i++) {
                str += letra+"";
            }
            semA.release();
        } catch (InterruptedException e) {
            System.err.println("Fallaron los semaforos");
        }
    }

    /* public void B(int cant) {
        
        try {
            semB.acquire();
            for (int i = 0; i < cant; i++) {
                str += "B";
            }
            semC.release();
        } catch (InterruptedException e) {
            System.err.println("Fallaron los semaforos");
        }
        
    }

    public void C(int cant) {

        try {
            semC.acquire();
            for (int i = 0; i < cant; i++) {
                str += "C";
            }
            semA.release();
        } catch (InterruptedException e) {
            System.err.println("Fallaron los semaforos");
        }
        
    } */

    public String getStr(int cantidad){
        String aux = "";
        for (int i = 0; i < cantidad; i++) {
            aux += str;
        }
        return aux;
    }
}
