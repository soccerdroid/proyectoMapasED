/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.TDAs;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author pavilion
 */
public class GrafoND {

    private LinkedList<Vertice> Lvertices;

    public GrafoND() {
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
                    Arco a = new Arco(origen, destino, peso);
                    if (!Larcos.contains(a)) {
                        Larcos.add(a);
                    }
                }
                if (destino.equals(v1)) {
                    Arco a2 = new Arco(destino, origen, peso);
                    if (!v1.getListaArcos().contains(a2)) {
                        v1.getListaArcos().add(a2);
                        return true;
                    }
                }
            }

        }
        return false;
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

                }
                if (v1.equals(destino)) {
                    LinkedList<Arco> Larcos = v1.getListaArcos();
                    ListIterator<Arco> itArco = Larcos.listIterator();
                    while (itArco.hasNext()) {
                        Arco a = itArco.next();
                        if (a.getVerticeDestino().equals(origen)) {
                            itArco.remove();
                            return true;
                        }
                    }

                }

            }
            return false;
        }
    }

    public void dijkstra(Vertice v) {
        for (Vertice vertice : this.Lvertices) {
            v.setPredecesor(null);
            v.setDistancia(Integer.MAX_VALUE);
            v.setVisitado(false);
        }
        PriorityQueue<Vertice> cola = new PriorityQueue<>((Vertice v1, Vertice v2) -> v1.getDistancia() - v2.getDistancia());
        cola.offer(v);
        v.setVisitado(true);
        v.setDistancia(0);

        while (!cola.isEmpty()) {
            Vertice analizado = cola.poll();
            for (Arco a : analizado.getListaArcos()) {
                if (!a.getVerticeDestino().estaVisitado()) {
                    int nuevoPeso = analizado.getDistancia() + a.getPeso();
                    if (nuevoPeso < a.getVerticeDestino().getDistancia()) {
                        a.getVerticeDestino().setDistancia(nuevoPeso);
                        a.getVerticeDestino().setPredecesor(analizado);
                        cola.offer(a.getVerticeDestino());
                    }
                }
            }
        }
        this.limpiarVertices();
    }

    public void imprimirDijkstra() {
        for (Vertice v : this.Lvertices) {
            if (v.getPredecesor() != null) {
                System.out.println(v.getPredecesor() + " -" + v.getDistancia() + "-> " + v.getContenido());
            } else {
                System.out.println("origen -> " + v.getContenido());
            }
        }
    }

    public int grado(Vertice v) {
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

    public boolean esFuertementeConexo() {
        if (this.Lvertices.size() > 1) {
            
            LinkedList<Vertice> recorrido = this.recorridoProfundidad(this.Lvertices.peek());
            System.out.println(recorrido);
            System.out.println(this.Lvertices.size());
            if (recorrido.size() == this.Lvertices.size()) {
                return true;
            }
            return false;
        } else if (this.Lvertices.size() == 1) {
            return true;
        }
        return false;
    }

    public LinkedList<LinkedList<Vertice>> componentesConexas() {
        LinkedList<LinkedList<Vertice>> resultado = new LinkedList<>();

        LinkedList<Vertice> componente = new LinkedList<>();

        if (this.Lvertices.size() > 1) {
            for (Vertice v : this.Lvertices) {
                componente = this.recorridoAnchura(v);

                if (!resultado.contains(componente)) {
                    resultado.add(componente);
                }

                componente.clear();

            }
        } else if (this.Lvertices.size() == 1) {
            LinkedList<Vertice> uno = new LinkedList<>();
            uno.add(this.Lvertices.peek());
            resultado.add(uno);
        }
        return resultado;
    }

    public GrafoND kruskal() {
        if (this.esFuertementeConexo()) {
            GrafoND kruskal = new GrafoND();
            PriorityQueue<Arco> cola = new PriorityQueue<>((Arco a1, Arco a2) -> a1.getPeso() - a2.getPeso());
            Vertice v;
            for (Vertice vertice : this.Lvertices) {
                LinkedList<Arco> arcos = vertice.getListaArcos();
                for (Arco a : arcos) {
                    
                    cola.offer(a); //agrego arcos a la cola
                }
                v = new Vertice(vertice.getContenido());
                kruskal.agregarVertice(v); //agrego todos los vertices de mi grafo original al nuevo
            }
           
            int contador = 0;
            int tamaño = this.Lvertices.size();
            while (contador != tamaño - 1) {
                Arco arco = cola.poll(); //escojo el arco con menor peso
                Vertice v2 = kruskal.buscarVertice(arco.getOrigen().getContenido());
                LinkedList<Vertice> componentesConexas = kruskal.recorridoProfundidad(v2); //si el vertice origen del arco no está visitado y el vertice destino no pertenece a una componente conexa del vertice origen
                if (!arco.getOrigen().estaVisitado() && !componentesConexas.contains(kruskal.buscarVertice(arco.getDestino()))) {
                    kruskal.agregarArco(arco.getOrigen(), arco.getDestino(), arco.getPeso()); //agrego arco al nuevo grafo
                    arco.getOrigen().setVisitado(true); //seteo visitado el origen del arco
                    contador+=1;
                }

            }

            return kruskal;
        }
        return null;
    }

    public LinkedList<Vertice> getLvertices() {
        return Lvertices;
    }

    public void setLvertices(LinkedList<Vertice> Lvertices) {
        this.Lvertices = Lvertices;
    }

    public GrafoND prim() {
        
        if (this.esFuertementeConexo()) {
            GrafoND resultado = new GrafoND();
            Vertice v;
            for (Vertice vertice : this.Lvertices) {
                v = new Vertice(vertice.getContenido());
                resultado.agregarVertice(v);
            }
            int contador = 1;
            
            PriorityQueue<Arco> cola = new PriorityQueue<>((Arco a1, Arco a2) -> a1.getPeso() - a2.getPeso());
            Vertice inicio = this.Lvertices.getFirst();
            inicio.setVisitado(true);
            LinkedList<Arco> arcos = new LinkedList<>();
            int tamaño = this.Lvertices.size();
            while (contador != tamaño) {
                inicio.setVisitado(true);
                arcos = inicio.getListaArcos();
                for (Arco a : arcos) {
                    if (!a.getDestino().estaVisitado()) {
                        cola.offer(a);
                    }
                    Arco arco1 = cola.poll();
                    
                    resultado.agregarArco(arco1.getOrigen(), arco1.getDestino(), arco1.getPeso());
                    inicio = arco1.getDestino();
                    contador += 1;
                }
            }
            return resultado;
        }
        return null;
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
                s = s +a.getOrigen().getContenido()+"<-"+ "(" + v.getContenido() + "->" + a.getVerticeDestino().getContenido() + ")[" + a.getPeso() + "]\n";
            }
        }
        return s;
    }
}
