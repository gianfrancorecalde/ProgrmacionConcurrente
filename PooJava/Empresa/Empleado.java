package ProgrmacionConcurrente.PooJava.Empresa;
import java.util.*;
import ProgrmacionConcurrente.Lineales.Dinamicas.*;
public  class Empleado extends Persona {
    
    private int legajo;
    private int porcentAntiguedad;
    protected double salarioBase;
    protected int cantHoras;

    public Empleado(int cantHoras ,double salarioBase, String nombre, int dni, String direccion, Date fchaNacimiento, String sexo, int legajo, int porcentAntiguedad){
        super(nombre, dni,  direccion,fchaNacimiento, sexo);
        this.legajo = legajo;
        this.porcentAntiguedad = porcentAntiguedad;
        this.salarioBase = salarioBase;
        this.cantHoras = cantHoras;
    }

    public int getLegajo() {
        return legajo;
    }

    public int getAntiguedad() {
        return porcentAntiguedad;
    }
    public int getCantHoras() {
        return cantHoras;
    }

    public void setAntiguedad(int porcentAntiguedad) {
        this.porcentAntiguedad = porcentAntiguedad;
    }

    public void setCantHoras(int cantHoras) {
        this.cantHoras = cantHoras;
    }

    public boolean verificarAntiguedad(){
        return this.porcentAntiguedad >10;
    }

    public String mostrarDatos(){
        return super.mostrarDatos()+" Legajo: "+legajo+" Porcentaje por Antiguedad: "+porcentAntiguedad+" Salario base: "+salarioBase+" Horas trabajadas: "+cantHoras;
    }

    public double getSalario(){
        return salarioBase;
    }

    public void setSalario(double newSalario){
        this.salarioBase = newSalario;
    }

    public double calcularSalario(Lista infoTitulos){
        double salarioEmpleado = salarioBase+porcentAntiguedad;
        if(cantHoras > 160){
            salarioEmpleado += (salarioBase*0.95)*(cantHoras-160);
        }
        return salarioEmpleado;
    }
}
