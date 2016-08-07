            /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.TDAs;

import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author pavilion
 */
public class Estudiante {
    String nombreCompleto,carrera,matricula;
    LinkedList<Asignatura> lAsignaturas;

    public Estudiante(String nombreCompleto, String carrera, String matricula) {
        this.nombreCompleto = nombreCompleto;
        this.carrera = carrera;
        this.matricula = matricula;
        this.lAsignaturas=new LinkedList<>();
    }
    

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LinkedList<Asignatura> getlAsignaturas() {
        return lAsignaturas;
    }

    public void setlAsignaturas(LinkedList<Asignatura> lAsignaturas) {
        this.lAsignaturas = lAsignaturas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.nombreCompleto);
        return hash;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Estudiante){
            Estudiante e=(Estudiante)o;
            if(this.matricula.equals(e.getMatricula()))
                return true;
            return false;
        }
        return false;
    }

    public String toString(){
        return "Nombre: "+ this.nombreCompleto+" , "+"< "+this.matricula+" , "+this.carrera+" >";
    }
}
