
package radviz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author rafael
 */
public class AbrirArquivo{
    
    ArrayList<String[]> matriz = new ArrayList<String[]>();

    public AbrirArquivo(String caminho){
        File arquivoCSV = new File(caminho);
        
        try{
            //armazeno o arquivo na variavel leitor
            Scanner leitor = new Scanner(arquivoCSV);
            //i é um contador de linhas
            int i = 0;
      
           //faço um looping até o final do arquivo csv e vou adicionando linhas no array e atraves do split adiciono as 
           //colunas do array. O split divide a linha aonde ele encontrar ","
           while(leitor.hasNext()){
              matriz.add(i, leitor.nextLine().split(","));
               i++;
           }         
       } catch(FileNotFoundException e){
            
        }
        
         
    }

    
}
