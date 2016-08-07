/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.TDAs;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author Administrador
 */
public class SetLO {

    private LinkedList<Integer> l;

    public SetLO() {
        l = new LinkedList<>();
    }

    public boolean isEmpty() {
        return this.l.isEmpty();
    }

    public boolean remove(Integer x) {
        if (!this.isEmpty()) {
            ListIterator<Integer> i = this.l.listIterator();
            while (i.hasNext()) {
                if (i.next().equals(x)) {
                    i.remove();
                    return true;
                }

            }
            return false;
        }
        return false;
    }

    public boolean search(Integer x) {
        if (this.isEmpty()) {
            return false;
        } else {
            ListIterator<Integer> i = this.l.listIterator();
            while (i.hasNext()) {
                if (i.next().equals(x)) {
                    return true;
                }
            }
            return false;
        }
    }
    
    
    public SetLO interseccion(SetLO conjuntoA){
        SetLO interseccion = new SetLO();
        if(!conjuntoA.isEmpty() && !this.isEmpty()){
            int max=0;
            if(this.l.size()<=conjuntoA.getL().size())
                max=this.l.size();
            else 
                max=conjuntoA.getL().size();
            for(int i=0;i<max;i++){
                if(this.l.contains(conjuntoA.getL().get(i)))
                    interseccion.add(conjuntoA.getL().get(i));
            }                 
        }
        
        
        
        return interseccion;
    }
    
    public SetLO union(SetLO conjuntoA){
    SetLO union = new SetLO();
        if(!conjuntoA.isEmpty() && !this.isEmpty()){
            int max=0;
            int tamano1=this.l.size();
            int tamano2=conjuntoA.getL().size();
            if(tamano1>=tamano2)
                max=tamano1;
            else 
                max=tamano2;
            for(int i=0;i<max;i++){
                union.add(this.l.get(i));
                if(i<tamano2)
                    union.add(conjuntoA.getL().get(i));
            }                 
        }
        else if(!this.isEmpty()){
            ListIterator<Integer> i = this.l.listIterator();
            while (i.hasNext()) {
                union.add(i.next());
            }
        
        }
        return union;
    }
    
    public SetLO diferencia(SetLO conjuntoA){
        SetLO diferencia = new SetLO();
        if(!conjuntoA.isEmpty() && !this.isEmpty()){
            
            int tamano1=this.l.size();
            
            for(int i=0;i<tamano1;i++){
                if(!conjuntoA.getL().contains(this.l.get(i)))
                    diferencia.add(this.l.get(i));
                
            }                 
        }
        else if(!this.isEmpty()){
            ListIterator<Integer> i = this.l.listIterator();
            while (i.hasNext()) {
                diferencia.add(i.next());
            }
        
        }
        return diferencia;
    
    }
    
    public boolean add(Integer x) {
        if (!l.contains(x)) {
            this.insertarEnOrden(x);
            return true;
        } else {
            return false;
        }
    }

    public LinkedList<Integer> getL() {
        return l;
    }

    public void setL(LinkedList<Integer> l) {
        this.l = l;
    }

    public boolean insertarEnOrden(Integer x) {
        if (l.isEmpty()) {
            this.l.add(x);
            return true;

        } else if (this.l.getFirst().compareTo(x) > 0) {
            this.l.addFirst(x);
        } else if (this.l.getLast().compareTo(x) < 0) {
            this.l.addLast(x);
        } else {
            ListIterator<Integer> i = this.l.listIterator();
            while (i.hasNext()) {
                Integer n = i.next();
                if (n.compareTo(x) > 0 ) {
                    
                    this.l.add(i.previousIndex(),x);
                    break;
                }
            }

        }
        return true;
    }

    public String toString() {
        ListIterator<Integer> i = this.l.listIterator();
        String cadena = "[ ";
        while (i.hasNext()) {
            cadena += i.next() + " ";
        }
        cadena += "]";
        return cadena;
    }
}
