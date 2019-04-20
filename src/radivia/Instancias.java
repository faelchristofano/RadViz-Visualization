/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radviz;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public final class Instancias {
    
    public Color[] instanciasEmCores;
   
    private List<Double> angulosRadianos = new ArrayList<Double>();
    public ArrayList<double[]> vetorInstancias = new ArrayList<double[]>();
    public double[] ArrayPosAtrX;
    public double[] ArrayPosAtrY;
    
    public Classes classes;
    
     
     
    public Instancias(AbrirArquivo matriz, Atributos atributos){
     
        //TRANSFORMA OS ANGULOS EM RADIADOS
        AngulosEmRadianos(atributos);
        instanciasEmCores = new Color[matriz.matriz.size()];
        //List<Color> instanciasEmCores = new ArrayList<Color>();
        RetornarCosESenDoAngulo(atributos.NroAtributos);
        
        classes = new Classes(matriz);
        boolean achou;
          
        
        
        
       //ARMAZENA AS INSTANCIAS EM UM OUTRO ARRAY SEM OS ATRIBUTOS E SEM A COLUNA DAS CLASSES
       //iniciei i de 1 pão não caputurar o cabeçalho
        for (int i = 1; i < matriz.matriz.size(); i++) {
             double[] instancias = new double[atributos.NroAtributos];          
             achou = false;
            for (int j = 0; j < matriz.matriz.get(i).length ; j++) {
                //if(j!=matriz.matriz.get(i).length-1){
                if(j!=classes.posicaoClasse){
                    if(!achou)
                        instancias[j] = Double.parseDouble(matriz.matriz.get(i)[j]);   
                    else
                        instancias[j-1] = Double.parseDouble(matriz.matriz.get(i)[j]);   
                } else{
                    //AS CLASSES SÃO DIVIDIDAS CORES
                    instanciasEmCores[i-1] = InstanciasEmCores(matriz.matriz.get(i)[j]);
                    achou = true;
                    //instanciasEmCores[i-1] = classes.cores.get(i);
                 }
 
            }
            //ADICIONADO AO VETOR DE INSTANCIAS 
            vetorInstancias.add(vetorInstancias.size(), instancias);
           
           
        }
    
    }
    
    //SEPARA AS INSTANCIAS POR CORES
    //0 metodo recebe o nome de uma Instancia e procura ela
    //nas classes, se encotrou retorna a cor da classe procurada
   public Color InstanciasEmCores(String texto){
       int i;
       for (i = 0; i < classes.classes.size(); i++) {
           if(classes.classes.get(i).equals(texto)){
               break;
           }   
       }
       return classes.cores.get(i);
    }
    //TRANSFORMA ANGULOS EM RADIANOS
    public void AngulosEmRadianos(Atributos atributos){
       for (int i = 0; i < atributos.NroAtributos; i++) {
            angulosRadianos.add(i,Math.toRadians(atributos.anguloEntreAtributos.get(i)));      
        } 
    }
    //CALCULA O SENO E O COSSETO DE CADA INSTANCIA
    public void RetornarCosESenDoAngulo(int Qtdeatributos){
        ArrayPosAtrX = new double[Qtdeatributos];
        ArrayPosAtrY= new double[Qtdeatributos];
        for (int i = 0; i < ArrayPosAtrX.length; i++) {
            ArrayPosAtrX[i] = Math.cos(angulosRadianos.get(i).doubleValue());
            ArrayPosAtrY[i] = Math.sin(angulosRadianos.get(i).doubleValue());   
        }
        
    }
       
}
