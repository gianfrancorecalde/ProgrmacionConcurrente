package ProgrmacionConcurrente.SeccionCritica.Ejercicio7;

public class Pasajero implements Runnable{
    
    private String destino;
    private Taxi taxi;

    public Pasajero(String lugar, Taxi t){
        destino = lugar;
        taxi = t;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " Buscando Taxi");
            Thread.sleep(600); // Simula la busqueda del taxi
            taxi.subir();
            Thread.sleep(200); // Simula la respuesta la pregunta del taxista
            taxi.indicarDestino(destino);
            Thread.sleep(2000); // Simula viaje del cliente
            taxi.bajar();
        } catch (Exception e) {
            //TODO: handle exception
        }
        // TODO Auto-generated method stub
        
    }
}
