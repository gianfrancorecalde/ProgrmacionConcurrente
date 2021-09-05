package ProgrmacionConcurrente.Concurrencia;

public class Cajero {
    private String nombre; 
   
    public Cajero(String name){
        nombre = name;
    }

    public void procesarCompra(Clienta cliente, long timeStamp){
       System.out.println("El cajero "+this.nombre+" COMIENZA A PROCESAR "+
       "LA COMPRA DEL CLIENTE "+ cliente.getNombre() + " EN EL TIMEPO: "+
       (System.currentTimeMillis()-timeStamp)/1000+" seg");

        for(int i=0; i<cliente.getCarroCompra().length; i++){
           this.esperarXSegundos(cliente.getCarroCompra()[i]);
           System.out.println("Procesado el producto "+(i+1)+
           "->Tiempo: "+((System.currentTimeMillis()-timeStamp)/1000+" seg"));
        }
        System.out.println("El cajero "+this.nombre+" HA TERMINADO DE PROCESAR "+
        cliente.getNombre() + "EN EL TIEMPO: "+ ((System.currentTimeMillis()-timeStamp)/1000+" seg"));
    }

    private void esperarXSegundos(int segundos){
        int conatado = 0;
        for(int i=0; i<segundos; i++){
            conatado+=i;
        }
    }
}
