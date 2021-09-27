package ProgrmacionConcurrente.SeccionCritica.Ejercicio6;

public class Atleta extends Thread{
    
    private Testigo testigo;

    public Atleta(Testigo t, String nombre){
        super(nombre);
        testigo = t;
    }

    @Override
    public void run() {
        testigo.correr();
    }


    
}
