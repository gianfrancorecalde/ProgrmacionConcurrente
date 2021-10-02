package ProgrmacionConcurrente.SeccionCritica.Ejercicio6;

public class Atleta implements Runnable {

    private Carrera testigo;
    private int grupo;

    public Atleta(Carrera t, int g) {
        testigo = t;
        grupo = g;
    }

    @Override
    public void run() {
        try {
            testigo.intentaTomarElTestigo(grupo);
            Thread.sleep((int) Math.random() * 10000);
            if(grupo==1){
                testigo.terminaDeCorrer(grupo-1);
            }else{
                testigo.terminaDeCorrer(grupo+1);
            }
            // System.out.println(Thread.currentThread().getName() + " tardo " + System.currentTimeMillis());
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
