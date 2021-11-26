package ProgrmacionConcurrente.Parcial2_Recalde_FAI2757.Ejercicio1;

public class Pedido {
    
    int tipo;
    // tipo 1 -> pizza Napolitana 
    // tipo 2 -> pizza vegetarina
    String nombreCliente;

    public Pedido(int tipo, String nombre){
        this.tipo = tipo;
        nombreCliente = nombre;
    }

    public int getTipo() {
        return tipo;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    
}
