package ProgrmacionConcurrente.Parcial2_Recalde_FAI2757.Ejercicio1;

public class GeneradorDePedidos implements Runnable{
    
    Restaurante sala;

    public GeneradorDePedidos(Restaurante s){
        sala = s;
    }

    private void proximoPedido(){
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Override
    public void run() {
        while (true) {
            sala.generarPedido();
            System.out.println("generando pedido");
            proximoPedido();
        }
        
    }
}
