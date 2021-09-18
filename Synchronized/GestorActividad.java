package ProgrmacionConcurrente.Synchronized;

public class GestorActividad implements Runnable {

    private Actividad act;

    public GestorActividad() {
        act = new Actividad();
    }

    public void run() {
        try {

            System.out.println(Thread.currentThread().getName() + " Quiere comer\n");
            act.comerPlato();

            System.out.println(Thread.currentThread().getName() + " Quiere usar la rueda\n");
            act.usarRueda();

            System.out.println(Thread.currentThread().getName() + " Quiere hamacarse\n");
            act.usandoHamaca();

        } catch (Exception e) {
            System.err.println("algo fallo");
        }
    }
}
