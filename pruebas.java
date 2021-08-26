package ProgrmacionConcurrente;
import java.util.*;

public class pruebas {
    
    public static void main(String[] args) {
        Date hoy = new Date();
        long aux = hoy.getTime();
        hoy.setTime(aux+(86400000*7));
        System.out.println(hoy);
    }
}
