package ProgrmacionConcurrente.SincronizacionPorCooperacion.Monitor;

public class Main {
    
    public static void main(String[] args) {
        
        Comedor comedor = new Comedor(3);

        Thread arr[] = new Thread[4];
        Thread arr2[] = new Thread[4];
        for (int i = 0; i < 4; i++) {
            arr[i]= new Thread(new Gato(comedor), "Gato " + i);
            arr2[i] = new Thread(new Perro(comedor), "Perro " + i);
            arr[i].start();
            arr2[i].start();
        }
    }

}
