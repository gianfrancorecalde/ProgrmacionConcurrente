package ProgrmacionConcurrente.Puerto;
import ProgrmacionConcurrente.Lineales.Dinamicas.*;

import java.util.*;
public class Puerto {
    private  int[] colAmarre;
    private Lista colAlquileres;
    private int valorFijo = 200;
    private static int tamanio = 3;

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

    public boolean agregarAlquiler(int nroAmarre, Barco unBarco, int unCliente, int cantDias){
        Calendar today = new GregorianCalendar(2021, 8, 24);
        long aux = today.getTimeInMillis();
        Alquiler alq = new Alquiler(unCliente, unBarco, nroAmarre,today,today);
        return colAlquileres.insertar(alq, colAlquileres.longitud()+1);
    }
}
