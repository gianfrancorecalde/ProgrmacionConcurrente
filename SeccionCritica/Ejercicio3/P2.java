package ProgrmacionConcurrente.SeccionCritica.Ejercicio3;

import ProgrmacionConcurrente.SeccionCritica.Ejercicio1.Datos;

public class P2 extends Thread {
    private Datos unDato;

    public P2(Datos unD){
        unDato = unD;
    }

    public void run() {
        for (int i = 1; i < 10; i++) {
            unDato.proceso2();
            //System.out.println(unDato.getDato());
        }
    }
}
