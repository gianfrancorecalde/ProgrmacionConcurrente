package ProgrmacionConcurrente.Synchronized.Ejercicio6;

public class Vehiculo{
    
    private String patente;
    private String modelo;
    private String marca;
    private int kmFaltanteParaService;

    public Vehiculo(String patente, String modelo, String marca, int km){
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        kmFaltanteParaService = km;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getKmFaltanteParaService() {
        return kmFaltanteParaService;
    }

    public void setKmFaltanteParaService(int kmFaltanteParaService) {
        this.kmFaltanteParaService = kmFaltanteParaService;
    }


}
