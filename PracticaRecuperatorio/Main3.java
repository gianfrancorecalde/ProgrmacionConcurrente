package ProgrmacionConcurrente.PracticaRecuperatorio;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* EJERCICIO DE PIZZA */

public class Main3 {
    
    public static void main(String[] args) {
        
    }
}

class Cocina {

    Queue pedidosPendientesV;
    Queue pedidosPendientesN;

    Lock accesoPedidoN;
    Condition hayPedidosN;
    
    Lock accesoPedidoV;
    Condition hayPedidosV;

    Pedido pedidoN;
    Pedido pedidoV;

    public Cocina(){
        
        accesoPedidoN = new ReentrantLock();
        hayPedidosN = accesoPedidoN.newCondition();

        accesoPedidoV = new ReentrantLock();
        hayPedidosV = accesoPedidoV.newCondition();

    }


}

class Mostrador {

    Queue pedidosListos;
}


class PizzeroV implements Runnable {
    
    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }
}// pizzero que frabrica vegetarianas

class PizzeroN implements Runnable {

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }
}// pizzero que fabrica napolitanas

class Repartidor implements Runnable {



    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }
}// clase repartidor

class Generador implements Runnable{

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }
}// generar de pedidos

class Pedido {

    private int nroCliente;
    private char tipo;

    public Pedido(char t, int cliente){
        tipo = t;
        nroCliente = cliente;
    }

    public int getNroCliente() {
        return nroCliente;
    }

    public char getTipo() {
        return tipo;
    }
    
}// clase pedido