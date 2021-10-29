package ProgrmacionConcurrente.Recalde_FAI2757;

public class Main {
    
    public static void main(String[] args) {
        
        PuestoBuceo puesto = new PuestoBuceo(3, 3);
        Thread empAnt = new Thread(new EmpleadoAntiparras(puesto), "Empleado Antiparra");
        Thread empSnork = new Thread(new EmpleadoSnorkel(puesto), "Empleado Snorkel");

        empAnt.start();
        empSnork.start();

        for (int i = 0; i < 3; i++) {
            new Thread(new Persona(puesto), "Cliente "+i).start();
        }
    }
}
