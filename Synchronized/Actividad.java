package ProgrmacionConcurrente.Synchronized;

public class Actividad {


    public Actividad(){}

    public synchronized void comerPlato(){ 
        try {
            procesadoAct();
            System.out.print(" comida\n");
            Thread.sleep(500);
            terminaAct();
            System.out.print(" comida\n");
        } catch (Exception e) {
            System.err.println("No pudo comer");
        }
    } 

    public synchronized void usarRueda(){
        try {
            procesadoAct();
            System.out.print(" rueda\n");
            Thread.sleep(1000);
            terminaAct();
            System.out.print(" rueda\n");
        } catch (Exception e) {
            System.err.println("No pudo usar la rueda");
        }
    }

    public void usandoHamaca(){
        synchronized(this){
            try {
                procesadoAct();
                System.out.print(" hamaca\n");
                Thread.sleep(2000);
                terminaAct();
                System.out.print(" hamaca\n");
            } catch (Exception e) {
                System.err.println("No pudo hamacarse");
            }
        }
    }

    private  void procesadoAct(){
        System.out.print( Thread.currentThread().getName() +" Esta realizando la actividad");
    }

    private  void terminaAct(){
        System.out.print( Thread.currentThread().getName() +" Termina la actividad");
    }
}
