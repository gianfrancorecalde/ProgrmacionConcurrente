package ProgrmacionConcurrente.Empresa;
import java.util.*;
import ProgrmacionConcurrente.Lineales.Dinamicas.*;
public class Administrativo extends Empleado {
    
    private String categoria;
    private int adPorCategoria;

    public Administrativo(int adPorCategoria,int cantHoras,double salarioBase, String nombre, int dni, String direccion, Date fchaNacimiento, String sexo, int legajo, int antiguedad, String categoria){
        super(cantHoras,salarioBase, nombre, dni, direccion, fchaNacimiento, sexo, legajo, antiguedad);
        this.categoria = categoria;
        this.adPorCategoria = adPorCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String mostrarDatos(){
        return super.mostrarDatos()+" Categoria: "+categoria+" Adicional por categoria: "+adPorCategoria;
    }

    public int getAdPorCategoria() {
        return adPorCategoria;
    }

    public void setAdPorCategoria(int adPorCategoria) {
        this.adPorCategoria = adPorCategoria;
    }

    public double calcularSalario(Lista infoTitulos){
        double salarioEmpleado = super.calcularSalario(infoTitulos);
        salarioEmpleado += adPorCategoria + (salarioBase*0.9);
        return salarioEmpleado;
    }
}
