package ProgrmacionConcurrente.SeccionCritica.Ejercicio5;

public class Impresora {
    
    private char tipo;
    private int nro;
    private boolean estado;

    public Impresora(char t, int n){
        tipo = t;
        nro = n;
        estado = false;
    }

    public char getTipo() {
        return tipo;
    }

    public int getNro() {
        return nro;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    
    
}
