package ProgrmacionConcurrente.Recalde_FAI2757;

public class Main {
    
    public static void main(String[] args) {
        
        PuestoBuceo puesto = new PuestoBuceo(3, 5);
        Thread empAnt = new Thread(new EmpleadoAntiparras(puesto), "Empleado Antiparra");
        Thread empSnork = new Thread(new EmpleadoSnorkel(puesto), "Empleado Snorkel");

        empAnt.start();
        empSnork.start();

        int tamanio = 5;
        Persona [] arrPers = new Persona[tamanio];
        Thread [] hP = new Thread[tamanio];

        for (int i = 0; i < tamanio; i++) {
            arrPers[i] = new Persona(puesto);
            hP[i] = new Thread(arrPers[i], "Cliente"+i);
            hP[i].start();
        }
    }
}
