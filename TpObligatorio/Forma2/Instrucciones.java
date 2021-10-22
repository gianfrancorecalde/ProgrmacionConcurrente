package ProgrmacionConcurrente.TpObligatorio.Forma2;

import java.util.concurrent.Semaphore;

public class Instrucciones {
    private int a;
    private int b;
    private int x;
    private int y;
    private int z;
    private int c;
    private int w;
    private Semaphore actS3; 
    private Semaphore actS4; 

    public Instrucciones(int x, int y, int z){
        actS3 = new Semaphore(0);
        actS4 = new Semaphore(0);
        this.x = x;
        this.y = y;
        this.z = z;
        
    }

    public void s1(){
        a = x+y;
        actS3.release();
    }
    public void s2(){
        b = z+1;
        actS3.release();
    }
    public void s3(){
        try {
            actS3.acquire(2);
        } catch (Exception e) {
            //TODO: handle exception
        }
        c = a-b;
        actS4.release();
    }
    public void s4(){
        try {
            actS4.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        w = c+1;
    }

    public int getW(){
        return w;
    }
}
