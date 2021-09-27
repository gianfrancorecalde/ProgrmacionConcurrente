package ProgrmacionConcurrente.Synchronized.Ejercicio5;

public class GestorTurno {

    private int valor;

    public GestorTurno() {
        valor = 0;
    }

    public synchronized int getValor() {
        return valor;
    }

    public synchronized int turno(int repeticion, int turno) {
        int i = 0;
        if (valor == turno) {
            for (int j = 0; j < repeticion; j++) {
                System.out.println(Thread.currentThread().getName());
            }
            valor = (valor + 1) % 3;
            i = 1;
        }
        return i;
    }
    // AABBBBBCCCCC
    // AACCCBBB
}
