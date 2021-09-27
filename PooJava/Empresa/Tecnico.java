package ProgrmacionConcurrente.PooJava.Empresa;
import java.util.*;
import ProgrmacionConcurrente.Lineales.Dinamicas.*;
public class Tecnico extends Empleado{
    
    private String titulo;
    private int anioRecibido;
    private int adPorTitulo;

    public Tecnico(int adPorTitulo,int cantHoras, double salarioBase, String nombre, int dni, String direccion, Date fchaNacimiento, String sexo, int legajo, int antiguedad, String titulo, int anioRecibido){
        super( cantHoras,salarioBase, nombre, dni, direccion, fchaNacimiento, sexo, legajo, antiguedad);
        this.titulo = titulo;
        this.anioRecibido = anioRecibido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAdPorTitulo() {
        return adPorTitulo;
    }

    public void setAdPorTitulo(int adPorTitulo) {
        this.adPorTitulo = adPorTitulo;
    }

    public int getAnioRecibido() {
        return anioRecibido;
    }

    public void setAnioRecibido(int anioRecibido) {
        this.anioRecibido = anioRecibido;
    } 

    public String mostrarDatos(){
        return super.mostrarDatos()+" Titulo: "+titulo+" Adicional por Titulo: "+adPorTitulo+" Añio recibido: "+anioRecibido;
    }

    public double calcularSalario(Lista infoTitulos){
        double salarioEmpleado = super.calcularSalario(infoTitulos);
        double adicional = obtenerAdPorTitulo(infoTitulos);
        salarioEmpleado += adicional;
        if(cantHoras > 100){
            if(cantHoras <= 160){
                salarioEmpleado += 50*(cantHoras-100);
            }else{
                salarioEmpleado += 50*60;
            }
        }

        return salarioEmpleado;
    }

    private double obtenerAdPorTitulo(Lista infoLista){
        double adicional = 0;
        if(!infoLista.esVacia()){
            boolean encontrado = false;
            int i =1;
            String aux;
            while(i<infoLista.longitud() && !encontrado){
                aux = (String)infoLista.recuperar(i);
                if(aux.equals(titulo)){
                    encontrado = true;
                    i++;
                    adicional += (double)infoLista.recuperar(i);
                }else{
                    i+=2;
                }
            }
        }
        return adicional;
    }

}
