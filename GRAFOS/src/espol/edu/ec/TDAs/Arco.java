/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.TDAs;

/**
 *
 * @author eduardo
 */
public class Arco {
    private Vertice origen,destino;
    private int peso;

    public Arco(Vertice origen, Vertice destino, int peso) {
        this.origen=origen;
        this.destino = destino;
        this.peso = peso;
    }

    public Vertice getOrigen() {
        return origen;
    }

    public void setOrigen(Vertice origen) {
        this.origen = origen;
    }

    public Vertice getDestino() {
        return destino;
    }

    public void setDestino(Vertice destino) {
        this.destino = destino;
    }
    
    public Vertice getVerticeDestino()
    {
        return destino;
    }
    
    public void setVerticeDestino(Vertice v)
    {
        destino = v;
    }
    
    public int getPeso()
    {
        return peso;
    }
    public String toString(){
        return this.origen+"<- "+this.peso+" ->"+this.destino;
    }
    @Override
    public boolean equals(Object o)
    {
        Arco a = (Arco)o;
        return this.destino.getContenido().equals(a.destino.getContenido()) && this.origen.getContenido().equals(a.origen.getContenido()) && this.peso==a.peso;
    }
}
