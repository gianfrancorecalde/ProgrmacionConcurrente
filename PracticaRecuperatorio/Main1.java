package ProgrmacionConcurrente.PracticaRecuperatorio;

public class Main1 {
    
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

    private int numA;
    private int numB;

    public CentroImpresion(int cantA, int cantB){
        numA = cantA;
        numB = cantB;
    }

    public synchronized void solicitarImpresionA(){
        try {
            while (numA <1) {
                this.wait();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        numA--;
    }

    public synchronized void solicitarImpresionB(){
        try {
            while (numB <1) {
                this.wait();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        numB--;
    }

    public synchronized void terminarImpresionA(){
        numA++;
        this.notifyAll();
    }

    public synchronized void terminarImpresionB(){
        numB++;
        this.notifyAll();
    }

    public synchronized int impresoraDisponible(){
        return numA;
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
            if (centro.impresoraDisponible()>0) {
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
