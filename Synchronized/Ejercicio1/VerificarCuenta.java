package ProgrmacionConcurrente.Synchronized.Ejercicio1;

import java.util.logging.*;

public class VerificarCuenta implements Runnable{
    private CuentaBanco cb = new CuentaBanco();

    private void hacerRetiro(int cantidad)throws InterruptedException{
        if(cb.getBalance() >= cantidad){
            System.out.println(Thread.currentThread().getName()+ " esta realizando un retiro de " + cantidad +".");
            Thread.sleep(1000);
            cb.retiroBancario(cantidad);
            System.out.println(Thread.currentThread().getName() + ": Retiro realizado");
            System.out.println(Thread.currentThread().getName() + ": Los Fondos son de: "+cb.getBalance());
        }else{
            System.out.println("no hay suficiente dinero en la cuenta para realizar el retiro Sr."+Thread.currentThread().getName());
            System.out.println("Su saldo actual es de" +cb.getBalance());
            Thread.sleep(1000);
        }
    }

    public void run() {
        for (int i = 0; i<=3;i++){
            try{
                System.out.println(Thread.currentThread().getName());
                this.hacerRetiro(10);
                if (cb.getBalance()<0) {
                    System.out.println("La cuenta esta sobregirada");
                }
            }catch (InterruptedException e) {
                Logger.getLogger(VerificarCuenta.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        
    }
}
