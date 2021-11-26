package ProgrmacionConcurrente.Parcial2_Recalde_FAI2757.Ejercicio1;

public class Repartidor implements Runnable{
    
    Restaurante sala;
    int pedidosEntregados;
    Pedido p;

    public Repartidor(Restaurante s){
        sala = s;
        pedidosEntregados = 0;
    }

    private void entregandoPedido(){
        try {
            System.out.println(Thread.currentThread().getName() + " esta entregando el pedido del cliente "+ p.getNombreCliente());
            Thread.sleep(5010);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    private void descansando(){
        try {
            System.out.println(Thread.currentThread().getName() + " descansando");
            Thread.sleep(500);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }


    private void volviendoPorPedido(){
        try {
            System.out.println(Thread.currentThread().getName() + " volviendo a la pizzeria");
            Thread.sleep(500);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    @Override
    public void run() {
        while (true) {
            p = sala.tomarPedidoDelMostrador();
            entregandoPedido();
            pedidosEntregados++;
            volviendoPorPedido();
            if(pedidosEntregados == 10){
                pedidosEntregados = 0;
                descansando();
            }
        }
        
    }

}
