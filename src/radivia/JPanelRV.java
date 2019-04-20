/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radviz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class JPanelRV extends javax.swing.JPanel {
   AbrirArquivo matriz;
   RadViz rv;
   int diametro;
   Classes classe;
  
    public JPanelRV(RadViz rv, int diametro) {
           initComponents();
            this.rv = rv;
            this.diametro = diametro;
  
           
    }
    //painel responsabel por exibir as classes das instancia
   public void PainelRotulos(){
       rotulo.setVisible(true);
       rotulo.setLocation(diametro, 20);
       rotulo.setSize(250, rv.instancias.classes.classes.size() * 25 + 25);
       int n = 10;
       //definição do titulo Classes
       JLabel titulo = new JLabel("Classes");
       titulo.setBounds(10, n, 150, 20);
       rotulo.add(titulo);
       n = n + 20;
       //começo a plottar as classes
       for (int i = 0; i < rv.instancias.classes.classes.size(); i++) {
           JLabel label = new JLabel(rv.instancias.classes.classes.get(i));   
           rotulo.add(label);
           label.setBounds(20, n , 150, 20);
           //esse panel ficará pintado de acordo com a cor da classe
           JPanel cor = new JPanel();
           rotulo.add(cor);
           cor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
           cor.setBackground(rv.instancias.InstanciasEmCores(rv.instancias.classes.classes.get(i)));
           cor.setSize(10, 10);
           cor.setLocation(5, n+5);
          
           n = n + 20;           
       }
   }
    //PLOTTA OS ATRIBUTOS NO CIRCULO
   public void PlottarAtributos(Graphics2D g2){
        JLabel texto;
        double[] posicao = new double[2];
        //a variavel achou é reponsavel por encontrar a coluna das classes 
        //e pular essa coluna para não ser impressa
        boolean achou = false;
        for (int i = 0; i <= rv.atributos.NroAtributos; i++) {
            
            if(i!=rv.instancias.classes.posicaoClasse){
                if(!achou){
                    posicao = rv.posicaoAnguloCircuferencia[i];
                   texto = new JLabel(rv.atributos.getRotulos(i), SwingConstants.LEFT);

                }
                else {
                    posicao = rv.posicaoAnguloCircuferencia[i-1];
                    texto = new JLabel(rv.atributos.getRotulos(i), SwingConstants.LEFT);

                }
                texto.setFont(new Font("Tahoma", 1, 14));
                  //POSIÇÃO PARA O ROTULO SER PLOTTADO NA SEGUNDA METADE DO CIRCULO 
                  if(posicao[0]>diametro/2){
                      texto.setBounds((int)posicao[0], (int)posicao[1], 90, 14);
                 //POSIÇÃO PARA O ROTULO SER PLOTTADO NA PRIMEIRA METADE DO CIRCULO 
                  } else{
                      texto.setBounds((int)posicao[0]-70, (int)posicao[1], 90, 14);
                  }
                   this.add(texto);
            } else 
                achou = true;
            
        }
        
    }
    
    //DESENHA AS INSTANCIAS NO INTERIOR DO CIRCULO
    public void PlottarInstancias(Graphics2D g2){
        for (int i = 0; i < rv.posicaoInstanciaCircuferencia.size(); i++) {
             //double[] posicao = rv.PlottarInstanciasNoInteriorCircuferencia();
             double x = rv.posicaoInstanciaCircuferencia.get(i)[0].doubleValue();
             double y = rv.posicaoInstanciaCircuferencia.get(i)[1].doubleValue();
             g2.setColor(rv.instancias.instanciasEmCores[i]);
             int tamanho_instancia = (int)(diametro*0.012);
             g2.fillRect((int)x, (int)y,tamanho_instancia,tamanho_instancia);
             g2.setColor(Color.black);
             g2.drawRect( (int)x, (int)y, tamanho_instancia, tamanho_instancia );
        }
        
    }
    
        
    @Override
    protected void paintComponent(Graphics g) {
                  

        super.paintComponent(g); 

        Graphics2D g2 = (Graphics2D) g;//cast
        
        //define a cor do circulo
        g2.setColor(Color.WHITE);
         //desenha o circulo
        g2.fillOval(100, 100, diametro-200, diametro-200);
             
        PlottarAtributos(g2);

        PlottarInstancias(g2);   

        PainelRotulos();
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rotulo = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(700, 700));

        rotulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout rotuloLayout = new javax.swing.GroupLayout(rotulo);
        rotulo.setLayout(rotuloLayout);
        rotuloLayout.setHorizontalGroup(
            rotuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        rotuloLayout.setVerticalGroup(
            rotuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(536, Short.MAX_VALUE)
                .addComponent(rotulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(rotulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(568, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel rotulo;
    // End of variables declaration//GEN-END:variables
}
