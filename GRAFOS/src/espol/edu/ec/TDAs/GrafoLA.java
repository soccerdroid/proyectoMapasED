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
public class GrafoLA {

    private LinkedList<Vertice> Lvertices;

    public GrafoLA() {
        Lvertices = new LinkedList<>();
    }

    public boolean existeVertice(Vertice v) {
        return this.Lvertices.contains(v);
    }

    public boolean agregarVertice(Vertice v) {
        if (existeVertice(v)) {
            return false;
        } else {
            Lvertices.add(v);
            return true;
        }
    }

    public boolean eliminarVertice(Vertice v) {
        if (!existeVertice(v)) {
            return false;
        } else {
            ListIterator<Vertice> i = Lvertices.listIterator();
            while (i.hasNext()) {
                Vertice v1 = i.next();
                LinkedList<Arco> Larcos = v1.getListaArcos();
                ListIterator<Arco> j = Larcos.listIterator();
                while (j.hasNext()) {
                    Arco a = j.next();
                    if (a.getVerticeDestino().equals(v)) {
                        j.remove();
                    }
                }
            }
            Lvertices.remove(v);
            return true;
        }
    }

    public boolean agregarArco(Vertice origen, Vertice destino, int peso) {
        if (!this.existeVertice(origen) || !this.existeVertice(destino)) {
            return false;
        } else {
            ListIterator<Vertice> i = Lvertices.listIterator();
            while (i.hasNext()) {
                Vertice v1 = i.next();
                if (origen.equals(v1)) {
                    LinkedList<Arco> Larcos = v1.getListaArcos();
                    Arco a = new Arco(origen,destino, peso);
                    if (!Larcos.contains(a)) {
                        Larcos.add(a);
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public boolean eliminarArco(Vertice origen, Vertice destino) {
        if (!this.existeVertice(origen) || !this.existeVertice(destino)) {
            return false;
        } else {
            ListIterator<Vertice> i = Lvertices.listIterator();
            while (i.hasNext()) {
                Vertice v1 = i.next();
                if (origen.equals(v1)) {
                    LinkedList<Arco> Larcos = v1.getListaArcos();
                    ListIterator<Arco> itArco = Larcos.listIterator();
                    while (itArco.hasNext()) {
                        Arco a = itArco.next();
                        if (a.getVerticeDestino().equals(destino)) {
                            itArco.remove();
                        }
                    }
                    return true;
                }

            }
            return true;
        }
    }

    public int gradoEntrada(Vertice v) {
        int grado = 0;
        if (!this.existeVertice(v)) {
            return -1;
        } else {
            ListIterator<Vertice> i = Lvertices.listIterator();
            while (i.hasNext()) {
                Vertice v1 = i.next();
                if (!v.equals(v1)) {
                    LinkedList<Arco> Larcos = v1.getListaArcos();
                    ListIterator<Arco> itArco = Larcos.listIterator();
                    while (itArco.hasNext()) {
                        Arco a = itArco.next();
                        if (a.getVerticeDestino().equals(v)) {
                            grado += 1;
                        }
                    }

                }
            }

        }
        return grado;
    }

    public int gradoSalida(Vertice v) {
        if (this.existeVertice(v)) {
            return v.getListaArcos().size();
        } else {
            return -1;
        }
    }

    public boolean esAdyacente(Vertice v1, Vertice v2) {
        if (!this.existeVertice(v1) || !this.existeVertice(v2)) {
            return false;
        } else {
            for (Vertice v : this.Lvertices) {
                if (v.equals(v1)) {
                    LinkedList<Arco> Larcos = v.getListaArcos();
                    for (Arco a : Larcos) {
                        if (a.getVerticeDestino().equals(v2)) {
                            return true;
                        }
                    }
                }
                if (v.equals(v2)) {
                    LinkedList<Arco> Larcos = v.getListaArcos();
                    for (Arco a : Larcos) {
                        if (a.getVerticeDestino().equals(v1)) {
                            return true;
                        }
                    }
                }

            }
            return false;

        }

    }

    public LinkedList<Vertice> recorridoProfundidad(Vertice v) {
        LinkedList<Vertice> L = new LinkedList<>();
        Stack<Vertice> pila = new Stack<>();
        pila.push(v);
        v.setVisitado(true);
        while (!pila.isEmpty()) {
            Vertice pivote = pila.pop();
            L.add(pivote);
            for (Arco a : pivote.getListaArcos()) {
                if (a.getVerticeDestino().getListaArcos().size() > 0 && !a.getVerticeDestino().estaVisitado()) {
                    a.getVerticeDestino().setVisitado(true);
                    pila.push(a.getVerticeDestino());
                }

            }
        }
        this.limpiarVertices();
        return L;

    }

    public LinkedList<Vertice> getLvertices() {
        return Lvertices;
    }

    public void setLvertices(LinkedList<Vertice> Lvertices) {
        this.Lvertices = Lvertices;
    }

    public void limpiarVertices() {
        for (Vertice v : this.Lvertices) {
            v.setVisitado(false);
        }
    }

    public LinkedList<Vertice> recorridoAnchura(Vertice v) {
        LinkedList<Vertice> L = new LinkedList<>();
        Queue<Vertice> cola = new LinkedList<>();
        cola.offer(v);
        v.setVisitado(true);
        while (!cola.isEmpty()) {
            Vertice pivote = cola.poll();
            L.add(pivote);
            for (Arco a : pivote.getListaArcos()) {
                if (a.getVerticeDestino().getListaArcos().size() > 0 && !a.getVerticeDestino().estaVisitado()) {
                    a.getVerticeDestino().setVisitado(true);
                    cola.offer(a.getVerticeDestino());
                }

            }
        }
        this.limpiarVertices();
        return L;
    }

    //función que me devuelve el vértice de mi grafo recibiendo un contenido de otro vértice
    public Vertice buscarVertice(Object contenido) {
        for (Vertice v : this.Lvertices) {
            if (v.getContenido().equals(contenido)) {
                return v;
            }
        }
        return null;

    }

    public GrafoLA invertir() {
        GrafoLA resultado = new GrafoLA();
        //agrego los vertices de mi actual grafo al nuevo grafo, pero como nuevas instancias de vertices para no mantener referencias
        for(Vertice v : this.Lvertices) {

            Vertice n = new Vertice(v.getContenido());
            resultado.agregarVertice(n);
        }
        //recorro nuevamente los vertices
        for (Vertice v : this.Lvertices) {
            ListIterator<Arco> j = v.getListaArcos().listIterator();
            while (j.hasNext()) {
                Arco a = j.next();
                Vertice vo = resultado.buscarVertice(a.getVerticeDestino().getContenido()); //busco en mi nuevo grafo el vértice que tenga igual contenido que el vértice destino del arco
                Vertice vd = resultado.buscarVertice(v.getContenido());
                resultado.agregarArco(vo, vd, a.getPeso()); //creo los arcos en mi nuevo grafo, con los orígenes y destinos invertidos
            }
        }
        return resultado;

    }
    public boolean esFuertementeConexo(){
        if(this.Lvertices.size()>1){
        GrafoLA invertido= this.invertir();
        LinkedList<Vertice> recorrido= this.recorridoProfundidad(this.Lvertices.peek());
        LinkedList<Vertice> recorridoInvertido= this.recorridoProfundidad(invertido.Lvertices.peek());
        if(recorrido.size()==recorridoInvertido.size())
            return true;
        return false;
        }
        else if(this.Lvertices.size()==1)
            return true;
        return false;
    }
    
    public LinkedList<LinkedList<Vertice>> componentesConexas(){
        LinkedList<LinkedList<Vertice>> resultado= new LinkedList<>();
        LinkedList<Vertice> interseccion= new LinkedList<>();
        LinkedList<Vertice> componente= new LinkedList<>();
        LinkedList<Vertice> componenteInvertida= new LinkedList<>();
        if(this.Lvertices.size()>1){
            GrafoLA invertido= this.invertir();
            for(Vertice v: this.Lvertices){
                componente=this.recorridoAnchura(v);
                componenteInvertida=invertido.recorridoAnchura(v);
                for(Vertice vv: componente){
                    if(componenteInvertida.contains(vv))
                        interseccion.add(vv);
                }
                resultado.add(interseccion);
                interseccion.clear();
                componente.clear();
                componenteInvertida.clear();
                
            }
        }
        else if (this.Lvertices.size()==1){
            LinkedList<Vertice> uno= new LinkedList<>();
            uno.add(this.Lvertices.peek());
            resultado.add(uno); 
        }
        return resultado;
    }
    @Override
    public String toString() {
        String s = "";
        ListIterator<Vertice> i = Lvertices.listIterator();
        while (i.hasNext()) {
            Vertice v = i.next();
            ListIterator<Arco> j = v.getListaArcos().listIterator();
            while (j.hasNext()) {
                Arco a = j.next();
                s = s + "(" + v.getContenido() + "->" + a.getVerticeDestino().getContenido() + ")[" + a.getPeso() + "]\n";
            }
        }
        return s;
    }

}
