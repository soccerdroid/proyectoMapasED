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
    String nombre,apellidos,matricula;
    LinkedList<Curso> lCursos;

    public Estudiante(String nombre, String apellidos, String matricula) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.matricula = matricula;
        this.lCursos=new LinkedList<>();
    }
    

    public String getNombreCompleto() {
        return nombre;
    }

    public void setNombreCompleto(String nombre) {
        this.nombre = nombre;
    }

    public String getCarrera() {
        return apellidos;
    }

    public void setCarrera(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    

    public LinkedList<Curso> getlCursos() {
        return lCursos;
    }

    public void setlCursos(LinkedList<Curso> lCursos) {
        this.lCursos = lCursos;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.nombre);
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
        return "Nombre: "+ this.nombre+" , "+"< "+this.matricula+" , "+this.apellidos+" >";
    }
}
