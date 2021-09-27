package ProgrmacionConcurrente.PooJava.Puerto;

public  class ExcepcioneDias extends Exception {
    
    public ExcepcioneDias(String message){
        super(message);
    }

    public boolean limiteDeDias(Puerto p, int cantDias) throws Exception{
        int limiteDias = p.getLimiteDias();
        if(cantDias < limiteDias){
            return true;
        }else{
            throw new Exception();
        }
    }
}
