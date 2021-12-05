package ProgrmacionConcurrente.PracticaRecuperatorio;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class dificil {
    
    public static void main(String[] args) {
        
        Entrada e = new Entrada();
        new Thread(new Control(e)).start();
        for (int i = 0; i < 3; i++) {
            new Thread(new Soltero(false,e), " Soltero "+i).start();
            //new Thread(new Pareja(true,e),"Pareja "+i).start();
        }
    }
}

class Entrada {

    private Lock accesoFila; // Lock para exclusividad al ingresar al hotel
    
    private Lock fila1; // Lock para la exclusividad al Bailar de la fila 1
    private Condition esperarEnFila1; // Condicion para salir a bailar dela fila 1
    private Lock fila2; // Lock para la exclusividad al Bailar de la fila 2
    private Condition esperarEnFila2; // condicion para salir a bailar de la fila 2
    

    private int cantFila1; // Cantidad de personas en fila 1
    private int cantFila2; // Cantidad de personas en fila 2
    private int bailarFila2;
    private int bailarFila1;
    //private boolean salioDeFila1;
    //private boolean salioDeFila2;

    public Entrada(){
        accesoFila = new ReentrantLock(true);
        fila1 = new ReentrantLock(true);
        fila2 = new ReentrantLock(true);
        esperarEnFila1 = fila1.newCondition();
        esperarEnFila2 = fila2.newCondition();
        
        cantFila1 = 0;
        cantFila2 = 0;

        bailarFila1 = 0;
        bailarFila2 = 0;
        //salioDeFila1 = false;
        //salioDeFila2 = false;
    }

    public int ingresarHotel(boolean estado){
        accesoFila.lock();
        int filaAsignada = 0;
        if (estado) {   // pareja
            new Thread(new Pareja(true,1,this), Thread.currentThread().getName()+" 1").start();
            cantFila1++;
            new Thread(new Pareja(true,2,this), Thread.currentThread().getName()+" 2").start();
            cantFila2++;
        } else {    // soltero
            if(new Random().nextInt(2) == 0){
                cantFila1++;
                filaAsignada = 1;
            }else{
                cantFila2++;
                filaAsignada = 2;
            }
        }
        accesoFila.unlock();
        return filaAsignada;
    }

    public void esperarEnFila1(){
        fila1.lock();
        try {
            while (bailarFila1 == 0) {
                esperarEnFila1.await();
            }
            bailarFila1 --;
        } catch (Exception e) {
            //TODO: handle exception
        }
        
        fila1.unlock();
    }

    public void esperarEnFila2(){
        fila2.lock();
        try {
            while (bailarFila2 == 0) {
                esperarEnFila2.await();
            }
            bailarFila2 --;
        } catch (Exception e) {
            //TODO: handle exception
        }
        
        fila2.unlock();
    }

    public void bailar(){
        accesoFila.lock();
        if(cantFila1>0 && cantFila2>0){
            bailarFila1 = 1;
            bailarFila2 = 1;
            esperarEnFila1.signal();
            cantFila1--;
            esperarEnFila2.signal();
            cantFila2--;
        }
        accesoFila.unlock();
    }
}

class Persona implements Runnable{

    boolean pareja;
    Entrada e;

    public Persona(boolean estado, Entrada e){

        pareja = estado;
        this.e=e;
    }

    @Override
    public void run() {
        e.ingresarHotel(pareja);
        
    }
}

class Soltero extends Persona implements Runnable {

    int filaAsignada;

    public Soltero(boolean estado,Entrada e){
        super(estado,e);
    }

    @Override
    public void run() {
        filaAsignada = e.ingresarHotel(pareja);
        if(filaAsignada == 1){
            e.esperarEnFila1();
        }else{
            e.esperarEnFila2();
        }
    }
}

class Pareja extends Persona implements Runnable{
    
    int filaAsignada;

    public Pareja(boolean estado, int fila, Entrada e){
        super(estado,e);
        filaAsignada = fila;
    }

    private void bailando(){
        try {
            System.out.println(Thread.currentThread().getName() +" bailando");
            Thread.sleep(1000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Override
    public void run() {
        if(filaAsignada == 1){
            e.esperarEnFila1();
        }else{
            e.esperarEnFila2();
        }
        bailando();
    }
}

class Control implements Runnable{

    Entrada e;

    public Control(Entrada e){
        this.e = e;
    }

    @Override
    public void run() {
        while (true) {
            e.bailar();
            System.out.println("permitir bailar");
        }
    }
}