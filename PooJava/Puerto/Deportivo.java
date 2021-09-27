package ProgrmacionConcurrente.PooJava.Puerto;

public class Deportivo extends Barco {
    
    private double cv;

    public Deportivo(double cv,String matricula, int eslora, int anio){
        super(matricula, eslora, anio);
        this.cv = cv;
    }

    public double getCv() {
        return cv;
    }

    public void setCv(double cv) {
        this.cv = cv;
    }

    public double obtenerModulo(){
        return super.obtenerModulo() + this.cv;
    }
}
