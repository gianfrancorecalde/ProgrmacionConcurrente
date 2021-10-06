package ProgrmacionConcurrente.PrimerParcial;

import java.util.concurrent.Semaphore;

public class CentroHemoterapia {

    private Semaphore mutex;
    private Semaphore turnoParaControl;
    private Semaphore turnoParaExtraccion;
    private Semaphore controlMedico;
    private Semaphore extraccion;
    private Semaphore llamada;
    private Semaphore certificado;
    private Semaphore desayuno;

    public CentroHemoterapia() {
        mutex = new Semaphore(1);
        turnoParaControl = new Semaphore(1, true);
        turnoParaExtraccion = new Semaphore(1, true);
        controlMedico = new Semaphore(0);
        extraccion = new Semaphore(0);
        llamada = new Semaphore(0);
        certificado = new Semaphore(1);
        desayuno = new Semaphore(1);

    }

    public void llamarAlCentro() {

        llamada.release();
        System.out.println(Thread.currentThread().getName() + " llama al Centro de Hemoterapia");
    }

    public void atiendeLLamado() {
        try {
            mutex.acquire();
            llamada.acquire();
            System.out.println(Thread.currentThread().getName() + " recepciona al donante");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void finalizaLlamada(){
        mutex.release();
        System.out.println(Thread.currentThread().getName() + " Termina de recepcionar al donante");
    }

    public void recibeTurnoParaControlClinico(){
        try {
            turnoParaControl.acquire();
            System.out.println(Thread.currentThread().getName() + " adquiere un turno");
            controlMedico.release();
        } catch (Exception e) {
            //TODO: handle exception
        }

    }

    public void atiendeDonanteParaControl() {
        try {
            controlMedico.acquire();
            System.out.println(Thread.currentThread().getName() + " comieza la revision ");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void finalizaControl() {
        System.out.println("Se libera al donante");
        turnoParaControl.release();
    }

    public void recibeTurnoParaExtraccion(){
        try {
            turnoParaExtraccion.acquire();
            System.out.println(Thread.currentThread().getName() + " Adquiere un turno");
            extraccion.release();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void atiendeDonanteParaExtraccion(){
        try {
            extraccion.acquire();
            System.out.println(Thread.currentThread().getName() + " comieza la extraccion ");
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void finalizaExtraccion(){
        System.out.println("Se libera al donante");
        System.out.println("Se le entrega un certificado de donacion al donante");
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
