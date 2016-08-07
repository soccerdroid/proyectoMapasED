/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.main;

import espol.edu.ec.TDAs.*;

/**
 *
 * @author Administrador
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        GrafoMA grafo=new GrafoMA(10);
        String l1="l";
        String l2="c";
       grafo.agregarVertice(l1);
       grafo.agregarVertice(l2);
       
       
        System.out.println(grafo);
        grafo.agregarArco(l1, l2, 2);
        System.out.println(grafo.gradoEntrada(l2));
        System.out.println(grafo.gradoSalida(l2));
        grafo.agregarVertice("d");
        System.out.println(grafo.esAdyacente(l1, "d"));
        grafo.agregarArco("d", l1, 20);
        grafo.eliminarVertice(l2);
        System.out.println(grafo);
        System.out.println(grafo.esAdyacente(l1, "d"));
        
    }
    
    
}
