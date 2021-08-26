package ProgrmacionConcurrente.Empresa;
import java.util.*;
public abstract class Empleado extends Persona {
    
    private int legajo;
    private int antiguedad;
    private double salario;

    protected Empleado(double salario, String nombre, int dni, String direccion, Date fchaNacimiento, String sexo, int legajo, int antiguedad){
        super(nombre, dni,  direccion,fchaNacimiento, sexo);
        this.legajo = legajo;
        this.antiguedad = antiguedad;
        this.salario = salario;
        
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

    public double getSalario(){
        return salario;
    }

    public void setSalario(double newSalario){
        this.salario = newSalario;
    }
}
