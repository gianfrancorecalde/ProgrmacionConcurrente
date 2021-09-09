package ProgrmacionConcurrente.Synchronized;

public class Vida{
    
    private int life;

    public Vida(int cantidad){
        life = cantidad;
    }

    public synchronized int getVida(){
        return life;
    }

    public synchronized void modificarVida(int cantidad){
        // el cartel solo incluirlo si realmente es necesario, debe existir una justificacion.
        if(cantidad <0){
            life = life - Math.abs(cantidad);
        }else{
            life+=cantidad;
        }
        System.out.println(Thread.currentThread().getName()+" Nos modifico la vida en "+life+"\n");
    }

}
