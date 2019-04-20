
package radviz;

import java.util.ArrayList;
import java.util.List;

public final class RadViz {
   
   
    int posicaoInicialX, posicaoInicialY, diametro,colClasses;
    double raio;
    AbrirArquivo matriz;
    public Atributos atributos; 
    public Instancias instancias;
    public double[][] posicaoAnguloCircuferencia = new double[200][2];
    public List<Double[]> posicaoInstanciaCircuferencia = new ArrayList<Double[]>();  
    public ArrayList<String[]> classesAgrupadas = new ArrayList<String[]>();
    
    public RadViz(int posicaoInicialX, int posicaoInicialY, int diametro, String caminho){
        matriz = new AbrirArquivo(caminho);
        atributos = new Atributos(matriz);
        instancias = new Instancias(matriz, atributos);
        this.posicaoInicialX = posicaoInicialX;
        this.posicaoInicialY = posicaoInicialY;
        this.diametro = diametro;
        PosicionarAtributoNaCircuferencia();
        PosicionarInstanciasNoInteriorCircuferencia();
        
    }   
    //calcula o raio da circuferencia de acordo com as
    //informações recebeidas
    public double CalcularRaio(double xa, double xb){
        
        double r = (xb - xa)/2;
        return r;
        
    }
   //posiciona os atributos em torno da Circuferencia
   public void PosicionarAtributoNaCircuferencia(){
     
        for (int i = 0; i < atributos.NroAtributos; i++) {      
            //captura o valor do algulo e transforma em posição X na circuferencia
            double cos = Math.cos(Math.toRadians(atributos.anguloEntreAtributos.get(i)));
            //captura o valor do algulo e transforma em posição Y na circuferencia
            double sen = Math.sin(Math.toRadians(atributos.anguloEntreAtributos.get(i)));
            //identifica o centro da circuferencia
            double centroX = diametro/2;
            double centroY = diametro/2;
            double x;
            double y;

            x = centroX + (cos * CalcularRaio(100, diametro-75));
            y = centroY - (sen * CalcularRaio(100, diametro-75)) ;
          
            double[] ponto = new double[2];
            ponto[0] = x;
            ponto[1] = y;
          
            posicaoAnguloCircuferencia[i] = ponto;

        }
    }
  
    public void PosicionarInstanciasNoInteriorCircuferencia(){
        //aramazena os valores se seno e cosseno dos atributos na circuferencia
        double cos[] = instancias.ArrayPosAtrX;
        double sen[] = instancias.ArrayPosAtrY;
        //pega o raio
        raio = CalcularRaio(120, diametro-105);
        
        for (int i = 0; i < instancias.vetorInstancias.size(); i++) {                
           double somaInstancias = 0;
           
           double[] posX = new double[cos.length];
           double[] posY = new double[sen.length];
            for (int j = 0; j < cos.length; j++) {
                //SOMA O VALOR TOTAL DE UMA LINHA DA INSTNACIA
                somaInstancias += instancias.vetorInstancias.get(i)[j];   
                //MULTIPLICA O RAIO DO CIRCULO PELOS COSSENOS E SENOS
                posX[j] = raio * cos[j];
                posY[j] = raio * sen[j];
                
            }
            
            double MultiplicaX = 0;
            double MultiplicaY = 0;
            for (int j = 0; j < posX.length; j++) {
                //MULTIPLICAS OS COSSENOS E OS SENOS JA PROPOSRICIONAL AO RAIO
                //COM O VALOR DE CADA LINHA DE INSTANCIAS
                 MultiplicaX += posX[j] * instancias.vetorInstancias.get(i)[j];
                 MultiplicaY += posY[j] * instancias.vetorInstancias.get(i)[j];
        
            }
            //DIVIDE A O CALCULO DO VALOR DAS INSTANCIAS * OS COSSENOS E SENOS * RAIO
            //PELA A SOMA DAS INSTNACIAS DE UMA LINHA
            double x = MultiplicaX/somaInstancias;
            double y = MultiplicaY/somaInstancias;
            
           
            double centroX = diametro/2;
            double centroY = diametro/2;
            //A PARTIR DO CENTRO DO CIRCULO, SÃO DEIFINDAS AS POSIÇÕS QUE DEVEM SER 
            //PLOTADAS NO CIRCULO
            Double[] ponto = new Double[2];
            ponto[0] = centroX +  x;
            ponto[1] = centroY - y;
            
            //TODOS OS VALORES SÇAO APRMAZENADOS NO ARRAY
            posicaoInstanciaCircuferencia.add(i, ponto );

    }
 }

}     