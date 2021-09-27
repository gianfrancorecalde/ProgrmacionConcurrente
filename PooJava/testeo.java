package ProgrmacionConcurrente.PooJava;

import java.util.*;

import ProgrmacionConcurrente.Lineales.Dinamicas.*;
import ProgrmacionConcurrente.PooJava.Empresa.Empleado;
import ProgrmacionConcurrente.PooJava.Empresa.Tecnico;
import ProgrmacionConcurrente.PooJava.Puerto.*;
import ProgrmacionConcurrente.Concurrencia.*;

public class testeo {
    

    /* public static void main(String[] args) {
        Puerto p1 = new Puerto();
        p1.agregarAmarres();
        Yate y1 = new Yate(3, "912381", 4, 2202, 456);
        Alquiler alq1 = new Alquiler(1,y1 , p1.getAmarre(1), 23, 5, 8, 9, 2021, 2021);
        p1.agregarAlquiler(alq1);
        double algo = calcularAlquiler(p1, 1);
        System.out.println(algo);
    } */

    /* public static void main(String[] args) {
        long t1 = new GregorianCalendar(2021, 8, 24).getTimeInMillis();
        Calendar t = Calendar.getInstance();
        t.set(Calendar.HOUR, 0);
        t.set(Calendar.HOUR_OF_DAY, 0);
        t.set(Calendar.MINUTE, 0);
        t.set(Calendar.SECOND, 0);
        Date actual = new Date();
        System.out.println(t.get(Calendar.MONTH) + " "+ t.get(Calendar.DATE) + " "+t.get(Calendar.YEAR));
        System.out.println(actual.getMonth());
    } */

    /* public static void main(String[] args) {
        Empleado e = new Tecnico(00000, "nombre", 66, "direccion", new Date(), "sexo", 4, 5, "titulo", 6);
        
    } */

    /* public static void main(String[] args) {
        System.out.println("Hilo princiapl iniciando.");
        unHilo mh = new unHilo("#1");
        unHilo mh2 = new unHilo("#2");
        unHilo mh3 = new unHilo("#3");
        //Thread nuevoHilo = new Thread(mh);
        //nuevoHilo.start();
        mh.start();
        mh2.start();
        mh3.start();
        for(int i=0; i<50;i++){
            System.out.print(" .");
        }
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println("Hilo principal interrumpido");
        }
        System.out.println("hilo principal finalizado");
    } */

    
    /*                                                  EJERCICIO 6 */

    /* public static void main(String[] args) {
        Clienta cliente1 = new Clienta("Cliente 1", new int[]{2,2,1,5,2,3});
        Clienta cliente2 = new Clienta("Cliente 2", new int[]{1,3,5,1,1});
        Cajero cajero1 = new Cajero("Cajero 1");
        long initialTime = System.currentTimeMillis();
        cajero1.procesarCompra(cliente1, initialTime);
        cajero1.procesarCompra(cliente2, initialTime);
    } */

    /* EJERCICIO A CON CLASE THREAD */

    /* public static void main(String[] args) {
        Clienta cliente1 = new Clienta("Cliente 1", new int[]{2,2,1,5,2,3});
        Clienta cliente2 = new Clienta("Cliente 2", new int[]{1,3,5,1,1});

        CajeroThread c1 = new CajeroThread("Cajero 1", cliente1, System.currentTimeMillis());
        CajeroThread c2 = new CajeroThread("Cajero 2", cliente2, System.currentTimeMillis());
        
        c1.start();
        c2.start();

        try {
            c1.join();
            c2.join();
        } catch (Exception e) {
            System.err.println("algo salio mal");
        }
        System.out.println("Finalizaron los cajeros");
    } */

    /* EJERCICIO 6 CON INTERFAZ RUNNABLE */

    public static void main(String[] args) {
        Clienta cliente1 = new Clienta("Cliente 1", new int[]{2,2,1,5,2,3});
        Clienta cliente2 = new Clienta("Cliente 2", new int[]{1,3,5,1,1});

        CajeroRunnable c1 = new CajeroRunnable("Cajero 1", cliente1, System.currentTimeMillis());
        CajeroRunnable c2 = new CajeroRunnable("Cajero 2", cliente2, System.currentTimeMillis());
        
        Thread h1 = new Thread(c1);
        Thread h2 = new Thread(c2);

        h1.start();
        h2.start();

        try {
            h1.join();
            h2.join();
        } catch (Exception e) {
            System.err.println("algo salio mal");
        }
        System.out.println("Finalizaron los cajeros");
    }
}
