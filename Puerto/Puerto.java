package ProgrmacionConcurrente.Puerto;
import ProgrmacionConcurrente.Lineales.Dinamicas.*;

import java.util.*;
public class Puerto {
    private  int[] colAmarre;
    private Lista colAlquileres;
    private int valorFijo = 200;
    private static int tamanio = 3;
    private static int limiteDias = 30;

    public Puerto(){
        colAmarre = new int[tamanio];
        colAlquileres = new Lista();
    }

    public void agregarAmarres(){
        Scanner sc = new Scanner(System.in);
        int i = 0;
        while(i<this.tamanio){
            System.out.println("Ingrese un valor para el amarrre");
            int amarre = sc.nextInt();
            colAmarre[i] = amarre;
            i++;
        }
    }

    public int getValorFijo(){
        return this.valorFijo;
    }

    public int getAmarre(int posicion){
        return this.colAmarre[posicion];
    }

    public Lista getAlquileres(){
        return this.colAlquileres;
    }

    public static int getLimiteDias() {
        return limiteDias;
    }

    public boolean agregarAlquiler(int nroAmarre, Barco unBarco, int unCliente, int cantDias){
        
        Date hoy = new Date();
        Date fin = new Date();
        fin.setTime(hoy.getTime()+(1000*60*60*24*cantDias));
        Alquiler alq = new Alquiler(unCliente, unBarco, nroAmarre, hoy, fin, unBarco.calcularValor(cantDias, valorFijo));
        return colAlquileres.insertar(alq, colAlquileres.longitud()+1);
    }
}
