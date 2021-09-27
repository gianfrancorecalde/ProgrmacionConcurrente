package ProgrmacionConcurrente.SeccionCritica.Ejercicio5;

import java.util.concurrent.Semaphore;

public class CentroDeImpresion {

    private Semaphore tipoA;
    private Semaphore tipoB;
    private ImpresoraTipoA numA;
    private ImpresoraTipoB numB;

    public CentroDeImpresion() {
        tipoA = new Semaphore(1);
        tipoB = new Semaphore(1);
        numA = new ImpresoraTipoA();
        numB = new ImpresoraTipoB();
    }

    public void imprimir(char tipo, String contenido) {
        switch (tipo) {
            case 'A':
                    imprimirConA(contenido);
                break;
            case 'B':
                    imprimirConB(contenido);
                break;
            case 'X':
                boolean impreso = false;
                
                while(!impreso){
                    if(tipoA.tryAcquire()){
                        imprimirConA(contenido);
                        impreso = true;
                    }else{
                        if(tipoB.tryAcquire()){
                            imprimirConB(contenido);
                            impreso = true;
                        }
                    }
                    System.out.println("estoy en el while"+Thread.currentThread().getName());
                }
                break;
            default:
                break;
        }

    }

    private void imprimirConA(String contenido) {
        try {
            tipoA.acquire();
            System.out.println("ok");
            numA.imprimiendo(contenido);
            Thread.sleep(1000);
            tipoA.release();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private void imprimirConB(String contenido) {
        try {
            tipoB.acquire();
            numB.imprimiendo(contenido);
            Thread.sleep(1000);
            tipoB.release();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
