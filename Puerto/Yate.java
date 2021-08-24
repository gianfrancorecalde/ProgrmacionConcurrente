package ProgrmacionConcurrente.Puerto;


public class Yate extends Barco{
    
    private int nroCamarotes;
    private double cv;

    public Yate(int nroCamarotes, String matricula, int eslora, int anio, double cv){
        super(matricula, eslora, anio);
        this.cv = cv;
        this.nroCamarotes = nroCamarotes;
    }

    public int getNroCamarotes() {
        return nroCamarotes;
    }

    public void setNroCamarotes(int nroCamarotes) {
        this.nroCamarotes = nroCamarotes;
    }

    public double getCv() {
        return cv;
    }

    public void setCv(double cv) {
        this.cv = cv;
    }

    public double obtenerModulo(){
        return super.obtenerModulo() + this.cv + this.nroCamarotes;
    }
}
