package ProgrmacionConcurrente.Parcial2_Recalde_FAI2757.Ejercicio1;

public class Main {
    
    public static void main(String[] args) {
        
        Restaurante r = new Restaurante(3);
        new Thread(new GeneradorDePedidos(r), "GeneradorDePedidos").start();;
        for (int i = 0; i < 10; i++) {
            new Thread(new PizzeroNapolitana(r), "PizzeroNapolitana "+i).start();
            new Thread(new PizzeroVegetariana(r), "PizzeroVegetariana "+i).start();
            new Thread(new Repartidor(r), "Repartidor "+i).start();
        }
        
    }
}
