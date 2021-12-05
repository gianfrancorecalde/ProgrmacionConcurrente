package ProgrmacionConcurrente.PracticaRecuperatorio;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main2 {
    
    public static void main(String[] args) {
        CentroImpresion centro = new CentroImpresion(5, 3);
        for (int i = 0; i < 20; i++) {
            new Thread(new Usuario(centro, 'A'), "Usuario A").start();
            new Thread(new Usuario(centro, 'B'), "Usuario B").start();
            new Thread(new Usuario(centro, 'G'), "Usuario Generico").start();
        }
    }
}

class CentroImpresion {
    Lock accesoImpresoraA;
    Condition esperarImpresoraA;
    int numA;

    Lock accesoImpresoraB;
    Condition esperarImpresoraB;
    int numB;

    public CentroImpresion(int cantA, int cantB){
        accesoImpresoraA = new ReentrantLock();
        esperarImpresoraA = accesoImpresoraA.newCondition();
        numA = cantA;
        accesoImpresoraB = new ReentrantLock();
        esperarImpresoraB = accesoImpresoraB.newCondition();
        numB=cantB;
    }

    public void solicitarImpresionA(){
        accesoImpresoraA.lock();
        try {
            while (numA<1) {
                esperarImpresoraA.await();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        numA--;
        accesoImpresoraA.unlock();
    }

    public void solicitarImpresionB(){
        accesoImpresoraB.lock();
        try {
            while (numB<1) {
                esperarImpresoraB.await();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        numB--;
        accesoImpresoraB.unlock();
    }

    public void terminarImpresionA(){
        accesoImpresoraA.lock();
        numA++;
        esperarImpresoraA.signal();
        accesoImpresoraA.unlock();
    }

    public void terminarImpresionB(){
        accesoImpresoraB.lock();
        numB++;
        esperarImpresoraB.signal();
        accesoImpresoraB.unlock();
    }
}

class Usuario implements Runnable{

    private CentroImpresion centro;
    private char impresion;

    public Usuario(CentroImpresion centro, char tipo){
        this.centro = centro;
        impresion = tipo;
    }

    private void imprimiendoA(){
        try {
            System.out.println(Thread.currentThread().getName() + " imprimiendo A");
            Thread.sleep(1000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    private void imprimiendoB(){
        try {
            System.out.println(Thread.currentThread().getName() + " imprimiendo B");
            Thread.sleep(1000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @Override
    public void run() {
        switch (impresion) {
            case 'A':
                centro.solicitarImpresionA();
                imprimiendoA();
                centro.terminarImpresionA();
            break;
            case 'B':
                centro.solicitarImpresionB();
                imprimiendoB();
                centro.terminarImpresionB();
            break;
            default:
            if (new Random().nextInt(2)==0) {
                centro.solicitarImpresionA();
                imprimiendoA();
                centro.terminarImpresionA();
            } else {
                centro.solicitarImpresionB();
                imprimiendoB();
                centro.terminarImpresionB();
            }
            break;
        }
    }
}
