package ProgrmacionConcurrente.Parcial2_Recalde_FAI2757.Ejercicio1;

public class PizzeroVegetariana implements Runnable{
    
    Pedido p; 
    Restaurante sala;

    public PizzeroVegetariana(Restaurante s){
        sala = s;
    }

    private void fabricandoVegetariana(){
        try {
            System.out.println(Thread.currentThread().getName() + " fabricando Vegetariana"); // ERROR CARTEL EQUIVOCADO (NAPOLITANA -> VEGETARIANA)
            Thread.sleep(500);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Override
    public void run() {
        while(true){
            p = sala.fabricarPizzaVegetariana();
            fabricandoVegetariana();
            sala.ponerEnMostradorVegetariana(p);

        }
        
    }
}
