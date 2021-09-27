package ProgrmacionConcurrente.Synchronized.Ejercicio2;

public class Daniar implements Runnable{
    
    private Vida v1;

    public Daniar(Vida v){
        v1 = v;
    }

    private  void danio(int danio) throws InterruptedException{
        System.out.println(Thread.currentThread().getName()+" nos golpea quitandonos "+ danio*-1+ " de Vida");
        Thread.sleep(1000);
        v1.modificarVida(danio*-1);
        
    }

    

    @Override
    public void run() {
        for(int i= 0; i<5;i++){
            try {
                this.danio((int)(Math.random()*10));
            } catch (InterruptedException e) {
                System.err.println("Algo fallo");
            }
        }
        
    }
}
