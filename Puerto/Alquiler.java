package ProgrmacionConcurrente.Puerto;

import java.util.*;


public class Alquiler {
    
    private int cliente;
    private Barco barco;
    private int amarre;
    private Calendar fchIni;
    private Calendar fchFinal;

    public Alquiler(int  cliente, Barco barco, int amarre, Calendar fchIni, Calendar fchFin){
        this.cliente = cliente;
        this.barco = barco;
        this.amarre = amarre;
        this.fchFinal = fchFin;
        this.fchIni = fchIni;
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

    public Calendar getFchIni() {
        return this.fchIni;
    }

    public void setFchIni(int diaIni, int mesIni, int anioIni) {
        this.fchIni.set(anioIni, mesIni, diaIni);
    }

    public Calendar getFchFinal() {
        return this.fchFinal;
    }

    public void setFchFinal(int diaFinal, int mesFinal, int anioFinal) {
        this.fchFinal.set(anioFinal, mesFinal, diaFinal);
    }



}
