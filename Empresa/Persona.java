package ProgrmacionConcurrente.Empresa;

import java.util.*;

public abstract class Persona {
    
    private String nombre;
    private int dni;
    private String direccion;
    private Date fchaNacimiento;
    private String sexo; 

    protected Persona(String nombre, int dni, String direccion, Date fchaNacimiento, String sexo){
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = direccion;
        this.sexo = sexo;
        this.fchaNacimiento = fchaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFchaNacimiento() {
        return fchaNacimiento;
    }

    public void setFchaNacimiento(Date fchaNacimiento) {
        this.fchaNacimiento = fchaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String mostrarDatos(){
        return "Nombre: "+this.nombre+" DNI: "+ this.dni+" Direccion: "+direccion+" Sexo: "+sexo;
    }
}
