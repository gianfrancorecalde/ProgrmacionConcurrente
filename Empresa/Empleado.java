package ProgrmacionConcurrente.Empresa;
import java.util.*;
public class Empleado extends Persona {
    
    private int legajo;
    private int antiguedad;

    public Empleado(String nombre, int dni, String direccion, Calendar fchaNacimiento, String sexo, int legajo, int antiguedad){
        super(nombre, dni,  direccion,fchaNacimiento, sexo);
        this.legajo = legajo;
        this.antiguedad = antiguedad;
    }

    public int getLegajo() {
        return legajo;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    public boolean verificarAntiguedad(){
        return this.antiguedad >10;
    }

    public String mostrarDatos(){
        return super.mostrarDatos()+" Legajo: "+legajo+" Antiguedad: "+antiguedad;
    }
}
