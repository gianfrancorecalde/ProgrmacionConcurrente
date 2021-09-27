package ProgrmacionConcurrente.SeccionCritica;

import ProgrmacionConcurrente.SeccionCritica.Ejercicio4.GestorLetras;
import ProgrmacionConcurrente.SeccionCritica.Ejercicio4.Letra;
import ProgrmacionConcurrente.SeccionCritica.Ejercicio5.CentroDeImpresion;
import ProgrmacionConcurrente.SeccionCritica.Ejercicio5.Usuario;
import ProgrmacionConcurrente.SeccionCritica.Ejercicio6.Atleta;
import ProgrmacionConcurrente.SeccionCritica.Ejercicio6.Testigo;
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

    public static void main(String[] args) {
        CentroDeImpresion centroImp = new CentroDeImpresion();
        Usuario usu1 = new Usuario('A', "algo de usuaraio1", centroImp);
        Usuario usu2 = new Usuario('B', "algo de usuaraio2", centroImp);
        Usuario usu3 = new Usuario('B', "algo de usuaraio3", centroImp);
        Usuario usu4 = new Usuario('A', "algo de usuaraio4", centroImp);
    
        usu1.start();
        usu2.start();
        usu3.start();
        usu4.start();
    }

    /*      EJERCICIO 6      */

    /* public static void main(String[] args) {
        Testigo t = new Testigo();
        Atleta corredor1 = new Atleta(t, "corredor1");
        Atleta corredor2 = new Atleta(t, "corredor2");
        Atleta corredor3 = new Atleta(t, "corredor3");
        Atleta corredor4 = new Atleta(t, "corredor4");


        corredor1.start();
        corredor2.start();
        corredor3.start();
        corredor4.start();
    } */
}
