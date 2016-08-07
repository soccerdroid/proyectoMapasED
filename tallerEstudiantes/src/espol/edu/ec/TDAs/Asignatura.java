/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.TDAs;

import java.util.Objects;

/**
 *
 * @author pavilion
 */
public class Asignatura {
    String nombre;
    Double promedioFinal;

    public Asignatura(String nombre, Double promedioFinal) {
        this.nombre = nombre;
        this.promedioFinal = promedioFinal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPromedioFinal() {
        return promedioFinal;
    }

    public void setPromedioFinal(Double promedioFinal) {
        this.promedioFinal = promedioFinal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.nombre);
        return hash;
    }
    @Override
    public boolean equals(Object o){
        if(o instanceof Asignatura){
            Asignatura a=(Asignatura)o;
            if(this.nombre.equals(a.getNombre()))
                return true;
            return false;
        }
        return false;
    }
}
