package ProgrmacionConcurrente.PooJava.Puerto;



public class Velero extends Barco {
    
    private int nroMastil;

    public Velero(int nroMastil,String matricula, int eslora, int anio){
        super(matricula, eslora, anio);
        this.nroMastil = nroMastil;
    }

    public int getNroMastil(){
        return this.nroMastil;
    }

    public void setNroMastil(int nroMastil){
        this.nroMastil = nroMastil;
    }

    public double obtenerModulo(){
        return super.obtenerModulo() + this.nroMastil;
    }
}
