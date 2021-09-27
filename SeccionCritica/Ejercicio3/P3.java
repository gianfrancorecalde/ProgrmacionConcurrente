package ProgrmacionConcurrente.SeccionCritica.Ejercicio3;

import ProgrmacionConcurrente.SeccionCritica.Ejercicio1.Datos;

public class P3 extends Thread {
    private Datos unDato;

    public P3(Datos unD){
        unDato = unD;
    }

    public void run() {
        for (int i = 1; i < 10; i++) {
            unDato.proceso3();
            //System.out.println(unDato.getDato());
        }
    }
}
