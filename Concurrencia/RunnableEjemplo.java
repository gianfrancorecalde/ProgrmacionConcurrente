package ProgrmacionConcurrente.Concurrencia;

public class RunnableEjemplo implements Runnable{

    private String str;

    public RunnableEjemplo(String str){
        this.str = str;
    }

    public void run(){
        for(int i=0; i<10;i++){
            System.out.println(i+" "+ this.str);
            System.out.println("Termina thread "+this.str+"\n");
        }
    }
    
    public static void main(String[] args) {

        RunnableEjemplo a1 = new RunnableEjemplo("Maria Jose");
        RunnableEjemplo a2 = new RunnableEjemplo("Jose Maria");
        
        Thread t1 = new Thread(a1);
        Thread t2 = new Thread(a2);

        t1.start();
        t2.start();
        System.out.println("termina thread main");
    }
}
