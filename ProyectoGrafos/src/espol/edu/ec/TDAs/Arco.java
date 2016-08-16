/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.TDAs;

/**
 *
 * @author pavilion
 */
public class Arco {
    private Vertice origen,destino;
    private final int peso=1;
    private String pelicula;

    public Arco(Vertice origen, Vertice destino, String pelicula) {
        this.origen=origen;
        this.destino = destino;
        this.pelicula=pelicula;
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

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }
    
    
    public String toString(){
        return this.origen+"<- "+this.peso+" ->"+this.destino;
    }
    @Override
    public boolean equals(Object o)
    {
        Arco a = (Arco)o;
        return this.destino.getId().equals(a.destino.getId()) && this.origen.getId().equals(a.origen.getId()) && this.peso==a.peso;
    }
}
