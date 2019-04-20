

package radviz;

import java.util.ArrayList;
import java.util.List;


public final class Atributos {
    private double anguloAtual;
    public int NroAtributos;
    private AbrirArquivo matriz;
    public List<Double> anguloEntreAtributos = new ArrayList<Double>();


    public Atributos(AbrirArquivo matriz) {
     
        this.matriz = matriz;
        //coloquei -1 para não incluir as Classes como atributo
        NroAtributos = matriz.matriz.get(0).length-1;
        AnguloEntreAtributos();
    }
 
    //RETORNA OS NOMES DOS ATRIBUTOS QUE SERVIRÃO DE ROTULOS
    //AO REDOR DA CIRCUFRENCIA
    public String getRotulos(int posicao){
        String texto = matriz.matriz.get(0)[posicao];
        return texto;      
    }
    
   //DESCOBRE O ANGULO PARA CADA ATRIBUTO NA CIRCUFERENCIA DE ACORDO COM QUANTIDADE DE ATRIBUTO
   public void AnguloEntreAtributos(){   
      anguloAtual = 360 / NroAtributos;
      int i = 1;
      anguloEntreAtributos.add(anguloEntreAtributos.size(),0.0);
        while(i < NroAtributos) 
        { 
            anguloEntreAtributos.add(i, anguloEntreAtributos.get(i-1) + anguloAtual);
            i++;
        }    
    }
    
}
