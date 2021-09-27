package ProgrmacionConcurrente.Synchronized;

import ProgrmacionConcurrente.Synchronized.Ejercicio5.*;

public class main {
    /* public static void main(String[] args) {
        VerificarCuenta vc = new VerificarCuenta();
        Thread Luis = new Thread(vc, "Luis");
        Thread Manuel = new Thread(vc, "Manuel");
        Luis.start();
        Manuel.start();

    } */

    /* public static void main(String[] args) {
        Vida v = new Vida(10);
        Thread orco = new Thread(new Daniar(v), "Orco");
        Thread curandero = new Thread(new Curar(v), "Curandero");
        orco.start();
        curandero.start();
    } */

    
    
    /*      EJERCICIO 4              */


    /* public static void main(String[] args) {
        
        GestorActividad gestorAct = new GestorActividad();
        Thread hamster1 = new Thread(gestorAct, "hasmter1");
        Thread hamster2 = new Thread(gestorAct, "hasmter2");
        Thread hamster3 = new Thread(gestorAct, "hasmter3");

        hamster1.start();
        hamster2.start();
        hamster3.start();
        
    } */



    /* EJERCICIO 5 */


    public static void main(String[] args) {
        Letra a = new Letra(2,0);
        Letra b = new Letra(3,1);
        Letra c = new Letra(4,2);
        
        Thread h1 = new Thread(a, "A");
        Thread h2 = new Thread(b, "B");
        Thread h3 = new Thread(c, "C");

        h1.start();
        h2.start();
        h3.start();
        
    }

}
