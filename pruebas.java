package ProgrmacionConcurrente;
import java.util.*;

import ProgrmacionConcurrente.Concurrencia.Cliente;
import ProgrmacionConcurrente.Concurrencia.MiEjecucion;
import ProgrmacionConcurrente.Concurrencia.PingPong;
import ProgrmacionConcurrente.Concurrencia.PingPong2;

public class pruebas {
    
    /* public static void main(String[] args) {
        Date hoy = new Date();
        long aux = hoy.getTime();
        hoy.setTime(aux+(86400000*7));
        System.out.println(hoy);
    } */

    /* public static void main(String[] args) {
        PingPong t1 = new PingPong("Ping", 1000);
        PingPong t2 = new PingPong("Pong", 1000);

        t1.start();
        t2.start();
        System.out.println("algo pasas");
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            System.err.println("falló");
        }
    } */

    /* public static void main(String[] args) {
        PingPong2 p1 = new PingPong2("Ping", 1000);
        PingPong2 p2 = new PingPong2("Pong", 2000);

        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);
        t1.start();
        t2.start();

        try{
            Thread.sleep(100);
        }catch(InterruptedException e){
            System.err.println("falló");
        }
    } */

    /* -------------------------------------------------------------------------------------------------------------- */
    /* -------------------------------------------------------------------------------------------------------------- */
    /* -------------------------------------------------------------------------------------------------------------- */
    
    /* public static void main(String[] args) {
        Cliente juan = new Cliente();
        juan.setName("Juan Lopez");
        Cliente ines = new Cliente();
        ines.setName("Ines Garcia");
        juan.start();
        ines.start();

    } */

    public static void main(String[] args) {
        Thread miHilo = new MiEjecucion();
        miHilo.start();
        try {
            miHilo.join();
        } catch (Exception e) {
            System.err.println("Algo fallo");
        }
        System.out.println("En el main");
    }
}


