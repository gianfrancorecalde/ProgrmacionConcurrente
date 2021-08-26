package ProgrmacionConcurrente.Puerto;

import java.util.*;


public class Alquiler {
    
    private int cliente;
    private Barco barco;
    private int amarre;
    private Date fchIni;
    private Date fchFinal;
    private double valorAlquiler;

    public Alquiler(int  cliente, Barco barco, int amarre, Date fchIni, Date fchFin, double valorAlquiler){
        this.cliente = cliente;
        this.barco = barco;
        this.amarre = amarre;
        this.fchFinal = fchFin;
        this.fchIni = fchIni;
        this.valorAlquiler = valorAlquiler;
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

    public Date getFchIni() {
        return this.fchIni;
    }

    public void setFchIni(Date otraFchaIni) {
        this.fchIni = otraFchaIni;
    }

    public Date getFchFinal() {
        return this.fchFinal;
    }

    public void setFchFinal(Date fchFinal) {
        this.fchFinal = fchFinal;
    }



}
