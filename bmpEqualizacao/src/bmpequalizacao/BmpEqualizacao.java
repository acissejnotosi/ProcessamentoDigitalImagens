/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bmpequalizacao;
//package bmpqualizacao.bancoImagens;

import com.idrsolutions.image.bmp.BmpDecoder;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.round;
import java.lang.reflect.Array;
import java.util.Arrays;
import javax.imageio.ImageIO;


/**
 * Equalização de imagem do banco de imagens
 * @author Jessica & Guilherme
 */
public class BmpEqualizacao extends com.idrsolutions.image.JDeliImage {

    /**
     * @param args the command line arguments
     */
    
     
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
   
        BmpDecoder decoder = new BmpDecoder();
        File rios = new File("C:\\Users\\Jessica\\Documents\\NetBeansProjects\\Distribuidos-WebService-RestFul\\Distribuidos-WebService-RestFul2\\bmpEqualizacao\\src\\bmpequalizacao\\bancoImagens\\areaverde.bmp");
        BufferedImage image = decoder.read(rios);
        
        //Equalização da imagem:
        
        /*Encontrar quantidade de pixels para cada nível de cinza*/
          Integer[] QPPorNC = new Integer[256]; //quantidade de píxels por nível d cinza
          Arrays.fill(QPPorNC, 0);//QPPorNC[] = 0; //zerando todas as posições
          int i = 0;
          int j = 0;
         //percorrendo imagem em busca da quantidade de pixeis por nível de cinza
         
          System.out.println("Quantidade de pixels por nível de cinza sendo gerada:");
          for(j = 0 ; j < image.getHeight() ; j++){
              for(i = 0; i < image.getWidth() ; i++){
                int rgb =  image.getRGB(i, j);  //retorna 32 bits para o canal rgb mais alpha
                int alpha = (rgb >> 24) & 255;
                int red =   (rgb >> 16) & 255;
                int green = (rgb >>  8) & 255;
                int blue =  (rgb      ) & 255;
                int rgbdiv3 = (red+green+blue)/3; //valor do nível de cinza equivalente mesmo que a imagem fosse colorida
                QPPorNC[rgbdiv3] += 1;
                
             //  System.out.println("Nível de cinza  : " + rgbdiv3 +", quantidade: " + QPPorNC[rgbdiv3] );
              }
          }
    
        /*Fazer a probabilidade de um nível de cinza ocorrer na imagem*/
        
        float pixelTotal = image.getHeight()*image.getWidth();
        
        Float[] prob = new Float[256]; //quantidade de píxels por nível d cinza
         Arrays.fill(prob,0,255, 0f); //, j);prob[256] = Float.valueOf(); //zerando todas as posições
        System.out.println("Probabilidade sendo gerada:");
        for(i=0 ; i<256 ; i++)
        {
            prob[i] = QPPorNC[i]/pixelTotal;
          //  System.out.println("Nível de cinza  : " + i +", probabilidade: " + prob[i]);
        }
        
        /*Fazer a função de transformação acumulada da imagem*/
        
        Float[] probA =  new Float[256];
        Arrays.fill(probA,0,255, 0f);//probA[256] = Float.valueOf(0);
       
        float temp1= 0f;
        System.out.println("Probabilidade acumulada sendo gerada:");
        for(i = 0 ; i<256 ; i++)
        {
            for(j= 0 ; j <= i ; j++)
            {
                if(j == 256)
                    break;
                temp1 += prob[j];
                probA[i] =  temp1; 
               
            }
            // System.out.println("Nível de cinza  : " + i +", probabilidade Acumulada: " + probA[i]);
        
            temp1 = 0f;
        }
        
        /*Equalizando níveis de cinza conforme os 256 níveis possíveis*/
        
        for(i = 0 ; i< 256 ; i++)
        {
            probA[i] = probA[i] * 255;
        }
        
        Integer[] histEqu =  new Integer[256];
        Arrays.fill(histEqu, 0);//histEqu[256] = 0;
       
        System.out.println("Arrendondamento sendo gerado:"); 
        for(i = 0 ; i< 256 ; i++)
        {
            histEqu[i] = round(probA[i]);
            System.out.println("Nível de cinza  : " + i +", Novo Nível de Cinza: " + histEqu[i]);
        }
        
        /*Salva novos níveis de cinza*/
        
        for(j = 0 ; j < image.getHeight() ; j++){
              for(i = 0; i < image.getWidth() ; i++){

                int rgb = image.getRGB(i, j);
                int alpha = (rgb >> 24) & 255;
                int red =   (rgb >> 16) & 255;
                int green = (rgb >>  8) & 255;
                int blue =  (rgb      ) & 255;                  
                int rgbdiv3 = (red+green+blue)/3; //valor do nível de cinza equivalente mesmo que a imagem fosse colorida
                Color temp  = new Color(histEqu[rgbdiv3],histEqu[rgbdiv3],histEqu[rgbdiv3], 1 );
                image.setRGB(i, j, temp.getRGB());
                
                
              }
          }
    
       File bmpFile = new File("ImgEqualizado.bmp");
       ImageIO.write(image,"bmp",bmpFile);
    }
    


}


