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
public final class Classes {
    
    ArrayList<String> classes = new ArrayList<String>();
    AbrirArquivo matriz;
    GeradorCoresAleatorias gerarCor = new GeradorCoresAleatorias();
    public int posicaoClasse;
    public List<Color> cores;
    
    public Classes(AbrirArquivo matriz){
        this.matriz = matriz;
        EncontrarColunaClasses();
        AgruparClasses();
        DefinirCores();
        
    }
    //procura pela coluna reponsavel pela a definicção das classes
    public void EncontrarColunaClasses(){
        int i = 0;
        int[] ret = new int[2];
        while(!matriz.matriz.get(0)[i].equals("Classes")) {
            i++;
        }
        //se achou
        if(matriz.matriz.get(0)[i].equals("Classes"))
           posicaoClasse = i;
         //senão achou    
    }
    
    //apos indetifica a coluna das classes, esse metodo fica responsavel por 
    //Agrupar as classes e definir a cor para cada classe de arcodo com a posição 
    //da classe agrupada no vetor classes
    public void AgruparClasses(){        
        boolean achou;
        //1 primeira posição é caputura e inicia a comparação
        classes.add(classes.size(),matriz.matriz.get(1)[posicaoClasse]);
        //inicia i de 2 pois começa na segunda linha com as instancias
        for (int i = 2; i < matriz.matriz.size(); i++) {
            achou = false;
            for (int j = 0; j < classes.size(); j++) {               
                if(matriz.matriz.get(i)[posicaoClasse].equals(classes.get(j))){
                    achou = true;
                    break;
                }
            }
            if(!achou){
                 classes.add(classes.size(),matriz.matriz.get(i)[posicaoClasse]);
            }
        }
        
        
    }
    
    //gera as cores que serão usadas de acordo com a quantidade de 
    //classes que foram agrupadas
     public void DefinirCores(){
         cores =  gerarCor.gerarCores(classes.size());               
    }
            
}     
        
