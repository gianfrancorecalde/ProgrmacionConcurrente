package ProgrmacionConcurrente.Parcial2_Recalde_FAI2757.Ejercicio1;

import java.util.Random; 
import java.util.concurrent.Semaphore;
import ProgrmacionConcurrente.Lineales.Dinamicas.*;

public class Restaurante {
    
    Pedido napolitana;                // se guarda un pedido napolitana
    Pedido vegetariano;               // se guarda un pedido vegetariano

    Semaphore mutexPedidoVegetariano;          // Semaforo binario para la excluvidad en el manejo del pedido

    Semaphore mutexPedidoNapolitana;          // AGREGE ESTE SEAMFORO
    
    Semaphore fabricarNapolitana;   // Semaforo para avisar a pizzero q debe fabrciar vegetariana
    Semaphore fabricarVegetarianas; // Semaforo para avisar a pizzero q debe fabrciar napoitana
    Semaphore mutexMostrador;       // Semaforo binario para la exclusividad en el uso del mostrador
    Semaphore colocarEnMostrador;   // Semaforo con los permisos para poner pedidos en mostrador
    Semaphore avisoDePedidosListoParaEntregar;    // Semaforo generico con los pemisos que avisan que hay pedidos para entregar
    Semaphore ordenGenerarPedido;   // Semaforo que da la orden para la generacio automatica de pedidos
    
    Cola pedidoParaEntrega;         // guarda los pedidos listos para entrega

    int pedidosEnMostrador;               // cantidad de pedidos en el mostrador
    int pedidosEnMostradorPermitidos;   // Cantidad de pedidos permitidos en el mostrador
    
    public Restaurante(int cantMaxPizzasMostrador){
        pedidosEnMostradorPermitidos = cantMaxPizzasMostrador;

        mutexPedidoVegetariano = new Semaphore(1); // CAMBIE EL NOMBRE DEL SEMAFORO
        mutexPedidoNapolitana = new Semaphore(1); //AGREGE ESTE SEMAFORO PARA PODER CREAR PEDIDOS NAPOLITANAS Y VEGETARIAN EN SIMULNATENOS
        mutexMostrador = new Semaphore(1);
        fabricarNapolitana = new Semaphore(0);
        fabricarVegetarianas = new Semaphore(0);
        avisoDePedidosListoParaEntregar = new Semaphore(0); 
        colocarEnMostrador = new Semaphore(cantMaxPizzasMostrador);
        ordenGenerarPedido = new Semaphore(1);

        pedidoParaEntrega = new Cola();

        pedidosEnMostrador = 0;
         
    }

    public void generarPedido(){
        // Lo ejecuta el hilo generador para ir generando los pedidos automaticamente
        Pedido aux;
        try {
            //ordenGenerarPedido.acquire();
        aux = new Pedido((new Random().nextInt(2)+1), "Cliente " +new Random().nextInt(100)); // ERROR nextInt(2)
            // CAMBIA LA ORGANIZACION
        if(aux.getTipo() == 1){
            mutexPedidoNapolitana.acquire();
            napolitana = aux;
            fabricarNapolitana.release();
            mutexPedidoNapolitana.release();
        }else{
            if (aux.getTipo() == 2) {
                mutexPedidoVegetariano.acquire();
                vegetariano = aux;
                fabricarVegetarianas.release();
                mutexPedidoVegetariano.release();
            }
        }
        } catch (Exception e) {
            //TODO: handle exception
        }
        //ordenGenerarPedido.release();
        //mutexPedidoVegetariano.release();
    }

    public Pedido fabricarPizzaNapolitana(){
        // lo ejecuta el pizzeroNapolitana para fabricar la napolitana
        Pedido aux;
        try {
            fabricarNapolitana.acquire();
            mutexPedidoNapolitana.acquire();
        } catch (Exception e) {
            //TODO: handle exception
        }
        aux = napolitana;
        mutexPedidoNapolitana.release();
        return aux;
    }

    public Pedido fabricarPizzaVegetariana(){
        // lo ejecuta el pizzeroNapolitana para fabricar la vegetariano
        Pedido aux;
        try {
            fabricarVegetarianas.acquire();
            mutexPedidoVegetariano.acquire();
        } catch (Exception e) {
            //TODO: handle exception
        }
        aux = vegetariano;
        mutexPedidoVegetariano.release();
        return aux;
    }

    public void ponerEnMostradorNapolitana(Pedido p){
        // lo ejecuta el hilo pizzeroNapolitan para poner pedido en mostrador
        try {
            colocarEnMostrador.acquire();
            mutexMostrador.acquire();
            pedidosEnMostrador++;
        } catch (Exception e) {
            //TODO: handle exception
        }
        pedidoParaEntrega.poner(p);
        System.out.println(Thread.currentThread().getName() + " Dejo pedido napolitana en mostrador"); // AGREGE CARTEL PARA SIMULAR EL DEJAR EL PEDIDO EN EL MOSTRADOR
        avisoDePedidosListoParaEntregar.release();
        mutexMostrador.release();
    }

    public void ponerEnMostradorVegetariana(Pedido p){
        // lo ejecuta el hilo pizzeroVegetariana para poner pedido en mostrador
        try {
            colocarEnMostrador.acquire();
            mutexMostrador.acquire();
            pedidosEnMostrador++;
        } catch (Exception e) {
            //TODO: handle exception
        }
        pedidoParaEntrega.poner(p);
        System.out.println(Thread.currentThread().getName() + " Dejo pedido vegetariano en mostrador");// AGREGE CARTEL PARA SIMULAR EL DEJAR EL PEDIDO EN EL MOSTRADOR
        avisoDePedidosListoParaEntregar.release();
        mutexMostrador.release();
    }

    public Pedido tomarPedidoDelMostrador(){
        // lo ejecuta el hilo repartidor para repartir los pedidos
        Pedido aux;
        try {
            avisoDePedidosListoParaEntregar.acquire();
            mutexMostrador.acquire();
        } catch (Exception e) {
            //TODO: handle exception
        }
        /* if(pedidosEnMostrador == pedidosEnMostradorPermitidos){
            ordenGenerarPedido.release();
        } */
        pedidosEnMostrador--;
        aux = (Pedido)pedidoParaEntrega.obtenerFrente();
        pedidoParaEntrega.sacar();
        colocarEnMostrador.release();
        mutexMostrador.release();
        return aux;
    }
    
}
