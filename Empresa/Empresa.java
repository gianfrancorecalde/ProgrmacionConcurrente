package ProgrmacionConcurrente.Empresa;
import ProgrmacionConcurrente.Lineales.Dinamicas.*;

public class Empresa {
    
    private Lista colEmpleados;

    public Empresa(){
        this.colEmpleados = new Lista();
    }

    public Lista getColEmpleados(){
        return this.colEmpleados;
    }

    public Lista empleadosMayoresA(){
        Lista colEmpleadosMayores = new Lista();
        if(!this.colEmpleados.esVacia()){
            int i=1;
            while(i<=colEmpleados.longitud()){
                Empleado aux = (Empleado)colEmpleados.recuperar(i);
                if(aux.verificarAntiguedad()){
                    colEmpleadosMayores.insertar(aux, colEmpleadosMayores.longitud()+1);
                }
                i++;
            }
        }
        return colEmpleadosMayores;
    }
}
