package ProgrmacionConcurrente.Synchronized.Ejercicio3;

public class sumaMatriz {

    private int sum;

    /* USANDO METODO SINCRONIZADO */

    synchronized int sumMatriz(int nums[]) {
            sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                System.out.println(sum);
                System.out.println("Total acumulado de " + Thread.currentThread().getName() + " es " + sum);
                try {
                    Thread.sleep(10);// permitir el cambio de tarea
                } catch (InterruptedException exc) {
                    System.out.println("Hilo interrumpido");
                }
            }    
            return sum;
    } 

    /* USANDO BLOQUE PARA SINCRONIZAR */

    /* int sumMatriz(int nums[]) {
        synchronized ((Integer)sum) {
            //PORQUE NO SE SINCRONIZA BIEN CON BLOQUE QUE SINCRONICE sum?
            sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                System.out.println(sum);
                System.out.println("Total acumulado de " + Thread.currentThread().getName() + " es " + sum);
                try {
                    Thread.sleep(10);// permitir el cambio de tarea
                } catch (InterruptedException exc) {
                    System.out.println("Hilo interrumpido");
                }
            }    
            return sum;
        }
    } */
}
