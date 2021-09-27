package ProgrmacionConcurrente.PooJava.Empresa;
import ProgrmacionConcurrente.Lineales.Dinamicas.*;

public class Empresa {
    
    private Lista colEmpleados;
    protected Lista infoTitulos; 

    public Empresa(){
        this.colEmpleados = new Lista();
        this.infoTitulos = new Lista();
    }

    public Lista getColEmpleados(){
        return this.colEmpleados;
    }

    public Lista getinfoTitulos(){
        return this.infoTitulos;
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

    public Lista ordenarPorLegajo(){
        Lista colEmpleadosOrdenados = new Lista();
        if(!colEmpleados.esVacia()){
            Empleado aux,aux2;
            colEmpleadosOrdenados.insertar(colEmpleados.recuperar(1), colEmpleadosOrdenados.longitud()+1);
            int i=2, j=1;
            while(i<= colEmpleados.longitud()){
                aux = (Empleado)colEmpleados.recuperar(i);
                j=1;
                while(j<=colEmpleadosOrdenados.longitud()){
                    aux2 = (Empleado)colEmpleadosOrdenados.recuperar(j);
                    if(aux.getLegajo()>aux2.getLegajo()){
                        j++;
                    }else{
                        colEmpleadosOrdenados.insertar(aux, j);
                    }
                }
                i++;
            }            
        }
        return colEmpleadosOrdenados;
    }

    public Lista cobroMensualEmpleado(){
        Lista colCobros = new Lista();
        if(!colEmpleados.esVacia()){
            int i;
            Empleado aux;
            for(i=1;i<colEmpleados.longitud()+1; i++){
                aux = (Empleado)colEmpleados.recuperar(i);
                colCobros.insertar(aux, colCobros.longitud()+1);
                colCobros.insertar(aux.calcularSalario(infoTitulos), colCobros.longitud()+1);
            }
        }
        return colCobros;
    }
}
