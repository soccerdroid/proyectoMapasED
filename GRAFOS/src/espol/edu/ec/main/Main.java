/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.main;
import java.util.*;
import espol.edu.ec.TDAs.*;
/**
 *
 * @author CltControl
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       /* GrafoMA grafo=new GrafoMA(10);
        System.out.println("********** GRAFOS **********\n");
        System.out.println("Agregando vertices y arcos: ");
        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");
        grafo.agregarVertice("D");
        grafo.agregarVertice("T");
        grafo.agregarVertice("R");
        grafo.agregarVertice("H");
        grafo.agregarArco("D","B", 1);
        grafo.agregarArco("D","C", 2);
        grafo.agregarArco("C","R", 3);
        grafo.agregarArco("B","H", 4);
        grafo.agregarArco("R","H", 5);
        grafo.agregarArco("H","D", 6);
        grafo.agregarArco("H","A", 7);
        grafo.agregarArco("H","T", 8);
        System.out.println(grafo);
        System.out.println("\n\nRecorrido en Anchura: ");
        LinkedList<String> l= grafo.recorridoAnchura("D");
        System.out.println(l);
        GrafoLA g= new GrafoLA();
        Vertice vv1=new Vertice(5);
        Vertice vv2=new Vertice(3);
        Vertice vv3=new Vertice(2);
        g.agregarVertice(vv1);
        g.agregarVertice(vv2);
        g.agregarVertice(vv3);
        g.agregarArco(vv1, vv2, 20);
        g.agregarArco(vv3, vv2, 40);
        g.agregarArco(vv1, vv3, 10);
        if(g.esAdyacente(vv1, vv2)){
            System.out.println("vv1 y vv2 son adyacentes");
            System.out.println(g.gradoEntrada(vv3));
            System.out.println(g.gradoEntrada(vv3));
            g.eliminarArco(vv1, vv3);
            
        }
        System.out.println(g);
        GrafoLA invertido= g.invertir();
        System.out.println(invertido);
        LinkedList<Vertice> prueba1= g.recorridoAnchura(vv1);
        for(Vertice x: prueba1){
        System.out.println(x);}
        
        g.agregarArco(vv1, vv3,5);
        LinkedList<Vertice> prueba2= g.recorridoProfundidad(vv1);
        for(Vertice x: prueba2)
        System.out.println(x);
*/
       LinkedList<Integer> l=new LinkedList <>();
       l.add(2);
       l.add(3);
       Collections.sort(l);
       for(Integer i:l)
            System.out.println(i);
        GrafoND grafo = new GrafoND();
       Vertice va = new Vertice("A");
       Vertice vb = new Vertice("B");
       Vertice vc = new Vertice("C");
       Vertice vd = new Vertice("D");
       Vertice ve = new Vertice("E");
       Vertice vf = new Vertice("F");
       grafo.agregarVertice(va);
       grafo.agregarVertice(vb);
       grafo.agregarVertice(vc);
       grafo.agregarVertice(vd);
       grafo.agregarVertice(ve);
       grafo.agregarVertice(vf);
       grafo.agregarArco(va,vb,24);
       grafo.agregarArco(va,vc,10);
       grafo.agregarArco(va,ve,9);
       grafo.agregarArco(va,vf,8);
       grafo.agregarArco(vb,vf,7);
       grafo.agregarArco(vb,vc,19);
       grafo.agregarArco(vb,vd,11);
       grafo.agregarArco(vc,ve,15);
       grafo.agregarArco(vc,vd,6);
       grafo.agregarArco(vf,ve,5);
       grafo.agregarArco(ve,vd,12);
       grafo.dijkstra(va);
       grafo.imprimirDijkstra();

    }
    
    
}
