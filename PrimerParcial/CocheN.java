package ProgrmacionConcurrente.PrimerParcial;

public class CocheN implements Runnable{
    
    private GestorCruce gC;

    public CocheN(){
        gC = new GestorCruce();
    }

    @Override
    public void run() {
        /* gC.llegaNorte();
        //gC.cruzar();
        gC.saleSur(); */
    }
}
