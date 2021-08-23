package ProgrmacionConcurrente.Puerto;
import ProgrmacionConcurrente.Lineales.Dinamicas.*;

import java.util.*;
public class Puerto {
    private  int[] colAmarre;
    private Lista colAlquileres;
    private int valorFijo = 200;
    private static int tamanio = 10;

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


}
