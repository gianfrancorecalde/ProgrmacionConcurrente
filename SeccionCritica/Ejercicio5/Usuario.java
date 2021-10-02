package ProgrmacionConcurrente.SeccionCritica.Ejercicio5;

public class Usuario extends Thread{
    
    private char tipoImpresora;
    private CentroDeImpresion cI;

    public Usuario(char tipo, String contenido, CentroDeImpresion centroImpre){
        tipoImpresora = tipo;
        cI = centroImpre;
    } 

    @Override
    public void run() {
        boolean exito = false;
        int i = 0;
        while(exito){
            cI.intenta();
            switch (tipoImpresora) {
                case 'A':
                    exito = cI.intentaTomarA(i);
                    if(exito){
                        try {
                            System.out.println("Imprimiendo en "+tipoImpresora);
                            Thread.sleep(1000);
                            cI.liberarB(i);
                        } catch (Exception e) {
                            //TODO: handle exception
                        }
                    }
                    i++;
                break;
                case 'B':
                    exito = cI.intentaTomarB(i);
                    if(exito){
                        try {
                            System.out.println("Imprimiendo en "+tipoImpresora);
                            Thread.sleep(1000);
                            cI.liberarB(i);
                        } catch (Exception e) {
                            //TODO: handle exception
                        }
                    }
                    i++;
                break;
                default:
                    break;
            }
            
        }
    }

    
}
