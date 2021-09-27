package ProgrmacionConcurrente.Synchronized.Ejercicio1;

public class Turno {
    private int valor = 1;
    private String algo = "";

    public synchronized int getValor(){
        return valor;
    }

    public synchronized void deshabilitar(){
        valor = 0;
    }
    
    public synchronized void habilitar(){
        valor = 1;
    }
    public synchronized void escribir(String a){
        algo += a;
    }

    public String imprimir(){
        return algo;
    }
}
