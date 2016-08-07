/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.TDAs;

/**
 *
 * @author Administrador
 */
public class SetVB {

    private boolean[] vectors;
    private final int max = 100;

    public SetVB() {
        this.vectors = new boolean[this.max];
        for (int i = 0; i < this.max; i++) {
            vectors[i] = false;
        }
    }

    public boolean add(int n) {
        if (n > 0 && n < this.max && this.vectors[n] == false) {
            this.vectors[n] = true;
            return true;
        }
        return false;
    }
    public boolean remove(Integer x){
        if(!this.isEmpty()){
            int i=0;
            while(i<this.max){
                if(this.vectors[i]){
                    this.vectors[i]=false;
                    return true;
                }
                i++;
            }
        
        }
        return false;
    
    }
    public SetVB union(SetVB conjuntoA) {
        SetVB union = new SetVB();
        if (conjuntoA != null && !conjuntoA.isEmpty()) {
            for (int i = 0; i < conjuntoA.max; i++) {
                if (conjuntoA.getVectors()[i] || this.vectors[i]) {
                    union.getVectors()[i] = true;
                }
            }
            return union;
        } else {
            for (int i = 0; i < this.max; i++) {
                if (this.vectors[i]) {
                    union.getVectors()[i] = true;
                }
            }
            return union;
        }

    }
    
    public boolean isEmpty(){
        boolean isEmpty=true;
        for(int i=0;i<this.max;i++){
            if (this.vectors[i])
                isEmpty=false;
        }
        return isEmpty;
    }
    public SetVB interseccion(SetVB conjuntoA) {
        SetVB interseccion = new SetVB();
        if (conjuntoA != null && !conjuntoA.isEmpty()) {
            for(int i=0;i<this.max;i++){
                if(this.vectors[i]  && conjuntoA.getVectors()[i])
                    interseccion.getVectors()[i]=true;
            }
        }
        else{
            for (int i = 0; i < this.max; i++) {
                if (this.vectors[i]) {
                    interseccion.getVectors()[i] = true;
                }
            }
            
        }
        return interseccion;
    }
    
    public SetVB diferencia(SetVB conjuntoA){
        SetVB diferencia= new SetVB();
        if(conjuntoA!=null && !conjuntoA.isEmpty()){
            for(int i=0;i<this.max;i++){
                if(this.vectors[i] && conjuntoA.getVectors()[i]==false)
                    diferencia.getVectors()[i]=true;
            }
        }
        else{
            for (int i = 0; i < this.max; i++) {
                if (this.vectors[i]) {
                    diferencia.getVectors()[i] = true;
                }
            }
        
        }
        return diferencia;
    }
    public boolean search(Integer x){
        boolean esta=false;
        if(!this.isEmpty()){
        int i=0;
        while(i<this.max &&!esta ){
            if(this.vectors[i]){
                esta=true;
                return esta;
            }
            i++;
            
        }
        return esta;
        }
        return esta;
        
    }
    
    
    public boolean[] getVectors() {
        return vectors;
    }

    public void setVectors(boolean[] vectors) {
        this.vectors = vectors;
    }
    
    public String toString(){
        
        String cadena="[ ";
        for(int i=0;i<this.max;i++){
            if(this.vectors[i])
                cadena+=i+" ";
        }
        cadena+="]";
        return cadena;
   }
    
    
}
