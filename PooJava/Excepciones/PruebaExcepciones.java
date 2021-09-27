package ProgrmacionConcurrente.PooJava.Excepciones;

import java.io.IOException;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class PruebaExcepciones {

    /*
     * public static void main(String[] args) { String [] array_string = new String
     * [25]; System.out.println (array_string [3].length()); }
     */

    /*
     * public static void main(String[] args) { String aux ="hola"; int aux2 =
     * Integer.parseInt (aux); }
    */

    /*                  Ejercicio 6                      */

    /*
     * private static double acceso_por_indice(double[] v, int j) throws
     * RuntimeException {
     * 
     * return v[j]; }
     * 
     * public static double capturarExcepcion(double[] v, int j){ double aux; try{
     * aux = acceso_por_indice(v,j); }catch(RuntimeException exc){ aux = 0;
     * System.out.println("El indice " + j + " no existe en el vector");
     * 
     * } return aux; }
     * 
     * // Desde el siguiente cliente “main”: public static void main(String[] args)
     * { double[] v = new double[15]; capturarExcepcion(v, 16); }
     */

    /* public static double acceso_por_indice(double[] v, int j) {
        double aux = 0;
        try {
            aux = v[j];
        } catch (RuntimeException exc) {
            System.out.println("El indice " + j + " no existe en el vector");
        }
        return aux;
    }

    // Desde el siguiente cliente “main”:
    public static void main(String[] args) {
        double[] v = new double[15];
        acceso_por_indice(v, 16);
    } */

    /*                          Ejercicio 7                      */

    /* private static int metodo(){
        int valor = 0;
        try{
            valor += 1;
            valor += Integer.parseInt("42");
            valor += 1;
            System.out.println("valor al final del try: "+valor);
        }catch(NumberFormatException e){
            valor += Integer.parseInt("42");
            System.out.println("valor al final del catch: "+valor);
        }finally{
            valor += 1;
            System.out.println("valor al final del finally: "+valor);
        }
        valor += 1;
        System.out.println("valor al final del return: "+valor);
        return valor;
    }

    public static void main(String[] args) {
        try{
            System.out.println(metodo());
        }catch(Exception e){
            System.err.println("Excepcion en metodo()");
            e.printStackTrace();
        }
    } */

    /* private static int metodo(){
        int valor = 0;
        try{
            valor += 1;
            valor += Integer.parseInt("W");
            valor += 1;
            System.out.println("valor al final del try: "+valor);
        }catch(NumberFormatException e){
            valor += Integer.parseInt("42");
            System.out.println("valor al final del catch: "+valor);
        }finally{
            valor += 1;
            System.out.println("valor al final del finally: "+valor);
        }
        valor += 1;
        System.out.println("valor al final del return: "+valor);
        return valor;
    }

    public static void main(String[] args) {
        try{
            System.out.println(metodo());
        }catch(Exception e){
            System.err.println("Excepcion en metodo()");
            e.printStackTrace();
        }
    } */

    /* private static int metodo(){
        int valor = 0;
        try{
            valor += 1;
            valor += Integer.parseInt("W");
            valor += 1;
            System.out.println("valor al final del try: "+valor);
            throw new IOException();
        }catch(IOException e){
            valor += Integer.parseInt("W");
            System.out.println("valor al final del catch: "+valor);
        }finally{
            valor += 1;
            System.out.println("valor al final del finally: "+valor);
        }
        valor += 1;
        System.out.println("valor al final del return: "+valor);
        return valor;
    }

    public static void main(String[] args) {
        try{
            System.out.println(metodo());
        }catch(Exception e){
            System.err.println("Excepcion en metodo()");
            e.printStackTrace();
        }
    } */

    /*          Ejercicio 8              */

    /* public static void mayorEdad(int edad) throws Exception{
        if(edad >= 18){
            System.out.println("mayor edad");
        }else{
            throw new Exception();
        }
    } */

    /* public static String ruleta(int num) throws Exception{
            if((int)Math.random()*100 == num){
                return "Numero acertado";
            }else{
                throw new Exception();
            }
    } */

    public static void agregarElem(int [] arr){
        Scanner sc = new Scanner(System.in);
        int i;
        for(i=0; i<5; i++){
            System.out.println("Ingrese un numero");
            int x = sc.nextInt();
            arr[i] = x;
        }
    }

    public static void main(String[] args) {
        int [] arr = new int[5];
        agregarElem(arr);
        try{
            int i;
            for(i=0; i<7;i++){
                System.out.println(arr[i]);
            }
        }finally{
            System.out.println("nada");
        }
    }

    
}
