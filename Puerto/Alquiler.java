package ProgrmacionConcurrente.Puerto;

import java.util.*;


public class Alquiler {
    
    private int cliente;
    private Barco barco;
    private int amarre;
    private Calendar fchIni;
    private Calendar fchFinal;

    public Alquiler(int cliente, Barco barco, int amarre, int diaInicial, int diaFinal, int mesInicial, int mesFinal, int anioIni, int anioFin){
        this.cliente = cliente;
        this.barco = barco;
        this.amarre = amarre;
        this.fchFinal = new GregorianCalendar(anioFin, mesFinal, diaFinal);
        this.fchIni = new GregorianCalendar(anioIni, mesInicial, diaInicial);
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public Barco getBarco() {
        return barco;
    }

    public void setBarco(Barco barco) {
        this.barco = barco;
    }

    public int getAmarre() {
        return amarre;
    }

    public void setAmarre(int amarre) {
        this.amarre = amarre;
    }

    public Long getFchIni() {
        return fchIni.getTimeInMillis();
    }

    public void setFchIni(int diaIni, int mesIni, int anioIni) {
        this.fchIni.set(anioIni, mesIni, diaIni);
    }

    public Long getFchFinal() {
        return fchFinal.getTimeInMillis();
    }

    public void setFchFinal(int diaFinal, int mesFinal, int anioFinal) {
        this.fchFinal.set(anioFinal, mesFinal, diaFinal);
    }

    public double calcularValor(){
        long fin = this.fchFinal.getTimeInMillis();
        long ini = this.fchIni.getTimeInMillis();
        int dias = (int)Math.abs(fin-ini)/(1000*60*60*24);
        double costo = dias*this.barco.obtenerModulo();
        return costo;
    }


}
