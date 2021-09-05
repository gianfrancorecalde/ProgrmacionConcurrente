package ProgrmacionConcurrente.Concurrencia;

public class Clienta {
    private String nombre;
    private int [] carroCompra;

    public Clienta(String name, int [] arr){
        nombre = name;
        carroCompra = arr;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int[] getCarroCompra() {
        return carroCompra;
    }

    public void setCarroCompra(int[] carroCompra) {
        this.carroCompra = carroCompra;
    }

    
}
