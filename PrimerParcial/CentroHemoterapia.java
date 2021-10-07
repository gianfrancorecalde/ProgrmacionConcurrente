package ProgrmacionConcurrente.PrimerParcial;

import java.util.concurrent.Semaphore;

public class CentroHemoterapia {

    private Semaphore mutex;
    private Semaphore turnoParaControl;
    private Semaphore turnoParaExtraccion;
    private Semaphore controlMedico;
    private Semaphore finalizaControl;
    private Semaphore finalizaExtraccion;
    private Semaphore extraccion;
    private Semaphore habilitarEnfermero;
    private Semaphore llamada;
    private Semaphore cortaLlamada;
    private Semaphore desayuno;
    private Semaphore mutexTurno;
    private Semaphore enEspera;
    private int sillasOcupadas;
    
    public CentroHemoterapia() {
        mutex = new Semaphore(1);
        turnoParaControl = new Semaphore(1, true);
        turnoParaExtraccion = new Semaphore(1, true);
        controlMedico = new Semaphore(0);
        finalizaControl = new Semaphore(0);
        extraccion = new Semaphore(0);
        finalizaExtraccion = new Semaphore(0);
        llamada = new Semaphore(0);
        habilitarEnfermero = new Semaphore(0);
        cortaLlamada = new Semaphore(0);
        desayuno = new Semaphore(0);
        enEspera = new Semaphore(1,true);
        mutexTurno = new Semaphore(1);
        sillasOcupadas = 0;
    }

    public void llamarAlCentro() {
        try {
            mutex.acquire();
        } catch (Exception e) {
            //TODO: handle exception
        }
        llamada.release();
        System.out.println(Thread.currentThread().getName() + " se comunico con el Centro de Hemoterapia");
    }

    public void atiendeLLamado() {
        try {
            llamada.acquire();
            System.out.println(Thread.currentThread().getName() + " recepciona al donante");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void finalizaLlamada(){
        cortaLlamada.release();
        System.out.println(Thread.currentThread().getName() + " Termina de recepcionar al donante");
    }

    
    public void esperaFinalizacionDeLaRecepcion(){
        try {
            cortaLlamada.acquire();
        } catch (Exception e) {
            //TODO: handle exception
        }
        mutex.release();
        System.out.println(Thread.currentThread().getName() + " se despide");
    }
    
    private void incrementar(){
        try {
            mutexTurno.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        sillasOcupadas++;
        mutexTurno.release();
    }

    private void perdirTurno() {
        try {
            enEspera.acquire();
            if(sillasOcupadas < 5){
                incrementar();
                turnoParaControl.acquire();
                System.out.println("Turno dado a" + Thread.currentThread().getName());
            }else{
                System.out.println(Thread.currentThread().getName() + " en espera de turno");
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }

    public void esperandoControlClinico(){
        try {
            perdirTurno();
            mutexTurno.release();
            turnoParaControl.acquire();
            System.out.println(Thread.currentThread().getName() + " esta listo para ser atendido por el clinico");
            controlMedico.release();
        } catch (Exception e) {
            //TODO: handle exception
        }

    }

    public void atiendeDonanteParaControl() {
        try {
            controlMedico.acquire();
            sillasOcupadas--;
            System.out.println(Thread.currentThread().getName() + " atiende al donante para control ");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void finalizaControl() {
        System.out.println(Thread.currentThread().getName() + " libera al donante");
        finalizaControl.release();
        
    }
    
    public void proseguirAExtraccion(){
        try {
            finalizaControl.acquire();
            turnoParaControl.release();
            turnoParaExtraccion.acquire();
        } catch (Exception e) {
            //TODO: handle exception
        }
        System.out.println(Thread.currentThread().getName() + " se despide del clinico y esta listo para la extraccion");
        habilitarEnfermero.release(); // libera semaforo para que el medico avise a enfermo de que tiene un paciente disponible
    }

    public void comunicarEnfermero() {
        try {
          habilitarEnfermero.acquire();
        } catch (Exception e) {
            //TODO: handle exception
        }
        System.out.println(Thread.currentThread().getName() + " comunico al enfermero de que hay una extraccion disponible");
        extraccion.release();
    }
    

    public void atiendeDonanteParaExtraccion(){
        try {
            extraccion.acquire();
            System.out.println(Thread.currentThread().getName() + " atiende al donante para extraccion ");
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void finalizaExtraccion(){
        System.out.println(Thread.currentThread().getName() + " libera al donante");
        System.out.println(Thread.currentThread().getName() + " le entrega un certificado de donacion al donante");
        finalizaExtraccion.release();
    }
    
    public void despideAlEnfermero() {
        try {
            finalizaExtraccion.acquire();
        } catch (Exception e) {
            //TODO: handle exception
        }
        System.out.println(Thread.currentThread().getName() + " agradece al enfermero y se va a desayunar");
        turnoParaExtraccion.release();
        desayuno.release();
    }

    public void desayunando(){
        try {
            desayuno.acquire();
            System.out.println(Thread.currentThread().getName() + " desayuna");
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
