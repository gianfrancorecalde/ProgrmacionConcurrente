package ProgrmacionConcurrente.Concurrencia;

public class CajeroThread extends Thread{
    
    private String nombre;
    private Clienta cliente;
    private long initialTime;

    public CajeroThread(String name, Clienta c, long time){
        nombre = name;
        cliente = c;
        initialTime = time;
    }

    public void run() {
        System.out.println("El cajero "+this.nombre+" COMIENZA A PROCESAR "+
       "LA COMPRA DEL CLIENTE "+ cliente.getNombre() + " EN EL TIMEPO: "+
       (System.currentTimeMillis()-this.initialTime)/1000+" seg");

        for(int i=0; i<cliente.getCarroCompra().length; i++){
           this.esperarXSegundos(cliente.getCarroCompra()[i]);
           System.out.println("Procesado el producto "+(i+1)+" del cliente "+ cliente.getNombre()+
           "->Tiempo: "+((System.currentTimeMillis()-this.initialTime)/1000+" seg"));
        }
        System.out.println("El cajero "+this.nombre+" HA TERMINADO DE PROCESAR "+
        cliente.getNombre() + "EN EL TIEMPO: "+ ((System.currentTimeMillis()-this.initialTime)/1000+" seg"));
    }

    private void esperarXSegundos(int segundo){
        try {
            Thread.sleep(segundo*1000);
        } catch (Exception e) {
            System.err.println("algo fallo");
        }
    }
}
