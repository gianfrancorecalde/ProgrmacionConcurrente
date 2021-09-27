package ProgrmacionConcurrente.Synchronized.Ejercicio5;

public class Letra implements Runnable {

    private int repeticion;
    private int turno;
    private static GestorTurno gt = new GestorTurno();

    public Letra(int cant, int t) {
        repeticion = cant;
        turno = t;
    }

    @Override
    public void run() {
        int i = 0;
        while ( i<10) {
            i += gt.turno(repeticion, turno);
        }
        
    }
}
