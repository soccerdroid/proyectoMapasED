/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba1;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author pavilion
 */
public class Prueba1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
           /*PrintWriter writer = new PrintWriter("archivo.txt", "UTF-8");
            writer.println("The first line");
            writer.println("The second line");
            writer.close();*/
           
           SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
           Date fecha=formatter.parse("2/02/2012");
            System.out.println(formatter.format(fecha));
           File file= new File("archivo.txt");
           FileWriter fw= new FileWriter(file);
           BufferedWriter bw= new BufferedWriter(fw);
           
           bw.write("sdfsdfadsf");
           bw.close();
           
            String x= JOptionPane.showInputDialog("adfjhadskjf");
            System.out.println(x);
            if(x.equals(""))
                System.out.println("lol");
        }catch (Exception e){
            System.out.println("Error");  
        }
    }
}
    

