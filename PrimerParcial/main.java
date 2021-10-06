package ProgrmacionConcurrente.PrimerParcial;

public class main {
    
    public static void main(String[] args) {
        CentroHemoterapia cH = new CentroHemoterapia();
        Thread d1 = new Thread(new Donante(cH), "Jorge");
        Thread d2 = new Thread(new Donante(cH), "Pablo");
        Thread d3 = new Thread(new Donante(cH), "Pedro");
        Thread m1 = new Thread(new MedicoClinico(cH), "Medico1");
        Thread e1 = new Thread(new Enfermero(cH), "Enfermero1");
        Thread r1 = new Thread(new Recepcionista(cH), "Recepcionista1");

        Thread [] arr = {d1,d2,m1,e1,r1};
        for (int i = 0; i < arr.length; i++) {
            arr[i].start();
        }
    }
}
