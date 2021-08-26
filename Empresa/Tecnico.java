package ProgrmacionConcurrente.Empresa;
import java.util.*;
public class Tecnico extends Empleado{
    
    private String titulo;
    private int anioRecibido;

    public Tecnico(double salario, String nombre, int dni, String direccion, Date fchaNacimiento, String sexo, int legajo, int antiguedad, String titulo, int anioRecibido){
        super(salario, nombre, dni, direccion, fchaNacimiento, sexo, legajo, antiguedad);
        this.titulo = titulo;
        this.anioRecibido = anioRecibido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnioRecibido() {
        return anioRecibido;
    }

    public void setAnioRecibido(int anioRecibido) {
        this.anioRecibido = anioRecibido;
    } 

    public String mostrarDatos(){
        return super.mostrarDatos()+" Titulo: "+titulo+" Añio recibido: "+anioRecibido;
    }

}
