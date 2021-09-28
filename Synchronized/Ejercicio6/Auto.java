package ProgrmacionConcurrente.Synchronized.Ejercicio6;

public class Auto extends Vehiculo implements Runnable {
    
    private int kmRecorridos;
    private Surtidor s = new Surtidor();

    public Auto(String patente, String modelo, String marca, int km, int kmR){
        super(patente, modelo, marca, km);
        kmRecorridos = kmR;
    }

    @Override
    public void run() {
        while(kmRecorridos < this.getKmFaltanteParaService()){
            kmRecorridos+= Math.random()*100;
        }
        if(kmRecorridos>this.getKmFaltanteParaService()){
            s.cargarCombustible();
        }
    }
}
