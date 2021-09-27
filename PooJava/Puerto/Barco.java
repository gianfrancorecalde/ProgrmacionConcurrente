package ProgrmacionConcurrente.PooJava.Puerto;

import java.sql.Date;
import java.util.Calendar;

public class Barco {
    
    private String matricula;
    private int eslora;
    private int anio;

    public Barco(String matricula, int eslora, int anio){
        this.matricula = matricula;
        this.eslora = eslora;
        this.anio = anio;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    protected int getEslora() {
        return eslora;
    }

    public void setEslora(int eslora) {
        this.eslora = eslora;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public double obtenerModulo(){
        return this.eslora*10;
    }

    public double calcularValor(int cantDias, int valorFijo){
        double costo = (cantDias*this.obtenerModulo())+valorFijo;
        return costo;
    }
    
}
