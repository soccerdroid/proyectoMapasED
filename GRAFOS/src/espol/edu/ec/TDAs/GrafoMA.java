package espol.edu.ec.TDAs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GrafoMA {
    private Object[] vertices;
    private int[][] matriz;
    private int size;
    
    public GrafoMA(int n)
    { 
        vertices = new Object[n];
        matriz = new int [n][n];
        size = 0;
    }
    
    public boolean existeVertice(Object o)
    {
        if (o!=null)
        {
            for(int i=0; i<size; i++)
            {
                if(o.equals(vertices[i]))
                    return true;
            }
        }
        return false;
    }
    
    public boolean agregarVertice(Object o)
    {
        if (existeVertice(o) || o==null || size == vertices.length)
            return false;
        else{
            vertices[size]=o;
            size++;
            return true;
        }           
    }
    
    public boolean eliminarVertice(Object o)
    {
        if (!existeVertice(o) || o==null)
            return false;
        else
        {
            int x = indiceVertice(o);
            vertices[x] = vertices[size-1];
            vertices[size-1] = null;
            for(int j=0; j<size ; j++)
            {
                matriz[x][j]=matriz[size-1][j];
                matriz[j][x]=matriz[j][size-1];
                matriz[size-1][j]=0;
                matriz[j][size-1]=0;
            }
            size--;
            return true;
        }
    }
    
    public boolean agregarArco(Object origen, Object destino, int peso)
    {
        int i= this.indiceVertice(origen);
        int j= this.indiceVertice(destino);
        if (i==-1 || j==-1)
            return false;
        else
        {
            this.matriz[i][j]=peso;
            return true;
        }
    }
    
    public boolean eliminarArco(Object origen, Object destino)
    {
        int i= this.indiceVertice(origen);
        int j= this.indiceVertice(destino);
        if (i==-1 || j==-1)
            return false;
        else{
            matriz[i][j]=0;
            return true;
        }
    }
    
    public boolean esAdyacente(Object o1,Object o2)
    {
        if (!existeVertice(o1) || !existeVertice(o2))
            return false;
        else
        {
            int i= this.indiceVertice(o1);
            int j= this.indiceVertice(o2);
            return matriz[i][j] != 0;
        }
    }
    
    public int gradoEntrada(Object o)
    {
        if (!existeVertice(o))
            return -1;
        else
        {
            int cont=0;
            int j= this.indiceVertice(o);
            for(int i=0; i<size; i++)
            {
                if(matriz[i][j]!=0)
                   cont++;
            }
            return cont;
        }
    }
    
    public int gradoSalida(Object o)
    {
        if (!existeVertice(o) || o == null)
            return -1;
        else
        {
            int cont=0;
            int j= this.indiceVertice(o);
            for(int i=0; i<size; i++)
            {
                if(matriz[j][i]!=0)
                   cont++;                    
            }
            return cont;
        }
    }
    
    public int indiceVertice(Object o)
    {
        if (o!=null)
        {
            for(int i=0; i<size; i++)
            {
                if(o.equals(vertices[i]))
                    return i;                    
            }
        }
        return -1;
    }
    
    public LinkedList recorridoAnchura(Object o)
    {
        LinkedList L = new LinkedList();
        boolean visitados[] = new boolean[this.vertices.length];
        int x = indiceVertice(o);
        if(x != -1)
        {         
            Queue cola = new LinkedList<>();
            cola.offer(this.vertices[x]);
            visitados[x] = true;
            while(!cola.isEmpty())
            {
                Object obj = cola.poll();
                L.add(obj);
                x = indiceVertice(obj);
                for(int j=0 ; j<this.vertices.length ; j++)
                {
                    if(matriz[x][j]!=0 && visitados[j] == false)
                    {
                        visitados[j]=true;
                        cola.offer(vertices[j]);
                    }
                }
            }
        }
        return L;
    }
    public LinkedList recorridoProfundidad(Object o){
        LinkedList L = new LinkedList();
        boolean visitados[] = new boolean[this.vertices.length];
        int x = indiceVertice(o);
        if(x != -1)
        {         
            Stack<Object> pila = new Stack<>();
            pila.push(this.vertices[x]);
            visitados[x] = true;
            while(!pila.isEmpty())
            {
                Object obj = pila.pop();
                L.add(obj);
                x = indiceVertice(obj);
                for(int j=0 ; j<this.vertices.length ; j++)
                {
                    if(matriz[x][j]!=0 && visitados[j] == false)
                    {
                        visitados[j]=true;
                        pila.push(vertices[j]);
                    }
                }
            }
        }
        return L;
    }
    @Override
    public String toString()
    {
        String s = "";
        for(int i = 0;i<size;i++){
            for(int j=0; j<size; j++){
                if(matriz[i][j]!=0){
                    s = s + "(" + vertices[i] + "->" + vertices[j] + ")[" + matriz[i][j] + "]\n";
                }
            }
        }
        return s;
    }
}