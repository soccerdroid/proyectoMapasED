/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.TDAs;
import java.util.*;
/**
 *
 * @author eduardo
 */
public class Vertice {
    private Object contenido;
    private LinkedList<Arco> Larcos;

    public LinkedList<Arco> getLarcos() {
        return Larcos;
    }

    public void setLarcos(LinkedList<Arco> Larcos) {
        this.Larcos = Larcos;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public Vertice getPredecesor() {
        return predecesor;
    }

    public void setPredecesor(Vertice predecesor) {
        this.predecesor = predecesor;
    }
    private boolean visitado;
    private int distancia;
    private Vertice predecesor;
    
    public Vertice(Object contenido) {
        this.contenido = contenido;
        this.Larcos = new LinkedList<>();
        visitado=false;
        distancia=Integer.MAX_VALUE;
        predecesor=null;
    }
    
    public LinkedList<Arco> getListaArcos()
    {
        return Larcos;
    }
    
    public Object getContenido()
    {
        return contenido;
    }
    
    public void setContenido(Object contenido)
    {
        this.contenido = contenido;
    }
    
    public boolean estaVisitado()
    {
        return visitado;
    }
    
    public void setVisitado(boolean estado)
    {
        visitado = estado;
    }
    
    @Override
    public boolean equals(Object o)
    {
        Vertice v = (Vertice)o;
        return this.contenido.equals(v.contenido);
    }
    
    @Override
    public int hashCode(){
        return 1;
    }
    @Override
    public String toString(){
        String s="";
    
        for(Arco a: this.Larcos){
            s+=this.contenido+"->";
            s+=a.getVerticeDestino().contenido;
            s+="\n";
            
        }
        return s;
    }
}
