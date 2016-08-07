/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.TDAs;

import java.util.LinkedList;

/**
 *
 * @author pavilion
 */
public class Curso {
    public String materia;
    public int paralelo;
    public LinkedList<Estudiante> lEstudiantes;

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public int getParalelo() {
        return paralelo;
    }

    public void setParalelo(int paralelo) {
        this.paralelo = paralelo;
    }

    public LinkedList<Estudiante> getlEstudiantes() {
        return lEstudiantes;
    }

    public void setlEstudiantes(LinkedList<Estudiante> lEstudiantes) {
        this.lEstudiantes = lEstudiantes;
    }

    public Curso(String materia, int paralelo) {
        this.materia = materia;
        this.paralelo = paralelo;
        this.lEstudiantes=new LinkedList<>();
    }
    
}
