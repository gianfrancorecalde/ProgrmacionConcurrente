package ProgrmacionConcurrente.SeccionCritica;

import java.util.concurrent.Semaphore;

import ProgrmacionConcurrente.SeccionCritica.Ejercicio4.GestorLetras;
import ProgrmacionConcurrente.SeccionCritica.Ejercicio4.Letra;
import ProgrmacionConcurrente.SeccionCritica.Ejercicio5.CentroDeImpresion;
import ProgrmacionConcurrente.SeccionCritica.Ejercicio5.Impresora;
import ProgrmacionConcurrente.SeccionCritica.Ejercicio5.Usuario;
import ProgrmacionConcurrente.SeccionCritica.Ejercicio6.Atleta;
import ProgrmacionConcurrente.SeccionCritica.Ejercicio6.Carrera;
import ProgrmacionConcurrente.SeccionCritica.Ejercicio7.Pasajero;
import ProgrmacionConcurrente.SeccionCritica.Ejercicio7.Taxi;
import ProgrmacionConcurrente.SeccionCritica.Ejercicio7.Taxista;
import jdk.incubator.vector.VectorOperators.Test;

public class main {
    /* public static void main(String[] args) {
        ClaseAuxiliar aux = new ClaseAuxiliar();
        Thread h2 = new Thread(aux,"h2");
        Thread h1 = new Thread(aux,"h1");
        
        h1.start();
        h2.start();
    } */

    /*      EJEMPLO CON SEMAFORO         */

    /* public static void main(String[] args) {
        Datos dt = new Datos(1);
        P1 p1 = new P1(dt);
        P2 p2 = new P2(dt);
        P3 p3 = new P3(dt);
        
        p1.start();
        p2.start();
        p3.start();
    } */

    /*      EJERCICIO 4      */

    /* public static void main(String[] args) {
        
        GestorLetras gl =new GestorLetras();
        Letra A = new Letra('A',1,gl);
        Letra B = new Letra('B',2,gl);
        Letra C = new Letra('C',3,gl);

        A.start();
        B.start();
        C.start();
        
        try {
            A.join();
            B.join();
            C.join();
        } catch (Exception e) {
            //TODO: handle exception
        }

        System.out.println(gl.getStr(3));
    } */

    /*      EJERCICIO 5      */

    /* public static void main(String[] args) {
        
        Semaphore arrA[] = new Semaphore[5];
        Semaphore arrB[] = new Semaphore[5];
        for (int i = 0; i < arrA.length; i++) {
            arrA[i]= new Semaphore(1);
        }
        for (int i = 0; i < arrA.length; i++) {
            arrB[i]= new Semaphore(1);
       }
        CentroDeImpresion centroImp = new CentroDeImpresion(arrA, arrB);
        Usuario usu1 = new Usuario('A', "algo de usuaraio1", centroImp);
        Usuario usu2 = new Usuario('B', "algo de usuaraio2", centroImp);
        Usuario usu3 = new Usuario('B', "algo de usuaraio3", centroImp);
        Usuario usu4 = new Usuario('A', "algo de usuaraio4", centroImp);
    
        usu1.start();
        usu2.start();
        usu3.start();
        usu4.start();
    } */

    /*      EJERCICIO 6      */

    /* public static void main(String[] args) {
        Semaphore [] arr = {new Semaphore(1),new Semaphore(0)};
        Carrera t = new Carrera(arr);
        Atleta corredor1 = new Atleta(t,0);
        Atleta corredor2 = new Atleta(t,0);
        Atleta corredor3 = new Atleta(t,1);
        Atleta corredor4 = new Atleta(t,1);
        Thread h1 = new Thread(corredor1, "Corredor1");
        Thread h2 = new Thread(corredor2, "Corredor2");
        Thread h3 = new Thread(corredor3, "Corredor3");
        Thread h4 = new Thread(corredor4, "Corredor4");
        
        Thread [] hilos = {h1,h2,h3,h4};
        for (int i = 0; i < hilos.length; i++) {
            hilos[i].start();
        }

        } */

    /*      EJERCICIO 7      */

    public static void main(String[] args) {
        Taxi nro1 = new Taxi();
        Taxista t1 = new Taxista(nro1);
        Pasajero p1 = new Pasajero("Neuquen", nro1);
        Pasajero p2 = new Pasajero("Cipolletti", nro1);

        Thread puesto1 = new Thread(t1, "Jorge");
        Thread v2 = new Thread(p1, "Matias");
        Thread v3 = new Thread(p2, "Franco");

        puesto1.start();
        v2.start();
        v3.start();

    }
}
