package ProgrmacionConcurrente.Synchronized;

public class main {
    /* public static void main(String[] args) {
        VerificarCuenta vc = new VerificarCuenta();
        Thread Luis = new Thread(vc, "Luis");
        Thread Manuel = new Thread(vc, "Manuel");
        Luis.start();
        Manuel.start();

    } */

    /* public static void main(String[] args) {
        Vida v = new Vida(10);
        Thread orco = new Thread(new Daniar(v), "Orco");
        Thread curandero = new Thread(new Curar(v), "Curandero");
        orco.start();
        curandero.start();
    } */

    
    
    /*      EJERCICIO 4              */


    public static void main(String[] args) {
        
        GestorActividad gestorAct = new GestorActividad();
        Thread hamster1 = new Thread(gestorAct, "hasmter1");
        Thread hamster2 = new Thread(gestorAct, "hasmter2");
        Thread hamster3 = new Thread(gestorAct, "hasmter3");

        hamster1.start();
        hamster2.start();
        hamster3.start();
        
    }

}
