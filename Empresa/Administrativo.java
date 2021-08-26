package ProgrmacionConcurrente.Empresa;
import java.util.*;
public class Administrativo extends Empleado {
    
    private String categoria;

    public Administrativo(String nombre, int dni, String direccion, Calendar fchaNacimiento, String sexo, int legajo, int antiguedad, String categoria){
        super(nombre, dni, direccion, fchaNacimiento, sexo, legajo, antiguedad);
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String mostrarDatos(){
        return super.mostrarDatos()+" Categoria: "+categoria;
    }
}
