package ProgrmacionConcurrente.Synchronized;

import java.util.Random;

import ProgrmacionConcurrente.Synchronized.Ejercicio5.*;
import ProgrmacionConcurrente.Synchronized.Ejercicio6.Auto;

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


    /* public static void main(String[] args) {
        Letra a = new Letra(2,0);
        Letra b = new Letra(3,1);
        Letra c = new Letra(4,2);
        
        Thread h1 = new Thread(a, "A");
        Thread h2 = new Thread(b, "B");
        Thread h3 = new Thread(c, "C");

        h1.start();
        h2.start();
        h3.start();
        
    } */


    /*      EJERCICIO 6      */

    /* public static void main(String[] args) {
        Auto a1 = new Auto("patente1", "modelo1", "marca1", 50, 23);
        Auto a2 = new Auto("patente2", "modelo2", "marca2", 50, 10);
        Auto a3 = new Auto("patente3", "modelo3", "marca3", 50, 20);
        Auto a4 = new Auto("patente4", "modelo4", "marca4", 50, 5);
        Auto a5 = new Auto("patente5", "modelo5", "marca5", 50, 15);

        Thread ha1 = new Thread(a1, "patente1");
        Thread ha2 = new Thread(a2, "patente2");
        Thread ha3 = new Thread(a3, "patente3");
        Thread ha4 = new Thread(a4, "patente4");
        Thread ha5 = new Thread(a5, "patente5");
        
        ha1.start();
        ha2.start();
        ha3.start();
        ha4.start();
        ha5.start();
    } */

    /*  EJERCICIO 7      */

    public static void main(String[] args) {
        int arr[] = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(9);
        }

        
    }

}
