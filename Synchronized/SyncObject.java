package ProgrmacionConcurrente.Synchronized;

public class SyncObject {
    public static void main(String[] args) {
        final DualSync ds = new DualSync();
        Thread hilo = new Thread() {
            public void run() {
                ds.f();
            }
        }; // del Thread
        hilo.start();
        ds.g();
    }
}
