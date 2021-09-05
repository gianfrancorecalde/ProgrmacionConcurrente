package ProgrmacionConcurrente.Concurrencia;

public class ThreadEjemplo2 implements Runnable{

    private String str;

    public ThreadEjemplo2(String str){
        this.str = str;
    }

    public void run(){
        for(int i=0; i<10;i++){
            System.out.println(i+" "+ this.str);
            System.out.println("Termina thread "+this.str+"\n");
        }
    }
    
    public static void main(String[] args) {

        ThreadEjemplo2 a1 = new ThreadEjemplo2("Maria Jose");
        ThreadEjemplo2 a2 = new ThreadEjemplo2("Jose Maria");
        
        Thread t1 = new Thread(a1);
        Thread t2 = new Thread(a2);
        System.out.println("termina thread main");
    }
}
