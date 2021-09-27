package ProgrmacionConcurrente.SeccionCritica.Ejercicio5;

public class Usuario extends Thread{
    
    private char tipoImpresora;
    private String contenido;
    private CentroDeImpresion cI;

    public Usuario(char tipo, String contenido, CentroDeImpresion centroImpre){
        tipoImpresora = tipo;
        this.contenido = contenido;
        cI = centroImpre;
    } 

    @Override
    public void run() {
        cI.imprimir(tipoImpresora, contenido);
    }
}
