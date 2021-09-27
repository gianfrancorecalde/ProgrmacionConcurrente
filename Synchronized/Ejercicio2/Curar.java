package ProgrmacionConcurrente.Synchronized.Ejercicio2;

public class Curar implements Runnable{
    
    private Vida life;

    public Curar(Vida v){
        life = v;
    }

    private  void curar(int sanacion) throws InterruptedException{
        System.out.println(Thread.currentThread().getName()+" nos cura dandonos +"+ sanacion+ " de Vida");
        Thread.sleep(1000);
        life.modificarVida(sanacion);
        
    }
    public void run() {
        for(int i=0; i<5; i++){
            try {
                this.curar((int)(Math.random()*10));
            } catch (InterruptedException e) {
                System.err.println("Algo fallo");
            }
        }
        
    }
}
