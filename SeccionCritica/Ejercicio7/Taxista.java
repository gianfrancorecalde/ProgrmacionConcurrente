package ProgrmacionConcurrente.SeccionCritica.Ejercicio7;

public class Taxista implements Runnable {

    private Taxi taxi;

    public Taxista(Taxi auto) {
        taxi = auto;
    }

    @Override
    public void run() {
        try {
            while(true){
                System.out.println(Thread.currentThread().getName() + " a caido en un profundo sueño");
                taxi.atenderCliente();
                Thread.sleep(2000); // simula viaje a destino
                taxi.paradaAlcanzada(); 
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
