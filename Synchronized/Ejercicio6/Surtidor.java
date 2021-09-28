package ProgrmacionConcurrente.Synchronized.Ejercicio6;

public class Surtidor {
    
    public void cargarCombustible(){

        synchronized(this){
            System.out.println("Cargadno combustible "+Thread.currentThread().getName());
        }
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }
}
