package ProgrmacionConcurrente.Synchronized;

public class main {
    /* public static void main(String[] args) {
        VerificarCuenta vc = new VerificarCuenta();
        Thread Luis = new Thread(vc, "Luis");
        Thread Manuel = new Thread(vc, "Manuel");
        Luis.start();
        Manuel.start();

    } */

    public static void main(String[] args) {
        Vida v = new Vida(10);
        Thread orco = new Thread(new Daniar(v), "Orco");
        Thread curandero = new Thread(new Curar(v), "Curandero");
        orco.start();
        curandero.start();
    }
}
