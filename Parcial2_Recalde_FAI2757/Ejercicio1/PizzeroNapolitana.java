package ProgrmacionConcurrente.Parcial2_Recalde_FAI2757.Ejercicio1;

public class PizzeroNapolitana implements Runnable {
    
    Pedido p; 
    Restaurante sala;

    public PizzeroNapolitana(Restaurante s){
        sala = s;
    }

    private void fabricandoNapolitana(){
        try {
            System.out.println(Thread.currentThread().getName() + " fabricando Napolitana");
            Thread.sleep(500);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Override
    public void run() {
        while (true) {
            p = sala.fabricarPizzaNapolitana();
            fabricandoNapolitana();
            sala.ponerEnMostradorNapolitana(p);
        }
    }
}
