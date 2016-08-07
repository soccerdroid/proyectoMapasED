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
    private Vertice destino;
    private int peso;

    public Arco(Vertice destino, int peso) {
        this.destino = destino;
        this.peso = peso;
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
    
    @Override
    public boolean equals(Object o)
    {
        Arco a = (Arco)o;
        return this.destino.getContenido().equals(a.destino.getContenido());
    }
}
