package ProgrmacionConcurrente.SeccionCritica.Ejercicio4;

public class Letra extends Thread{
    
    private char letra;
    private int cant; 
    private GestorLetras gestorLetra;

    public Letra( char letra, int cantidad, GestorLetras gl){
        this.letra = letra;
        cant = cantidad;
        gestorLetra = gl;
    }

    @Override
    public void run() {
        gestorLetra.generarCadena(cant, letra);
    }
}
